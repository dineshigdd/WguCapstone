/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.AddRecord;
import Model.Assignment;
import Model.Freelancer;
import Model.Job;
import Model.Message;
import Model.User;
import java.io.IOException;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dinesh
 */
public class ResponseJobPostController {

    @FXML
    private AnchorPane paneMainResponse;
    @FXML
    private ToggleGroup responsetoJobGroup;
    @FXML
    private RadioButton radbtnLeaveMsg;
    @FXML
    private TextArea textareaMsg;
    @FXML
    private Button btnSubmit;

    /**
     * Initializes the controller class.
     */
    private Job job;
    private User user;
    private final static int MESSAGE = 4;
    @FXML
    private RadioButton radbtnApplyJob;
    private boolean isApplyForJob;
    private int contractorID;
    public void initialize( Job job,  User user ){
        // TODO
        this.job = job;
        this.user = user;
        this.contractorID = job.getJobPostedBy();
    }    

    @FXML
    private void radbtnLeaveMsgHandler(ActionEvent event) throws IOException {
        isApplyForJob = false;
        textareaMsg.setDisable(false);
        textareaMsg.setPromptText("");
        btnSubmit.setDisable(false);
    
    }

    @FXML
    private void btnSubmitHandler(ActionEvent event) {
        
        //get message
        
        String inputMessage = textareaMsg.getText().trim();
        if( !inputMessage.isEmpty()){
            
            Message message = new Message(        
                    textareaMsg.getText(),
                    AddRecord.FREELANCER,
                    LocalDateTime.now(),
                    user,
                    job             
            );
            
            AddRecord.setDbRecord(message, MESSAGE );
    }else{
        alert("Please enter your message","Input Error","",AlertType.ERROR);
    }
      
      
      if( isApplyForJob ){
         
          Assignment assignment = new Assignment(                  
               contractorID,
               ((Freelancer) user).getFreelancerID(),
               job.getJobID() 
          );
          
          //set contract status
          assignment.setContractStatus(MainScreenController.APPLIED_FREELANCER);
          //write int to assignment table
          
          AddRecord.setDbRecord(assignment, AddRecord.ASSIGNMENT);
      }
    }

    @FXML
    private void radbtnApplyJobHandler(ActionEvent event) {
        textareaMsg.setDisable(false);
        textareaMsg.setPromptText("Optional message");
        btnSubmit.setDisable(false);
        
        isApplyForJob = true;
    }
    
    
    private void alert(String message, String title,String header, AlertType alertType ){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();     
            
    }
    
}
