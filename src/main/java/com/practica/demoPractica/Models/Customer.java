package com.practica.demoPractica.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int customerID;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="name")
    private String name;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Customer() {
    }
}
