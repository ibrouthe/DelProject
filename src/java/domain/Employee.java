/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author TOcvfan
 */
public class Employee {
     
    int empID;
    String empName;
    int empStatus;
    String empMail;
    String empPass;

    public Employee(int empID, String empName, int empStatus, String empMail, String empPass) {
	this.empID = empID;
	this.empName = empName;
	this.empStatus = empStatus;
	this.empMail = empMail;
	this.empPass = empPass;
    }

    public Employee() {
    }

    public int getEmpID() {
	return empID;
    }

    public void setEmpID(int empID) {
	this.empID = empID;
    }

    public String getEmpName() {
	return empName;
    }

    public void setEmpName(String empName) {
	this.empName = empName;
    }

    public int getEmpStatus() {
	return empStatus;
    }

    public void setEmpStatus(int empStatus) {
	this.empStatus = empStatus;
    }

    public String getEmpMail() {
	return empMail;
    }

    public void setEmpMail(String Mail) {
	this.empMail = empMail;
    }

    public String getEmpPass() {
	return empPass;
    }

    public void setEmpPass(String empPass) {
	this.empPass = empPass;
    }
    
}

