package purchaseordermanagementsystem;

import java.util.ArrayList;

public class Administrator extends User{
    
    private String Admin_ID;

    public Administrator(String UserID, String UserName, String UserPassword, String UserEmail, String UserPhone, String Department, String Admin_ID) {
        super(UserID, UserName, UserPassword, UserEmail, UserPhone, Department);
        this.Admin_ID=Admin_ID;
    }

    public String getAdmin_ID() {
        return Admin_ID;
    }

    public void setAdmin_ID(String Admin_ID) {
        this.Admin_ID = Admin_ID;
    }

////////////////////////////////////////
    
    public String generateUserID(){
        FileManager file = new FileManager("User.txt");
        ArrayList<String> userData = file.readFile();
        int newNo=0;
        if(userData.size() ==0){
            newNo=1;
        }
        else{
            String lastRow = userData.get(userData.size()-1);
            String[] data = lastRow.trim().split("\\|");
            String lastUserID = data[0];
            newNo = Integer.parseInt(lastUserID.substring(1))+1;
        }        
        String newUserID = "U" + String.format("%05d", newNo);  
        return newUserID;
    }
    
    public PurchaseRequisition checkPRInfo(String PRID){
        FileManager file = new FileManager("Purchase_Requisition.txt");
        String[] PRData = file.searchByPrimaryKey(PRID);
        PurchaseRequisition PR  = new PurchaseRequisition(PRData[0],PRData[1],PRData[2],PRData[3],PRData[4],PRData[5],PRData[6]);
        return PR;
    }
    
    public String generatestaffID(String userType){
        FileManager file = new FileManager("User.txt");
        ArrayList<String> userData = file.readFile();
        ArrayList<String> adminList = new ArrayList<String>();
        ArrayList<String> SMList = new ArrayList<String>();;
        ArrayList<String> PMList = new ArrayList<String>();;
        String lastID;
        String newStaffID ="";
        
        for(int i =  0 ; i< userData.size();i++){
            String[] data = userData.get(i).trim().split("\\|");
            String staffID = data[data.length-1];
            Character TypeID = staffID.charAt(0);
            if (TypeID.equals('A')){
                adminList.add(staffID);
            }
            else if (TypeID.equals('S')){
                SMList.add(staffID);
            }
            else if(TypeID.equals('P')){
                PMList.add(staffID);
            }
        }
        
        if (userType.equals("Admin")){
            int newNo=0;
            if(adminList.size()==0){
                newNo=1;
            }
            else{
                lastID = adminList.get(adminList.size()-1);
                newNo = Integer.parseInt(lastID.substring(1))+1;                
            }
            newStaffID = "A" + String.format("%05d", newNo);
        }
        else if(userType.equals("SaleManager")){
            int newNo=0;
            if(SMList.size()==0){
                newNo=1;
            }
            else{
                lastID = SMList.get(SMList.size()-1);
                newNo = Integer.parseInt(lastID.substring(1))+1;                
            }
            newStaffID = "S" + String.format("%05d", newNo);
        }
        else if(userType.equals("PurchaseManager")){
            int newNo=0;
            if(PMList.size()==0){
                newNo=1;
            }
            else{
                lastID = PMList.get(PMList.size()-1);
                newNo = Integer.parseInt(lastID.substring(1))+1;                
            }
            newStaffID = "P" + String.format("%05d", newNo);
        }  
        return newStaffID;
    }
            
    
    // Method Overloading
    public void registerUser(Administrator newAdmin){
        //Validation
        String[] newUser = {newAdmin.getUserID(),newAdmin.getUserName(),newAdmin.getUserPassword(),newAdmin.getUserEmail(),newAdmin.getUserPhone(),newAdmin.getUserType(),newAdmin.getAdmin_ID()};
        FileManager file = new FileManager("User.txt");
        file.addToFile(newUser);
    }
    
    public void registerUser(SaleManager newSM){
        String[] newUser = {newSM.getUserID(),newSM.getUserName(),newSM.getUserPassword(),newSM.getUserEmail(),newSM.getUserPhone(),newSM.getUserType(),newSM.getSM_ID()};
        FileManager file = new FileManager("User.txt");
        file.addToFile(newUser);
    }
    
    public void registerUser(PurchaseManager newPM){
        String[] newUser = {newPM.getUserID(),newPM.getUserName(),newPM.getUserPassword(),newPM.getUserEmail(),newPM.getUserPhone(),newPM.getUserType(),newPM.getPM_ID()};
        FileManager file = new FileManager("User.txt");
        file.addToFile(newUser);
    }

    public void manageUser(String mode,String[] oldData,String[] newData){
        User user = new User();
        switch (mode){                
            case "edit":
                user.editUser(oldData, newData);
                break;
                       
            case "remove":
                user.removeUser(oldData[0]);
                break;
            
            default:
                break;
        }
    }
    
    public String toString(){
        return this.getUserID()+"|"+this.getUserName()+"|"+this.getUserPassword()+"|"+this.getUserEmail()+"|"+
                this.getUserPhone()+"|"+this.getUserType()+"|"+this.getAdmin_ID();
    }
}
