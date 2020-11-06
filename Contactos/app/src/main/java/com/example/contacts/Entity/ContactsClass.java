package com.example.contacts.Entity;

import java.io.Serializable;

public class ContactsClass implements Serializable {

    // SOLO CREAR LOS ATRIBUTOS QUE NECESITAS
    String fullName, phone, email, imageUrl;

    public ContactsClass() {
    }

    public ContactsClass(String fullName, String phone, String email, String imageUrl) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
