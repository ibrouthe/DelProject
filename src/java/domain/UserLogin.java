/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Gruppe 2 - Silas, Martin, Thomas, Christian, Ib
 */
public class UserLogin {
    String id = null;
    String username = null;
    String password = null;
 
    public void setUsername(String user) {
        username = user;
 
    }
 
    public void setPassword(String pw) {
        password = pw;
 
    }
 
    public String getUsername() {
        return username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
}
