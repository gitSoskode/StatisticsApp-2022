/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statsapp_22;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SosKode
 */
public class LOGINController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        login.setOnAction(logging->{
            if(usernameTF.getText().equals("group") && pinTF.getText().equals("soskode")){
                try {
                    Parent root;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("functionsWin_styler.fxml"));
                    root=loader.load();
                    Stage stage ;
                    stage= (Stage) login.getScene().getWindow();   //to avoid duplicating/cascading windows
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("GROUP STATISTICAL ANALYSIS APPLICATION-2023");
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LOGINController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Login validation");
                alert.setHeaderText("INCORRECT USERNAME OR PIN");
                alert.setContentText("Use PIN created at sign up.");
                alert.showAndWait();
                
            }
        });
    } 
    
    
    //Controls
    @FXML private Button login;
    @FXML private TextField usernameTF;
    @FXML private PasswordField pinTF;
    
    
    
    
}
