package purchaseordermanagementsystem;

public interface Checker {
    public PurchaseRequisition checkPRInfo(String PRID);
    public PurchaseOrder checkPOInfo(String POID);
}
