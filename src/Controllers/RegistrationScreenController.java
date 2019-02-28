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
import DBConnection.UpdateRecord;
import Model.Contact;
import Model.Contractor;
import Model.Freelancer;
import Model.User;
import Model.UserAccount;
import Validation.Validation;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.VBox;
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
    private TextField txtState;
    @FXML
    private TextField txtCountry;
    @FXML
    private Button btnSignup;
   // private CheckBox chkBoxContractor;
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
    private Label spinnerLabel;
    @FXML
    private Label spinnerLabel1;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private DatePicker datePickerDOB;
    @FXML
    private HBox textArea;
    
    
    @FXML
    private Label lblTitle;
    /**lblTitle
     * Initializes the controller class.
     */
    private ObservableList<String> freelancerExperienceList;
    private ObservableList<String> ContractorTypeList;
    //private Object obj;
    private User user;
    private UserAccount userAccount;
    private Contact contact;
    private boolean isNewUser;
    private boolean isUpdate;
    private int userType;
    public final int  FREELANCER = 1;
    public final int  CONTRACTOR = 0;
    private UpdateRecord record;
    private Freelancer freelancer;
    private Contractor contractor;
    @FXML
    private ToggleGroup userGroup;
    private HBox hzBoxPayrate;
    @FXML
    private TextField txtAmountCharge;
    private HBox hzBoxUserType;
    @FXML
    private HBox hzBoxAmountCharge;
    @FXML
    private VBox vBoxUser;
    @FXML
    private Label lblUserRequired;
    @FXML
    private ComboBox<String> cmbBoxState;
    private ObservableList<String> stateList;
    @FXML
    private TextField txtAreaCode;
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        // TODO
       userAccount = new UserAccount();
       
       stateList =  FXCollections.observableArrayList(
        "Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana",
        "Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska",
        "Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island",
        "South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming");
       
       cmbBoxState.setItems(stateList);
            
       freelancerExperienceList = FXCollections.observableArrayList();
       freelancerExperienceList.add("less than 1 year");
       freelancerExperienceList.add("1 year");
       freelancerExperienceList.add("1 - 5 years");
       freelancerExperienceList.add("more than 5 years");
         
         
       ContractorTypeList = FXCollections.observableArrayList();
       ContractorTypeList.add("Indivitual");
       ContractorTypeList.add("Organization");
       ContractorTypeList.add("Government");
         
        hzBoxContracotorType.setVisible(false);
        conn = new DBConnection();
        textArea.setVisible(false);
        hzBoxAmountCharge.setVisible(false);
        isNewUser = false;
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
         boolean  isInputValid = getInput();
         
         if( isInputValid ){
           if( !isUpdate ){
                isNewUser  = true;         
                Stage stage;
                Parent root;

                stage = (Stage) btnSignup.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AccountCreationScreen.fxml"));
                root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

                AccountCreationScreenController controller =  loader.getController();
                
             
                controller.setNewUser(isNewUser,userType, user, contact);
                
           }else if( isUpdate ){
                Stage stage;
                Parent root;

                stage = (Stage) btnSignup.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainScreen.fxml"));
                root = loader.load();
                
                MainScreenController controller = loader.getController();        
                controller.setLoginInfo(userAccount);
                
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
           }
         }else{
             
         }
            
        
    }
    
    
//    private Timestamp  toTimeStamp(LocalDate localDate){
//                
//       return Timestamp.valueOf(localDate.atStartOfDay());
//    }
   
    
    
    public boolean getInput()  {        
        
        //get textField inuput
        boolean isValid = true;
      
         ObservableList<TextField> list = FXCollections.observableArrayList();
         ObservableList<TextField> listClear = FXCollections.observableArrayList();
      
    //    SimpleDateFormat dobFormat = new SimpleDateFormat("MM/dd/YYYY");
        String firstName = "";
        String lastName = "";
        String stAddress = "";
        String apt = "";
        String city = "";
        String zip = "";
        String state = "";
        String country = "";
        String phone = "";
        String email = "";
        
        
            
            firstName = txtFirstName.getText().trim();
            if( firstName.isEmpty() ||  Validation.isStringHasAnumber( firstName ) || Validation.isStringLetters( firstName ) ){
                list.add(txtFirstName);
                
                if(  Validation.isStringHasAnumber( firstName ) || Validation.isStringLetters( firstName ) ){
                    alert("First Name should not have special characters or numbers","Input Error","",AlertType.ERROR);
                }
                     
            }else{
                
                listClear.add(txtFirstName);
            }
            
            
            lastName = txtLastName.getText().trim();
            if( lastName.isEmpty() ||  Validation.isStringHasAnumber( lastName ) || Validation.isStringLetters( lastName ) ){
                list.add(txtLastName);
                
               if(  Validation.isStringHasAnumber( lastName ) || Validation.isStringLetters( lastName ) ){
                    alert("Last Name should not have special characters or numbers","Input Error","",AlertType.ERROR);
                }
               
            }else{
                listClear.add(txtLastName);
            }
            
            stAddress = txtStaddress.getText().trim();
            if( stAddress.isEmpty()){
                list.add(txtStaddress);
               
            }else{
                listClear.add(txtStaddress);
            }
            
            apt = txtApt.getText().trim();
            
            
            city = txtCity.getText().trim();
            if( city.isEmpty() || Validation.isStringHasAnumber(city)){
                list.add(txtCity);
                  if( Validation.isStringHasAnumber(city)){
                      if( alert("Does your city name include a number in it?","City name confirmation","", AlertType.CONFIRMATION) ){
                         list.remove(txtCity);
                      }
                  }
            }else{
                listClear.add(txtCity);
            }
            
            zip = txtZip.getText().trim();
            if( zip.isEmpty()){
                list.add(txtZip);
                //txtZip.setStyle("-fx-border-color:red");   
            }else{
                listClear.add(txtZip);
            }
            
            state = cmbBoxState.getValue();
            
            country = txtCountry.getText().trim();
            if(  country.isEmpty() || Validation.isStringHasAnumber(country)){
                list.add(txtCountry);
                if( Validation.isStringHasAnumber(country) ){
                      alert("Country Name should not have special characters or numbers","Input Error","",AlertType.ERROR);
                }
            }else{
                listClear.add(txtCountry);
            }
            
            String areacode =  txtAreaCode.getText().trim();
            
             if(!( areacode.length() == 3 && Validation.isStringAnumber(areacode))){
                    list.add(txtAreaCode);
                    alert("Area code should have three numbers and no letters or special characters","Input Error","", AlertType.ERROR);
                }
            
             
            phone = txtPhoneNumber.getText().trim();
            if( ! (phone.length() == 7 && Validation.isStringAnumber(phone))){
                 list.add(txtPhoneNumber);
                 alert("Phone should have seven numbers and no letters or special characters","Input Error","", AlertType.ERROR);
            }else{
                listClear.add(txtPhoneNumber);
            }
            
            email = txtEmail.getText().trim();
            if(  email.isEmpty() || !Validation.isValidEmail(email) ){
                list.add(txtEmail);
                
                if( !Validation.isValidEmail(email)){
                    alert("The Email address you entered is not valid","Invalid Email address","", AlertType.ERROR);
                }
               // txtEmail.setStyle("-fx-border-color:red");   
            }else{
                listClear.add(txtEmail);
            }
            
            
//            if( txtFirstName.getText().isEmpty() ||   txtLastName.getText().isEmpty() || txtStaddress.getText().isEmpty()||
//                txtCity.getText().isEmpty() || txtZip.getText().isEmpty() || txtState.getText().isEmpty() || txtCountry.getText().isEmpty() ||
//                txtPhoneNumber.getText().isEmpty() || txtEmail.getText().isEmpty() ){
//                
//                isValid = false;
//              }
           LocalDate DOB = null;     
            try{
            
             DOB = datePickerDOB.getValue();  
            
                if( DOB == null){
                   datePickerDOB.setStyle("-fx-border-color:red");
                   isValid = false;
                }else{
                   datePickerDOB.setStyle("-fx-border-color:transparent");
                }
          
//         DOB = dobFormat.parse(dateInput.getDayOfMonth() + "/" + dateInput.getDayOfWeek() + "/" + dateInput.getYear());
      
             }catch(Exception e){
                  e.printStackTrace();
                
              }
          
            
            
           if(  isValid ){
            contact = new Contact(
                stAddress,
                apt,
                city,
                zip,       
                state,
                country,
                areacode + phone,
                email
            );
            
           }  
       
        
    
        if( radBtnContractor.isSelected() ){    
                       
           lblUserRequired.setVisible(false);
           vBoxUser.setStyle("-fx-border-color:transparent");
           
       //  System.out.println("Testing setUser()" + Boolean.toString(setUserTest(address)));//for testing
          //ObservableList jobList = FXCollections.observableArrayList();
          
          //create contractor object
          
          String typeOfContractor = spinner.getValue();
          
          if( isValid ){
                  user = new Contractor( 
                    firstName,
                    lastName, 
                    DOB, 
                    contact,                   
                    typeOfContractor
               );  
          }         
                              
                //user = contractor;
                userType = CONTRACTOR;
                 
            
        // setDbRecord( contact , contractor , "contractor"); 
//          System.out.println("Testing Contractor:" + Boolean.toString(ContractorTest(contractor)));
        } 
      
      
     
       if( radBtnFreelancer.isSelected() ){              

                lblUserRequired.setVisible(false);
                vBoxUser.setStyle("-fx-border-color:transparent");
                String selfDescription = txtAreaDescription.getText().trim();;
                String yearsOfExperience =   spinner.getValue();
                String amountCharge = txtAmountCharge.getText().trim();;
                int amount = 0;
               
              if( selfDescription.isEmpty() ){
                    
                    isValid = false;
                    txtAreaDescription.setStyle("-fx-border-color:red");
                }
             
              if( amountCharge.isEmpty()){
                  isValid = false;
                  list.add(txtAmountCharge);
              }else{
              
                    try{

                        amount = Integer.parseInt(amountCharge);
                          }catch( NumberFormatException e){
                              list.add(txtAmountCharge);
                              isValid = false;
                              alert("Input Error","Amount must be a number","",AlertType.ERROR);
                      }
              }

               


                if( isValid ){
                    user = new Freelancer( 
                      firstName,
                      lastName, 
                      DOB, 
                      contact, 
                      yearsOfExperience,  
                      selfDescription,
                      amount
                  );  
                }
               
            
           // obj = freelancer;
            userType = FREELANCER;
           //setDbRecord( contact , freelancer , "freelancer");    
        }
     
       if(!isUpdate){    
            if( (!radBtnContractor.isSelected() && !radBtnFreelancer.isSelected())){
                     isValid = false;
                     vBoxUser.setStyle("-fx-border-color:red");
                     lblUserRequired.setVisible(true);

             }
       }
       
        if( !list.isEmpty()){
               isValid = false;
         }else{
            if( radBtnFreelancer.isSelected() ){
                 if( !(txtAreaDescription.getText().isEmpty() && txtAmountCharge.getText().isEmpty())){
                     isValid = true;
                 }
            }else if( radBtnContractor.isSelected() ){
                isValid = true;
            }
            
         }
           
        for(int i = 0; i < list.size(); i++ ){
               list.get(i).setStyle("-fx-border-color:red");
        }
       
        for(int i = 0; i < listClear.size(); i++ ){
            listClear.get(i).setStyle( "-fx-border-color:transparent");
        }
        list.clear();
       if( isUpdate ){        
                contact.setContactId(this.user.getContact().getContactId());
                this.user.setFirstName(firstName);
                this.user.setLastName(lastName);
                this.user.setContact(contact);           
                this.user.setDOB(DOB);
                   
           //try{           
                if( user.getUserAccount().getUserType() == CONTRACTOR ){
                contractor = ( Contractor)user;
                this.contractor.setTypeOfContractor(spinner.getValue());
                userType = this.contractor.getUserAccount().getUserType();
                }else{
            //    UpdateRecord.setUpdateRecord( user, userType );
            
           //}catch(Exception e){
//                contact.setContactId(this.freelancer.getContact().getContactId());
//                this.freelancer.setFirstName(firstName);
//                this.freelancer.setLastName(lastName);
//                this.freelancer.setContact(contact);           
//                this.freelancer.setDOB(DOB);
                this.freelancer.setYearsOfExperince(spinner.getValue());
                this.freelancer.setSelfDescription(txtAreaDescription.getText());
                this.freelancer.setAmountCharge(Integer.parseInt(txtAmountCharge.getText()));
                userType = this.freelancer.getUserAccount().getUserType();
               // UpdateRecord.setUpdateRecord( freelancer, userType );
           }
            UpdateRecord.setUpdateRecord( user, userType );
            UpdateRecord.setUpdateContactRecord(contact); //update Database record for the contact
       }
       
       return isValid;
    }

//    private int setAddress(Contact contact){   //Inserting address
//        
//        
//         try{
//             String query = "";
//                if( !isUpdate ){
//                  query = "INSERT INTO Contact( streetAddress,apt,city,state,zip,country,phone,email)" + 
//                                            "VALUES( ? , ? , ? ,? ,? , ? ,?, ? );";
//                }else{
//                  // query = "UPDATE SET"  
//                }
//                                                
//                 PreparedStatement ps = conn.insertRecord(query);
//                                ps.setString( 1, contact.getStreetAddress());
//                                ps.setString( 2, contact.getApt());
//                                ps.setString( 3, contact.getCity());
//                                ps.setString( 4, contact.getState());
//                                ps.setString( 5, contact.getZip());
//                                ps.setString( 6, contact.getCountry());
//                                ps.setString( 7, contact.getPhone());
//                                ps.setString( 8, contact.getEmail());
//                                ps.execute();
//                                
//                                //conn.closeDBConnection();
//                }catch(SQLException e){
//                    e.printStackTrace();
//                }
//    
//                //conn.connectDatabase();
//                conn.setStatement("select LAST_INSERT_ID();");
//                ResultSet sqlResult  = conn.getStatement();
//                
//                int addressID = 0;
//                try{
//                while( sqlResult.next() ){
//                    addressID = sqlResult.getInt("LAST_INSERT_ID()");
//                }
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//                   System.out.println( "Address ID:" + addressID );
//                return addressID;
//    }
    
//    private void setDbRecord( Contact address, Object obj, String objType ){
//        
//        
//        conn.connectDatabase();     
//       
//            int contactID = setAddress( address );
//            System.out.println( "Fk key ID:" + contactID );
//        switch( objType ){
//            
//            case "contractor": 
//                try{
//                Contractor contractor = ( Contractor )obj;
//                
//                String query = "";
//                if( ! isUpdate ){
//                    query = "INSERT INTO Contractor(firstName,lastName,DOB,contractorType,contactID)" + 
//                                            "VALUES( ? , ? , ? ,? ,? );";
//                }else{
//                    //update query
//                }
//                                                
//                 PreparedStatement ps = conn.insertRecord(query);
//                                ps.setString( 1, contractor.getFirstName());
//                                ps.setString( 2, contractor.getLastName());
//                                ps.setTimestamp(3, toTimeStamp(contractor.getDOB()));
//                                ps.setString( 4, contractor.getTypeOfContractor());
//                                ps.setInt( 5, contactID);
//                                ps.execute();
//                 conn.closeDBConnection();                    
//                }catch(SQLException e){
//                    e.printStackTrace();
//                }
//                break;
//            case "freelancer":
//                try{
//                Freelancer freelancer = ( Freelancer )obj;
//                
//                String query = "";
//                if( ! isUpdate ){
//                        query = "INSERT INTO Freelancer(firstName,lastName,DOB,yearsOfExperience,selfDescription,contactID)" + 
//                                            "VALUES( ? , ? , ? ,? ,? , ? );";
//                }else{
//                    //update query
//                }
//                                                
//                 PreparedStatement ps = conn.insertRecord(query);
//                                ps.setString( 1, freelancer.getFirstName());
//                                ps.setString( 2, freelancer.getLastName());
//                                ps.setTimestamp(3,toTimeStamp( freelancer.getDOB()));
//                                ps.setString(4, freelancer.getYearsOfExperince());
//                                ps.setString(5, freelancer.getSelfDescription());
//                                ps.setInt( 6, contactID);
//                                ps.execute();
//                 conn.closeDBConnection();       
//                }catch( SQLException e){
//                    e.printStackTrace();
//                }
//               
//        }
//        
//        
//        
//    }
    
    @FXML
    private void radBtnContractorHandler(MouseEvent event) {
        
         lblUserRequired.setVisible(false);
         hzBoxContracotorType.setVisible(true);
         hzBoxAmountCharge.setVisible(false);
         textArea.setVisible(false);
         spinnerLabel.setText("Type Of Contractor:");
                
         
         SpinnerValueFactory<String> valueFactory = 
                 new  SpinnerValueFactory.ListSpinnerValueFactory<String>(ContractorTypeList);
         
         valueFactory.setValue(ContractorTypeList.get(0));         
         spinner.setValueFactory(valueFactory);
         
         
    
    }
    
    
 
    @FXML
    private void radBtnFreelancerHandler(MouseEvent event) {
        
         lblUserRequired.setVisible(false);
         hzBoxAmountCharge.setVisible(true);
         textArea.setVisible(true);
         spinnerLabel.setText("Experience:");    
         
         
         SpinnerValueFactory<String> valueFactory = 
                 new  SpinnerValueFactory.ListSpinnerValueFactory<String>(freelancerExperienceList);
         
         valueFactory.setValue(freelancerExperienceList.get(0));         
         spinner.setValueFactory(valueFactory);
    }   
       
  //---------for testing purpose------------------------------------------    
//   private boolean setUserTest(Contact address){
//            
//      
//         return (address.getStreetAddress().equals("4919 Coldwater") &&
//         address.getApt().equals("1") &&              
//         address.getCity().equals( "Sherman Oaks") &&     
//         address.getZip().equals("91423")&&         
//         address.getState().equals("CA") &&         
//         address.getCountry().equals("USA"));
//                
//   }
//   
//   private boolean ContractorTest(Contractor contractor){
//            
//      
//         return (contractor.getFirstName().equals("Dinesh") &&
//                 contractor.getLastName().equals("gamage") &&
//                 contractor.getTypeOfContractor().equalsIgnoreCase("Indivitual") &&
//                 contractor.getContact().getPhone().equalsIgnoreCase("818") &&
//                 contractor.getContact().getEmail().equalsIgnoreCase("d@d.com")&&
//                 contractor.getContact().getStreetAddress().equalsIgnoreCase("4919 coldwater")) &&
//                 contractor.getContact().getApt().equals("1") &&       
//                 contractor.getContact().getCity().equals( "Sherman Oaks") &&     
//                 contractor.getContact().getZip().equals("91423") &&     
//                 contractor.getContact().getState().equals("CA") && 
//                 contractor.getContact().getCountry().equals("USA");
//                 
//    }    

    public void setUpdate(boolean isUpdate, UserAccount userAccount) {
        
      
       this.userAccount.setUserID(userAccount.getUserID());
       this.userAccount.setUserType(userAccount.getUserType());
       this.userAccount.setUsername(userAccount.getUsername());
       this.userAccount.setPassword(userAccount.getPassword());
       this.isUpdate = isUpdate;
       
        if( isUpdate ){  
           lblTitle.setText("Update Your Record");
        }
        
        
        // record= new UpdateRecord();        
         User user = UpdateRecord.getUpdateRecord(userAccount.getUsername());    
         setUpdateFields(user);
        
    }

   
//    private void getUpdateRecord(String username){
//        
//       int userID = 0; 
//       int userType = -1;
//       conn.connectDatabase();     
//       String query = "select userID,userType,username from User where username="+ "'" + username + "'";       
//       conn.setStatement( query );
//       ResultSet sqlResult = conn.getStatement();
//       
//       try{
//            while( ! sqlResult.next()){
//                userID = sqlResult.getInt("userID");
//                userType = sqlResult.getInt("userType");
//            }
//        }catch(SQLException e){
//
//         }
//       
//       
//       if( userType == FREELANCER ){
//                Contact contact = null;
//           
//                try{
//                      query = "select * from contact, freelancer where contact.contactID = freelancer.contactID";
//                      conn.setStatement(query);
//                      sqlResult = conn.getStatement(); 
//
//                      while( !sqlResult.next()){
//                             contact = new Contact(
//                              sqlResult.getString("streetAddress"),
//                              sqlResult.getString("apt"),
//                              sqlResult.getString("city"),
//                              sqlResult.getString("zip"), 
//                              sqlResult.getString("state"), 
//                              sqlResult.getString("country"),                             
//                              sqlResult.getString("phone"), 
//                              sqlResult.getString("email")
//                           );
//                      }
//                 }catch( SQLException sql){
//
//                 }
//
//
//                 Freelancer freelancer = null;   
//                 try{
//                     query = "select * from user, freelancer where user.userID = freelancer.userID";
//                     conn.setStatement(query);
//                     sqlResult = conn.getStatement();               
//
//                      while( !sqlResult.next()){
//                              freelancer = new Freelancer(
//                              sqlResult.getString("firstName"),
//                              sqlResult.getString("lastName"),
//                              sqlResult.getTimestamp("DOB").toLocalDateTime().toLocalDate(),
//                              contact,
//                              sqlResult.getString("yearsOfExperience"),
//                              sqlResult.getString("selfDescription")
//
//                            );          
//
//                      }
//                 }catch(SQLException e){
//
//                 }
//       
//       }else{
//           
//       }       
//       
//       
//    }
//    
   public void setUpdateFields( User user ){
             radBtnContractor.setVisible(false);
             radBtnFreelancer.setVisible(false);
             hzBoxContracotorType.setVisible(true);
             
             
      // try{            
             
             if( user.getUserAccount().getUserType() == FREELANCER ) {
             
             txtFirstName.setText(user.getFirstName());
             txtLastName.setText(user.getLastName());
             txtStaddress.setText(user.getContact().getStreetAddress());
             txtApt.setText(user.getContact().getApt());
             txtCity.setText(user.getContact().getCity());
             txtZip.setText(user.getContact().getZip());
             cmbBoxState.setValue(user.getContact().getState());
             txtCountry.setText(user.getContact().getCountry());
           
             String phoneNumber = user.getContact().getPhone().substring(3);
             String areaCode = user.getContact().getPhone().substring(0, 3);
             txtAreaCode.setText(areaCode);
             txtPhoneNumber.setText(phoneNumber);
             txtEmail.setText(user.getContact().getEmail());
             datePickerDOB.setValue(user.getDOB());             
             
             freelancer = (Freelancer)user;
             
             spinnerLabel.setText("Experience:");
             SpinnerValueFactory<String> valueFactory = 
             new  SpinnerValueFactory.ListSpinnerValueFactory<String>(freelancerExperienceList);

             int i = 0;
             while( i < freelancerExperienceList.size() && ! freelancerExperienceList.get(i).equals( freelancer.getYearsOfExperince())){
                     i++ ;
             }

             
             valueFactory.setValue(freelancerExperienceList.get(i));                 
             spinner.setValueFactory(valueFactory);
             
             textArea.setVisible(true);
             freelancer = (Freelancer)user;
             txtAreaDescription.setText(freelancer.getSelfDescription());
             
             txtAmountCharge.setVisible(true);
             txtAmountCharge.setText(Integer.toString(freelancer.getAmountCharge()));
             hzBoxAmountCharge.setVisible(true);
             this.user = user;
         
      }else{
       //catch(ClassCastException e){
            // contractor = (Contractor)obj;
             
             txtFirstName.setText(user.getFirstName());
             txtLastName.setText(user.getLastName());
             txtStaddress.setText(user.getContact().getStreetAddress());
             txtApt.setText(user.getContact().getApt());
             txtCity.setText(user.getContact().getCity());
             txtZip.setText(user.getContact().getZip());
             cmbBoxState.setValue(user.getContact().getState());
             txtCountry.setText(user.getContact().getCountry());
             
             String phoneNumber = user.getContact().getPhone().substring(3);
             String areaCode = user.getContact().getPhone().substring(0, 3);
             txtAreaCode.setText(areaCode);
             txtPhoneNumber.setText(phoneNumber);
             
             txtEmail.setText(user.getContact().getEmail());
             datePickerDOB.setValue(user.getDOB());
             
             contractor = (Contractor)user;
             
              spinnerLabel.setText("Type Of Contractor:");
             SpinnerValueFactory<String> valueFactory = 
             new  SpinnerValueFactory.ListSpinnerValueFactory<String>(ContractorTypeList);

             int i = 0;
             while( i < ContractorTypeList.size() &&  !ContractorTypeList.get(i).equals( contractor.getTypeOfContractor())){
                     i++ ;
             }

             
             valueFactory.setValue(ContractorTypeList.get(i));                 
             spinner.setValueFactory(valueFactory);
             this.user = user;
              
             
         }  
       
   }

  
      private boolean alert(String message, String title,String header, AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        if( alertType.equals(AlertType.CONFIRMATION )){
             alert.getButtonTypes().add(ButtonType.NO);
             alert.getButtonTypes().remove(ButtonType.CANCEL);
        }
        
        boolean isConfirmed = false;
        Optional<ButtonType> result = alert.showAndWait();
        if( result.get() == ButtonType.OK ){
            isConfirmed = true;
        }
            
        return isConfirmed;   
    }
   
    
}