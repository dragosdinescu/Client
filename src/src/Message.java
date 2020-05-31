package src;

import src.Model.Contact;

import java.io.Serializable;

public class Message implements Serializable {
    public String ID;
    public String action;
    public Contact contact;

    public Message (Contact contact, String action, String ID){
        this.contact = contact;
        this.action = action;
        this.ID = ID;
    }
    public String contactToString(){
         return this.ID + " "+ this.contact.getFirstName()+ " "+this.contact.getLastName()+ " "+ this.contact.getEmail()+ " "+ this.contact.getPhoneNumber()+
         " "+ this.contact.getCarrierEnum()+" " +this.contact.getDate();
    }


}
