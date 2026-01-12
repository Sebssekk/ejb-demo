package seb.course.sessionbeans.stateless;

import jakarta.ejb.Stateless;

@Stateless
public class BadService {

    // ⚠️⚠️⚠️ Careful ⚠️⚠️⚠️
    // This will be shared among ANY client
    // As any changes to it !
    private String myName = "seb";

    public String whatsYourName(){
        return myName;
    }

    public void changeName(String newName){
        this.myName = newName;
    }
}
