package com.major.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String country;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private String city;
    private String phone;
    private String email;
    private String additionalInformation;
//    private double totalAmount;
    @ManyToMany
    @JoinTable(name = "Order_Products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User userLoggedIn;

//    public User getUserLoggedIn() {
//        return userLoggedIn;
//    }
//
//    public void setUserLoggedIn(User userLoggedIn) {
//        this.userLoggedIn = userLoggedIn;
//    }

    public OrderDetails() {
    }

    public OrderDetails(Integer id, String firstName, String lastName, String country, String addressLine1, String addressLine2, String postcode, String city, String phone, String email, String additionalInformation, List<Product> productList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postcode = postcode;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.additionalInformation = additionalInformation;
//        this.totalAmount = totalAmount;
        this.productList = productList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
//    public double getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }

//    public String getUserLoggedIn() {
//        return userLoggedIn;
//    }
//
//    public void setUserLoggedIn(String userLoggedIn) {
//        this.userLoggedIn = userLoggedIn;
//    }
}
