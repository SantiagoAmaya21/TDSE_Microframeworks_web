package edu.eci.tdse.framework;

public class Response {

    private int status = 200;
    private String contentType = "text/plain";

    public void status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void type(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}