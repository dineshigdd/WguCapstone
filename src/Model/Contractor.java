/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Dinesh
 */
public class Contractor extends User{
    private String typeOfContractor;
  // private ObservableList<Job> jobList;    

    public Contractor(String firstName, String lastName, Date DOB,Address address, 
            String phone, String email,String typeOfContractor) {
       
        this.firstName= firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.typeOfContractor = typeOfContractor;
    }       
   
    
    

    public String getTypeOfContractor() {
        return typeOfContractor;
    }

    public void setTypeOfContractor(String typeOfContractor) {
        this.typeOfContractor = typeOfContractor;
    }

//    public ObservableList<Job> getJobList() {
//        return jobList;
//    }
//
//    public void setJobList(Job job) {
//        this.jobList.add(job);
//    }


    
    
    
    
}
