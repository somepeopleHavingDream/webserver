package org.yangxin.webserver.connector;

/**
 * @author yangxin
 * 2020/10/02 14:20
 */
public enum HttpStatus {

    SC_OK(200, "OK"),
    SC_NOT_FOUND(404, "File Not Found");

    private final int statusCode;
    private final String reason;

    HttpStatus(int code, String reason) {
        this.statusCode = code;
        this.reason = reason;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReason() {
        return reason;
    }
}
