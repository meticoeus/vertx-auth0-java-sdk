package io.swiftworks.auth0;

import io.vertx.core.json.JsonObject;

public class CreateUserRequest {
    private String userId;
    private String email;
    private String username;
    private String password;
    private String connection;
    private String phoneNumber;
    private JsonObject userMetadata;
    private Boolean emailVerified;
    private Boolean verifyEmail;
    private Boolean phoneVerified;
    private JsonObject appMetadata;

    public CreateUserRequest() {
    }

    public boolean isValid() {
        return this.email != null && this.password != null;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConnection() {
        return connection;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public JsonObject getUserMetadata() {
        return userMetadata;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public Boolean getVerifyEmail() {
        return verifyEmail;
    }

    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    public JsonObject getAppMetadata() {
        return appMetadata;
    }

    public CreateUserRequest setUserId(String userId) {
        this.userId = userId;

        return this;
    }

    public CreateUserRequest setEmail(String email) {
        this.email = email;

        return this;
    }

    public CreateUserRequest setUsername(String username) {
        this.username = username;

        return this;
    }

    public CreateUserRequest setPassword(String password) {
        this.password = password;

        return this;
    }

    public CreateUserRequest setConnection(String connection) {
        this.connection = connection;

        return this;
    }

    public CreateUserRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

        return this;
    }

    public CreateUserRequest setUserMetadata(JsonObject userMetadata) {
        this.userMetadata = userMetadata;

        return this;
    }

    public CreateUserRequest setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;

        return this;
    }

    public CreateUserRequest setVerifyEmail(Boolean verifyEmail) {
        this.verifyEmail = verifyEmail;

        return this;
    }

    public CreateUserRequest setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;

        return this;
    }

    public CreateUserRequest setAppMetadata(JsonObject appMetadata) {
        this.appMetadata = appMetadata;

        return this;
    }
}
