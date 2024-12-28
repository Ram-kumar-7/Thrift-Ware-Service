package com.ramTech.ThriftWare.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userDetails")
public class User {
    String access;
    String mailId;
    String password;
    String lastName;
    String firstName;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getMailId() {
        return mailId;
    }

    public User() {
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
