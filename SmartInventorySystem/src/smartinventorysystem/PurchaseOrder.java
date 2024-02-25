package purchaseordermanagementsystem;

public class PurchaseOrder {
    private String purchaseOrderID;
    private String orderDate;
    private PurchaseRequisition purchaseRequisition;
    private PurchaseManager purchaseManager;

    public PurchaseOrder(String purchaseOrderID, PurchaseRequisition purchaseRequisition, PurchaseManager purchaseManager,String orderDate) {
        this.purchaseOrderID = purchaseOrderID;
        this.orderDate = orderDate;
        this.purchaseRequisition = purchaseRequisition;
        this.purchaseManager = purchaseManager;
    }
    // with all String
    public PurchaseOrder(String purchaseOrderID,String purchaseRequisitionID,String purchaseManagerID, String orderDate){
        this.purchaseOrderID=purchaseOrderID;
        this.orderDate = orderDate;
        FileManager PRfile = new FileManager("Purchase_Requisition.txt");
        String[] PRData =PRfile.searchByPrimaryKey(purchaseRequisitionID);   
        this.purchaseRequisition = new PurchaseRequisition(PRData[0],PRData[1],PRData[2],PRData[3],PRData[4],PRData[5],PRData[6]);
        FileManager PMfile = new FileManager("User.txt");
        String[] PMData =PMfile.searchByPrimaryKey(purchaseManagerID);        
        this.purchaseManager = new PurchaseManager(PRData[0],PRData[1],PRData[2],PRData[3],PRData[4],PRData[5],PRData[6]);
    }

    public PurchaseRequisition getPurchaseRequisition() {
        return purchaseRequisition;
    }

    public PurchaseManager getPurchaseManager() {
        return purchaseManager;
    }

    public String getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public void addPurchaseOrder(){
        String[] newPO = {this.getPurchaseOrderID(),this.getPurchaseRequisition().getPurchaseRequisitionID(),this.getPurchaseManager().getPM_ID(),this.getOrderDate()};
        FileManager file = new FileManager("Purchase_Order.txt");
        file.addToFile(newPO);
    }
    
    public void removePurchaseOrder(){
        FileManager file = new FileManager("Purchase_Order.txt");
        file.removeLineFromFile(this.getPurchaseOrderID());
    }
    
    public String toString(){
        return "";
    }

}
