/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfacees;

import domain.Partner;

import domain.Project;
import java.util.List;

/**
 *
 * @author TOcvfan
 */

public interface Interface {
    boolean authCheckPartner(String eMail, String pwd);
    boolean authCheckProject(String eMail, String pwd);    
    boolean authCheckEmployee(String employeeId, String pwd);
    Partner getPartner(String partnerId);
    Project getProject(String projectId);
//    Employee getEmployee (String employeeId);
    
    boolean addPartner(int parId, String parName, String parAdress, String parPhone, 
	    String eMail, String CVR, String parPass, int parFunds);
    
    boolean addProject(int proID, int proEmpID, int proParID, String proName,String proStartDate, String proEndDate, 
            String proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds);
    
    boolean addEmployee(String empId, String empName, String empPass, String empMail);
}
