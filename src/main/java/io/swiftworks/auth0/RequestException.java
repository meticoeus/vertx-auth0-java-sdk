/**
 * OpenTok Java SDK
 * Copyright (C) 2018 TokBox, Inc.
 * http://www.tokbox.com
 *
 * Licensed under The MIT License (MIT). See LICENSE file for more information.
 */
package io.swiftworks.auth0;

/**
 * Defines an exception object thrown when an API call to the Auth0 server fails.
 */
public class RequestException extends Exception {

    private static final long serialVersionUID = -3852834447530956514L;

    /**
     * Constructor.
     */
    public RequestException(String message) {
        super(message);
    }

    /**
     * Constructor.
     */
    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
