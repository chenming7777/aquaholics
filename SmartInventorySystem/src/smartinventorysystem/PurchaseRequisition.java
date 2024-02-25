package purchaseordermanagementsystem;

public class PurchaseRequisition {
    private String PurchaseRequisitionID;
    private String PurchaseRequisitionStatus;
    private String requestDate;
    private double grandTotalPrice;
    private String saleManager;
    private ItemLine[] itemList;
    private Supplier supplier;

    //Normal Constructor
    public PurchaseRequisition(String PurchaseRequisitionID, String saleManager, Supplier supplier, String requestDate, double grandTotalPrice, String PurchaseRequisitionStatus, ItemLine[] itemList) {
        this.PurchaseRequisitionID = PurchaseRequisitionID;
        this.saleManager = saleManager;
        this.supplier = supplier;
        this.requestDate = requestDate;
        this.grandTotalPrice = grandTotalPrice; // Redo
        this.PurchaseRequisitionStatus = PurchaseRequisitionStatus;
        this.itemList = itemList;
    }
    
    // Construct with String supplierID
    public PurchaseRequisition(String PurchaseRequisitionID, String saleManager, String supplierID, String requestDate, double grandTotalPrice, String PurchaseRequisitionStatus, ItemLine[] itemList) {
        this.PurchaseRequisitionID = PurchaseRequisitionID;
        this.saleManager = saleManager;
        FileManager file = new FileManager("Supplier.txt");
        String[] supplierData =file.searchByPrimaryKey(supplierID);        
        this.supplier = new Supplier(supplierData[0],supplierData[1],supplierData[2],supplierData[3],supplierData[4]);
        
        this.requestDate = requestDate;
        this.grandTotalPrice = grandTotalPrice; // Redo
        this.PurchaseRequisitionStatus = PurchaseRequisitionStatus;
        this.itemList = itemList;
    }

    // Construct with all String
    public PurchaseRequisition(String PurchaseRequisitionID, String saleManager, String supplierID, String requestDate, String grandTotalPrice, String PurchaseRequisitionStatus, String itemLines) {
        this.PurchaseRequisitionID = PurchaseRequisitionID;
        this.saleManager = saleManager;
        
        FileManager file = new FileManager("Supplier.txt");
        String[] supplierData = file.searchByPrimaryKey(supplierID);        
        this.supplier = new Supplier(supplierData[0],supplierData[1],supplierData[2],supplierData[3],supplierData[4]);
        
        this.requestDate = requestDate;
        this.grandTotalPrice = Double.parseDouble(grandTotalPrice);
        this.PurchaseRequisitionStatus = PurchaseRequisitionStatus;
        
        // Convert ItemLine
        String[] itemData = itemLines.split(",");
        // ["I00001;20"  ,"I00002;60"]
        ItemLine[] newItemList = new ItemLine[itemData.length];
        for(int i = 0 ; i< itemData.length;i++){
            String[] ItemIDQuantity = itemData[i].split(";");
            ItemLine itemLine = new ItemLine(Integer.parseInt(ItemIDQuantity[1]),ItemIDQuantity[0]);
            newItemList[i]=itemLine;
        }
        this.itemList=newItemList;
    }

    public String getPurchaseRequisitionID() {
        return PurchaseRequisitionID;
    }

    public void setPurchaseRequisitionID(String PurchaseRequisitionID) {
        this.PurchaseRequisitionID = PurchaseRequisitionID;
    }

    public String getPurchaseRequisitionStatus() {
        return PurchaseRequisitionStatus;
    }

    public void setPurchaseRequisitionStatus(String PurchaseRequisitionStatus) {
        this.PurchaseRequisitionStatus = PurchaseRequisitionStatus;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public double getGrandTotalPrice() {
        return grandTotalPrice;
    }

    public void setGrandTotalPrice(double grandTotalPrice) {
        this.grandTotalPrice = grandTotalPrice;
    }

    public String getSaleManager() {
        return saleManager;
    }

    public void setSaleManager(String saleManager) {
        this.saleManager = saleManager;
    }

    public ItemLine[] getItemList() {
        return itemList;
    }

    public void setItemList(ItemLine[] itemList) {
        this.itemList = itemList;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public void removePurchaseRequisition(){
        FileManager file = new FileManager("Purchase_Requisition.txt");
        file.removeLineFromFile(this.getPurchaseRequisitionID());
    }
    
    public void addPurchaseRequisition(){
        String line = "";
        for(ItemLine item: this.itemList){
            String ItemIDQuantity = String.join(";",item.getItem().getItemCode(),Integer.toString(item.getQuantity()));
            line = String.join(",", line,ItemIDQuantity);
        }
        line = line.substring(1);
        String[] newPR = {this.getPurchaseRequisitionID(),this.getSaleManager(),this.getSupplier().getSupplierID(),this.getRequestDate(),Double.toString(this.getGrandTotalPrice()),this.getPurchaseRequisitionStatus(),line};
        FileManager PRFile = new FileManager("Purchase_Requisition.txt");
        PRFile.addToFile(newPR);
    }
    
    public void editPurchaseRequisition(PurchaseRequisition newPR){
        // Get old itemList
        String oldLine="";
        for(ItemLine item: this.itemList){
            String ItemIDQuantity = String.join(";",item.getItem().getItemCode(),Integer.toString(item.getQuantity()));
            oldLine = String.join(",", oldLine,ItemIDQuantity);
        }
        oldLine = oldLine.substring(1);
        String[] oldPR = {this.getPurchaseRequisitionID(),this.getSaleManager(),this.getSupplier().getSupplierID(),this.getRequestDate(),Double.toString(this.getGrandTotalPrice()),this.getPurchaseRequisitionStatus(),oldLine};
        
        // Get new itemList
        String newLine="";
        for(ItemLine item: newPR.itemList){
            String ItemIDQuantity = String.join(";",item.getItem().getItemCode(),Integer.toString(item.getQuantity()));
            newLine = String.join(",", newLine,ItemIDQuantity);
        }
        newLine = newLine.substring(1);
        String[] editPR = {newPR.getPurchaseRequisitionID(),newPR.getSaleManager(),newPR.getSupplier().getSupplierID(),newPR.getRequestDate(),Double.toString(newPR.getGrandTotalPrice()),newPR.getPurchaseRequisitionStatus(),newLine};
        
        // Write to File
        FileManager PRFile = new FileManager("Purchase_Requisition.txt");
        PRFile.editFile(oldPR, editPR);
    }

    public String toString(){
        String line = "";
        for(ItemLine item: this.itemList){
            String ItemIDQuantity = String.join(";",item.getItem().getItemCode(),Integer.toString(item.getQuantity()));
            line = String.join(",", line,ItemIDQuantity);
        }
        line = line.substring(1);
        return this.getPurchaseRequisitionID()+"|"+this.getSaleManager()+"|"+this.getSupplier().getSupplierID()+"|"+this.getRequestDate()+"|"+Double.toString(this.getGrandTotalPrice())+"|"+this.getPurchaseRequisitionStatus()+"|"+line;
    }
}
