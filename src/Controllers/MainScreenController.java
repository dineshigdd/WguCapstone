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
import DBConnection.UpdateRecord;
import Model.Assignment;
import Model.Contractor;
import Model.Freelancer;
import Model.FreelancerLanguage;
import Model.Inbox;
import Model.Job;
import Model.Message;
import Model.PrgmLanguage;
import Model.SavedFreelancer;
import Model.User;
import Model.UserAccount;
import Reports.Report;
import Validation.Validation;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
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
    
    
    @FXML
    private TableColumn<?, ?> colAllJobTitle;
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
    
    
    
    /**
     * Initializes the controller class.
     */
    
    
   
    @FXML
    private HBox searchHzBoxContractor;
    @FXML
    private ToggleGroup searchCategory1;
    @FXML
    private HBox searchHzBoxFreelancer;
    @FXML
    private RadioButton radBtnSkill;
    @FXML
    private RadioButton radBtnExperience;
     @FXML
    private RadioButton radBtnCity;
    @FXML
    private Tab tabAddSkills;      
    @FXML
    private Button btnRightArrow;
    @FXML
    private Button btnLeftArrow;
    @FXML
    private ListView<String> ListallPrgmLanguages;
    @FXML
    private ListView<String> ListselectedPrgmLanguages; 
    @FXML
    private Button btnAddSkills;  
    @FXML
    private TextArea textAreaOtherTech;
    @FXML
    private TextArea textAreaNonTech;
    @FXML
    private Tab tabFreelancerSearchResult;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colExperience;
    @FXML
    private TableView<Freelancer> tableViewFreelancer;
    @FXML
    private TabPane tabPaneContractor;
    @FXML
    private TableColumn<Freelancer, String> colFreelancerFullName;
    @FXML
    private Button btnInviteFreelancer;
    @FXML
    private Tab tabPostJob;
    @FXML
    private RadioButton radBtnAllFreelancers;
   
    
//    private String username;
//    private String password;       
    
       
    
    @FXML
    private Tab tabSearch;  
    @FXML
    private ButtonBar searchResultHzBar;
   
    private static final int  FREELANCER = 1;
    private  static final int  CONTRACTOR = 0;
    private static final int  JOB = 3;
    public final static int APPLIED_FREELANCER = 1;
    public final static int INVITED_FREELANCER = 2;
    public final static int JOB_ASSIGNED_FREELANCER = 3;
    ObservableList<PrgmLanguage> prgmLanguageList;
    ObservableList<PrgmLanguage> selectedLanguageList; 
    ObservableList<Freelancer> freelancerList;
    private HashMap <Integer,String> languageMap;
    private HashMap<Integer,Integer> jobMap; 
    private HashMap<Integer,Integer> savedFreelancerMap;
    private HashMap<Integer,Integer> appliedFreelancerMap;
    private HashMap<Integer,Integer> invitedFreelancerMap;
    private DatePicker datepicker;
    private TextField txtSearch;
    private HBox hbox;
    private Button btnSearch;
    private String criteria;
    private LocalDate postDate;
    private ComboBox comboBox;
    private ComboBox comboBoxLanguage;
    
    private boolean isInviteFreelancer;
    private Assignment assignment;
    private UserAccount userAccount;
    private int jobID;
    private int freelancerID;
    
    @FXML
    private Button btnSave;
   // private boolean isAssignedJob;
    @FXML
    private ListView<String> listSavedFreelancer;
    @FXML
    private ListView<String> listAppliedFreelancer;
    @FXML
    private ListView<String> listInvitedFreelancer;
    @FXML
    private Tab tabAssignJob;
    @FXML
    private ComboBox<String> cmbBoxJob;
    @FXML
    private Label lblFreelancer;
    @FXML
    private Button btnOk;
    private boolean isSavedFreelancer;
    @FXML
    private RadioButton radBtnShowAllJobs;
    @FXML
    private Label lblJobInfo;
    @FXML
    private AnchorPane anchorPaneJobAssigned;
    private CheckBox chkBox;
    private boolean isCheckBoxAdded;
    private boolean isCheckBoxRemoved;
    @FXML
    private HBox hzBoxJob;
    @FXML
    private Button btnAllFreelancerContractor;
    @FXML
    private GridPane gridpaneJobPost;
    @FXML
    private AnchorPane anchorPanePostJob;
    private ComboBox comboBoxInvite;
    private ComboBox comboBoxJobPost;
    private VBox vbox;
    @FXML
    private RadioButton radBtnNewJobPost;
    @FXML
    private RadioButton radBtnInvite;
    @FXML
    private ToggleGroup jobPostCategory;
    @FXML
    private Tab tabReport;
    @FXML
    private Button btnAllFreelancerJobCount;

    private ComboBox comboBoxCategory;
    @FXML
    private Tab tabInbox;
    private WebView webview;
    private TextArea msgTextArea;
    @FXML
    private TableView<Inbox> tableviewInbox;
    @FXML
    private TableColumn<Inbox,String> colInboxJob;
    @FXML
    private TableColumn<Inbox, String> colInboxName;
    @FXML
    private TableColumn<Inbox, String> colInboxMsg;
    private TextArea contractorMsgTextaarea;
    @FXML
    private TextArea contractorMsgTextArea;
    @FXML
    private Button btnSend;
    private Job job;
    private User user;
    private String message;
    @FXML
    private TextArea textAreaJobPostMessage;
    @FXML
    private Label lblMessage;
    @FXML
    private GridPane gridpaneJobPost2;
    private Label labelForInvite;
    private Button btngridpaneJobPost2Submit;
   
  
  
  
 
             
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//           hbox = new HBox();           
//           hbox.setSpacing(15);
//           hbox.setLayoutX(250);
//           hbox.setLayoutY(170);    
//           searchPane.getChildren().add(hbox);
           
                   
           userAccount = new UserAccount();
          
         
           ObservableList<String> list = FXCollections.observableArrayList(
              "less than 1 year",
              "1 year",
              "1 - 5 years",
              "more than 5 years");
           
           comboBox = new ComboBox(list);
           comboBox.setPrefWidth(300);
           comboBox.setPromptText("Select Years Of Experience");
           
           
           
           int size =  SearchRecord.searchLanguage("all", "*").size();
           ObservableList<String> listLanguage = FXCollections.observableArrayList();      
            
           for( int i = 0 ; i < size ; i++ ){
                 listLanguage.add( SearchRecord.searchLanguage("all", "*").get(i).getProgLanguage());
                 
           }      
           comboBoxLanguage = new ComboBox(listLanguage);
           comboBoxLanguage.setPrefWidth(300);
           comboBoxLanguage.setPromptText("Select Programming Language");
           
            ObservableList<String> categoryList = FXCollections.observableArrayList(
              "onsite",
              "remote",
              "hybrid");
           
           comboBoxCategory = new ComboBox(categoryList);
           comboBoxCategory.setPrefWidth(300);
           comboBoxCategory.setPromptText("Select category");
           
           datepicker = new DatePicker(); 
           datepicker.setPrefWidth(300);
           btnSearch = new Button("Search");    
           txtSearch = new TextField();  
           txtSearch.setPrefWidth(300);
           criteria = null;          
           
           
           
           updateMenuItem.setStyle("-fx-padding:2 60 2 60");        
           deleteMenuItem.setStyle("-fx-padding:2 60 2 60");
         
    
           
           gridpaneJobPost.getChildren().remove(btnSubmit);
           gridpaneJobPost.getChildren().remove(lblMessage);
           gridpaneJobPost.getChildren().remove(textAreaJobPostMessage);
           gridpaneJobPost.add(btnSubmit, 1, 3 );
           
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("MyGui.fxml"));
//           Parent root = (Parent)loader.load();
//           MainScreenController controller = (MainScreenController)loader.getController();
//            controller.setStageAndSetupListeners(stage); // 
           
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
         controller.setUpdate(isUpdate, userAccount.getUsername());
         
        
    }       
    
    
    @FXML
    private void deleteMenuItemHandler(ActionEvent event) throws IOException, SQLException {
        
        //prompt for password
        UserAccount useraccount = new UserAccount();
        useraccount.setUsername(this.userAccount.getUsername());
       if( isPasswordConfirmed("Please Enter Your Password", "Password Confirmation","Password confirmation required") ){
                
            
             useraccount.setPassword(this.userAccount.getPassword());
             
             if( alert("Are you sure you want delete your account", "Delete confirmation","Deleting your account",AlertType.CONFIRMATION)){
                     DeleteRecord.deleteUserAccount(useraccount);
                     alert("Your account has been removed from our system","Account Deletion","",AlertType.INFORMATION);
             }
            
         }else{
           alert("Your password does not match with our records","Password mismatch","",AlertType.ERROR);
       }
    }
    
     public void setLoginInfo(UserAccount userAccount) {
//        this.username = username;
//        this.password = password;
        this.userAccount = userAccount;
        //userAccount.setPassword(password);
        int userType = -1;
       
        try {
        DBConnection conn = new DBConnection();
        conn.connectDatabase();
        conn.setStatement("select userType from user where username =" + "'" + userAccount.getUsername()+ "'");
        ResultSet sqlResult = conn.getStatement();
        while( sqlResult.next()){
              userType = sqlResult.getInt("userType");
        }
      
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.userAccount.setUserType(userType);
        
        if( userType == FREELANCER ){
            mainTabPane.getTabs().remove(tabContractor);
            searchHzBoxFreelancer.getChildren().add(txtSearch);
            searchHzBoxFreelancer.getChildren().add(btnSearch);
            searchJob();           
            setSkills();
        }else{
            mainTabPane.getTabs().remove(tabFreelancer);
          //  searchHzBoxContractor.getChildren().add(txtSearch);
            searchHzBoxContractor.getChildren().add(comboBoxLanguage);
            searchHzBoxContractor.getChildren().add(btnSearch);       
                      
        }         
        
          
    }
     
    
    private boolean isPasswordConfirmed(String message, String title,String header){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(message);
        
        Optional<String> result = dialog.showAndWait();
        return result.get().equals(userAccount.getPassword());
     }
     
     
      private boolean alert(String message, String title,String header, Alert.AlertType alertType){
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

//      private boolean alert(String message, String title,String header, Alert.AlertType alertType, ButtonType noBtn ){
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(header);
//        alert.setContentText(message);
//        alert.getButtonTypes().add(noBtn);
//        
//        
//        boolean isConfirmed = false;
//        Optional<ButtonType> result = alert.showAndWait();
//        if( result.get() == ButtonType.OK ){
//            isConfirmed = true;
//        }
//            
//        return isConfirmed;   
//    }
      
    @FXML
    private void radbtnDateHandler(ActionEvent event) throws IOException {
        
         if( searchHzBoxFreelancer.getChildren().isEmpty()){
             searchHzBoxFreelancer.getChildren().add(datepicker);
             searchHzBoxFreelancer.getChildren().add(btnSearch);
         }else if( searchHzBoxFreelancer.getChildren().get(0).equals(txtSearch) ||
                  searchHzBoxFreelancer.getChildren().get(0).equals(comboBoxCategory) ){
             searchHzBoxFreelancer.getChildren().remove(0);
             searchHzBoxFreelancer.getChildren().add(0,datepicker);            
         }
         criteria = "jobPostDate";
         
    }

    @FXML
    private void radbtnContractorTitleHandler(ActionEvent event) {
        
       
        if( searchHzBoxFreelancer.getChildren().isEmpty()){
             searchHzBoxFreelancer.getChildren().add(btnSearch);
             searchHzBoxFreelancer.getChildren().add(0,txtSearch );
        }else if( searchHzBoxFreelancer.getChildren().get(0).equals(datepicker) || 
                 searchHzBoxFreelancer.getChildren().get(0).equals(comboBoxCategory) ){
             searchHzBoxFreelancer.getChildren().remove(0);
             searchHzBoxFreelancer.getChildren().add(0,txtSearch );
        }   
        
         criteria = "jobTitle";
        
    }

    @FXML
    private void radbtnCategoryHandler(ActionEvent event) throws IOException {
        if( searchHzBoxFreelancer.getChildren().isEmpty()){          
             searchHzBoxFreelancer.getChildren().add(btnSearch);
             searchHzBoxFreelancer.getChildren().add(0,comboBoxCategory );
        }else if( searchHzBoxFreelancer.getChildren().get(0).equals(datepicker) || 
                 searchHzBoxFreelancer.getChildren().get(0).equals(txtSearch) ){
             searchHzBoxFreelancer.getChildren().remove(0);
             searchHzBoxFreelancer.getChildren().add(0,comboBoxCategory );
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
                try{
                        if( criteria.equals("jobPostDate")){            
                            jobList = SearchRecord.searchJob( criteria , datepicker.getValue().toString());               
                        }else if( criteria.equals("jobCategory")){
                            jobList =  SearchRecord.searchJob( criteria , comboBoxCategory.getValue().toString());
                        }else{              
                            if( !txtSearch.getText().isEmpty()){
                                jobList = SearchRecord.searchJob( criteria , txtSearch.getText() );
                            }else{
                                throw new Exception();
                            }
                        }

                         colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
                         colJobDescription.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));
                         colJobType.setCellValueFactory(new PropertyValueFactory<>("jobCategory"));
                         colJobPostdate.setCellValueFactory(new PropertyValueFactory<>("postDate"));
                         tableViewJob.setItems(jobList);


                         tabPaneFreelancer.getSelectionModel().selectNext();
                }catch(Exception ex){
                    alert("Please provide a search criteria","Search Criteria","Search Criteria",AlertType.INFORMATION);
                }
                
            }
        });
        
        
            
     
   } 

   private void searchFreelancer(){
              
        
//        if( criteria.equals("all")){
////            freelancerList  = SearchRecord.searchFreelancer( criteria , "");
//             
//        }
        //else{
            btnSearch.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                //get input
                try{
                    if( criteria.equals("yearsOfExperience")){
                          freelancerList  = SearchRecord.searchFreelancer( criteria , comboBox.getValue().toString() );
                    } else if( criteria.equals("progLanguage")){
                          freelancerList  = SearchRecord.searchFreelancer( criteria , comboBoxLanguage.getValue().toString());
                    } else {                
                          if(!txtSearch.getText().isEmpty()){                 
                              freelancerList  = SearchRecord.searchFreelancer( criteria ,txtSearch.getText() );
                          }else{
                              throw new Exception();
                          }
                    }
            
                    setTableViewFreelancer();
                    tabPaneContractor.getSelectionModel().selectNext();
                }catch(Exception x){
                    alert("Please provide a search criteria","Search Criteria","Search Criteria",AlertType.INFORMATION);
                }
                } 
               
            });
       
       // }
        
                   
                     
     
   } 
   
   private void setTableViewFreelancer(){
      colFreelancerFullName.setCellValueFactory(
                    cellData -> Bindings.concat(
                        cellData.getValue().getFirstName(),
                                " ", 
                        cellData.getValue().getLastName()));      

                     colDescription.setCellValueFactory(new PropertyValueFactory<>("selfDescription"));
                     colExperience.setCellValueFactory(new PropertyValueFactory<>("yearsOfExperince"));

                     tableViewFreelancer.setItems(freelancerList);       
   }
   
   

    @FXML
    private void tabAllJobsHandler(Event event) {
        int userID = getUserID();
        int contractorID = getUserTypeID("contractorID","Contractor",userID);  
        ObservableList<Job> jobList = SearchRecord.searchJob("all", Integer.toString(contractorID));
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
         
         try{
         Job job = tableViewJobPosted.getSelectionModel().getSelectedItem();
         ManagePostScreenController controller = loader.getController();         
         controller.setJob(job , userAccount);
         }catch(Exception e){
             alert("Error in Editing the job","","",AlertType.ERROR);
         }
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
        
       int userID;
       
       userID = getUserID();
       int freelancerID = getUserTypeID("freelancerID","Freelancer",userID);      
       User user = SearchRecord.searchFreelancer("freelancerNeed", String.valueOf(freelancerID )).get(0);
       
       Stage response = new Stage();
       Parent root;
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ResponseJobPost.fxml"));
       

        Scene scene = new Scene( loader.load());
        response.setScene(scene); 
        response.setTitle("Response to job posted");
        response.centerOnScreen();
        response.show();
        
        Job job = tableViewJob.getSelectionModel().getSelectedItem();
        ResponseJobPostController controller = loader.getController();
        controller.initialize( job, user );
    }

   
    private int getUserID(){
         String query = "select userID, username from User where userName ="+ "'" + userAccount.getUsername() + "'";       
       
         
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
    
    
//     private int getUserType( int userID ){
//         String query = "select userType from User where userName ="+ userID;       
//       
//         
//         //get userID
//         int userType = 0;
//         try{
//               DBConnection conn = new DBConnection();
//               conn.connectDatabase();
//               conn.setStatement(query);
//               ResultSet sqlResult = conn.getStatement();
//            while( sqlResult.next()){
//                userType = sqlResult.getInt("userType");
//            }
//            
//              conn.closeDBConnection();
//         }catch(SQLException e){}
//         
//        
//        return userType;
//    }
     
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

    @FXML
    private void radbtnSkillHandler(ActionEvent event) {        
        
        
        if( searchHzBoxContractor.getChildren().isEmpty()){
             searchHzBoxContractor.getChildren().add(comboBoxLanguage);
             //searchHzBoxContractor.getChildren().add(txtSearch);
             searchHzBoxContractor.getChildren().add(btnSearch);
         }else if( searchHzBoxContractor.getChildren().get(0).equals(txtSearch) || 
                 searchHzBoxContractor.getChildren().get(0).equals(comboBox)){             
             searchHzBoxContractor.getChildren().remove(0);
             searchHzBoxContractor.getChildren().add(0,comboBoxLanguage);
           //  searchHzBoxContractor.getChildren().add(0,txtSearch);            
         }        
        
        
        criteria = "progLanguage";
    }

    @FXML
    private void radbtnExperienceHandler(ActionEvent event) {
        
        if( searchHzBoxContractor.getChildren().isEmpty()){
             searchHzBoxContractor.getChildren().add(comboBox);
             searchHzBoxContractor.getChildren().add(btnSearch);
         }else if( searchHzBoxContractor.getChildren().get(0).equals(txtSearch) ||
                 searchHzBoxContractor.getChildren().get(0).equals(comboBoxLanguage)){
             searchHzBoxContractor.getChildren().remove(0);
             searchHzBoxContractor.getChildren().add(0,comboBox);            
         }
        criteria = "yearsOfExperience";
    }

    @FXML
    private void radbtnFreelancerCityHandler(ActionEvent event) {
        
        if( searchHzBoxContractor.getChildren().isEmpty()){
             searchHzBoxContractor.getChildren().add(txtSearch);
             searchHzBoxContractor.getChildren().add(btnSearch);
         }else if( searchHzBoxContractor.getChildren().get(0).equals(comboBox) || 
                 searchHzBoxContractor.getChildren().get(0).equals(comboBoxLanguage)){
             searchHzBoxContractor.getChildren().remove(0);
             searchHzBoxContractor.getChildren().add(0,txtSearch); 
         }
        criteria = "city";
    }

     @FXML
    private void radBtnAllFreelancersHandler(ActionEvent event) {
         if( !searchHzBoxContractor.getChildren().isEmpty()){
             searchHzBoxContractor.getChildren().remove(0, 2);
        }
       
        freelancerList  = SearchRecord.searchFreelancer( "all" , "");
        setTableViewFreelancer();
        tabPaneContractor.getSelectionModel().selectNext();
    }
    
    private void setSkills(){
       
        int  userID = getUserID();
        int freelancerID = getUserTypeID("freelancerID","Freelancer",userID);     
        selectedLanguageList = SearchRecord.searchLanguage("freelancerLanguages", Integer.toString(freelancerID));           
            
         prgmLanguageList = SearchRecord.searchLanguage("all", "*");    
         languageMap = new HashMap<>();
//         for(int i = 0; i < prgmLanguageList.size(); i++ ){
//                  languageMap.put(ListallPrgmLanguages.getItems().indexOf(ListallPrgmLanguages.getItems().get(i)),
//                          prgmLanguageList.get(i).getProgLanguage());
//         }
                  
       if( selectedLanguageList.isEmpty() ){            
            
            for(int i = 0; i < prgmLanguageList.size(); i++ ){
               ListallPrgmLanguages.getItems().add(prgmLanguageList.get(i).getProgLanguage());
            
            }                
            
        }else {
           
          // prgmLanguageList = SearchRecord.searchLanguage("all", "*"); 
           for(int i = 0; i < selectedLanguageList.size(); i++ ){
               ListselectedPrgmLanguages.getItems().add(selectedLanguageList.get(i).getProgLanguage());              
               
              
           }
           
                    
                  
           int temp;           
           
           for(int i = 0; i < prgmLanguageList.size(); i++ ){
                temp = prgmLanguageList.get(i).getProgLanguageID();   
                
                int r = 0;
                boolean isFound = false;
                while( r < selectedLanguageList.size() &&  !isFound ) {
                    if( temp != selectedLanguageList.get(r).getProgLanguageID() ){
                     r++;      
                    }else{                     
                            isFound = true;
                    }
                }
//               if( prgmLanguageList.get(i).getProgLanguageID() == selectedLanguageList.get(i).getProgLanguageID()){
//                   
//                   prgmLanguageList.remove(i);
//                }
                if( ! isFound )
                     ListallPrgmLanguages.getItems().add( prgmLanguageList.get(i).getProgLanguage());   

            }
       }
       
       
       //set other skills and non techical
       ObservableList<Freelancer> freelancerOtherSkills  = SearchRecord.searchFreelancer("otherSkills", Integer.toString(freelancerID));
       textAreaOtherTech.setText(freelancerOtherSkills.get(0).getOtherTechSkills());
       textAreaNonTech.setText(freelancerOtherSkills.get(0).getNonTechSkills());
       
    }
    
    
    @FXML
    private void tabAddSkillsHandler(Event event) {
        
        
    }

    @FXML
    private void btnRightArrowHandler(ActionEvent event) {             
            
            
        if( !ListallPrgmLanguages.getSelectionModel().isEmpty() ){
            
          int  userID = getUserID();
          int freelancerID = getUserTypeID("freelancerID","Freelancer",userID);
          FreelancerLanguage freelancerLanguage = new FreelancerLanguage();
                  
           for( int i = 0; i < prgmLanguageList.size(); i++ ){
                      
                     if( ListallPrgmLanguages.getSelectionModel().getSelectedItem().equals(prgmLanguageList.get(i).getProgLanguage())){
                            
                           freelancerLanguage.setProgLanguageID(prgmLanguageList.get(i).getProgLanguageID());
                     }
                }        
            
            freelancerLanguage.setFreelancerID(freelancerID);
            int isRecordAdded =   AddRecord.setDbRecord( freelancerLanguage, AddRecord.FREELANCER_PRGM_LANGUAGE);  
            
            ListselectedPrgmLanguages.getItems().add(       
                    ListallPrgmLanguages.getSelectionModel().getSelectedItem());
            
         
            ListallPrgmLanguages.getItems().remove(ListallPrgmLanguages.getSelectionModel().getSelectedIndex());
        }
        
        
       
         
    }

    @FXML
    private void btnLeftArrowHandler(ActionEvent event) {
        
        
    
        if(  !ListselectedPrgmLanguages.getSelectionModel().isEmpty() ){
            
          int  userID = getUserID();
          int freelancerID = getUserTypeID("freelancerID","Freelancer",userID);
          FreelancerLanguage freelancerLanguage = new FreelancerLanguage();
                  
           for( int i = 0; i < prgmLanguageList.size(); i++ ){
                      
                     if( ListselectedPrgmLanguages.getSelectionModel().getSelectedItem().equals(prgmLanguageList.get(i).getProgLanguage())){
                            
                           freelancerLanguage.setProgLanguageID(prgmLanguageList.get(i).getProgLanguageID());
                     }
                }        
            
            freelancerLanguage.setFreelancerID(freelancerID);           
            boolean isRecordDeleted =   DeleteRecord.deleteFreelancerLanguage(freelancerLanguage);   
            
            ListallPrgmLanguages.getItems().add(       
                        ListselectedPrgmLanguages.getSelectionModel().getSelectedItem());   

            ListselectedPrgmLanguages.getItems().remove(
                        ListselectedPrgmLanguages.getSelectionModel().getSelectedItem());       
        }
          
            
    }
    
   

    @FXML
    private void btnAddSkillsHandler(ActionEvent event) {
        
                   
        addOtherSkills( textAreaOtherTech.getText(), textAreaNonTech.getText());
        
    }

    
    private void addOtherSkills(String otherTech, String nonTech){
        try{
                DBConnection  conn = new DBConnection();
                conn.connectDatabase();   
                
                int userID = getUserID();
                freelancerID = getUserTypeID("freelancerID","Freelancer",userID);
                System.out.println("freelancer ID:"+ freelancerID);
                String query;
                query = "Update Freelancer set "
                        + "otherTechSkills = ?," 
                        + "nonTechSkills = ?"
                        + " where freelancerID = ?";
                                       
                                    
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, otherTech);
                                ps.setString( 2, nonTech);      
                                ps.setInt(3, freelancerID);
                                ps.executeUpdate();
                                
                 conn.closeDBConnection();    
                 
                }catch( SQLException e){
                }
    }

   
    @FXML
    private void tableViewFreelancerHandler(MouseEvent event) {
       
            if( !tableViewFreelancer.getItems().isEmpty()){
                int freelancerID = tableViewFreelancer.getSelectionModel().getSelectedItem().getFreelancerID();             
                assignment = new Assignment();        
                assignment.setFreelancerID(freelancerID);
            }else{
                alert("You must search for freelancers before make any selection","","",AlertType.INFORMATION);
            }

    }
   
    
    @FXML
    private void btnSubmitHandler(ActionEvent event) {       
        boolean isValidPost = true;
        String jobCategory ="";     
        
        
        
        int userID = getUserID();
        
         //get contractorID          
        int contractorID = getUserTypeID("contractorID","Contractor",userID);     
        
        String jobTitle =  txtJobTitle.getText().trim();
        if( jobTitle.isEmpty() ){
            alert( "Job Title is requird","Input Error","",AlertType.ERROR );
            isValidPost = false;
        }else if( Validation.isStringAnumber( jobTitle )){
            if( ! alert("Are you sure your job title is a number","Job Title Confirmation","",AlertType.CONFIRMATION)){                
                    txtJobTitle.setText("");
                    isValidPost = false;
             }else{
                isValidPost = true;
            }
        }
        
        
        
        if( radbtnRemote.isSelected() ){
            jobCategory = radbtnRemote.getText().toLowerCase();
        }else if( radbtnOnsite.isSelected()){
            jobCategory = radbtnOnsite.getText().toLowerCase(); 
        }else if( radbtnHybrid.isSelected()){
            jobCategory = radbtnHybrid.getText().toLowerCase();
        }else{
            alert("Please select the job category","Input Error","",AlertType.ERROR);
            isValidPost = false;
        }
        
       String description  = txtAreaDescription.getText();
       
       if( description.isEmpty()){
           alert("Job description is required","Input Error","",AlertType.ERROR );
           isValidPost = false;
       }
        
      String messageTofreelancer ="";
       if( isInviteFreelancer ){       
           messageTofreelancer = textAreaJobPostMessage.getText();
       if( messageTofreelancer.isEmpty()){
            alert("Message is required when you direct your job post to a specific freelancer","Input Error","",AlertType.ERROR );
            isValidPost = false;
       }
       }
       
        if( isValidPost ){
            Job job = new Job(
                    jobTitle,
                    description,
                    jobCategory,       
                    LocalDateTime.now()
            );

            job.setJobPostedBy(contractorID);
            int jobID ;
            jobID = AddRecord.setDbRecord(job, JOB);
            
            
            if( isInviteFreelancer ){
                    assignment.setContractorID(contractorID);       
                    assignment.setJobID(jobID);
                    assignment.setContractStatus(INVITED_FREELANCER);
                    assignment.setJobAssignedDate(null);
                    AddRecord.setDbRecord(assignment, AddRecord.ASSIGNMENT);
            
           
                    job.setJobID(jobID);
                    User user = tableViewFreelancer.getSelectionModel().getSelectedItem();

                    Message message  = new Message(
                             messageTofreelancer,
                             AddRecord.CONTRACTOR,
                             LocalDateTime.now(),
                             user,
                             job      
                    );

                    AddRecord.setDbRecord( message, AddRecord.MESSAGE );
                    
                    
                    isInviteFreelancer = false;
                    textAreaJobPostMessage.clear();
            }
            
          
            radBtnNewJobPost.isSelected();
            txtJobTitle.clear();
            radbtnRemote.selectedProperty().set(false);
            radbtnOnsite.selectedProperty().set(false);
            radbtnHybrid.selectedProperty().set(false);
            txtAreaDescription.clear();
            
            alert("Your job has been posted","","",AlertType.INFORMATION);
        }
    }
    
    
    @FXML
    private void btnInviteFreelancerHandler(ActionEvent event) {  
            
             tabPaneContractor.getSelectionModel().select(tabPostJob); 
             isInviteFreelancer = true;     
             
             gridpaneJobPost.getChildren().remove(btnSubmit);
             
             gridpaneJobPost.add(lblMessage, 0, 3);
             gridpaneJobPost.add(textAreaJobPostMessage, 1, 3);
             gridpaneJobPost.add(btnSubmit, 1,4);
             
             radBtnInvite.setDisable(false);
           
    }
    
    @FXML
    private void radBtnInviteHandler(MouseEvent event) {
        
        gridpaneJobPost.setVisible(false);
        gridpaneJobPost2.setVisible(true);
    
       
         int userID = getUserID();
         int contractorID = getUserTypeID("contractorID","Contractor",userID);          
             
            
             // btnSubmit = null;
             if( gridpaneJobPost2.getChildren().isEmpty() ){ 
                 labelForInvite = new Label("Choose the job to invite: ");        //    GridPane.setHalignment(label, HPos.RIGHT);   
                 comboBoxJobPost = new ComboBox();
                 comboBoxJobPost.setPromptText("Please select the Job post: ");
                 btngridpaneJobPost2Submit = new Button("Submit");
                 
            
                    gridpaneJobPost2.add(labelForInvite, 0, 0);
                    gridpaneJobPost2.add(comboBoxJobPost, 1, 0);
                    gridpaneJobPost2.add(lblMessage, 0, 1);
                                  
                   
                   gridpaneJobPost2.add(textAreaJobPostMessage, 1, 1);                   
                   btngridpaneJobPost2Submit.setPrefWidth(97);
                   btngridpaneJobPost2Submit.setPrefHeight(32);
                   GridPane.setHalignment(btngridpaneJobPost2Submit, HPos.RIGHT);       
                   gridpaneJobPost2.add(btngridpaneJobPost2Submit, 1, 2);     
                   
                   
             }

            
             ObservableList<Job> jobList = SearchRecord.searchJob("all", Integer.toString(contractorID ));
             jobMap = new HashMap();    
        
            for( int i = 0; i < jobList.size(); i++ ){
                jobMap.put( i , jobList.get(i).getJobID());
            }
            for(int i = 0; i < jobList.size(); i++ ){
                comboBoxJobPost.getItems().add( jobList.get(i).getJobTitle());
            }
        
            
            btngridpaneJobPost2Submit.setOnAction((e) -> {
                       try{
                            boolean isValidPost = true;
                            String message ="";
                            int jobID = 0;
                            
                             try{
                                    jobID = jobMap.get(comboBoxJobPost.getSelectionModel().getSelectedIndex()); 
                                 
                                }catch(NullPointerException ex){   
                                        alert("Please select the job to invite","Input Error","",AlertType.ERROR );
                                        isValidPost = false;
                                }
                              
                            
                            if( isInviteFreelancer ){                        
                                
                                message =  textAreaJobPostMessage.getText().trim();                                
                                
                                if( message.isEmpty()){
                                     alert("Message is required when you direct your job post to a specific freelancer","Input Error","",AlertType.ERROR );
                                     isValidPost = false;
                                }
                            }
                            
                            if( isValidPost ){
                                                                      
                                    assignment.setJobID(jobID);
                                    assignment.setContractorID(contractorID);
                                    assignment.setContractStatus(INVITED_FREELANCER);
                                    assignment.setJobAssignedDate(null);                            
                                    int freelancerID = tableViewFreelancer.getSelectionModel().getSelectedItem().getFreelancerID();
                                    assignment.setFreelancerID(freelancerID);
                                     
                                    int addStatus = AddRecord.setDbRecord(assignment, AddRecord.ASSIGNMENT);
                                    
                                    if( addStatus == AddRecord.ERROR ){
                                        alert("Error adding record. you may have already invited this freelancer for the selected job","","",AlertType.ERROR);
                                        isInviteFreelancer = false;
                                        tableViewFreelancer.getItems().clear();
                                    }else{
                                                                
                                        job = new Job();
                                        job.setJobID(jobID);
                                        User freelancer = tableViewFreelancer.getSelectionModel().getSelectedItem();

                                        Message messageForFreelancer  = new Message(
                                            message,
                                            AddRecord.CONTRACTOR,
                                            LocalDateTime.now(),
                                            freelancer,
                                            job      
                                         );

                                        AddRecord.setDbRecord(messageForFreelancer, AddRecord.MESSAGE); 
                                        
                                        alert("The freelancer has been invited for the job posted","","",AlertType.INFORMATION);
                                      
                                    }
                                    //resetting controls and variables
                                        tableViewFreelancer.getSelectionModel().clearSelection();
                                        assignment = null;
                                        comboBoxJobPost = null;
                                        isInviteFreelancer = false;
                                        btngridpaneJobPost2Submit = null;
                                        tableViewFreelancer.getItems().clear();
                                        gridpaneJobPost2.getChildren().clear();
                                        textAreaJobPostMessage.clear();
                                        gridpaneJobPost2.setVisible(false);                                        
                                        radBtnInvite.setDisable(true);
                                        radBtnNewJobPost.setSelected(true);
                                        gridpaneJobPost.setVisible(true);
                                        setbtnSubmitOnGridPane();
                            }
                       }catch(NullPointerException x){
                                x.printStackTrace();
                                isInviteFreelancer = false;
                                tableViewFreelancer.getItems().clear();

                         
                       }

                        
               });            
        
    }
    
    private void setbtnSubmitOnGridPane(){
    if( isInviteFreelancer ){         
            try{
             gridpaneJobPost.add(lblMessage, 0, 3);
             gridpaneJobPost.add(textAreaJobPostMessage, 1, 3);
             gridpaneJobPost.add(btnSubmit, 1,3);
            }catch(Exception e){
        
            gridpaneJobPost.getChildren().remove(btnSubmit);
            gridpaneJobPost.getChildren().remove(lblMessage);
            gridpaneJobPost.getChildren().remove(textAreaJobPostMessage);
            gridpaneJobPost.add(btnSubmit, 1, 3 );
            }
        }else{
            try{
            gridpaneJobPost.getChildren().remove(textAreaJobPostMessage);
            gridpaneJobPost.add(btnSubmit, 1, 3 );
            }catch(Exception e){}
        }
    }
    
    @FXML
    private void radBtnNewJobPostHandler(MouseEvent event) {
        
        gridpaneJobPost.setVisible(true);
        gridpaneJobPost2.setVisible(false);
        gridpaneJobPost2.getChildren().clear();
        textAreaJobPostMessage.clear();
        setbtnSubmitOnGridPane();    
            
            
    }

    
    
    @FXML
    private void tabSearchHandler(Event event) {
         
         searchFreelancer();
    }

    @FXML
    private void btnSaveHandler(ActionEvent event) {
        
        int freelancerID = tableViewFreelancer.getSelectionModel().getSelectedItem().getFreelancerID();
        int userID = getUserID();
        int contractorID = getUserTypeID("contractorID","Contractor",userID);      
        SavedFreelancer savedFreelancer = new SavedFreelancer();
        savedFreelancer.setContractorID(contractorID);
        savedFreelancer.setFreelancerID(freelancerID);
       
        int inserRecord =  AddRecord.setDbRecord(savedFreelancer , AddRecord.SAVED_FREELANCER);
        
        if( inserRecord == AddRecord.ERROR ){
            alert("You have already saved this freelancer", "Saving Freelancer","",AlertType.INFORMATION);
        }  
        
    }


    @FXML
    private void tabAssignJobHandler(Event event) {
        
        
        int userID = getUserID();
        int contractorID = getUserTypeID("contractorID","Contractor",userID); 
        
        //saved Freelancer
        freelancerList = SearchRecord.searchFreelancer("savedFreelancer", String.valueOf(contractorID));
        for( int i = 0 ; i < freelancerList.size(); i++ ){
            System.out.println( freelancerList.get(i).getFullName());
        }
        savedFreelancerMap = new HashMap();  
        
        chkBox = new CheckBox();
        chkBox.setText("Check if you need to assign a new job");
        chkBox.setLayoutX(40);
        chkBox.setLayoutY(360);
        isCheckBoxAdded = false;
        isCheckBoxRemoved = true;
        
                
        chkBox.setOnAction((chkevent) -> {
                      hzBoxJob.setLayoutX(0);
                      hzBoxJob.setLayoutY(407);
                   if( chkBox.isSelected() ){        
                       hzBoxJob.setVisible(true);
                      //anchorPaneJobAssigned.getChildren().add(hzBoxJob);
                 }
                 else{
                      hzBoxJob.setVisible(false);
                     //anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
                 }
            });
      
                
        for( int i = 0; i < freelancerList.size(); i++ ){
           savedFreelancerMap.put( i , freelancerList.get(i).getFreelancerID());
        }
       
        for(int i = 0; i < freelancerList.size(); i++ ){
                    boolean isIntheList = false;
                   int r = 0;
                   while( r < listSavedFreelancer.getItems().size()){
                        if ( listSavedFreelancer.getItems().get(r).equals(freelancerList.get(i).getFullName())){
                            isIntheList = true;
                        }
                            r++;
                            
                   }
                   if ( !isIntheList){
                        listSavedFreelancer.getItems().add(freelancerList.get(i).getFullName());
                   }
        }
                        
        
        
        
        //AppliedFreelancer
        freelancerList = SearchRecord.searchFreelancer("appliedFreelancer", String.valueOf(contractorID));
        appliedFreelancerMap = new HashMap(); 
        for( int i = 0; i < freelancerList.size(); i++ ){
           appliedFreelancerMap.put( i , freelancerList.get(i).getFreelancerID());
        }
        
        //applied freelancer
       for(int i = 0; i < freelancerList.size(); i++ ){
                    boolean isIntheList = false;
                   int r = 0;
                   while( r < listAppliedFreelancer.getItems().size()){
                        if ( listAppliedFreelancer.getItems().get(r).equals(freelancerList.get(i).getFullName())){
                            isIntheList = true;
                        }
                            r++;
                            
                   }
                   if ( !isIntheList){
                        listAppliedFreelancer.getItems().add(freelancerList.get(i).getFullName());
                   }
        }
        
        
        //Invited Freelancer
        freelancerList = SearchRecord.searchFreelancer("invitedFreelancer", String.valueOf(contractorID));
        invitedFreelancerMap = new HashMap(); 
        for( int i = 0; i < freelancerList.size(); i++ ){
           invitedFreelancerMap.put( i , freelancerList.get(i).getFreelancerID());
        }
        
        //invited list
         for(int i = 0; i < freelancerList.size(); i++ ){
                    boolean isIntheList = false;
                   int r = 0;
                   while( r < listInvitedFreelancer.getItems().size()){
                        if ( listInvitedFreelancer.getItems().get(r).equals(freelancerList.get(i).getFullName())){
                            isIntheList = true;
                        }
                            r++;
                            
                   }
                   if ( !isIntheList){
                        listInvitedFreelancer.getItems().add(freelancerList.get(i).getFullName());
                   }
        }
//        
         
        ObservableList<Job> jobList = SearchRecord.searchJob("all", Integer.toString(contractorID ));
        jobMap = new HashMap();    
        
        for( int i = 0; i < jobList.size(); i++ ){
            jobMap.put( i , jobList.get(i).getJobID());
        }
        for(int i = 0; i < jobList.size(); i++ ){
            cmbBoxJob.getItems().add( jobList.get(i).getJobTitle());
        }
        
    }

    @FXML
    private void listSavedFreelancerHandler(MouseEvent event) {
        
       
        
        if( ! listSavedFreelancer.getItems().isEmpty()){
              isSavedFreelancer = true;   
              hzBoxJob.setVisible(true);
//              anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
//              hzBoxJob.setLayoutY(382);
//              hzBoxJob.setLayoutX(0);
//              anchorPaneJobAssigned.getChildren().add(hzBoxJob); 
            if( isCheckBoxAdded ){
               isCheckBoxRemoved = anchorPaneJobAssigned.getChildren().remove(chkBox);
               isCheckBoxAdded = false;              
      
            }
                
            
            System.out.println(" isCheckBoxAdded save:"+ isCheckBoxAdded + " and " + "isCheckBoxRemoved:" + isCheckBoxRemoved);
            freelancerID = savedFreelancerMap.get(listSavedFreelancer.getSelectionModel().getSelectedIndex()); 
    //        ObservableList<Job> jobList  = SearchRecord.searchJob("jobApplied", Integer.toString(freelancerID));
    //        lblJobInfo.setText("Title:" + jobList.get(0).getJobTitle()+ "   " + "Category:" + jobList.get(0).getJobCategory());
            lblFreelancer.setText(" to " + listSavedFreelancer.getSelectionModel().getSelectedItem());
        }

    }
    
    @FXML
    private void listAppliedFreelancerHandler(MouseEvent event) {
        //gridPaneJob.getChildren().remove(hzBoxJob);
        chkBox.setSelected(false);
        hzBoxJob.setVisible(false);
        
//         anchorPaneJobAssigned.getChildren().remhzBoxJobove();
        if( ! listAppliedFreelancer.getItems().isEmpty()){
                isSavedFreelancer = false;
             
               
    //          
            if( isCheckBoxRemoved ){
                isCheckBoxAdded = anchorPaneJobAssigned.getChildren().add(chkBox);
                isCheckBoxRemoved = false;
               
//                hzBoxJob.setLayoutX(0);
//                hzBoxJob.setLayoutY(407);
              //  anchorPaneJobAssigned.getChildren().add(hzBoxJob)
            }
            
             if ( chkBox.isSelected()){
                 hzBoxJob.setVisible(true);
                 //anchorPaneJobAssigned.getChildren().add(hzBoxJob);
               }else{
               //  hzBoxJob.setVisible(true);                     
                 //anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
               }
            
//             chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
//                public void changed(ObservableValue ov,Boolean old_val, Boolean new_val) {
//                         if( chkBox.isSelected() ){
//                      anchorPaneJobAssigned.getChildren().add(hzBoxJob);
//                 }
//                 else{
//                     anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
//                 }
//                }
//            });
                   
             
//            chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
//                @Override
//                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                    
//                    System.out.println("chkBox.isSelected():"  +chkBox.isSelected());
////                                     if( chkBox.isSelected() ){
////                      anchorPaneJobAssigned.getChildren().add(hzBoxJob);
////                 }
////                 else{
////                     anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
////                 }
//                    
//                    
//                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//                
//            
//            });
               
            
            System.out.println(" isCheckBoxAdded save:"+ isCheckBoxAdded + " and " + "isCheckBoxRemoved:" + isCheckBoxRemoved);
            freelancerID = appliedFreelancerMap.get( listAppliedFreelancer.getSelectionModel().getSelectedIndex());
            ObservableList<Job> jobList  = SearchRecord.searchJob("jobAppliedOrInvited", Integer.toString(freelancerID));
            jobID = jobList.get(0).getJobID();
            lblJobInfo.setText("Title:" + jobList.get(0).getJobTitle()+ "   " + "Category:" + jobList.get(0).getJobCategory());
            lblFreelancer.setText(" to " + listAppliedFreelancer.getSelectionModel().getSelectedItem());
            }
    }

    @FXML
    private void listInvitedFreelancerHandler(MouseEvent event) {
         chkBox.setSelected(false);
          hzBoxJob.setVisible(false);
         
        if( ! listInvitedFreelancer.getItems().isEmpty()){
            isSavedFreelancer = false;
            
            //anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
           if( isCheckBoxRemoved ){
               
                isCheckBoxAdded = anchorPaneJobAssigned.getChildren().add(chkBox);
                isCheckBoxRemoved = false;
                
//                hzBoxJob.setLayoutX(0);
//                hzBoxJob.setLayoutY(407);
            }
            
           
           if ( chkBox.isSelected()){
                  hzBoxJob.setVisible(true);
                 //anchorPaneJobAssigned.getChildren().add(hzBoxJob);
               }else{
                hzBoxJob.setVisible(false);
                 //anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
               }
            System.out.println(" isCheckBoxAdded save:"+ isCheckBoxAdded + " and " + "isCheckBoxRemoved:" + isCheckBoxRemoved);
            freelancerID = invitedFreelancerMap.get( listInvitedFreelancer.getSelectionModel().getSelectedIndex());
            ObservableList<Job> jobList  = SearchRecord.searchJob("jobAppliedOrInvited", Integer.toString(freelancerID));
            jobID = jobList.get(0).getJobID();
            lblJobInfo.setText("Title:" + jobList.get(0).getJobTitle()+ "   " + "Category:" + jobList.get(0).getJobCategory());
            lblFreelancer.setText(" to " + listInvitedFreelancer.getSelectionModel().getSelectedItem());
        }
    }
    
    
    
    @FXML
    private void cmbBoxJobHandler(ActionEvent event) {     
       
       jobID = jobMap.get(cmbBoxJob.getSelectionModel().getSelectedIndex() );      
       System.out.println("Job ID in combo:"+ jobID);
     
    }

    
    
    @FXML
    private void btnOkHandler(ActionEvent event) {
        
        int userID = getUserID();
        int contractorID = getUserTypeID("contractorID","Contractor",userID); 
        
        System.out.println("ContractorID:" + contractorID + "\n" + 
                            "Job ID:" + jobID + "\n" +
                            "FreelancerID:" + freelancerID );
        
        Assignment assignment = new Assignment(
                contractorID,
                freelancerID,
                jobID);
        assignment.setContractStatus(JOB_ASSIGNED_FREELANCER);
        
            try{
                if( isSavedFreelancer || chkBox.isSelected()){
                     assignment.setJobAssignedDate(LocalDateTime.now());
                     int insertStatus = AddRecord.setDbRecord(assignment, AddRecord.ASSIGNMENT);
                     
                     if( insertStatus == -1 ){
                         alert("Please choose a job to assign","","",AlertType.INFORMATION);
                     }
                }else{            

                    //assignment = SearchRecord.searchAssignment("jobAppliedOrInvited", assignment);
                    assignment.setJobAssignedDate(LocalDateTime.now());
                    UpdateRecord.setUpdateRecord(assignment, UpdateRecord.ASSIGNMENT);
                }
            }catch( Exception e){
               if( jobID == 0 ){
                   alert("Please choose a job to assign","","",AlertType.INFORMATION);
               }
      
            }
        
    }

    @FXML
    private void radBtnShowAllJobsHandler(ActionEvent event) {
          ObservableList<Job> jobList = SearchRecord.searchJob("", "*");
                 colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
              //   colAllJobTitle.setCellFactory(TextFieldTableCell.forTableColumn());               
                 colJobDescription.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));
                 colJobType.setCellValueFactory(new PropertyValueFactory<>("jobCategory"));
                 colJobPostdate.setCellValueFactory(new PropertyValueFactory<>("postDate"));
                 tableViewJob.setItems(jobList);
                 
          tabPaneFreelancer.getSelectionModel().selectNext();
    }

    @FXML
    private void tabReportsHandler(Event event) {
        
       if( userAccount.getUserType() == CONTRACTOR ){
                btnAllFreelancerContractor.setText("Your Job Assignments");
                btnAllFreelancerJobCount.setText("Freelancer experience and charges");
       }else if( userAccount.getUserType() == FREELANCER ){
                btnAllFreelancerContractor.setText("Your Job Report");
                btnAllFreelancerJobCount.setText("Contractors Job Assignment Report");
       }
        
    }

    @FXML
    private void btnAllFreelancerContractorHandler(ActionEvent event) throws IOException {
        
         if( userAccount.getUserType() == CONTRACTOR ){            
                  setReport("contractorJobOffer");
         }else if(  userAccount.getUserType() == FREELANCER ){
                 setReport("freelancerJob");
                }
    }

    @FXML
    private void btnAllFreelancerJobCountHandler(ActionEvent event) {
        
        if( userAccount.getUserType() == CONTRACTOR ){
                 setReport("AllFreelancerCount");
        }else if( userAccount.getUserType() == FREELANCER ){
                setReport("contractorJoboffers&Assignment");
        }
    }


  

   private void setReport(String criteria){
       
        int userID = getUserID();
        int contractorID = getUserTypeID("contractorID","Contractor",userID); 
       
       
        ObservableList<Report> list = null;   
        TableView<Report> tableview = new TableView();
        tableview.setPrefSize(881, 447);
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn name = new TableColumn();
            name.setMinWidth(20);
            name.setPrefWidth(150);
            name.setMaxWidth(5000);
            name.setResizable(true);      
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            
        
        if( userAccount.getUserType() == CONTRACTOR ){         
             
            list =  (ObservableList<Report>) SearchRecord.getReportData(criteria , Integer.toString(contractorID));
    //        System.out.println("Get number of jobs:" + list.get(0).getNumberOfJobs());
            
            
            switch( criteria ){

                case "contractorJobOffer":
                    name.setText("Freelancer");
                    
                    TableColumn job = new TableColumn("Job Assigned");
                    job.setMinWidth(20);
                    job.setPrefWidth(150);
                    job.setMaxWidth(5000);
                    job.setResizable(true);

                    TableColumn jobAssignedDate = new TableColumn("Job Aissgend Date");
                    jobAssignedDate.setMinWidth(20);
                    jobAssignedDate.setPrefWidth(150);
                    jobAssignedDate.setMaxWidth(5000);
                    jobAssignedDate.setResizable(true);
                    
                   
                    tableview.getColumns().addAll(name,job,jobAssignedDate);
                    job.setCellValueFactory(new PropertyValueFactory<>("job"));
                    jobAssignedDate.setCellValueFactory( new PropertyValueFactory<>("jobAssignedDate"));

                    break;

               case "AllFreelancerCount":        
                   
                    name.setText("Freelancer");
                    TableColumn amountCharge = new TableColumn("Amount Charge($/hour)");
                    
                    TableColumn yearsOfExperience = new TableColumn("Years Of Experience");
                    
                    TableColumn jobNumber = new TableColumn("Number of Jobs");
                    jobNumber.setMinWidth(20);
                    jobNumber.setPrefWidth(150);
                    jobNumber.setMaxWidth(5000);
                    jobNumber.setResizable(true);
                    
                    
                    tableview.getColumns().addAll(name,amountCharge,yearsOfExperience,jobNumber);
                    amountCharge.setCellValueFactory(new PropertyValueFactory<>("amountCharge"));
                    yearsOfExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));
                    jobNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfJobs"));
                    break;
            
            }
              
              
        }else if( userAccount.getUserType() == FREELANCER ){           
          
          int freelancerID = getUserTypeID("freelancerID","Freelancer",userID); 
            
             list =  (ObservableList<Report>) SearchRecord.getReportData(criteria , Integer.toString(freelancerID));
                    
                
              switch( criteria ){
                  case "freelancerJob":                         
                    
                    TableColumn job = new TableColumn("Job Assigned");
                    job.setMinWidth(20);
                    job.setPrefWidth(150);
                    job.setMaxWidth(5000);
                    job.setResizable(true);
                    
                    TableColumn description = new TableColumn("Description");
                    description.setMinWidth(20);
                    description.setPrefWidth(150);
                    description.setMaxWidth(5000);
                    description.setResizable(true);
                    
                    TableColumn jobPostedDate = new TableColumn("Posted Date");
                    jobPostedDate.setMinWidth(20);
                    jobPostedDate.setPrefWidth(150);
                    jobPostedDate.setMaxWidth(5000);
                    jobPostedDate.setResizable(true);
                    
                    TableColumn status = new TableColumn("Status");
                    status.setMinWidth(20);
                    status.setPrefWidth(150);
                    status.setMaxWidth(5000);
                    status.setResizable(true);
                    
                    name.setText("Contractor");                                                   
                                 
                                           
                    tableview.getColumns().addAll(job,description,jobPostedDate,status,name);
                    job.setCellValueFactory(new PropertyValueFactory<>("job"));
                    description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    jobPostedDate.setCellValueFactory(new PropertyValueFactory<>("postedDate"));
                    status.setCellValueFactory(new PropertyValueFactory<>("contractStatus"));
                    name.setCellValueFactory(new PropertyValueFactory<>("name"));        
                    
                    break;
                    
                  case "contractorJoboffers&Assignment":
                         name.setText("Contractor");
                         
                         TableColumn joboffered = new TableColumn("Number of jobs offered");
                         joboffered.setMinWidth(20);
                         joboffered.setPrefWidth(150);
                         joboffered.setMaxWidth(5000);
                         joboffered.setResizable(true);
                         
                         TableColumn jobAssigned= new TableColumn("Number of jobs assignment");
                         jobAssigned.setMinWidth(20);
                         jobAssigned.setPrefWidth(150);
                         jobAssigned.setMaxWidth(5000);
                         jobAssigned.setResizable(true);                        
                         
                         
                         TableColumn percentage = new TableColumn("Percentage(%)");
                         jobAssigned.setMinWidth(20);
                         jobAssigned.setPrefWidth(150);
                         jobAssigned.setMaxWidth(5000);
                         jobAssigned.setResizable(true);
                         
                         tableview.getColumns().addAll(name,joboffered,jobAssigned,percentage);
                         joboffered.setCellValueFactory(new PropertyValueFactory<>("numberOfJobs"));
                         jobAssigned.setCellValueFactory(new PropertyValueFactory<>("numberOfassignment"));                         
                         percentage.setCellValueFactory(new PropertyValueFactory("rate"));
                         
                      break;
         
                    
              }            
              
            
        }
         tableview.setItems(list);
         Stage stage = new Stage();          
         Scene scene = new Scene(tableview);
               
         stage.setScene(scene);    
         stage.centerOnScreen();
         stage.show();
   }

 
   
//   public  static void shiftControls(){
//   
//      // btnOk.setText("moving");
//       
//       
////        Stage stage = (Stage)mainPane.getScene().getWindow();
////        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
////     // Do whatever you want
////            btnOk.setStyle("-fx-background-color:yellow");
////            
////        });
//       
//       
//   
//       // hzBoxFreelancerInviteApply.setStyle("-fx-padding:0 0 0 50");
//   }
    
private void setInbox(int userType){     

       
        ObservableList<Inbox> list =  FXCollections.observableArrayList();
        int userID = getUserID();
        int contractorID = getUserTypeID("contractorID","Contractor",userID); 
        DBConnection conn = new DBConnection();
        conn.connectDatabase();
        
        String userStr = "";
        String jobPostedBy = "";
        String messageStr = "";
                
        if( userAccount.getUserType() == CONTRACTOR ){
            userStr = "freelancer";
            jobPostedBy = " and jobPostedBy = " + contractorID; 
            messageStr = "message." + userStr + "ID";
           
        }else{
            userStr = "contractor";
            messageStr = "jobPostedBy";
            colInboxName.setText("Contractor Name");
        }
        
        String query = query = "SELECT job.jobID,jobTitle,jobPostedBy," + userStr + "." + userStr + "ID," + userStr + ".firstName," + userStr + ".lastName," + " message "+
                         "FROM job, message," + userStr + " WHERE job.jobID = message.jobID and " + userStr + "." + userStr + "ID ="+ messageStr + " and "+
                         "sender =" + userType;
        
        if( ! jobPostedBy.isEmpty() ){          
        
            query += jobPostedBy;
        }
        
        
         conn.setStatement(query);
         ResultSet sqlResult = conn.getStatement();
         
        try {
            while( sqlResult.next()){
                
                Message message = new Message(); 
                Job job = new Job();
                job.setJobID(sqlResult.getInt("jobID"));
                job.setJobTitle(sqlResult.getString("jobTitle"));
                message.setJob(job);
                User user = null;
                if( userAccount.getUserType() == CONTRACTOR ){
                      user = new Freelancer();
                    ((Freelancer)user).setFreelancerID(sqlResult.getInt("freelancerID"));
                }else{
                     user = new Contractor();
                    ((Contractor)user).setContractorID(sqlResult.getInt("jobPostedBy"));
                }
                
                user.setFirstName(sqlResult.getString("firstName"));
                user.setLastName(sqlResult.getString("lastName"));
                user.getFullName();
                message.setUser(user);
                message.setMessage(sqlResult.getString("message"));
                Inbox inbox = new Inbox(message);
                list.add(inbox);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
                      
        colInboxJob.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colInboxName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colInboxMsg.setCellValueFactory(new PropertyValueFactory<>("message"));
        tableviewInbox.setItems(list);

      }
  


   
   
    @FXML
    private void tableviewInboxHandler(MouseEvent event) {
       job = tableviewInbox.getSelectionModel().getSelectedItem().getJob();
       user = tableviewInbox.getSelectionModel().getSelectedItem().getUser();   
       
    }
   

    @FXML
    private void tabInboxHandler(Event event) {
        
        
         if( userAccount.getUserType() == FREELANCER ) {       
                    setInbox(CONTRACTOR);
         }else{
                    setInbox(FREELANCER);
         }
    }
        
    @FXML
    private void btnSendHandler(ActionEvent event) {
        
        message =   contractorMsgTextArea.getText();
       
       Message contractorResponse = new Message(       
               message,
               AddRecord.CONTRACTOR,
               LocalDateTime.now(),
               user,
               job
               );
       
       AddRecord.setDbRecord(contractorResponse, AddRecord.MESSAGE);
    }

    @FXML
    private void tabPostJobHandler(Event event) {
        
        radBtnInvite.setDisable(true);
//        if( isInviteFreelancer ){         
//            try{
//             gridpaneJobPost.add(lblMessage, 0, 3);
//             gridpaneJobPost.add(textAreaJobPostMessage, 1, 3);
//             gridpaneJobPost.add(btnSubmit, 1,3);
//            }catch(Exception e){
//        
//            gridpaneJobPost.getChildren().remove(btnSubmit);
//            gridpaneJobPost.getChildren().remove(lblMessage);
//            gridpaneJobPost.getChildren().remove(textAreaJobPostMessage);
//            gridpaneJobPost.add(btnSubmit, 1, 3 );
//            }
//        }
        
    }

    @FXML
    private void tabFreelancerSearchResultHandler(Event event) {
        if( tableViewFreelancer.getItems().isEmpty()){
            
            btnInviteFreelancer.setDisable(true);
            btnSave.setDisable(true);
         }else{
            btnInviteFreelancer.setDisable(false);
            btnSave.setDisable(false);
        }
    }

    
}



//private void tableViewJobHandler(MouseEvent event) throws IOException {
//        
//       int userID;
//       
//       userID = getUserID();
//       int freelancerID = getUserTypeID("freelancerID","Freelancer",userID);      
//       
//       Stage response = new Stage();
//       Parent root;
//       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ResponseJobPost.fxml"));
//       
//
//        Scene scene = new Scene( loader.load());
//        response.setScene(scene); 
//        response.setTitle("Response to job posted");
//        response.centerOnScreen();
//        response.show();
//        
//        Job job = tableViewJob.getSelectionModel().getSelectedItem();
//        ResponseJobPostController controller = loader.getController();
//        controller.initialize( job, freelancerID );
//    }