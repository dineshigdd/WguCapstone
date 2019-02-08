/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;
import static java.lang.System.out;
import java.util.regex.Pattern; 
/**
 *
 * @author Dinesh
 */
public class Validation {

    public static boolean isStringAnumber(String inString){
        boolean isValid = true;
        for(int i = 0; i < inString.length() ;i++){
                if( !Character.isDigit( inString.charAt(i))){
                       isValid = false;
                  }      
        }
     return isValid;
    }
    
        
     public static boolean isStringHasAnumber(String inString){
        for(int i = 0; i <  inString.length()  ;i++){
                if(Character.isDigit( inString.charAt(i))){
                   System.out.println(Character.isDigit( inString.charAt(i)));
                   return true;
                }
         }
        return false;
    }
     
    public static boolean isStringLetters(String inString){
        boolean isValid = false;
        for(int i = 0; i <  inString.length()  ;i++){
                if(!Character.isLetter(inString.charAt(i))){
                   isValid = true;
                   
                   if(Character.isWhitespace(inString.charAt(i))){
                
                     isValid = false;
                    }
                   
                   return isValid;
                }
        }
         
        return false;
    }
     
  
   public static boolean isValidEmail(String email){
       
        String EmailRegExpression = "^[a-zA-Z+&*-]+(?:\\."+ 
                                    "[a-zA-Z0-9_+&*-]+)*@" + 
                                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                                    "A-Z]{2,7}$"; 
        
        Pattern pat = Pattern.compile(EmailRegExpression);
        
        return pat.matcher(email).matches();
   
   }
}
