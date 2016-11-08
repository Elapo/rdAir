package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ViewScoped
public class RegistrationBean {
    @Inject
    UserRepository ur;

    @Inject
    private LoginBean login;

    private Customer user;

    private String fName;

    private String lName;

    private String address;

    private String telephone;

    private String email;

    private String password;

    private String passwordHash;

    public String doRegistration(){
        passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        user = new Customer(fName, lName, address, telephone, email, passwordHash, null);
        ur.save(user);
        login.setUser(user);
        login.setLoggedIn(true);
        return "pretty:view-login";
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
