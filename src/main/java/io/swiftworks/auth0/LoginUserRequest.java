package io.swiftworks.auth0;

public class LoginUserRequest {
    private String username;
    private String password;
    private String scope;
    private String ipAddress;

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

    public String getIpAddress() {
        return ipAddress;
    }

    public LoginUserRequest setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;

        return this;
    }
}
