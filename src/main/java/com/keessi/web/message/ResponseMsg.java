package com.keessi.web.message;

public class ResponseMsg {
    public enum Status {
        SUCCESS, FAILED;
    }

    private Status status;
    private Object content;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
