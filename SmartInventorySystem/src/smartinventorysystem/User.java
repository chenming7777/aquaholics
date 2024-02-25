package purchaseordermanagementsystem;

import java.util.ArrayList;

public class User {
    private String UserID;
    private String UserName;
    private String UserPassword;
    private final String UserEmail;
    private String UserPhone;
    private String userType;

    public User(){
        this.UserID = null;
        this.UserName = null;
        this.UserPassword = null;
        this.UserEmail = null;
        this.UserPhone = null;
        this.userType = null;
    }
    
    public User(String UserID, String UserName, String UserPassword, String UserEmail, String UserPhone, String userType) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.UserPassword = UserPassword;
        this.UserEmail = UserEmail;
        this.UserPhone = UserPhone;
        this.userType = userType;
    }
    
    // getter and setter

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String UserPassword) {
        this.UserPassword = UserPassword;
    }

    public String getUserEmail(){
        return UserEmail;
    }
    
    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String UserPhone) {
        this.UserPhone = UserPhone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public void editUser(String[] oldData, String[] newData){
        FileManager file = new FileManager("User.txt");
        file.editFile(oldData, newData);
    }
    public void removeUser(String userID){
        FileManager file = new FileManager("User.txt");
        file.removeLineFromFile(userID);
    }
  
    // User function
    public static String[] loginAccess(String loginUserID,String loginPassword){
        FileManager file = new FileManager("User.txt");
        ArrayList<String> userData = file.readFile();
        String[] savedData = new String[7];
        int count = 0; 
          
        for(int i=0 ; i< userData.size();i++){
            String line = userData.get(i);
            String[] data = line.split("\\|");
            
            if (loginUserID.equals(data[0]) && loginPassword.equals(data[2])){
                savedData = data;
                count++;
            }
        }
        
        if (count == 0){
            savedData[5] = "fail";
            return savedData;
        }
        else if (count == 1){
            return savedData;
        }
        else{
            savedData[5] = "fail";
            return savedData;
        }
    }
    
    public String toString(){
        return this.getUserID()+"|"+this.getUserName()+"|"+this.getUserPassword()+"|"+this.getUserEmail()+"|"
                +this.getUserPhone()+"|"+this.getUserType();
    }
    
}
