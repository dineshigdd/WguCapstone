/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinesh
 */
public class DBConnection {
       private Connection connection = null;
       private Statement statement = null;
       private ResultSet resultSet = null;
       private  PreparedStatement  prePareStatement = null;
       
    public void connectDatabase(){
                final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
                final String DB_URL = "jdbc:mysql://52.206.157.109/U056ht";       
                final String DBUSER = "U056ht";
                final String DBPASS = "53688428145";
                             
                
                
                
                ResultSet logInResult = null;
                String location = null;

                try{
                  //  Class.forName( JDBC_DRIVER );
                    connection = DriverManager.getConnection(DB_URL, DBUSER, DBPASS);    
                    statement = connection.createStatement();
                    
//                }catch(ClassNotFoundException e){
//                       e.printStackTrace();
                }catch(SQLException e){
                       e.printStackTrace();
                }     
        
        }

    public void setStatement(String sqlQuery) {
       
        try{
            
            resultSet = statement.executeQuery(sqlQuery);
            
        }catch(SQLException e){
            e.printStackTrace();
              
        }
         
   
    }
    
    public PreparedStatement insertRecord(String sqlQuery){
       
           try {
                System.out.println(sqlQuery);
               prePareStatement  = connection.prepareStatement(sqlQuery);
               
               
           
           } catch (SQLException ex) {
               Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
           }
        return prePareStatement;
    }

    public ResultSet getStatement() {
      
        return resultSet;
    }
       
    public void closeDBConnection() throws SQLException{
        connection.close();
    }
}