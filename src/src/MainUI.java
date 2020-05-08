package src;

import src.Model.CarrierEnum;
import src.Model.Contact;
import src.View.UI;

import java.util.Date;
import java.util.HashMap;

public class MainUI {

    public static void main(String[] args) {

        CarrierEnum carrierEnum = new CarrierEnum("Digi","vodafloci","orange");
        Contact c1 = new Contact("Radu","Soro","wtf","077",carrierEnum.getDigi(), new Date(1/22/2020));
        Contact c2 = new Contact("Ade","Mirea","wtf","077",carrierEnum.getDigi(), new Date(1/22/2020));
        Contact c3 = new Contact("Caius","Dinesku","wtf","077",carrierEnum.getDigi(), new Date(1/22/2020));
        HashMap<String,Contact> contactMap = new HashMap<>();
        contactMap.put("01",c1);
        contactMap.put("02",c2);
        contactMap.put("03",c3);

        UI ui=new UI(contactMap);
        ui.startUI();
    }

}
