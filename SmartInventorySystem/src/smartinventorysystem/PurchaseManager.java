package purchaseordermanagementsystem;

import java.util.ArrayList;

public class PurchaseManager extends User implements Checker {
    private String PM_ID;

    public PurchaseManager(String UserID, String UserName, String UserPassword, String UserEmail, String UserPhone, String Department,String PM_ID) {
        super(UserID, UserName, UserPassword, UserEmail, UserPhone, Department);
        this.PM_ID=PM_ID;
    }

    public String getPM_ID() {
        return PM_ID;
    }

    public void setPM_ID(String PM_ID) {
        this.PM_ID = PM_ID;
    }
    
    public PurchaseRequisition checkPRInfo(String PRID){
        FileManager file = new FileManager("Purchase_Requisition.txt");
        String[] PRData = file.searchByPrimaryKey(PRID);
        PurchaseRequisition PR  = new PurchaseRequisition(PRData[0],PRData[1],PRData[2],PRData[3],PRData[4],PRData[5],PRData[6]);
        return PR;
    }
    
    public PurchaseOrder checkPOInfo(String POID){
        FileManager file = new FileManager("Purchase_Order.txt");
        String[] POData = file.searchByPrimaryKey(POID);
        PurchaseOrder PO  = new PurchaseOrder(POData[0],POData[1],POData[2],POData[3]);
        return PO;
    }
    
    public String generatePOID(){
        FileManager file = new FileManager("Purchase_Order.txt");
        ArrayList<String> POData = file.readFile();
        int newNo=0;
        if(POData.size() ==0){
            newNo=1;
        }
        else{
            String lastRow = POData.get(POData.size()-1);
            String[] data = lastRow.trim().split("\\|");
            String lastPOID = data[0];
            newNo = Integer.parseInt(lastPOID.substring(2))+1;
        }        
        String newPOID = "PO" + String.format("%05d", newNo);  
        return newPOID;
    }
    
    
    public void changePRStatus(PurchaseRequisition PR,String Status){
        PurchaseRequisition newPR =new PurchaseRequisition(PR.getPurchaseRequisitionID(),PR.getSaleManager(),PR.getSupplier().getSupplierID(),PR.getRequestDate(),PR.getGrandTotalPrice(),PR.getPurchaseRequisitionStatus(),PR.getItemList());
        newPR.setPurchaseRequisitionStatus(Status);
        PR.editPurchaseRequisition(newPR);
    }
    
    public String toString(){
        return this.getUserID()+"|"+this.getUserName()+"|"+this.getUserPassword()+"|"+this.getUserEmail()+"|"+
                this.getUserPhone()+"|"+this.getUserType()+"|"+this.getPM_ID();
    }    
}
