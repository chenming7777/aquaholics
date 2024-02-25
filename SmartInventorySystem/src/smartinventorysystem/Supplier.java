package purchaseordermanagementsystem;


public class Supplier {
    private String supplierID, supplierName, supplierAddress, supplierEmail,supplierPhone;
    
    public Supplier(String supplierID, String supplierName,String supplierPhone,String supplierEmail,String supplierAddress){
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierEmail = supplierEmail;
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }  
    
    public void addSupplier(){
        String[] newSupplier = {this.getSupplierID(),this.getSupplierName(),this.getSupplierPhone(),this.getSupplierEmail(),this.getSupplierAddress()};
        FileManager file = new FileManager("Supplier.txt");
        file.addToFile(newSupplier);
    }
    
    public void removeSupplier(){
        FileManager file = new FileManager("Supplier.txt");
        file.removeLineFromFile(this.getSupplierID());
    }
    
    public void editSupplier(Supplier newSupplier){
        FileManager file = new FileManager("Supplier.txt");
        String[] oldData = {this.supplierID,this.supplierName,this.supplierPhone,this.supplierEmail,this.supplierAddress};
        String[] newData = {newSupplier.supplierID,newSupplier.supplierName,newSupplier.supplierPhone,newSupplier.supplierEmail,newSupplier.supplierAddress};
        file.editFile(oldData, newData);
    }   
    
    public String toString(){
        return this.getSupplierID()+"|"+this.getSupplierName()+"|"+this.getSupplierPhone()+"|"+this.getSupplierEmail()+"|"+this.getSupplierAddress();
    }
}
