/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dinesh
 */
public class ResponseJobPostController implements Initializable {

    @FXML
    private AnchorPane paneMainResponse;
    @FXML
    private RadioButton radbtnShowInterest;
    @FXML
    private ToggleGroup responsetoJobGroup;
    @FXML
    private RadioButton radbtnLeaveMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void radbtnLeaveMsgHandler(ActionEvent event) {
    }
    
}
