/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import domain.AppController;
import domain.Project;
import java.util.ArrayList;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
public class DBfacade {

    
    public static void main(String[] args) {
        
        AppController ctrl = new AppController();
        
        
//         ProjectMapper mapper = new ProjectMapper();

        ArrayList<Project> showlist;
        
        showlist = ctrl.listAllProjects();

//        showlist = mapper.listProject(DBconnector.getInstance().getConnection());
        
        for (Project temp: showlist){
        
        
            System.out.println(temp.getProName());

                
        
        
        }
        
        
    }
    
   
}
