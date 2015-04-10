/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
public class Project {

    int proID;
    int proEmpID;
    int proParID;
    String proName;
    String proStartDate;
    String proEndDate;
    String proPeo;
    int proStatus;
    int proSteps;
    int proFunds;

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public int getProEmpID() {
        return proEmpID;
    }

    public void setProEmpID(int proEmpID) {
        this.proEmpID = proEmpID;
    }

    public int getProParID() {
        return proParID;
    }

    public void setProParID(int proParID) {
        this.proParID = proParID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProStartDate() {
        return proStartDate;
    }

    public void setProStartDate(String proStartDate) {
        this.proStartDate = proStartDate;
    }

    public String getProEndDate() {
        return proEndDate;
    }

    public void setProEndDate(String proEndDate) {
        this.proEndDate = proEndDate;
    }

    public String getProPeo() {
        return proPeo;
    }

    public void setProPeo(String proPeo) {
        this.proPeo = proPeo;
    }

    public int getProStatus() {
        return proStatus;
    }

    public void setProStatus(int proStatus) {
        this.proStatus = proStatus;
    }

    public int getProSteps() {
        return proSteps;
    }

    public void setProSteps(int proSteps) {
        this.proSteps = proSteps;
    }

    public int getProFunds() {
        return proFunds;
    }

    public void setProFunds(int proFunds) {
        this.proFunds = proFunds;
    }

}
