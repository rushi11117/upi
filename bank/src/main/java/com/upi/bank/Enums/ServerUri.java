package com.upi.bank.Enums;


/**
 * The enum Server uri.
 */
public enum ServerUri {
    /**
     * Upi server server uri.
     */
    UPI_SERVER("htto://localhost:8081");

    private final String serviceUrl;

    ServerUri(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    /**
     * Gets serviceurl.
     *
     * @return the serviceurl
     */
    public String getServiceurl() {
        return serviceUrl;
    }
}
