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



}
