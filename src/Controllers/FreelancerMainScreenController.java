/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.DeleteRecord;
import Model.UserAccount;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dinesh
 */
public class FreelancerMainScreenController implements Initializable {

    @FXML
    private MenuItem updateMenuItem;
    @FXML
    private MenuButton settings;
    
    private String username;
    private String password;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    
    
    @FXML
    private void updateMenuItemHandler(ActionEvent event) throws IOException {
        
        boolean isUpdate = true;
         Stage stage = null;
         Parent root;
         stage = (Stage) settings.getScene().getWindow();       
       
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RegistrationScreen.fxml"));
         root = loader.load();
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.centerOnScreen();
         stage.show();
         
         RegistrationScreenController controller = loader.getController();         
         controller.setUpdate(isUpdate, username);
         
        
    }       
    
    
    @FXML
    private void deleteMenuItemHandler(ActionEvent event) throws IOException, SQLException {
        
        //prompt for password
        UserAccount useraccount = new UserAccount();
        useraccount.setUsername(this.username);
       if( isPasswordConfirmed("Please Enter Your Password", "Password Confirmation","Password confirmation required") ){
                
            
             useraccount.setPassword(password);
             
             if( alert("Are you sure you want delete your account", "Delete confirmation","Deleting your account",AlertType.CONFIRMATION)){
                     DeleteRecord.deleteRecord(useraccount);
                     alert("Your account has been removed from our system","Account Deletion","",AlertType.INFORMATION);
             }
            
         }
    }
    
     void setLoginInfo(String username , String password) {
        this.username = username;
        this.password = password;
        
       
        
    }
     
     
     private boolean isPasswordConfirmed(String message, String title,String header){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(message);
        
        Optional<String> result = dialog.showAndWait();
        return result.get().equals(this.password);
     }
     
     
      private boolean alert(String message, String title,String header, Alert.AlertType alertType ){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        boolean isConfirmed = false;
        Optional<ButtonType> result = alert.showAndWait();
        if( result.get() == ButtonType.OK ){
            isConfirmed = true;
        }
            
        return isConfirmed;   
    }
      
     
}
