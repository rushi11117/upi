package com.upi.transactionmanager.Enums;

/**
 * The enum Server uri enums.
 */
public enum ServerURIEnums {


    /**
     * Upi server uri server uri enums.
     */
    UPI_SERVER_URI("http://localhost:8081"),

    /**
     * Bank server uri server uri enums.
     */
    BANK_SERVER_URI("http://localhost:8082");
    private final String serverUri;


    ServerURIEnums(String serverUri) {
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
