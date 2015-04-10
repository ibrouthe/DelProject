/*
 * Builds up the select statement
 * execute the statement
 * transfer data from resultset til List
 * return domaineobejct
 */
package datasource;

import java.beans.Statement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Pc
 */
public class ProjectMapper {

    Statement statement;
    ResultSet resultset;
    String textstring;

    public String lookUp(String name, Connection con) {

        String SQLString = "SELECT * FROM ACTOR WHERE ACTORNAME='" + name + "'";
        PreparedStatement statement = null;

        try {

            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String ano = rs.getString(1);

                String ana = rs.getString(2);

                textstring = ano + " " + ana;

                System.out.println("Test: " + textstring);

            }

        } catch (SQLException ee) {

        }

        return textstring;

    }

    public String greeting() {

        return "Working Method";

    }

}
