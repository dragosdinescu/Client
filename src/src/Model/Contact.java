package src.Model;

import java.util.Date;

public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private CarrierEnum carrierEnum;
    private Date date;

    public Contact(String firstName, String lastName, String email, String phoneNumber, CarrierEnum carrierEnum, Date date){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.carrierEnum= carrierEnum;
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CarrierEnum getCarrierEnum() {
        return carrierEnum;
    }

    public Date getDate() {
        return date;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCarrierEnum(CarrierEnum carrierEnum) {
        this.carrierEnum = carrierEnum;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
