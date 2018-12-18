/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.AddRecord;
import DBConnection.DBConnection;
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
    private RadioButton radbtnShowInterest;
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
    
    public void initialize( int jobID,  int freelancerID ){
        // TODO
        this.jobID = jobID;
        this.freelancerID = freelancerID;
    }    

    @FXML
    private void radbtnLeaveMsgHandler(ActionEvent event) throws IOException {
        
        textareaMsg.setDisable(false);
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
    }
    
    
    
}
