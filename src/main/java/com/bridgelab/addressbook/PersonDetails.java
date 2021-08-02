package com.bridgelab.addressbook;

import com.opencsv.bean.CsvBindByPosition;

import java.util.List;
import java.util.Objects;

public class PersonDetails
{
    private int id;
    @CsvBindByPosition(position = 0)
    private String firstName;
    @CsvBindByPosition(position = 1)
    private String lastName;
    @CsvBindByPosition(position = 2)
    private String address;
    @CsvBindByPosition(position = 3)
    private String city;
    @CsvBindByPosition(position = 4)
    private String state;
    @CsvBindByPosition(position = 5)
    private int zipCode;
    @CsvBindByPosition(position = 6)
    private long phoneNumber;
    @CsvBindByPosition(position = 7)
    private String emailId;

    public PersonDetails(int id, String firstName, String lastname, String address, String city, String state, String zip, int phoneNumber, String email) {

    }

    public PersonDetails() {

    }

    public PersonDetails(int id, String first, String last, String address, String city, String state, String zip, int phoneNumber, String email, List<String> groups) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", phoneNumber=" + phoneNumber +
                ", emailId='" + emailId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonDetails other = (PersonDetails) obj;
        return Objects.equals(address, other.address) && Objects.equals(city, other.city)
                && Objects.equals(emailId, other.emailId) && Objects.equals(firstName, other.firstName) && id == other.id
                && Objects.equals(lastName, other.lastName) && phoneNumber == other.phoneNumber
                && Objects.equals(state, other.state) && Objects.equals(zipCode, other.zipCode);
    }
}
