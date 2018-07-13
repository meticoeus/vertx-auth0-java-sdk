package io.swiftworks.auth0;

import java.util.Date;

public class ManagementApiCredentials {
    public static Long BUFFER = 10L * 60L * 1000L; // 10 Minutes

    private String accessToken;
    private Long expiresAt;

    public ManagementApiCredentials() {

    }

    public ManagementApiCredentials(String accessToken, Long expiresAt) {
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
    }

    public boolean isValid() {
        if (accessToken == null || expiresAt == null) {
            return false;
        } else {
            return expiresAt > new Date().getTime() - BUFFER;
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }
}
