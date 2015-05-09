/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfacees;

import domain.Employee;
import domain.Partner;
import domain.Project;
import java.io.InputStream;
import java.util.ArrayList;

public interface DBFacadeInterface {

    ArrayList<Project> listProject();

    ArrayList<Project> listParProjects(String email);

    ArrayList<Project> searchProjects(String searchField);

    ArrayList<Project> searchParProjects(String searchField, String email);

    ArrayList<Partner> searchPartner(String searchField);

    ArrayList<Employee> searchEmployee(String searchField);

    ArrayList<Partner> listPartner();

    ArrayList<Project> listRelevantProjects(String name, String role);

    ArrayList<Employee> listEmployee();

    void parTimeStamp(String email);

    void uploadPOE(InputStream iS, String clickedID);

    void updateStep(Project currentPro);

    void updateApproveProject(int currentProID, int choice);

  

    void startNewTransaction();



    String returnName();

    String returnRole();

    String returnEmail();

    Project getSelectedProject(String ClickedID);

    Partner getSelectedPartner(String ClickedID);

    Employee getSelectedEmployee(String ClickedID);

    boolean addEmployee(int empId, String empName, int empStatus, String empMail, String empPass);

    boolean checkEmpPw(String user, String pw);



    boolean checkParPw(String user, String pw);

    boolean addPartner(int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds, String contactName);

    boolean addProject(int proID, int proEmpID, int proParID, String proName, String proStartDate,
            String proEndDate, InputStream proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds);



    boolean commitTransaction();



}
