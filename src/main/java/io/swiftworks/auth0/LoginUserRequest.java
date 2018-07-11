package io.swiftworks.auth0;

public class LoginUserRequest {
    private String username;
    private String password;
    private String scope;
    private String host;

    public LoginUserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public LoginUserRequest setUsername(String username) {
        this.username = username;

        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserRequest setPassword(String password) {
        this.password = password;

        return this;
    }

    public String getScope() {
        return scope;
    }

    public LoginUserRequest setScope(String scope) {
        this.scope = scope;

        return this;
    }

    public String getHost() {
        return host;
    }

    public LoginUserRequest setHost(String host) {
        this.host = host;

        return this;
    }
}
