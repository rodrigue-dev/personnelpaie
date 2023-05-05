package com.example.gpaie.Model;

public class EmailModel {
    private String message;
    private String subject;
    private Long[] items;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long[] getItems() {
        return this.items;
    }

    public void setItems(Long[] items) {
        this.items = items;
    }

}
