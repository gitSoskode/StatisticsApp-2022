/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statsapp_22;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 *
 * @author SosKode
 */
public class members {
    String index;
    String fullname;
    String gender;
    String role;
    
    public members(){
        this.index="";
        this.fullname=null;
        this.gender=null;
        this.role="";
    }
    public members(String index, String fullname, String gender, String role ){
        this.index=index;
        this.fullname=fullname;
        this.gender=gender;
        this.role=role;
    }
    
    public String getIndex(){
        return this.index;
    }
    public String getFullname(){
        return this.fullname;
    }
    public String getGender(){
        return this.gender;
    }
    public String getRole(){
        return this.role;
    }
    
    
    public void setIndex(String index){
        this.index=index;
    }
    
    public void setFullname(String fullname){
        this.fullname=fullname;
    }
    
    public void setGender(String gender){
        this.gender=gender;
    }
    
    public void setRole(String role){
        this.role=role;
    }
    
    
}
