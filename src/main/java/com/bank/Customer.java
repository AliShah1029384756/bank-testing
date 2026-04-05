package com.bank;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private int age;

    public Customer(String customerId, String name, String email, int age) {
        if (age < 18) throw new IllegalArgumentException("Customer must be 18 or older");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public void setEmail(String email) { this.email = email; }
}