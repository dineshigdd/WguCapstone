/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Dinesh
 */
public class AccountCreationScreenController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnSign;
    
    private boolean isNewUser;
    

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }   
       
    
    @FXML
    private void btnSubmitHandler(ActionEvent event) {
        
        //check if the username is already exists
        //if not{
        //  get username
        //  get password
        //  write data on the database
        //-------------------------
        //get input
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        
        if( isNewUser )
        if ( checkUserName(username)){
            alert("The username has already been taken","User Name","Username Error", AlertType.ERROR);
             
//            alert("The Username exists", "Error in username","Error in username");
           
        }else{
            try{
                DBConnection conn = new DBConnection();
                conn.connectDatabase();
                String query = "insert into user(username, password) values( ?, ? )"; 
                PreparedStatement ps = conn.insertRecord(query);
                ps.setString(1, username );
                ps.setString(2, password );
                ps.execute();
                conn.closeDBConnection();
            }catch(SQLException e ){
                e.printStackTrace();
            }
        }
        
    }

    private boolean checkUserName(String username) {
      DBConnection conn = new DBConnection();
      conn.connectDatabase();
      ResultSet sqlResult;
      boolean isUsernameExit = false;
    
      try{           
            conn.setStatement( "select username from user where username ="+ "'" + username + "'" );
            sqlResult = conn.getStatement();
            isUsernameExit = sqlResult.next();
            conn.closeDBConnection();
             
      } catch(Exception e){
              e.printStackTrace();
        }
      
      
     return isUsernameExit;
    }
    
    
    private void alert(String message, String title,String header, AlertType alertType ){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();     
            
    }

    public void setNewUser(boolean isNewUser) {
        System.out.println("Account isNewUser:" + isNewUser);
        this.isNewUser = isNewUser;
       if( isNewUser ){
            btnSign.setVisible( false );
            btnSubmit.setText("Submit");
        }else{
             btnSign.setVisible( true );
        }
        
    }

    @FXML
    private void btnSignupHandler(ActionEvent event) throws IOException {
         Stage stage;
         Parent root;
         
         stage = (Stage) btnSign.getScene().getWindow();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RegistrationScreen.fxml"));
         root = loader.load();
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.centerOnScreen();
         stage.show();
         
    
        stage.show(); 
    }
   
    
}
