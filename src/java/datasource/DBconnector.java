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

            System.out.println("DellBOT: SQL exception in DBconnector contructor. You are not connected");

        } catch (ClassNotFoundException e) {

        }

    }

    public Connection getConnection() {

        return connection;

    }

    public static DBconnector getInstance() {

        if (instance == null) {

            instance = new DBconnector();

        }

        return instance;
    }

}
