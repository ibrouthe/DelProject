/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import Interfacees.DBFacadeInterface;
import domain.Employee;
import domain.Partner;
import domain.Project;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
public class DBfacade implements DBFacadeInterface {

    private static DBfacade instance;
    Mapper mapper;

    public DBfacade() {

        mapper = new Mapper();

    }

    public static DBfacade getInstance() {
        if (instance == null) {
            instance = new DBfacade();
        }
        return instance;
    }

    @Override
    public ArrayList<Project> listProject() {

        ArrayList<Project> showlist;

        showlist = mapper.listProject();

        return showlist;

    }

    @Override
    public ArrayList<Project> listRelevantProjects(String name, String role) {

        ArrayList<Project> showRelList;

        showRelList = mapper.listRelevantProjects(name, role);

        return showRelList;

    }

    @Override
    public void uploadPOE(InputStream iS, String clickedID) {

        mapper.uploadPOE(iS, clickedID);

    }

    @Override
    public ArrayList<Project> listParProjects(String email) {
        ArrayList<Project> ownlist;

        ownlist = mapper.listParProjects(email);

        return ownlist;
    }

    @Override
    public ArrayList<Project> searchProjects(String searchField) {
        ArrayList<Project> searchList;

        searchList = mapper.searchProjects(searchField);

        return searchList;
    }

    @Override
    public ArrayList<Project> searchParProjects(String searchField, String email) {
        ArrayList<Project> searchList;

        searchList = mapper.searchParProjects(searchField, email);

        return searchList;
    }

    @Override
    public ArrayList<Partner> searchPartner(String searchField) {
        ArrayList<Partner> searchList;

        searchList = mapper.searchPartner(searchField);

        return searchList;
    }

    @Override
    public ArrayList<Employee> searchEmployee(String searchField) {
        ArrayList<Employee> searchList;

        searchList = mapper.searchEmployee(searchField);

        return searchList;
    }

    @Override
    public ArrayList<Partner> listPartner() {
        ArrayList<Partner> showlist;

        showlist = mapper.listPartner();

        return showlist;
    }

    @Override
    public ArrayList<Employee> listEmployee() {
        ArrayList<Employee> showlist;

        showlist = mapper.listEmployee();

        return showlist;
    }

    @Override
    public void parTimeStamp(String email) {
        mapper.parTimeStamp(email);
    }

    @Override
    public void updateStep(Project currentPro) {
       
        int currentProID = currentPro.getProID();
        int oldStep = currentPro.getProSteps();
        int newStep = oldStep;
        int currentStat = currentPro.getProStatus();
        InputStream currentPOE = currentPro.getProPeo();

        if (currentPOE == null && oldStep > 5) {
            newStep = 5;
        }

        switch (currentStat) {
            //needs approval
            case 0: {
                newStep = 2;
                break;
            }
            //active
            case 1: {
                if (oldStep < 3) {
                    newStep = 3;
                }
                break;
            }
            //inactive
            case 2: {
                break;
            }
            //completed
            case 3: {
                newStep = 7;
                break;
            }
        }

        mapper.updateStep(currentProID, newStep);

    }

    @Override
    public void updateApproveProject(int currentProID, int choice) {

        mapper.updateApproveProject(currentProID, choice);

    }

    @Override
    public void startNewTransaction() {

        System.out.println("DBFACADE");

    }

    @Override
    public String returnName() {

        return mapper.returnName();

    }

    @Override
    public String returnRole() {
        return mapper.returnRole();
    }

    @Override
    public String returnEmail() {
        return mapper.returnEmail();
    }

    @Override
    public Project getSelectedProject(String ClickedID) {
        Project p;

        p = mapper.getSelectedProject(ClickedID);

        return p;
    }

    @Override
    public Partner getSelectedPartner(String ClickedID) {
        Partner pa;

        pa = mapper.getSelectedPartner(ClickedID);

        return pa;
    }

    @Override
    public Employee getSelectedEmployee(String ClickedID) {
        Employee em;

        em = mapper.getSelectedEmployee(ClickedID);

        return em;
    }

    @Override
    public boolean addEmployee(int empId, String empName, int empStatus, String empMail, String empPass) {
        return mapper.addEmployee(empId, empName, empStatus, empMail, empPass);
    }

    @Override
    public boolean checkEmpPw(String user, String pw) {
        return mapper.checkEmpPw(user, pw);
    }

    @Override
    public boolean checkParPw(String user, String pw) {
        return mapper.checkParPw(user, pw);
    }

    @Override
    public boolean addPartner(int parId, String parName, String parAdress, String parPhone, String eMail, String CVR, String parPass, int parFunds, String contactName) {

        return mapper.addPartner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds, contactName);

    }

    @Override
    public boolean addProject(int proID, int proEmpID, int proParID, String proName, String proStartDate, String proEndDate, InputStream proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds) {
        return mapper.addProject(proID, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE, proStatus, proSteps, proReqFunds, proFunds);
    }

    @Override
    public boolean commitTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
