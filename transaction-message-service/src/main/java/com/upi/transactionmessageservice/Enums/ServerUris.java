/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.transactionmessageservice.Enums;

/**
 * The enum Server uris.
 */
public enum ServerUris {
    /**
     * Bank server server uris.
     */
    BANK_SERVER("http://localhost:8082/");

    private String serverUri;

    ServerUris(String serverUri) {
        this.serverUri = serverUri;
    }

    /**
     * Gets server uri.
     *
     * @return the server uri
     */
    public String getServerUri() {
        return serverUri;
    }
}
