/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
//import java.util.Date;

/**
 *
 * @author Dinesh
 */
public class Contractor extends User{
    private int contractorID;
    private String typeOfContractor;
  // private ObservableList<Job> jobList;    

    
     public Contractor() {
        
    }
     
    public Contractor(String firstName, String lastName, LocalDate DOB,Contact contact,String typeOfContractor) {
       
        this.firstName= firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.contact = contact;
        this.typeOfContractor = typeOfContractor;
    }          

    public int getContractorID() {
        return contractorID;
    }

    public void setContractorID(int contractorID) {
        this.contractorID = contractorID;
    }
    
    
    
    
    public String getTypeOfContractor() {
        return typeOfContractor;
    }

    public void setTypeOfContractor(String typeOfContractor) {
        this.typeOfContractor = typeOfContractor;
    }
}
