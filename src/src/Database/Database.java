package src.Database;
import java.sql.*;
import java.util.ArrayList;


public class Database {

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
     * subject to change on bigger scale projects
     */
    private void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS fileSystem (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL\n"
                + ");";
        String sqlDelete = "DROP TABLE IF EXISTS fileSystem";
        try {
            Statement stmt = connect.createStatement();
            // create a new table
            stmt.execute(sqlDelete);
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param text @String feed from a file/used that gets inputed into the database
     * inserts the elements into the database
     */
    public void insertData(String text) {
        String sqlInsert = "INSERT INTO fileSystem(name) VALUES(?)";
        try {
            PreparedStatement pstmt = connect.prepareStatement(sqlInsert);
            pstmt.setString(1, text);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param text @String takes the user input and does a query through the database
     * @return @String with the query results each result has a new line after it.
     */
    public String searchDB(String text) {
        String sql = "SELECT * FROM fileSystem where name like '%" + text + "%'";
        String returnString = "";
        try {
            PreparedStatement pstmt  = connect.prepareStatement(sql);
            ResultSet rs  = pstmt.executeQuery();
            while (rs.next()) {
                returnString = returnString + rs.getString("name")+" ";
                returnString = returnString + rs.getString("id") + "\n";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnString;
    }

}
