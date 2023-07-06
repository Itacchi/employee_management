package com.machinelearning.employeemanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull(message = "is required")
    @Size(min = 4, message = "minimum 4 letter required")
    @Size(max = 25, message = "minimum 25 letter required")
    @Column
    private String country;

    @NotNull(message = "is required")
    @Size(min = 10, message = "minimum 10 letter required")
    @Size(max = 25, message = "minimum 25 letter required")
    @Column
    private String firstLine;

    @Column
    @NotNull(message = "is required")
    @Size(min = 10, message = "minimum 10 letter required")
    @Size(max = 25, message = "minimum 25 letter required")
    private String secondLine;

    @Column
    @NotNull(message = "is required")
    @Size(min = 4, message = "minimum 4 letter required")
    @Size(max = 25, message = "minimum 25 letter required")
    private String city;

    @Column
    @NotNull(message = "is required")
    @Size(min = 4, message = "minimum 4 letter required")
    @Size(max = 25, message = "minimum 25 letter required")
    private String state;

    @Column
    @NotNull(message = "is required")
    @Size(min = 4, message = "minimum 4 letter required")
    private String zipcode;

    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return firstLine + ",\n" +
                secondLine + ",\n" +
                city + ",\n" +
                zipcode + ",\n" +
                state + ",\n" +
                country;
    }
}
