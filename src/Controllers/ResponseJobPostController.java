/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.AddRecord;
import DBConnection.DBConnection;
import Model.Assignment;
import Model.Job;
import Model.Message;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.event.ChangeListener;

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
    private int jobID;
    private int freelancerID;
    private final static int MESSAGE = 4;
    @FXML
    private RadioButton radbtnApplyJob;
    private boolean isApplyForJob;
    private int contractorID;
    public void initialize( Job job,  int freelancerID ){
        // TODO
        this.jobID = job.getJobID();
        this.freelancerID = freelancerID;
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
        Message message = new Message(        
                textareaMsg.getText(),
                LocalDateTime.now(),
                freelancerID,
                jobID              
        );
      AddRecord.setDbRecord(message, MESSAGE );
      
      
      if( isApplyForJob ){
          
          Assignment assignment = new Assignment(                  
               contractorID,
               freelancerID,
               jobID 
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
    
    
    
}
