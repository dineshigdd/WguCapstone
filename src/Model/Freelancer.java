/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Dinesh
 */
public class Freelancer extends User{
    private String yearsOfExperince;
    private String selfDescription;

 
public Freelancer() {
       
}

public Freelancer(String firstName, String lastName, LocalDate DOB,Contact contact,String yearsOfExperience,String selfDescription) {
       
        this.firstName= firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.contact = contact;
        this.yearsOfExperince = yearsOfExperience;  
        this.selfDescription = selfDescription;
    }   
    
    
    public String getYearsOfExperince() {
        return yearsOfExperince;
    }

    public void setYearsOfExperince(String yearsOfExperince) {
        this.yearsOfExperince = yearsOfExperince;
    }

    public String getSelfDescription() {
        return selfDescription;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }
        
    
}
