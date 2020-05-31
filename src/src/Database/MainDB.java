package src.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainDB {

    public Database initDB() {

        //creates a new sqlite3 database in the same folder
        Database db = new Database("dataBase.db");

//        db.insertData("1 Dragos Dinescu dragos@yahoo.com 0744522600 Digi 10-12-2020");
//        db.insertData("2 Adelina Mirea ademirea@gmail.com 0733651320 Orange 14-10-2020");
//        db.insertData("3 Radu Sorostinean radusoro@gmail.com 0733723321 Digi 1-10-2020");
//        db.insertData("4 Andrei Brasoveanu andreibraso@gmail.com 0732341390 Vodafone 12-11-2020");
        return db;

        //db.deleteFromDB();
        //db.updateDB("1 alex radu radu@gmail.com 022256926 orange 10/08/2010");
    }

}
