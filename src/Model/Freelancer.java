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
     
    private int freelancerID;
    private String yearsOfExperince;
    private String selfDescription;
    private String otherTechSkills;
    private String nonTechSkills;
    private int amountCharge;
 
public Freelancer() {
       
}

public Freelancer(String firstName, String lastName, LocalDate DOB,
        Contact contact,String yearsOfExperience,String selfDescription, int amountCharge) {
       
        this.firstName= firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.contact = contact;
        this.yearsOfExperince = yearsOfExperience;  
        this.selfDescription = selfDescription;
        this.amountCharge = amountCharge;
    }   

    public int getFreelancerID() {
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
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

    public String getOtherTechSkills() {
        return otherTechSkills;
    }

    public void setOtherTechSkills(String otherTechSkills) {
        this.otherTechSkills = otherTechSkills;
    }

    public String getNonTechSkills() {
        return nonTechSkills;
    }

    public void setNonTechSkills(String nonTechSkills) {
        this.nonTechSkills = nonTechSkills;
    }

    public void setAmountCharge(int amountCharge) {
        this.amountCharge = amountCharge;
        
    }
        
    public int getAmountCharge(){
        return amountCharge;
    }
      
}
