package io.swiftworks.auth0;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonObject;

@SuppressWarnings("WeakerAccess")
public class Auth0HttpClient {
    private static final String AUTH0_FORWARDED_FOR = "auth0-forwarded-for";
    private static final String AUTHORIZATION = "Authorization";

    protected final String audience;
    protected final String clientId;
    protected final String clientSecret;
    protected final String apiUrl;
    protected final Vertx vertx;
    protected HttpClient httpClient;

    public Auth0HttpClient(String audience, String clientId, String clientSecret, String apiUrl, Vertx vertx) {
        this.audience = audience;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.apiUrl = apiUrl;
        this.vertx = vertx;
        this.httpClient = vertx.createHttpClient(new HttpClientOptions()
                .setSsl(true)
        );
    }

    public Auth0HttpClient(String audience, String clientId, String clientSecret, String apiUrl, Vertx vertx, HttpClientOptions httpClientOptions) {
        this.audience = audience;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.apiUrl = apiUrl;
        this.vertx = vertx;
        if (httpClientOptions == null) {
            this.httpClient = vertx.createHttpClient(new HttpClientOptions()
                    .setSsl(true)
            );
        } else {
            this.httpClient = vertx.createHttpClient(httpClientOptions);
        }
    }

    public void loginUser(LoginUserRequest loginUserRequest, Handler<AsyncResult<JsonObject>> handler) {
        try {
            JsonObject requestBody = new JsonObject();
            requestBody.put("grant_type", "password");
            requestBody.put("username", loginUserRequest.getUsername());
            requestBody.put("password", loginUserRequest.getPassword());
            requestBody.put("audience", audience);
            requestBody.put("scope", loginUserRequest.getScope());
            requestBody.put("client_id", clientId);
            requestBody.put("client_secret", clientSecret);

            String url = this.apiUrl + "/oauth/token";

            HttpClientRequest request = this.httpClient.postAbs(url, buildResponseHandler(handler,"Failed to log in user."));

            request.exceptionHandler(buildExceptionHandler(handler));

            request.putHeader(AUTH0_FORWARDED_FOR, loginUserRequest.getIpAddress())
                    .putHeader("content-type", "application/json")
                    .end(requestBody.toString());
        } catch (Throwable t) {
            handler.handle(Future.failedFuture(new RequestException("Failed to send request", t)));
        }
    }

    public void getProfile(String accessToken, Handler<AsyncResult<JsonObject>> handler) {
        try {
            String url = this.apiUrl + "/userinfo";

            HttpClientRequest request = this.httpClient.getAbs(url, buildResponseHandler(handler,"Failed to retrieve user profile."));

            request.exceptionHandler(buildExceptionHandler(handler));

            request.putHeader(AUTHORIZATION, "Bearer " + accessToken)
                    .end();
        } catch (Throwable t) {
            handler.handle(Future.failedFuture(new RequestException("Failed to send request", t)));
        }
    }

    private Handler<HttpClientResponse> buildResponseHandler(Handler<AsyncResult<JsonObject>> handler, String errorMessage) {
        return response -> {
            try {
                response.exceptionHandler(t ->
                        handler.handle(Future.failedFuture(new RequestException(errorMessage, t)))
                );

                response.bodyHandler(buildBodyHandler(handler, response));
            } catch (Throwable t) {
                handler.handle(Future.failedFuture(new RequestException(errorMessage + " Error binding request handlers.", t)));
            }
        };
    }

    private Handler<Buffer> buildBodyHandler(Handler<AsyncResult<JsonObject>> handler, HttpClientResponse response) {
        return buffer -> {
            try {
                JsonObject result;
                if (buffer.length() > 0) {
                    result = JsonUtils.toCamelCase(new JsonObject(buffer.toString()));
                } else {
                    result = new JsonObject();
                }
                result.put("statusCode", response.statusCode());

                if (response.statusCode() >= 200 && response.statusCode() < 400) {
                    handler.handle(Future.succeededFuture(result));
                } else {
                    handler.handle(Future.failedFuture(new RequestException(result, response.statusCode())));
                }

            } catch (Throwable t) {
                handler.handle(Future.failedFuture(new RequestException("Failed to parse response. Received: '" + (buffer == null ? "null" : buffer.toString()) + "'", t)));
            }
        };
    }

    private Handler<Throwable> buildExceptionHandler(Handler<AsyncResult<JsonObject>> handler) {
        return t -> handler.handle(Future.failedFuture(new RequestException("Failed to connect to server", t)));
    }
}
