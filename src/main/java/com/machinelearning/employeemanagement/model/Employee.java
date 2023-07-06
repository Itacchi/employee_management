package com.machinelearning.employeemanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "is required")
    @Size(min = 1, message = "minimum 1 letter required")
    @Size(max = 25, message = "minimum 25 letter required")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "minimum 1 letter required")
    @Size(max = 25, message = "minimum 25 letter required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "minimum 1 letter required")
    @Size(max = 25, message = "minimum 25 letter required")
    @Pattern(regexp = "^[\\w_.\\d]+@[\\w\\d_.]+[.][\\w]+",message = "Invalid Email")
    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address permanentAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address currentAddress;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", permanentAddress=" + permanentAddress +
                ", currentAddress=" + currentAddress +
                '}';
    }
}
