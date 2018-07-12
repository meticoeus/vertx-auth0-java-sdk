/**
 * OpenTok Java SDK
 * Copyright (C) 2018 TokBox, Inc.
 * http://www.tokbox.com
 *
 * Licensed under The MIT License (MIT). See LICENSE file for more information.
 */
package io.swiftworks.auth0;

import io.vertx.core.json.JsonObject;

/**
 * Defines an exception object thrown when an API call to the Auth0 server fails.
 */
public class RequestException extends Exception {

    private static final long serialVersionUID = -3852834447530956514L;

    private int statusCode = 500;
    private JsonObject asJson;

    /**
     * Constructor.
     */
    public RequestException(String message) {
        super(message);
    }

    /**
     * Constructor.
     */
    public RequestException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Constructor.
     */
    public RequestException(JsonObject message, int statusCode) {
        super(message.toString());
        this.statusCode = statusCode;
        this.asJson = message;
    }

    /**
     * Constructor.
     */
    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     */
    public RequestException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JsonObject getAsJson() {
        return asJson;
    }
}
