package src.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainDB {

    public static void main(String[] args) {

        //creates a new sqlite3 database in the same folder
        Database db = new Database("dataBase.db");
        //reads from the file list.txt
        System.out.println(System.getProperty("user.dir"));
        fileReader reader = new fileReader("./src/src/Database/list.txt");
        //iterates over the data and inputs it in the database
        for(String s : reader.readLines()) {
            db.insertData(s);
        }
        //makes the gui
        GUI ui = new GUI(db);
    }

}
