package com.infinite.util;

public enum PasswordEnum {

    REGEX("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*()_+|{}:\"<>?])[A-Za-z\\d~!@#$%^&*()_+|{}:\"<>?]{8,}");

    private String message;

    PasswordEnum(String message){
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
