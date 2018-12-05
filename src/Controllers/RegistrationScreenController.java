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
import Model.Contact;
import Model.Contractor;
import Model.Freelancer;
import java.io.IOException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
    private Spinner<String> spinner;
    @FXML
    private HBox hzBoxContracotorType;
    
    @FXML
    private ToggleGroup user;
    @FXML
    private Label spinnerLabel;
    @FXML
    private Label spinnerLabel1;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private DatePicker datePickerDOB;
    @FXML
    private HBox textArea;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        // TODO
          
        
        hzBoxContracotorType.setVisible(false);
        conn = new DBConnection();
        textArea.setVisible(false);
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
        
         Stage stage;
         Parent root;
         
         stage = (Stage) btnSignup.getScene().getWindow();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AccountCreationScreen.fxml"));
         root = loader.load();
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.centerOnScreen();
         stage.show();
         
    
        
    }
    
    
    private Timestamp  toTimeStamp(LocalDate localDate){
                
       return Timestamp.valueOf(localDate.atStartOfDay());
    }
    
    public void setUser(){
        
        
        //get textField inuput
        SimpleDateFormat dobFormat = new SimpleDateFormat("MM/dd/YYYY");
        
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        
        LocalDate DOB = null;
        
        try{
         DOB = datePickerDOB.getValue();
         System.out.println( "Date of Birth:" + DOB );
//         DOB = dobFormat.parse(dateInput.getDayOfMonth() + "/" + dateInput.getDayOfWeek() + "/" + dateInput.getYear());
      
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
        //get DoB
        
        
        Contact contact = null;
        contact = new Contact(
                stAddress,
                apt,
                city,
                zip,       
                state,
                country,
                phone,
                email
            );
        
        if( radBtnContractor.isSelected()){           
      
       //  System.out.println("Testing setUser()" + Boolean.toString(setUserTest(address)));//for testing
          //ObservableList jobList = FXCollections.observableArrayList();
          
          //create contractor object
          
            Contractor contractor = new Contractor( 
                  firstName,
                  lastName, 
                  DOB, 
                  contact,                   
                  spinner.getValue()
             );  
         
          
         setDbRecord( contact , contractor , "contractor"); 
//          System.out.println("Testing Contractor:" + Boolean.toString(ContractorTest(contractor)));
        }
      
       if( radBtnFreelancer.isSelected()){
           
           String selfDescription = txtAreaDescription.getText();
           String yearsOfExperience =   spinner.getValue();
           
            
           
            Freelancer freelancer = new Freelancer( 
                 firstName,
                 lastName, 
                 DOB, 
                 contact, 
                 yearsOfExperience,  
                 selfDescription
             );  
         
         
           setDbRecord( contact , freelancer , "freelancer");    
        }
    }
    
    private int setAddress(Contact address){   //Inserting address
        
        
         try{
                             
                String query = "INSERT INTO Contact( streetAddress,apt,city,state,zip,country,phone,email)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? ,?, ? );";
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, address.getStreetAddress());
                                ps.setString( 2, address.getApt());
                                ps.setString( 3, address.getCity());
                                ps.setString( 4, address.getState());
                                ps.setString( 5, address.getZip());
                                ps.setString( 6, address.getCountry());
                                ps.setString( 7, address.getPhone());
                                ps.setString( 8, address.getEmail());
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
    
    private void setDbRecord( Contact address, Object obj, String objType ){
        
        
        conn.connectDatabase();     
       
            int contactID = setAddress( address );
            System.out.println( "Fk key ID:" + contactID );
        switch( objType ){
            
            case "contractor": 
                try{
                Contractor contractor = ( Contractor )obj;
                
                String query = "INSERT INTO Contractor(firstName,lastName,DOB,contractorType,contactID)" + 
                                            "VALUES( ? , ? , ? ,? ,? );";
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, contractor.getFirstName());
                                ps.setString( 2, contractor.getLastName());
                                ps.setTimestamp(3, toTimeStamp(contractor.getDOB()));
                                ps.setString( 4, contractor.getTypeOfContractor());
                                ps.setInt( 5, contactID);
                                ps.execute();
                 conn.closeDBConnection();                    
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
            case "freelancer":
                try{
                Freelancer freelancer = ( Freelancer )obj;
                
                String query = "INSERT INTO Freelancer(firstName,lastName,DOB,yearsOfExperience,selfDescription,contactID)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? );";
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, freelancer.getFirstName());
                                ps.setString( 2, freelancer.getLastName());
                                ps.setTimestamp(3,toTimeStamp( freelancer.getDOB()));
                                ps.setString(4, freelancer.getYearsOfExperince());
                                ps.setString(5, freelancer.getSelfDescription());
                                ps.setInt( 6, contactID);
                                ps.execute();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    e.printStackTrace();
                }
               
        }
        
        
        
    }
    
    @FXML
    private void radBtnContractorHandler(MouseEvent event) {
        
         hzBoxContracotorType.setVisible(true);
         textArea.setVisible(false);
         spinnerLabel.setText("Type Of Contractor:");
         ObservableList<String> ContractorTypeList = FXCollections.observableArrayList();
         ContractorTypeList.add("Indivitual");
         ContractorTypeList.add("Organization");
         ContractorTypeList.add("Government");
        
         
         SpinnerValueFactory<String> valueFactory = 
                 new  SpinnerValueFactory.ListSpinnerValueFactory<String>(ContractorTypeList);
         
         valueFactory.setValue(ContractorTypeList.get(0));         
         spinner.setValueFactory(valueFactory);
         
         
    
    }
    
    
 
    @FXML
    private void radBtnFreelancerHandler(MouseEvent event) {
        
         hzBoxContracotorType.setVisible(true);
         textArea.setVisible(true);
         spinnerLabel.setText("Experience:");
         
         ObservableList<String> freelancerExperienceList = FXCollections.observableArrayList();
         
         freelancerExperienceList.add("less than 1 year");
         freelancerExperienceList.add("1 year");
         freelancerExperienceList.add("1 - 5 years");
         freelancerExperienceList.add("more than 5 years");
         
         SpinnerValueFactory<String> valueFactory = 
                 new  SpinnerValueFactory.ListSpinnerValueFactory<String>(freelancerExperienceList);
         
         valueFactory.setValue(freelancerExperienceList.get(0));         
         spinner.setValueFactory(valueFactory);
    }   
       
  //---------for testing purpose------------------------------------------    
   private boolean setUserTest(Contact address){
            
      
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
