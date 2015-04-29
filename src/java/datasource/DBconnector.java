/*
 * Object that handles the connection to the database
 * Singleton class - only 1 instance of this class pr user
 */
package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
public class DBconnector {

    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String URL = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";
    private String id = "cphir17";
    private String pw = "cphir17";

    private static DBconnector instance = null;

    private Connection connection;

    private DBconnector() {

        try {

            Class.forName(driver);

            connection = DriverManager.getConnection(URL, id, pw);

        } catch (SQLException ee) {

            printSQLException(ee);

        } catch (ClassNotFoundException e) {

        }

    }

    public Connection getConnection() {

        try {
            if (connection.isClosed()) {

                Class.forName(driver);

                connection = DriverManager.getConnection(URL, id, pw);
            }
            }catch (SQLException ee) {

              printSQLException(ee);

        }catch (ClassNotFoundException e) {

        

        }
        return connection;

    }

    public static DBconnector getInstance() {

        if (instance == null) {

            instance = new DBconnector();

        }

        return instance;
    }
    public static void printSQLException(SQLException ex) {
        
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                if (ignoreSQLException(
                        ((SQLException) e).
                        getSQLState()) == false) {
                    
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: "
                            + ((SQLException) e).getSQLState());
                    
                    System.err.println("Error Code: "
                            + ((SQLException) e).getErrorCode());
                    
                    System.err.println("Message: " + e.getMessage());
                    
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }
    
    public static boolean ignoreSQLException(String sqlState) {
        
        if (sqlState == null) {
            System.out.println("The SQL state is not defined!");
            return false;
        }

        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32")) {
            return true;
        }

        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55")) {
            return true;
        }
        
        return false;
    }

}
