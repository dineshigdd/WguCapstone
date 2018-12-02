/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Dinesh
 */
public class Freelancer extends User{
    private int yearsOfExperince;
    private String selfDescription;

    public int getYearsOfExperince() {
        return yearsOfExperince;
    }

    public void setYearsOfExperince(int yearsOfExperince) {
        this.yearsOfExperince = yearsOfExperince;
    }

    public String getSelfDescription() {
        return selfDescription;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }
        
    
}
