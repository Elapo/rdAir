package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.User;
import com.realdolmen.rdAir.repositories.UserRepository;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class RegistrationBean {
    @Inject
    UserRepository ur;

    private Customer user;

    @Inject
    private LoginBean login;

    @NotNull(message="{Register.fname.null}")
    @Size(min=1, max=50, message="{Register.fname.size}")
    private String fName;

    @NotNull(message="{Register.lname.null'}")
    @Size(min=1, max=50, message="{Register.lname.size}")
    private String lName;

    @NotNull(message="{Register.address.null}")
    @Size(min=1, max=100, message="{Register.address.size}")
    private String address;

    @NotNull(message="{Register.phnumber.null}")
    @Size(min=5, max=10, message="{Register.phnumber.size}")
    @Pattern(regexp = "[0-9]{5,10}",message = "{Register.phnumber.pattern}")
    private String telephone;

    @NotEmpty(message="{Register.email.empty}")
    @Email(message="{Register.email.email}")
    private String email;

    @NotEmpty(message="{Register.password.null}")
    @Pattern(regexp = "[a-zA-Z0-9]{8,30}",message = "{Register.password.pattern}")
    private String password;

    private String passwordHash;

    private boolean registered;

    public String doRegistration(){
        System.err.println("Succesfully registered customer: " + lName);
        passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        user = new Customer(fName, lName, address, telephone, email, passwordHash, null);
        User savedUser = ur.save(user);
        System.err.println("Id of persisted user is: " + savedUser.getId());
        login.setUser(savedUser);
        login.setLoggedIn(true);
        registered=true;
        System.err.println("Registered boolean value: " + registered);
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

    public void setRegistered(boolean registered) {this.registered = registered; }

    public boolean getRegistered() {return registered;}
}
