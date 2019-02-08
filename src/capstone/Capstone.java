/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dinesh
 */
public class Capstone extends Application {
    boolean running = true;
      
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AccountCreationScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UTILITY);
        stage.show();
        
        
        try{
               
               stage.iconifiedProperty().addListener((observable, oldValue, iconified) -> {
                  if (iconified) {
                        System.out.println("min");
                  } else {
                // do something on restore window
                  }
                });
        }catch(Exception e){}
        
  
        stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
     
          @Override
          public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                   
              
                  
                
               
                  
                  Platform.runLater(new Runnable() {
                      @Override
                      public void run(){
                        //  while(running){
                              try{
                                   
                                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainScreen.fxml"));
                                   loader.load();
                                   
                                  // MainScreenController controller = loader.getController();
                                   
                                
                                //    MainScreenController.shiftControls();   
                                   
//                                   if( stage.isMaximized() ){
//                                       
//                                       running = false;
//                                   }
//                                    
                                
                              }catch(Exception e){
                                  e.printStackTrace();
                                 
                              }
                                
                                
                          }
                      //}
                      
                  });
                  
                
                  
                  
                  
                  
                  
                  
              
         }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
