package com.yoursidea.webiment;

public class Requests {
    private String dateTime,email,name,service,status;

    public Requests() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Requests(String dateTime, String email, String name, String service, String status) {
        this.dateTime = dateTime;
        this.email = email;
        this.name = name;
        this.service = service;
        this.status = status;
    }
}
