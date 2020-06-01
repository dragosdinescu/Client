package src.Database;
import java.sql.*;
import java.util.ArrayList;

public class Database implements Communication {

    private Connection connect;
    private String location;

    /**
     *
     * @param location the path to the database
     * calls the creation of a new table after a database is already initialized
     */
    public Database(String location) {
        this.location = location;
        this.connect = createNewDatabase();
        createNewTable();
    }

    /**
     * creates a new database in the desired location, returns the connection
     */
    private Connection createNewDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String path = "jdbc:sqlite:" + location;
        try {
            Connection connection = DriverManager.getConnection(path);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * creates a new table and deletes the old one, so each time the prorgam runs it reads from the file and remakes the database
     * it deletes the table so it doesnt add the data each time the program is runs
     */
    private void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS contacts " +
                "(id INTEGER not NULL, " +
                " firstName VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " email VARCHAR(255), " +
                " phoneNumber VARCHAR(255), " +
                " carrierEnum VARCHAR(255), " +
                " registrationDate VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        //String sqlDelete = "DROP TABLE IF EXISTS contacts";
        try {
            Statement stmt = connect.createStatement();
            //stmt.executeUpdate(sqlDelete);
            stmt = connect.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     * @param text @String feed from a file/used that gets inputed into the database
     * inserts the elements into the database
     */
    public void insertData(String text) {
        String[] splitted = text.split("\\s+");

        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate("INSERT INTO contacts " + "VALUES ("+splitted[0]+", '"+splitted[1]+"'," +
                    "'"+splitted[2]+"', '"+splitted[3]+"','"+splitted[4]+"','"+splitted[5]+"','"+splitted[6]+" ')");//introduce in DB un String splituit prin
                                                                                                                    //space care contine toate datele din Contact
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    /**
     *
     * @param text @String takes the user input and does a query through the database
     * @return @String with the query results each result has a new line after it.
     */
    public String searchDB(String text) {
        String sql = "SELECT * FROM contacts where id ="+text+" ";
        String returnString = "";
        try {
            PreparedStatement pstmt  = connect.prepareStatement(sql);
            ResultSet rs  = pstmt.executeQuery();
            while (rs.next()) {
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {

                    int type = resultSetMetaData.getColumnType(i);
                    if (type == Types.VARCHAR || type == Types.CHAR) {
                        returnString = returnString + rs.getString(i) + " ";
                    } else {
                        returnString = returnString + rs.getLong(i) + " ";
                    }
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(returnString.equals(""))
            return null;

        return returnString;
    }

    public void deleteFromDB(String id) {

        Statement stmt = null;
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate("DELETE FROM contacts WHERE id ="+id+" ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateDB(String model) {
        try {
            String[] split = model.split("\\s+");
            PreparedStatement update = connect.prepareStatement("UPDATE contacts SET firstName = ?," +
                    " lastName = ?, email = ?, phoneNumber = ?, carrierEnum = ?, registrationDate = ? WHERE id = ?");

            update.setString(1, split[1]);
            update.setString(2, split[2]);
            update.setString(3, split[3]);
            update.setString(4, split[4]);
            update.setString(5, split[5]);
            update.setString(6, split[6]);
            update.setInt(7, Integer.parseInt(split[0]));

            update.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<String> returnID(){
        ArrayList<String> ids = new ArrayList<>();
        String sql = "SELECT id FROM contacts";
        try {
            PreparedStatement pstmt  = connect.prepareStatement(sql);
            ResultSet rs  = pstmt.executeQuery();
            while (rs.next()) {
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {

                    int type = resultSetMetaData.getColumnType(i);
                    if (type == Types.VARCHAR || type == Types.CHAR) {
                         ids.add(rs.getString(i));
                    } else {
                        ids.add(String.valueOf(rs.getLong(i)));
                    }
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ids;
    }




}
