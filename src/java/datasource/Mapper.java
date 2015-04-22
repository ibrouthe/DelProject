/*
 * Builds up the select statement
 * Executes the statement
 * Transfers data from the resultset an ArrayList
 * Returns an ArrayList with Projects
 */
package datasource;

import domain.AppController;
import domain.Partner;
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
public class Mapper {

    Statement statement;
    ResultSet rs;
    private int createdParID;

    public ArrayList<Project> listProject(Connection con) {

        ArrayList<Project> list = new ArrayList<Project>();

        String SQLString = "SELECT * FROM Project";
        PreparedStatement statement = null;

        try {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

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

                System.out.println("DEBUGGER " + project);
                list.add(project);

            }

        } catch (SQLException ee) {

        }

        return list;

    }

    public ArrayList<Partner> listPartner(Connection con) {

        ArrayList<Partner> list = new ArrayList<Partner>();

        String SQLString = "SELECT * FROM Partner";
        PreparedStatement statement = null;

        try {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

                Partner partner = new Partner();

                partner.setParID(rs.getInt(1));
                partner.setParName(rs.getString(2));
                partner.setParAdress(rs.getString(3));
                partner.setParPhone(rs.getString(4));
                partner.seteMail(rs.getString(5));
                partner.setCVR(rs.getString(6));
                partner.setParFunds(rs.getInt(7));

                System.out.println("DEBUGGER " + partner);
                list.add(partner);

            }

        } catch (SQLException ee) {

        }

        return list;

    }

    public boolean addPartner(Connection con, int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds) {

        Partner p = new Partner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds);
        if (checkInputPartner(p) == true) {
            int rowsInserted = 0;

            String sql = "INSERT INTO Partner VALUES(parSeq.nextval,'" + p.getParName() + "','" + p.getParAdress() + "','"
                    + p.getParPhone() + "','" + p.geteMail() + "','" + p.getCVR() + "'," + p.getParFunds() + ")";

            PreparedStatement statement1 = null;
            PreparedStatement statement2 = null;

            try {

                statement1 = con.prepareStatement(sql);

                rowsInserted = statement1.executeUpdate();

                String SQLString = "SELECT * FROM PARTNER WHERE PAREMAIL LIKE'" + p.geteMail() + "'";

                statement1 = con.prepareStatement(SQLString);

                rs = statement1.executeQuery();

                while (rs.next()) {

                    createdParID = rs.getInt(1);

                }
                String SQLparUserTable = "INSERT INTO PARUSER VALUES('" + createdParID + "','" + p.getParPass() + "')";

                statement2 = con.prepareStatement(SQLparUserTable);

                rowsInserted = statement1.executeUpdate();
                rowsInserted = statement2.executeUpdate();

            } catch (Exception e) {

                System.out.println("Fail1 in Partner details - addPartner");
                System.out.println(e.getMessage());
            } finally // must close statement1
            {
                try {
                    statement1.close();
                } catch (SQLException e) {
                    System.out.println("Fail2 in Partner details - addPartner");
                    System.out.println(e.getMessage());
                }
            }
            return rowsInserted == 1;
        }
        return true;
    }

    public boolean addProject(Connection con, int proID, int proEmpID, int proParID, String proName, String proStartDate,
            String proEndDate, String proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds) {

        Project pro = new Project(99, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE,
                proStatus, proSteps, proReqFunds, proFunds);

        if (checkInputProject(pro) == true) {
            int rowsInserted = 0;

            String sql = "INSERT INTO PROJECT VALUES(proSeq.nextval," + pro.getProEmpID() + "," + pro.getProParID() + ",'"
                    + pro.getProName() + "','" + pro.getProStartDate() + "','" + pro.getProEndDate() + "','peo',1,2,"
                    + pro.getProReqFunds() + ",100)";

            PreparedStatement statement = null;
            try {

                statement = con.prepareStatement(sql);

                rowsInserted = statement.executeUpdate();
            } catch (Exception e) {

                System.out.println("Fail1 in Partner details - addPartner");
                System.out.println(e.getMessage());
            } finally // must close statement1
            {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Fail2 in Partner details - addPartner");
                    System.out.println(e.getMessage());
                }
            }
            return rowsInserted == 1;
        }
        return true;
    }

    public boolean checkInputPartner(Partner p) {
        return true;
    }

    public boolean checkInputProject(Project pro) {
        return true;
    }

}
