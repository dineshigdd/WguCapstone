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
import Model.Freelancer;
import Model.FreelancerLanguage;
import Model.Job;
import Model.PrgmLanguage;
import Model.SavedFreelancer;
import Model.UserAccount;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.HBox;
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
    ObservableList<Freelancer> freelancerList;
    private HashMap <String,Integer> languageMap;
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
    
    private boolean isInviteFreelancer;
    private Assignment assignment;
    private UserAccount userAccount;
    private int jobID;
    private int freelancerID;
    
    @FXML
    private Button btnSave;
    @FXML
    private Button btnAssignedJob;
    private boolean isAssignedJob;
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
  
  
   
             
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//           hbox = new HBox();           
//           hbox.setSpacing(15);
//           hbox.setLayoutX(250);
//           hbox.setLayoutY(170);    
//           searchPane.getChildren().add(hbox);
           
                   
           userAccount = new UserAccount();
           btnSearch = new Button("Search");    
         
           ObservableList<String> list = FXCollections.observableArrayList(
              "less than 1 year",
              "1 year",
              "1 - 5 years",
              "more than 5 years");
           
           comboBox = new ComboBox(list);
           comboBox.setPromptText("Select Years Of Experience");
           
           datepicker = new DatePicker();
           txtSearch = new TextField();  
           criteria = null;
          
           
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
        
        if( userType == FREELANCER ){
            mainTabPane.getTabs().remove(tabContractor);
            searchJob();           
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
        return result.get().equals(userAccount.getPassword());
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
        
         if( searchHzBoxFreelancer.getChildren().isEmpty()){
             searchHzBoxFreelancer.getChildren().add(datepicker);
             searchHzBoxFreelancer.getChildren().add(btnSearch);
         }else if( searchHzBoxFreelancer.getChildren().get(0).equals(txtSearch)){
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
        }else if( searchHzBoxFreelancer.getChildren().get(0).equals(datepicker) ){
             searchHzBoxFreelancer.getChildren().remove(0);
             searchHzBoxFreelancer.getChildren().add(0,txtSearch );
        }   
        
         criteria = "jobTitle";
        
    }

    @FXML
    private void radbtnCategoryHandler(ActionEvent event) throws IOException {
        if( searchHzBoxFreelancer.getChildren().isEmpty()){          
             searchHzBoxFreelancer.getChildren().add(btnSearch);
             searchHzBoxFreelancer.getChildren().add(0,txtSearch );
        }else if( searchHzBoxFreelancer.getChildren().get(0).equals(datepicker) ){
             searchHzBoxFreelancer.getChildren().remove(0);
             searchHzBoxFreelancer.getChildren().add(0,txtSearch );
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
                     
                    jobList = SearchRecord.searchJob( criteria , datepicker.getValue().toString());               
                }else{                  
              
                   
                    jobList = SearchRecord.searchJob( criteria , txtSearch.getText() );
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
                    if( criteria.equals("yearsOfExperience")){
                          freelancerList  = SearchRecord.searchFreelancer( criteria , comboBox.getValue().toString() );

                    } else{                    
                          freelancerList  = SearchRecord.searchFreelancer( criteria , txtSearch.getText());
                    }         
                    setTableViewFreelancer();
                    tabPaneContractor.getSelectionModel().selectNext();
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
        int jobID ;
        jobID = AddRecord.setDbRecord(job, JOB);
        
       
        if ( isInviteFreelancer ){
            
            assignment.setContractorID(contractorID);       
            assignment.setJobID(jobID);
            assignment.setContractStatus(INVITED_FREELANCER);
            AddRecord.setDbRecord(assignment, AddRecord.ASSIGNMENT);
        }
    }

    @FXML
    private void tabAllJobsHandler(Event event) {
        
        ObservableList<Job> jobList = SearchRecord.searchJob("", "*");
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
         controller.setJob(job , userAccount);
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
        controller.initialize( job, freelancerID );
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
             searchHzBoxContractor.getChildren().add(txtSearch);
             searchHzBoxContractor.getChildren().add(btnSearch);
         }else if( searchHzBoxContractor.getChildren().get(0).equals(comboBox)){
             searchHzBoxContractor.getChildren().remove(0);
             searchHzBoxContractor.getChildren().add(0,txtSearch);            
         }        
        
        
        criteria = "progLanguage";
    }

    @FXML
    private void radbtnExperienceHandler(ActionEvent event) {
        
        if( searchHzBoxContractor.getChildren().isEmpty()){
             searchHzBoxContractor.getChildren().add(comboBox);
             searchHzBoxContractor.getChildren().add(btnSearch);
         }else if( searchHzBoxContractor.getChildren().get(0).equals(txtSearch)){
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
         }else if( searchHzBoxContractor.getChildren().get(0).equals(comboBox)){
             searchHzBoxContractor.getChildren().remove(0);
             searchHzBoxContractor.getChildren().add(0,txtSearch); 
         }
        criteria = "city";
    }

    
    
    
    @FXML
    private void tabAddSkillsHandler(Event event) {
        
     if( ListallPrgmLanguages.getItems().isEmpty() && ListselectedPrgmLanguages.getItems().isEmpty()  ){ 
        prgmLanguageList =  FXCollections.observableArrayList();
//        prgmLanguageList = FXCollections.observableArrayList(
//                    "Java","C","Python","C++","Visual Basic .NET","C#","JavaScript","PHP",
//                    "SQL","Objective-C","Delphi/Object Pascal","Assembly language","MATLAB",
//                    "Swift","Go","R","RubyprgmLanguageList","Perl","Other"
//         );
//        
         PrgmLanguage progrmLanguage;
         DBConnection conn = new DBConnection();
         conn.connectDatabase();
         conn.setStatement("select * from programlanguage");
         ResultSet sqlResult = conn.getStatement();
        
         languageMap = new HashMap<>();

        try {
            while( sqlResult.next()){
                progrmLanguage = new PrgmLanguage();
                progrmLanguage.setProgLanguageID(sqlResult.getInt("progLanguageID"));
                progrmLanguage.setProgLanguage(sqlResult.getString("progLanguage"));              
                
                languageMap.put(  progrmLanguage.getProgLanguage(),progrmLanguage.getProgLanguageID());
                prgmLanguageList.add(progrmLanguage);
            }
        } catch (SQLException ex) {
        }
        
        
        for(int i = 0; i < prgmLanguageList.size(); i++ ){
           ListallPrgmLanguages.getItems().add(prgmLanguageList.get(i).getProgLanguage());
        }
     }
    }

    @FXML
    private void btnRightArrowHandler(ActionEvent event) {
        
        if( !ListallPrgmLanguages.getSelectionModel().isEmpty() ){
            
            ListselectedPrgmLanguages.getItems().add(       
                    ListallPrgmLanguages.getSelectionModel().getSelectedItem());

            ListallPrgmLanguages.getItems().remove(ListallPrgmLanguages.getSelectionModel().getSelectedIndex());
        }
       
    }

    @FXML
    private void btnLeftArrowHandler(ActionEvent event) {
        
        if(  !ListselectedPrgmLanguages.getSelectionModel().isEmpty() ){
            
            ListallPrgmLanguages.getItems().add(       
                        ListselectedPrgmLanguages.getSelectionModel().getSelectedItem());   

            ListselectedPrgmLanguages.getItems().remove(
                        ListselectedPrgmLanguages.getSelectionModel().getSelectedItem());       
        }
            
                  
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

    @FXML
    private void btnAddSkillsHandler(ActionEvent event) {
        
        //get Prgramming laguagues
        int userID;
        int freelancerID;
        int size = ListselectedPrgmLanguages.getItems().size();
        userID = getUserID();
        freelancerID = getUserTypeID("freelancerID","Freelancer",userID);
       
        
        for( int i = 0 ; i < size; i++ ){
                int progLanguageID = languageMap.get(ListselectedPrgmLanguages.getItems().get(i));        
       
        
        
        FreelancerLanguage freelancerLanguage = new FreelancerLanguage(
                    freelancerID,
                    progLanguageID        
        );
        
        
         AddRecord.setDbRecord(freelancerLanguage, AddRecord.FREELANCER_PRGM_LANGUAGE);
        }
        
        
        addOtherSkills( textAreaOtherTech.getText(), textAreaNonTech.getText());
        
    }

    
    private void addOtherSkills(String otherTech, String nonTech){
        try{
                DBConnection  conn = new DBConnection();
                conn.connectDatabase();   
                
                String query;
                query = "Update Freelancer set "
                        + "otherTechSkills = ?," 
                        + "nonTechSkills = ?";
                       
                
                                    
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, otherTech);
                                ps.setString( 2, nonTech);                                
                                ps.executeUpdate();
                 conn.closeDBConnection();    
                 
                }catch( SQLException e){
                }
    }

   
    @FXML
    private void tableViewFreelancerHandler(MouseEvent event) {
        //String fullName =  tableViewFreelancer.getSelectionModel().getSelectedItem().getFullName();
        int freelancerID = tableViewFreelancer.getSelectionModel().getSelectedItem().getFreelancerID();
        assignment = new Assignment();        
        assignment.setFreelancerID(freelancerID);
//       Stage invite = new Stage();
//       Parent root;
//       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/InviteFreelancer.fxml"));
//       
//
//        Scene scene = new Scene( loader.load());
//        invite.setScene(scene); 
//        invite.setTitle("Invite the Freelancer");
//        invite.centerOnScreen();
//        invite.show();
//        
//        InviteFreelancerController controller = loader.getController();
//        controller.initialize( tableViewFreelancer.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void btnInviteFreelancerHandler(ActionEvent event) {
        
        tabPaneContractor.getSelectionModel().select(tabPostJob);
        isInviteFreelancer = true;
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
    private void btnAssignedJobHandler(ActionEvent event) {
        isAssignedJob = true;
    }

    @FXML
    private void tabAssignJobHandler(Event event) {
        
        
         int userID = getUserID();
        int contractorID = getUserTypeID("contractorID","Contractor",userID); 
        
        //saved Freelancer
        freelancerList = SearchRecord.searchFreelancer("savedFreelancer", String.valueOf(contractorID));
        savedFreelancerMap = new HashMap();  
        
        chkBox = new CheckBox();
        chkBox.setText("Check if you need to assign a new job");
        chkBox.setLayoutX(40);
        chkBox.setLayoutY(356);
        isCheckBoxAdded = false;
        isCheckBoxRemoved = true;
        
        
        chkBox.setOnAction((chkevent) -> {
                   if( chkBox.isSelected() ){
                      anchorPaneJobAssigned.getChildren().add(hzBoxJob);
                 }
                 else{
                     anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
                 };
            });
      
                
        for( int i = 0; i < freelancerList.size(); i++ ){
           savedFreelancerMap.put( i , freelancerList.get(i).getFreelancerID());
        }
        
        if( listSavedFreelancer.getItems().isEmpty()){
            for(int i = 0; i < freelancerList.size(); i++ ){
                listSavedFreelancer.getItems().add(freelancerList.get(i).getFullName());
            }
        }
        
        //AppliedFreelancer
        freelancerList = SearchRecord.searchFreelancer("appliedFreelancer", String.valueOf(contractorID));
        appliedFreelancerMap = new HashMap(); 
        for( int i = 0; i < freelancerList.size(); i++ ){
           appliedFreelancerMap.put( i , freelancerList.get(i).getFreelancerID());
        }
        
        if( listAppliedFreelancer.getItems().isEmpty()){
            for(int i = 0; i < freelancerList.size(); i++ ){
                listAppliedFreelancer.getItems().add(freelancerList.get(i).getFullName());
            }
        }
        
        //Invited Freelancer
        freelancerList = SearchRecord.searchFreelancer("invitedFreelancer", String.valueOf(contractorID));
        invitedFreelancerMap = new HashMap(); 
        for( int i = 0; i < freelancerList.size(); i++ ){
           invitedFreelancerMap.put( i , freelancerList.get(i).getFreelancerID());
        }
        
        if( listInvitedFreelancer.getItems().isEmpty()){
            for(int i = 0; i < freelancerList.size(); i++ ){
                listInvitedFreelancer.getItems().add(freelancerList.get(i).getFullName());
            }
        }
//        
         
        ObservableList<Job> jobList = SearchRecord.searchJob("all", "*");
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
             
            if( isCheckBoxAdded ){
               isCheckBoxRemoved = anchorPaneJobAssigned.getChildren().remove(chkBox);
               isCheckBoxAdded = false;
               hzBoxJob.setLayoutY(362);
               anchorPaneJobAssigned.getChildren().add(hzBoxJob);
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
        
        if( ! listAppliedFreelancer.getItems().isEmpty()){
            isSavedFreelancer = false;
             
              anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
            if( isCheckBoxRemoved ){
                isCheckBoxAdded = anchorPaneJobAssigned.getChildren().add(chkBox);
                isCheckBoxRemoved = false;
               
                hzBoxJob.setLayoutX(40);
                hzBoxJob.setLayoutY(407);
              //  anchorPaneJobAssigned.getChildren().add(hzBoxJob)
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
        if( ! listInvitedFreelancer.getItems().isEmpty()){
            isSavedFreelancer = false;
            
                anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
           if( isCheckBoxRemoved ){
                isCheckBoxAdded = anchorPaneJobAssigned.getChildren().add(chkBox);
                isCheckBoxRemoved = false;
                
                hzBoxJob.setLayoutX(40);
                hzBoxJob.setLayoutY(407);
            }
            
           
               if ( chkBox.isSelected()){
                 anchorPaneJobAssigned.getChildren().add(hzBoxJob);
               }else{
                      anchorPaneJobAssigned.getChildren().remove(hzBoxJob);
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
                     int insertStatus = AddRecord.setDbRecord(assignment, AddRecord.ASSIGNMENT);
                     
                     if( insertStatus == -1 ){
                         alert("Please choose a job to assign","","",AlertType.INFORMATION);
                     }
                }else{            

                    assignment = SearchRecord.searchAssignment("jobAppliedOrInvited", assignment);
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


   
    
    
}
