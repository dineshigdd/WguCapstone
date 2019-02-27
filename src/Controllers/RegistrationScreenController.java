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
               
            }else{
                listClear.add(txtEmail);
            }
            
            

           LocalDate DOB = null;     
            try{
            
             DOB = datePickerDOB.getValue();  
            
                if( DOB == null){
                   datePickerDOB.setStyle("-fx-border-color:red");
                   isValid = false;
                }else{
                   datePickerDOB.setStyle("-fx-border-color:transparent");
                }
          

      
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
                phone,
                email
            );
            
           }  
       
        
    
        if( radBtnContractor.isSelected() ){    
                       
           lblUserRequired.setVisible(false);
           vBoxUser.setStyle("-fx-border-color:transparent");
           
       
          
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
                              
               
                userType = CONTRACTOR;                 
            
        
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
               
            
          
            userType = FREELANCER;
            
        }
     
            
       if( (!radBtnContractor.isSelected() && !radBtnFreelancer.isSelected())){
                isValid = false;
                vBoxUser.setStyle("-fx-border-color:red");
                lblUserRequired.setVisible(true);

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
                   
                      
                if( user.getUserAccount().getUserType() == CONTRACTOR ){
                contractor = ( Contractor)user;
                this.contractor.setTypeOfContractor(spinner.getValue());
                userType = this.contractor.getUserAccount().getUserType();
                }else{
          
                this.freelancer.setYearsOfExperince(spinner.getValue());
                this.freelancer.setSelfDescription(txtAreaDescription.getText());
                this.freelancer.setAmountCharge(Integer.parseInt(txtAmountCharge.getText()));
                userType = this.freelancer.getUserAccount().getUserType();
               
           }
            UpdateRecord.setUpdateRecord( user, userType );
            UpdateRecord.setUpdateContactRecord(contact); //update Database record for the contact
       }
       
       return isValid;
    }

    
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
       
  

    public void setUpdate(boolean isUpdate, String username) {
      
       this.isUpdate = isUpdate;
       
        if( isUpdate ){  
           lblTitle.setText("Update Your Record");
        }
        
        
     
         User user = UpdateRecord.getUpdateRecord(username);    
         setUpdateFields(user);
        
    }

   

   public void setUpdateFields( User user ){
             radBtnContractor.setVisible(false);
             radBtnFreelancer.setVisible(false);
             hzBoxContracotorType.setVisible(true);
             
             
                  
             
             if( user.getUserAccount().getUserType() == FREELANCER ) {
             
             txtFirstName.setText(user.getFirstName());
             txtLastName.setText(user.getLastName());
             txtStaddress.setText(user.getContact().getStreetAddress());
             txtApt.setText(user.getContact().getApt());
             txtCity.setText(user.getContact().getCity());
             txtZip.setText(user.getContact().getZip());
             txtState.setText(user.getContact().getState());
             txtCountry.setText(user.getContact().getCountry());
             txtPhoneNumber.setText(user.getContact().getPhone());
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
      
             
             txtFirstName.setText(user.getFirstName());
             txtLastName.setText(user.getLastName());
             txtStaddress.setText(user.getContact().getStreetAddress());
             txtApt.setText(user.getContact().getApt());
             txtCity.setText(user.getContact().getCity());
             txtZip.setText(user.getContact().getZip());
             txtState.setText(user.getContact().getState());
             txtCountry.setText(user.getContact().getCountry());
             txtPhoneNumber.setText(user.getContact().getPhone());
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