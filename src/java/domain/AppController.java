/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import datasource.DBconnector;
import datasource.ProjectMapper;
import java.util.ArrayList;

/**
 *
 * @author Pc
 */
public class AppController {

    public ArrayList listAllProjects() {

        ProjectMapper mapper = new ProjectMapper();

        ArrayList<Project> showlist;

        showlist = mapper.listProject(DBconnector.getInstance().getConnection());

        return showlist;
        
    }

}
