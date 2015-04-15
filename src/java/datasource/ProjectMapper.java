/*
 * Builds up the select statement
 * Executes the statement
 * Transfers data from the resultset an ArrayList
 * Returns an ArrayList with Projects
 */
package datasource;

import domain.AppController;
import domain.Project;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
public class ProjectMapper {

    Statement statement;
    ResultSet rs;

    public ArrayList<Project> listProject(Connection con) {

        ArrayList<Project> list = new ArrayList<Project>();

        String SQLString = "SELECT * FROM Project";
        PreparedStatement statement = null;

        try {

            statement = con.prepareStatement(SQLString);
            
           
            rs = statement.executeQuery();
            
          
            while (rs.next()) {

//                Project project = new Project(rs.getInt("proID"),rs.getInt("proEmpID"),rs.getInt("proParID"),rs.getString("proName"),rs.getString("proStartDate"),rs.getString("proEndDate"),rs.getString("proPeo"),rs.getInt("proStatus"),rs.getInt("proSteps"),rs.getInt("proReqFunds"),rs.getInt("proFunds"));

                Project project = new Project();
                
                
                project.setProID(rs.getInt(1));
                project.setProEmpID(rs.getInt(2));
                project.setProParID(rs.getInt(3));
                project.setProName(rs.getString(4));         
                project.setProStartDate(rs.getString(5));
                project.setProEndDate(rs.getString(6));
                project.setProPeo(rs.getString(7));
                project.setProStatus(rs.getInt(8));
                project.setProSteps(rs.getInt(9));
                project.setProReqFunds(rs.getInt(10));
                project.setProFunds(rs.getInt(11));

                System.out.println("DEBUGGER "+project);
                list.add(project);

            }

        } catch (SQLException ee) {

        }

        return list;

    }

}
