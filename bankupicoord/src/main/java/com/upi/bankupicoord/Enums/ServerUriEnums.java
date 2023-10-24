package com.upi.bankupicoord.Enums;

/**
 * The enum Server uri.
 */
public enum ServerUriEnums {

    /**
     * Upi server uri server uri.
     */
    UPI_SERVER_URI("http://localhost:8081"),

    /**
     * Bank server uri server uri.m
     */
    BANK_SERVER_URI("http://localhost:8082");

    private final String serviceUri;

    ServerUriEnums(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    /**
     * Gets service uri.
     *
     * @return the service uri
     */
    public String getServiceUri() {
        return serviceUri;
    }
}
