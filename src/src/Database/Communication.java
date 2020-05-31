package src.Database;

import java.sql.Connection;
import java.util.ArrayList;

public interface Communication {
    private Connection createNewDatabase() {
        return null;
    }
    private void createNewTable(){}
    public void insertData(String text);
    public String searchDB(String text);
    public void deleteFromDB(String id);
    public void updateDB(String model);
    public ArrayList<String> returnID();
}
