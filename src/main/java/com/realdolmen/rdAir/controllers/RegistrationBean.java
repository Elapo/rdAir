package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.Customer;
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

    @Inject
    private LoginBean login;

    private Customer user;

    @NotNull(message="Your first name should be filled in!")
    @Size(min=1, max=50, message="Your first name should have at least 1 letter!")
    private String fName;

    @NotNull(message="Your last name should be filled in!")
    @Size(min=1, max=50, message="Your last name should have at least 1 letter!")
    private String lName;

    @NotNull(message="Your address should be filled in!")
    @Size(min=1, max=100, message="Your address should have at least 1 letter!")
    private String address;

    @NotNull(message="Your phone number should be filled in!")
    @Size(min=5, max=10, message="Your phone number should have at least 5 digits and maximum 10 digits!")
    @Pattern(regexp = "[0-9]{5,10}",message = "Your phone number should only contain digits.")
    private String telephone;

    @NotEmpty(message="Your email must be filled in!")
    @Email(message="Your email should be like 'example@example.com'!")
    private String email;

    @Pattern(regexp = "[a-zA-Z]{8,30}",message = "Your password should contain at least 8 and maximum 30 letters and/or digits!")
    private String password;

    private String passwordHash;

    public String doRegistration(){
        System.err.println("Succesfully registered customer: " + lName);
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
