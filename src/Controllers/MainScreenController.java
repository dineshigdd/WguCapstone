/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.AddRecord;
import DBConnection.DBConnection;
import DBConnection.DeleteRecord;
import DBConnection.SearchRecord;
import Model.Job;
import Model.UserAccount;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dinesh
 */
public class MainScreenController implements Initializable {

    @FXML
    private MenuItem updateMenuItem;
    @FXML
    private MenuButton settings;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private ToggleGroup searchCategory;
    @FXML
    private RadioButton radBtnDate;
    @FXML
    private RadioButton radBtnTitle;
    @FXML
    private HBox searchHzBox;
    @FXML
    private AnchorPane searchPane;
    
    
    
    @FXML
    private Tab tabSearchResult;
    @FXML
    private TableView<Job> tableViewJob;
    @FXML
    private TableView<Job> tableViewJobPosted;
    @FXML
    private Tab tabAllJobs;
    @FXML
    private TableColumn<Job, String> colJobTitle;
    @FXML
    private TableColumn<Job, String> colJobDescription;
    @FXML
    private TableColumn<Job,String> colJobType;
    @FXML
    private TableColumn<Job, String> colJobPostdate;
    @FXML
    private TextField txtJobTitle;
    @FXML
    private RadioButton radbtnRemote;
    @FXML
    private RadioButton radbtnOnsite;
    @FXML
    private Button btnSubmit;
    @FXML
    private ToggleGroup jobCategory;
    @FXML
    private RadioButton radbtnHybrid;
    @FXML
    private TextArea txtAreaDescription;
    
    private final int  JOB = 3;
    @FXML
    private TableColumn<Job, String> colAllJobTitle;
    @FXML
    private TableColumn<?, ?> colAllJobDescription;
    @FXML
    private TableColumn<?, ?> colAllJobCategory;
    @FXML
    private TableColumn<?, ?> colAllJobPostDate;
    @FXML
    private RadioButton radBtnCategory;
    @FXML
    private TabPane tabPaneFreelancer;
  
    @FXML
    private TabPane mainTabPane;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Tab tabContractor;
    @FXML
    private Tab tabFreelancer;
    
    
     private String username;
    private String password;
            
    /**
     * Initializes the controller class.
     */
    
    
    
    private static final int  FREELANCER = 1;
    private  static final int  CONTRACTOR = 0;
    private DatePicker datepicker;
    private TextField txtSearch;
    private HBox hbox;
    private Button btnSearch;
    private String criteria;
    private LocalDate postDate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           hbox = new HBox();           
           hbox.setSpacing(15);
           hbox.setLayoutX(250);
           hbox.setLayoutY(170);    
           searchPane.getChildren().add(hbox);
           btnSearch = new Button("Search");
           datepicker = new DatePicker();
           txtSearch = new TextField();        
           
          
           searchJob();
           
     }    

    
    
    @FXML
    private void updateMenuItemHandler(ActionEvent event) throws IOException {
        
        boolean isUpdate = true;
         Stage stage = null;
         Parent root;
         stage = (Stage) settings.getScene().getWindow();       
       
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RegistrationScreen.fxml"));
         root = loader.load();
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.centerOnScreen();
         stage.show();
         
         RegistrationScreenController controller = loader.getController();         
         controller.setUpdate(isUpdate, username);
         
        
    }       
    
    
    @FXML
    private void deleteMenuItemHandler(ActionEvent event) throws IOException, SQLException {
        
        //prompt for password
        UserAccount useraccount = new UserAccount();
        useraccount.setUsername(this.username);
       if( isPasswordConfirmed("Please Enter Your Password", "Password Confirmation","Password confirmation required") ){
                
            
             useraccount.setPassword(password);
             
             if( alert("Are you sure you want delete your account", "Delete confirmation","Deleting your account",AlertType.CONFIRMATION)){
                     DeleteRecord.deleteUserAccount(useraccount);
                     alert("Your account has been removed from our system","Account Deletion","",AlertType.INFORMATION);
             }
            
         }
    }
    
     void setLoginInfo(String username , String password) {
        this.username = username;
        this.password = password;
        int userType = -1;
       
        try {
        DBConnection conn = new DBConnection();
        conn.connectDatabase();
        conn.setStatement("select userType from user where username =" + "'" + username + "'");
        ResultSet sqlResult = conn.getStatement();
        while( sqlResult.next()){
              userType = sqlResult.getInt("userType");
        }
      
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        if( userType == FREELANCER ){
            mainTabPane.getTabs().remove(tabContractor);
        }else{
            mainTabPane.getTabs().remove(tabFreelancer);
        }
    }
     
     
     private boolean isPasswordConfirmed(String message, String title,String header){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(message);
        
        Optional<String> result = dialog.showAndWait();
        return result.get().equals(this.password);
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
    private void radbtnDateHandler(ActionEvent event) throws IOException {
        
         if( hbox.getChildren().isEmpty()){
             hbox.getChildren().add(datepicker);
             hbox.getChildren().add(btnSearch);
         }else if( hbox.getChildren().get(0).equals(txtSearch)){
             hbox.getChildren().remove(0);
             hbox.getChildren().add(0,datepicker);            
         }
         criteria = "jobPostDate";
    }

    @FXML
    private void radbtnTitleHandler(ActionEvent event) {
        
        
        if( hbox.getChildren().isEmpty()){
             hbox.getChildren().add(btnSearch);
             hbox.getChildren().add(0,txtSearch );
        }else if( hbox.getChildren().get(0).equals(datepicker) ){
             hbox.getChildren().remove(0);
             hbox.getChildren().add(0,txtSearch );
        }   
        
         criteria = "jobTitle";
        
    }

    @FXML
    private void radbtnCategoryHandler(ActionEvent event) throws IOException {
        if( hbox.getChildren().isEmpty()){          
             hbox.getChildren().add(btnSearch);
             hbox.getChildren().add(0,txtSearch );
        }else if( hbox.getChildren().get(0).equals(datepicker) ){
             hbox.getChildren().remove(0);
             hbox.getChildren().add(0,txtSearch );
        } 
        
         criteria = "jobCategory";
         Stage stage = null;
         Parent root;
         stage = (Stage) settings.getScene().getWindow();       
       
        
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/updatePostScreen.fxml"));
//         root = loader.load();
//         
//         Scene scene = new Scene(root);
//         stage.setScene(scene);
//         stage.centerOnScreen();
//         stage.show();
//         
//         RegistrationScreenController controller = loader.getController();         
//         controller.setUpdate(isUpdate, username);
//        ObservableList<Job> jobList = FXCollections.observableArrayList();
//        jobList.add(tableViewJobPosted.getItems().get(tableViewJobPosted.getItems().get));
    }
     
    //find job by posted date
    
   private void searchJob(){
       
       
        //get Input        
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //get input
                ObservableList<Job> jobList = null;
                
                if( criteria.equals("jobPostDate")){                   
                     
                    jobList = SearchRecord.searchRecord( criteria , datepicker.getValue().toString());               
                }else{                  
              
                   
                    jobList = SearchRecord.searchRecord( criteria , txtSearch.getText() );
                }
                
                 colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
                 colJobDescription.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));
                 colJobType.setCellValueFactory(new PropertyValueFactory<>("jobCategory"));
                 colJobPostdate.setCellValueFactory(new PropertyValueFactory<>("postDate"));
                 tableViewJob.setItems(jobList);
                 
                 tabPaneFreelancer.getSelectionModel().selectNext();
                
            }
        });
        
        
            
     
   } 

    @FXML
    private void btnSubmitHandler(ActionEvent event) {       
        
        String jobCategory;
        
        
        if( radbtnRemote.isSelected() ){
            jobCategory = radbtnRemote.getText().toLowerCase();
        }else if( radbtnOnsite.isSelected()){
            jobCategory = radbtnOnsite.getText().toLowerCase();
        }else{
            jobCategory = radbtnHybrid.getText().toLowerCase();
        }
        
         int userID = getUserID();

         //get contractorID          
        int contractorID = getUserTypeID("contractorID","Contractor",userID);     

        Job job = new Job(
                txtJobTitle.getText(),
                txtAreaDescription.getText(),
                jobCategory,       
                LocalDateTime.now()
        );
        
        job.setJobPostedBy(contractorID);
        AddRecord.setDbRecord(job, JOB);
    }

    @FXML
    private void tabAllJobsHandler(Event event) {
        
        ObservableList<Job> jobList = SearchRecord.searchRecord("", "*");
                 colAllJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
              //   colAllJobTitle.setCellFactory(TextFieldTableCell.forTableColumn());               
                 colAllJobDescription.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));
                 colAllJobCategory.setCellValueFactory(new PropertyValueFactory<>("jobCategory"));
                 colAllJobPostDate.setCellValueFactory(new PropertyValueFactory<>("postDate"));
                 tableViewJobPosted.setItems(jobList);
    }

    @FXML
    private void TableViewEditDataHandler(MouseEvent event) throws IOException {
        
         Stage stage = null;
         Parent root;
         stage = (Stage) tableViewJobPosted.getScene().getWindow();       
       
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ManagePostScreen.fxml"));
         root = loader.load();
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.centerOnScreen();
         stage.show();
         
         Job job = tableViewJobPosted.getSelectionModel().getSelectedItem();
         ManagePostScreenController controller = loader.getController();         
         controller.setJob(job);
//        colAllJobTitle.setOnEditCommit( 
//                         new EventHandler<CellEditEvent<Job,String>>() {  
//                            @Override
//                            public void handle(CellEditEvent<Job, String> event) {
//                               ((Job) event.getTableView().getItems().get(
//                                       event.getTablePosition().getRow())
//                                       ).setJobTitle(event.getNewValue());
//                                    }                                
//                        });
    }

    @FXML
    private void tableViewJobHandler(MouseEvent event) throws IOException {
        
       
       int jobID = tableViewJob.getSelectionModel().getSelectedItem().getJobID();
       int jobPostedBy = tableViewJob.getSelectionModel().getSelectedItem().getJobPostedBy();
       
       int userID;
       
       userID = getUserID();
       int freelancerID = getUserTypeID("freelancerID","Freelancer",userID);
      
       
       Stage response = new Stage();
       Parent root;
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ResponseJobPost.fxml"));
       

        Scene scene = new Scene( loader.load());
        response.setScene(scene); 
        response.setTitle("Response to job posted");
        response.centerOnScreen();
        response.show();
        
        ResponseJobPostController controller = loader.getController();
        controller.initialize( jobID, freelancerID );
    }

   
    private int getUserID(){
         String query = "select userID, username from User where userName ="+ "'" + username + "'";       
       
         
         //get userID
         int userID = 0;
         try{
               DBConnection conn = new DBConnection();
               conn.connectDatabase();
               conn.setStatement(query);
               ResultSet sqlResult = conn.getStatement();
            while( sqlResult.next()){
                userID = sqlResult.getInt("userID");
            }
            
              conn.closeDBConnection();
         }catch(SQLException e){}
         
        
        return userID;
    }
    
  private int getUserTypeID(String userCategoryID, String userCategory , int userID ){
       String query;
       int userCategoryId = 0;
       
       query = "select " + userCategoryID  + "," +  userCategory + ".userID from " + userCategory + ", User where " +
               userCategory + ".userID = User.userID and User.userID = " + userID;
       
       System.out.println(query);
       
       try{
               DBConnection conn = new DBConnection();
               conn.connectDatabase();
               conn.setStatement(query);
               ResultSet sqlResult = conn.getStatement();
               
            while( sqlResult.next()){
                userCategoryId = sqlResult.getInt(userCategoryID);
            }
            
              conn.closeDBConnection();
         }catch(SQLException e){}
       
       return userCategoryId;
      
  }
    
}
