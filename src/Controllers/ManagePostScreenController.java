/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.DeleteRecord;
import DBConnection.UpdateRecord;
import Model.Job;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dinesh
 */
public class ManagePostScreenController implements Initializable {

    @FXML
    private TextField txtJobTitle;
    @FXML
    private RadioButton radbtnRemote;
    @FXML
    private ToggleGroup jobCategory;
    @FXML
    private RadioButton radbtnOnsite;
    @FXML
    private RadioButton radbtnHybrid;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private Button btnSubmit;

    private Job job;
    private static final int  JOB = 3;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        job = new Job();
    }    

    @FXML
    private void btnSubmitHandler(ActionEvent event) throws IOException {
        
      String jobCategory;        
        
        if( radbtnRemote.isSelected() ){
            jobCategory = radbtnRemote.getText().toLowerCase();
        }else if( radbtnOnsite.isSelected()){
            jobCategory = radbtnOnsite.getText().toLowerCase();
        }else{
            jobCategory = radbtnHybrid.getText().toLowerCase();
        }
     
        
        job.setJobCategory(jobCategory);    
        job.setJobTitle(txtJobTitle.getText());
        job.setJobDescription(txtAreaDescription.getText());
        job.setUpdateDate( LocalDateTime.now());
        
        boolean isUpdated =  UpdateRecord.setUpdateRecord(job, JOB );
        
        if( isUpdated ){
            alert("Your Post has been Updated","Job Post","Job Post Update",AlertType.INFORMATION);
        }
        
         showMainScreen();
    }

    void setJob(Job job) {
        this.job = job;
        txtJobTitle.setText(job.getJobTitle());
        txtAreaDescription.setText(job.getJobDescription());
        
        if( job.getJobCategory().equalsIgnoreCase("remote")){
            radbtnRemote.setSelected(true);
        }else if( job.getJobCategory().equalsIgnoreCase("Onsite")){
            radbtnOnsite.setSelected(true);
        }else{
            radbtnHybrid.setSelected(true);
        }
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

    @FXML
    private void btnCancelHandler(ActionEvent event) throws IOException {
        showMainScreen();
    }
    
    
    private void showMainScreen() throws IOException{
        Stage stage = null;
         Parent root;
         stage = (Stage) btnSubmit.getScene().getWindow();            
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainScreen.fxml"));
         root = loader.load();
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.centerOnScreen();
         stage.show();
    }

    @FXML
    private void btnDeleteHandler(ActionEvent event) throws SQLException, IOException {
       
        if( alert("Are you sure you want to delete your post ?","Deleting job post","",AlertType.CONFIRMATION )){
               DeleteRecord.deleteJob(job.getJobID());
               txtJobTitle.setText("");
               radbtnRemote.setSelected(false);
               radbtnOnsite.setSelected(false);
               radbtnHybrid.setSelected(false);
               txtAreaDescription.setText("");
               alert("Your Post has been deleted","Job post removal","",AlertType.INFORMATION );
               showMainScreen();
        }
        
    }
}
