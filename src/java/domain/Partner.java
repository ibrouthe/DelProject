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
public class Partner {
    
    int parID;
    String parName;
    String parAdress;
    String parPhone;
    String eMail;
    String CVR;
    String parPass;
    int parFunds;
    String contactName;

    public Partner(int parID, String parName, String parAdress, String parPhone, String eMail, String CVR, String parPass, int parFunds, String contactName) {
	this.parID = parID;
	this.parName = parName;
	this.parAdress = parAdress;
	this.parPhone = parPhone;
	this.eMail = eMail;
	this.CVR = CVR;
	this.parPass = parPass;
	this.parFunds = parFunds;
	this.contactName = contactName;
    }

    public String getContactName() {
	return contactName;
    }

    public void setContactName(String kontactName) {
	this.contactName = contactName;
    }

    public String getParPhone() {
	return parPhone;
    }

    public void setParPhone(String parPhone) {
	this.parPhone = parPhone;
    }

    public String getCVR() {
	return CVR;
    }

    public void setCVR(String CVR) {
	this.CVR = CVR;
    }

   
    public int getParFunds() {
	return parFunds;
    }

    public void setParFunds(int parFunds) {
	this.parFunds = parFunds;
    }

    
    public String getParPass() {
	return parPass;
    }

    public void setParPass(String parPass) {
	this.parPass = parPass;
    }

    public Partner() {
    }

    public int getParID() {
        return parID;
    }

    public void setParID(int parID) {
        this.parID = parID;
    }

    public String getParName() {
        return parName;
    }

    public void setParName(String parName) {
        this.parName = parName;
    }

    public String getParAdress() {
        return parAdress;
    }

    public void setParAdress(String parAdress) {
        this.parAdress = parAdress;
    }

    
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    
    
    
}
