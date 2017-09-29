/*
Author : Teo Jia Han
Class  : DC02 D2
*/
package control;

import da.LoginDA;
import domain.Staff;

public class LoginControl {
    private LoginDA loginDa;
    
    public LoginControl(){
        loginDa = new LoginDA();
    }
    
    public Staff getRecord(String id){
        
        return loginDa.getRecord(id);
    }
}
