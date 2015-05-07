/*
 * Builds up the select statement
 * Executes the statement
 * Transfers data from the resultset an ArrayList
 * Returns an ArrayList with Projects
 */
package datasource;

import domain.AppController;
import domain.Employee;
import domain.Partner;
import domain.Project;
import domain.UserLogin;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
public class Mapper {

    UserLogin ul;
    PreparedStatement statement;
    ResultSet rs;
    private int createdParID;
    private int createdEmpID;

    public ArrayList<Project> listProject() {

        ArrayList<Project> list = new ArrayList<Project>();

        String SQLString = "SELECT * FROM Project";
        statement = null;

        try (Connection con = DBconnector.getInstance().getConnection()) {

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
                project.setProPeo(rs.getBinaryStream(7));
                project.setProStatus(rs.getInt(8));
                project.setProSteps(rs.getInt(9));
                project.setProReqFunds(rs.getInt(10));
                project.setProFunds(rs.getInt(11));

                list.add(project);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        return list;

    }

    public ArrayList<Partner> listPartner() {

        ArrayList<Partner> list = new ArrayList<Partner>();

        String SQLString = "SELECT * FROM Partner";
        statement = null;

        try (Connection con = DBconnector.getInstance().getConnection()) {

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

                list.add(partner);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };

        }

        return list;

    }

    public boolean addPartner(int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds) {

        Partner p = new Partner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds);
        if (checkInputPartner(p) == true) {
            int rowsInserted = 0;

            String sql = "INSERT INTO Partner VALUES(parSeq.nextval,'" + p.getParName() + "','" + p.getParAdress() + "','"
                    + p.getParPhone() + "','" + p.geteMail() + "','" + p.getCVR() + "'," + p.getParFunds() + ")";

            PreparedStatement statement1 = null;
            PreparedStatement statement2 = null;

            try (Connection con = DBconnector.getInstance().getConnection()) {

                statement1 = con.prepareStatement(sql);

                rowsInserted = statement1.executeUpdate();

                String SQLString = "SELECT * FROM PARTNER WHERE PAREMAIL LIKE'" + p.geteMail() + "'";

                statement1 = con.prepareStatement(SQLString);

                rs = statement1.executeQuery();

                while (rs.next()) {

                    createdParID = rs.getInt(1);

                }
                String SQLparUserTable = "INSERT INTO PARUSER VALUES(" + createdParID + ",'" + p.getParPass() + "')";

                statement2 = con.prepareStatement(SQLparUserTable);

                rowsInserted = statement1.executeUpdate();
                rowsInserted = statement2.executeUpdate();

            } catch (SQLException ee) {

                printSQLException(ee);

            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {

                };
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception e) {
                };
            }
            return rowsInserted == 1;
        }
        return true;
    }

    public boolean addProject(int proID, int proEmpID, int proParID, String proName, String proStartDate,
            String proEndDate, InputStream proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds) {

        Project pro = new Project(0, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE,
                proStatus, proSteps, proReqFunds, proFunds);

        if (checkInputProject(pro) == true) {
            int rowsInserted = 0;

            String sql = "INSERT INTO PROJECT VALUES(proSeq.nextval," + pro.getProEmpID() + "," + pro.getProParID() + ",'"
                    + pro.getProName() + "','" + pro.getProStartDate() + "','" + pro.getProEndDate() + "',null,0,2,"
                    + pro.getProReqFunds() + ",100)";

            PreparedStatement statement = null;

            try (Connection con = DBconnector.getInstance().getConnection()) {

                statement = con.prepareStatement(sql);

                rowsInserted = statement.executeUpdate();

//                try {
//                    InputStream is = filePart.getInputStream();
//                    BufferedImage bi = ImageIO.read(is);
//                    ImageIO.write(bi, "jpg", new File("C:\\Users\\Ib Routhe\\Dropbox\\coding\\java\\Dell\\images" + filePart.getSubmittedFileName()));
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                }
            } catch (SQLException ee) {

                printSQLException(ee);

            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {

                };
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception e) {
                };
            }
            return rowsInserted == 1;
        }
        return true;
    }

    public ArrayList<Project> listRelevantProjects(String name, String role) {

        int firstStep = 0;
        int secondStep = 0;

        if (role.equals("employee")) {
            firstStep = 2;
            secondStep = 5;
        }
        if (role.equals("partner")) {
            firstStep = 1;
            secondStep = 4;
        }

        ArrayList<Project> relList = new ArrayList<Project>();

        String SQLString = "SELECT * FROM Project WHERE prosteps = " + firstStep + " OR prosteps = " + secondStep + "";
        System.out.println("sql String in Mapper -> " + SQLString);
        statement = null;

        try (Connection con = DBconnector.getInstance().getConnection()) {

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
                project.setProPeo(rs.getBinaryStream(7));
                project.setProStatus(rs.getInt(8));
                project.setProSteps(rs.getInt(9));
                project.setProReqFunds(rs.getInt(10));
                project.setProFunds(rs.getInt(11));

                relList.add(project);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        return relList;

    }

    public void uploadPOE(InputStream iS, String clickedID) {
        
        String message = null;
        
        try (Connection con = DBconnector.getInstance().getConnection()) {
            // constructs SQL statement  
         //   "Update  [Inventory] set [Item Picture] = ? where [Item Number] = ?"
            String sql = "UPDATE Project SET propoe = (?) WHERE proID = " + clickedID;

            statement = con.prepareStatement(sql);

            if (iS != null) {
                // fetches input stream of the upload file for the blob column  
                statement.setBinaryStream(1, iS);
               // statement.setString(2, clickedID);
            }
            // sends the statement to the database server  
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Image is uploaded successfully into the Database";
            }
            System.out.println("sql fra mapper: " + sql);
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        }

    }

    public boolean checkInputPartner(Partner p) {
        return true;
    }

    public boolean checkInputProject(Project pro) {
        return true;
    }

    public void updateStep(int currentProID, int newStep) {

        String SQLString = "UPDATE project SET prosteps = " + newStep + " WHERE proID = " + currentProID;
        PreparedStatement statement = null;

        try (Connection con = DBconnector.getInstance().getConnection()) {
            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }
    }

    //1st method called by checkPW(). Using the user's email to find his ID in the database.
    public void getParID(String email) {
        String sql = "SELECT parid FROM partner WHERE paremail LIKE '" + email + "'";
        PreparedStatement statement = null;
        String parID = null;
        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                parID = rs.getString(1);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        ul.setId(parID);
    }

    //2nd method called by checkPW(), using the Partner ID from getParID() to find the user's password in the database.
    public void getParPW(String parID) {
        String sql = "SELECT ppass FROM parUser WHERE pid LIKE '" + parID + "'";
        PreparedStatement statement = null;
        String parPW = null;
        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {

                parPW = rs.getString(1);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }
        ul.setPassword(parPW);
    }

    //Method to check the user who is trying to login, by getting his ID and password in the database
    //and comparing the password in the database to the one he typed in.
    //Calling methods getParID() and getParPW() to do so.
    //Finally checking if the typed in password, matches the one in our database.
    //Returns true if they match.
    public boolean checkParPw(String user, String pw) {
        ul = new UserLogin();

        try (Connection con = DBconnector.getInstance().getConnection()) {

            getParID(user);
            getParName(user);

            String dbID = ul.getId();
            System.out.println("dbID: " + dbID);

            getParPW(dbID);

            String dbPass = ul.getPassword();

            if (pw.equalsIgnoreCase(dbPass)) {

                ul.setPassword(null);
                ul.setRole("partner");

                return true;
            } else {
                ul.setPassword(null);
                return false;
            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        return false;

    }

    public void getParName(String email) {

        String sql = "SELECT PARNAME FROM PARTNER WHERE PAREMAIL LIKE '" + email + "'";

        PreparedStatement statement = null;

        String parname = null;

        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {

                parname = rs.getString(1);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        ul.setUsername(parname);

    }

    public void getEmpID(String email) {
        String sql = "SELECT empid FROM employee WHERE empmail LIKE '" + email + "'";
        PreparedStatement statement = null;
        String empID = null;
        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                empID = rs.getString(1);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        ul.setId(empID);
    }

    public void getEmpName(String email) {
        String sql = "SELECT empname FROM employee WHERE empmail LIKE '" + email + "'";
        PreparedStatement statement = null;
        String empName = null;
        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                empName = rs.getString(1);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        ul.setUsername(empName);
    }

    //2nd method called by checkPW(), using the Partner ID from getParID() to find the user's password in the database.
    public void getEmpPW(String empID) {
        String sql = "SELECT epass FROM empUser WHERE eid LIKE '" + empID + "'";
        PreparedStatement statement = null;
        String empPW = null;
        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {

                empPW = rs.getString(1);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }
        ul.setPassword(empPW);
    }

    public boolean checkEmpPw(String user, String pw) {
        ul = new UserLogin();

        getEmpID(user);
        getEmpName(user);

        String dbID = ul.getId();

        getEmpPW(dbID);

        String dbPass = ul.getPassword();

        if (pw.equalsIgnoreCase(dbPass)) {
            ul.setPassword(null);
            ul.setRole("employee");
            return true;
        } else {
            ul.setPassword(null);
            return false;
        }

    }

    public String returnName() {
        String name = ul.getUsername();
        return name;
    }

    public String returnRole() {
        String role = ul.getRole();
        return role;
    }

    public Project getSelectedProject(String ClickedID) {

        System.out.println("ClickedID fra ProjectMapper: " + ClickedID);
        String SQLString = "SELECT * FROM Project WHERE proID = " + ClickedID;
        PreparedStatement statement = null;
        Project selProject = new Project();

        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

                Part tempFilePart = null;
                InputStream tempStream = null;
                selProject.setProID(rs.getInt(1));
                selProject.setProEmpID(rs.getInt(2));
                selProject.setProParID(rs.getInt(3));
                selProject.setProName(rs.getString(4));
                selProject.setProStartDate(rs.getString(5));
                selProject.setProEndDate(rs.getString(6));
                
                selProject.setProPeo(null);
                
                selProject.setProStatus(rs.getInt(8));
                selProject.setProSteps(rs.getInt(9));
                selProject.setProReqFunds(rs.getInt(10));
                selProject.setProFunds(rs.getInt(11));

                System.out.println("DEBUGGER " + selProject);

            }
        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        return selProject;

    }

    public void updateApproveProject(int currentProID, int choice) {

        String SQLString = "UPDATE project SET prostatus = " + choice + " WHERE proID = " + currentProID;
        PreparedStatement statement = null;

        try (Connection con = DBconnector.getInstance().getConnection()) {
            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }
    }

    public Partner getSelectedPartner(String ClickedID) {

        System.out.println("ClickedID fra Mapper: " + ClickedID);
        String SQLString = "SELECT * FROM Partner WHERE parID = " + ClickedID;
        PreparedStatement statement = null;
        Partner selPartner = new Partner();

        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

                selPartner.setParID(rs.getInt(1));
                selPartner.setParName(rs.getString(2));
                selPartner.setParAdress(rs.getString(3));
                selPartner.setParPhone(rs.getString(4));
                selPartner.seteMail(rs.getString(5));
                selPartner.setCVR(rs.getString(6));
                selPartner.setParPass(rs.getString(7));
                selPartner.setParFunds(rs.getInt(8));

                System.out.println("DEBUGGER " + selPartner);

            }
        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        return selPartner;

    }

    public boolean addEmployee(int empId, String empName, int empStatus, String empMail, String empPass) {

        Employee em = new Employee(empId, empName, empStatus, empMail, empPass);
        if (checkInputEmployee(em) == true) {
            int rowsInserted = 0;

            String sql = "INSERT INTO Employee VALUES(empSeq.nextval,'" + em.getEmpName() + "'," + em.getEmpStatus() + ",'"
                    + em.getEmpMail() + "','" + em.getEmpPass() + "')";

            PreparedStatement statement1 = null;
            PreparedStatement statement2 = null;

            try (Connection con = DBconnector.getInstance().getConnection()) {

                statement1 = con.prepareStatement(sql);

                rowsInserted = statement1.executeUpdate();

                String SQLString = "SELECT * FROM EMPLOYEE WHERE EMPMAIL LIKE'" + em.getEmpMail() + "'";

                statement1 = con.prepareStatement(SQLString);

                rs = statement1.executeQuery();

                while (rs.next()) {

                    createdEmpID = rs.getInt(1);

                }
                String SQLempUserTable = "INSERT INTO EMPUSER VALUES(" + createdEmpID + ",'" + em.getEmpPass() + "')";

                statement2 = con.prepareStatement(SQLempUserTable);

                rowsInserted = statement1.executeUpdate();
                rowsInserted = statement2.executeUpdate();

            } catch (SQLException ee) {

                printSQLException(ee);

            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {

                };
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception e) {
                };
            }
            return rowsInserted == 1;
        }
        return true;
    }

    public ArrayList<Employee> listEmployee() {

        ArrayList<Employee> list = new ArrayList<Employee>();

        String SQLString = "SELECT * FROM Employee";
        PreparedStatement statement = null;

        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setEmpID(rs.getInt(1));
                employee.setEmpName(rs.getString(2));
                employee.setEmpStatus(rs.getInt(3));
                employee.setEmpMail(rs.getString(4));
                employee.setEmpPass(rs.getString(5));

                System.out.println("DEBUGGER " + employee);
                list.add(employee);

            }

        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        return list;

    }

    public Employee getSelectedEmployee(String ClickedID) {

        System.out.println("ClickedID fra EmployeeMapper: " + ClickedID);
        String SQLString = "SELECT * FROM Employee WHERE empID = " + ClickedID;
        PreparedStatement statement = null;
        Employee selEmployee = new Employee();

        try (Connection con = DBconnector.getInstance().getConnection()) {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

                selEmployee.setEmpID(rs.getInt(1));
                selEmployee.setEmpName(rs.getString(4));
                selEmployee.setEmpStatus(rs.getInt(8));
                selEmployee.setEmpMail(rs.getString(5));
                selEmployee.setEmpPass(rs.getString(6));

                System.out.println("DEBUGGER " + selEmployee);

            }
        } catch (SQLException ee) {

            printSQLException(ee);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            };
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            };
        }

        return selEmployee;

    }

    public boolean checkInputEmployee(Employee em) {

        return true;
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
