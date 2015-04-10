/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pc
 */
public class DBconnector {

    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String URL = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";
    private String id = "cphir17";
    private String pw = "cphir17";

    private static DBconnector instance = null;

    private static Connection connection;

    private DBconnector() {

        try {
            connection = DriverManager.getConnection(URL, id, pw);

        } catch (SQLException ee) {

            System.out.println("Not connected");

        }

        System.out.println("-------------------");

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
