/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

/**
 *
 * @author Pc
 */
public class DBfacade {

  
    public static void main(String[] args) {

        ProjectMapper mapper = new ProjectMapper();

        System.out.println(mapper.lookUp("Will Smith", DBconnector.getInstance().getConnection()));

       
    }
    

}
