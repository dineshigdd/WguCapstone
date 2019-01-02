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
public class FreelancerLanguage {
    private int freelancerID;
    private int progLanguageID;

    public FreelancerLanguage(int freelancerID, int progLanguageID) {
        this.freelancerID = freelancerID;
        this.progLanguageID = progLanguageID;
    }

    public FreelancerLanguage() {
        
    }

    public int getFreelancerID() {
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
    }

    public int getProgLanguageID() {
        return progLanguageID;
    }

    public void setProgLanguageID(int progLanguageID) {
        this.progLanguageID = progLanguageID;
    }
    
    
}
