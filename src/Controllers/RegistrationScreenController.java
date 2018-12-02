/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Dinesh
 */
import DBConnection.DBConnection;
import Model.Address;
import Model.Contractor;
import java.io.IOException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class RegistrationScreenController implements Initializable {
    private DBConnection conn;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtStaddress;
    @FXML
    private TextField txtApt;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtZip;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtCountry;
    @FXML
    private Button btnSignup;
    private CheckBox chkBoxContractor;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private RadioButton radBtnFreelancer;
    @FXML
    private RadioButton radBtnContractor;
    @FXML
    private Spinner<String> contractTypeSpinner;
    @FXML
    private HBox hzBoxContracotorType;
    @FXML
    private TextField txtDOB;
    @FXML
    private ToggleGroup user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        // TODO
          
        
        hzBoxContracotorType.setVisible(false);
        conn = new DBConnection();
        
    }    
    
    @FXML
    private void chageCursor(MouseEvent event) {         

        Scene scene = btnSignup.getScene();
        scene.setCursor(Cursor.HAND);
      
        
    }
    
     @FXML
    private void setDefaultMousePointer(MouseEvent event) {
        
        Scene scene = btnSignup.getScene();
        scene.setCursor(Cursor.DEFAULT);
    }
    
    @FXML
    private void btnSignupHandler(ActionEvent event) throws IOException {
        setUser();
        
    }
    
    
    
    public void setUser(){
        
        
        //get textField inuput
        SimpleDateFormat dobFormat = new SimpleDateFormat("MM/dd/YYYY");
        
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        
        Date DOB = new Date();
        try{
         DOB = dobFormat.parse(txtDOB.getText());
        }catch(Exception e){
           e.printStackTrace();
        }
        
        String stAddress = txtStaddress.getText();
        String apt = txtApt.getText();
        String city = txtCity.getText();
        String zip = txtZip.getText();
        String state = txtState.getText();
        String country = txtCountry.getText();
        String phone = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        
        Address address = null;
        if( radBtnContractor.isSelected()){
           
             address = new Address(
                stAddress,
                apt,
                city,
                zip,       
                state,
                country
            );
         
         System.out.println("Testing setUser()" + Boolean.toString(setUserTest(address)));//for testing
          //ObservableList jobList = FXCollections.observableArrayList();
          
          //create contractor object
          Contractor contractor = new Contractor( 
                firstName,
                lastName, 
                DOB, 
                address,  
                phone,
                email,
                contractTypeSpinner.getValue()
           );  
         
          
          setDbRecord( address , contractor , "contractor"); 
//          System.out.println("Testing Contractor:" + Boolean.toString(ContractorTest(contractor)));
        }
      
    }
    
    private int setAddress(Address address){   //Inserting address
        
        
         try{
                             
                String query = "INSERT INTO Address( streetAddress,apt,city,state,zip,country )" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? );";
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, address.getStreetAddress());
                                ps.setString( 2, address.getApt());
                                ps.setString( 3, address.getCity());
                                ps.setString( 4, address.getState());
                                ps.setString( 5, address.getZip());
                                ps.setString( 6, address.getCountry());
                                ps.execute();
                                
                                //conn.closeDBConnection();
                }catch(SQLException e){
                    e.printStackTrace();
                }
    
                //conn.connectDatabase();
                conn.setStatement("select LAST_INSERT_ID();");
                ResultSet sqlResult  = conn.getStatement();
                
                int addressID = 0;
                try{
                while( sqlResult.next() ){
                    addressID = sqlResult.getInt("LAST_INSERT_ID()");
                }
                }catch(Exception e){
                    e.printStackTrace();
                }
                   System.out.println( "Address ID:" + addressID );
                return addressID;
    }
    
    private void setDbRecord( Address address, Object obj, String objType ){
        conn.connectDatabase();     
       
        int addressID = setAddress( address );
            System.out.println( "Fk key ID:" + addressID );
        switch( objType ){
            
            case "contractor": 
                try{
                Contractor contractor = ( Contractor )obj;
                
                String query = "INSERT INTO Contractor(firstName,lastName,contractorType,phoneNumber,email,addressID)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? );";
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, contractor.getFirstName());
                                ps.setString( 2, contractor.getLastName());
                                ps.setString( 3, contractor.getTypeOfContractor());
                                ps.setString( 4, contractor.getPhone());
                                ps.setString( 5, contractor.getEmail());
                                ps.setInt( 6, addressID);
                                ps.execute();
                 conn.closeDBConnection();                    
                }catch(SQLException e){
                    e.printStackTrace();
                }

               
        }
        
        
        
    }
    
    @FXML
    private void radBtnContractorHandler(MouseEvent event) {
        
         hzBoxContracotorType.setVisible(true);
         ObservableList<String> ContractorTypeList = FXCollections.observableArrayList();
         ContractorTypeList.add("Indivitual");
         ContractorTypeList.add("Organization");
         ContractorTypeList.add("Government");
        
         
         SpinnerValueFactory<String> valueFactory = 
                 new  SpinnerValueFactory.ListSpinnerValueFactory<String>(ContractorTypeList);
         
         valueFactory.setValue(ContractorTypeList.get(0));         
         contractTypeSpinner.setValueFactory(valueFactory);
         
         
    
    }
    
 
    @FXML
    private void radBtnFreelancerHandler(MouseEvent event) {
        
         hzBoxContracotorType.setVisible(false);
    }   
       
  //---------for testing purpose------------------------------------------    
   private boolean setUserTest(Address address){
            
      
         return (address.getStreetAddress().equals("4919 Coldwater") &&
         address.getApt().equals("1") &&              
         address.getCity().equals( "Sherman Oaks") &&     
         address.getZip().equals("91423")&&         
         address.getState().equals("CA") &&         
         address.getCountry().equals("USA"));
                
   }
   
   private boolean ContractorTest(Contractor contractor){
            
      
         return (contractor.getFirstName().equals("Dinesh") &&
                 contractor.getLastName().equals("gamage") &&
                 contractor.getTypeOfContractor().equalsIgnoreCase("Indivitual") &&
                 contractor.getPhone().equalsIgnoreCase("818") &&
                 contractor.getEmail().equalsIgnoreCase("d@d.com")&&
                 contractor.getAddress().getStreetAddress().equalsIgnoreCase("4919 coldwater")) &&
                 contractor.getAddress().getApt().equals("1") &&       
                 contractor.getAddress().getCity().equals( "Sherman Oaks") &&     
                 contractor.getAddress().getZip().equals("91423") &&     
                 contractor.getAddress().getState().equals("CA") && 
                 contractor.getAddress().getCountry().equals("USA");
                 
    }    

   

    
   
}
