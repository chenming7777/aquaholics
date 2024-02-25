package purchaseordermanagementsystem;

public class Item {
    private String itemCode, itemCategory, itemName;
    private Supplier supplier;
    private double itemUnitPrice;
    private int itemStock;

    public Item(String itemCode, String itemName, String itemCategory, double itemUnitPrice, int itemStock, Supplier supplier) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemUnitPrice = itemUnitPrice;
        this.itemStock = itemStock;
        this.supplier = supplier;
    }
    
    public Item(String itemCode, String itemName, String itemCategory, double itemUnitPrice, int itemStock, String supplierID) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemUnitPrice = itemUnitPrice;
        this.itemStock = itemStock;
        FileManager file = new FileManager("Supplier.txt");
        String[] supplierData = file.searchByPrimaryKey(supplierID);
        this.supplier = new Supplier(supplierData[0], supplierData[1], supplierData[2], supplierData[3], supplierData[4]);
            
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    public void addItem(){
        String itemUnitPrice = Double.toString(this.getItemUnitPrice());
        String itemStock = Integer.toString(this.getItemStock());
        String supplierID = this.getSupplier().getSupplierID();
        
        String[] newitem = {this.getItemCode(),this.getItemName(),this.getItemCategory(),itemUnitPrice,itemStock,supplierID};
        FileManager file = new FileManager("Item.txt");
        file.addToFile(newitem);
    }
    
    public void removeItem(){
        FileManager file = new FileManager("Item.txt");
        file.removeLineFromFile(this.getItemCode());
    }
    
    public void editItem(Item newItem){
        String itemUnitPrice = Double.toString(this.getItemUnitPrice());
        String itemStock = Integer.toString(this.getItemStock());

        String newItemUnitPrice = Double.toString(newItem.getItemUnitPrice());
        String newItemStock = Integer.toString(newItem.getItemStock());

        
        FileManager file = new FileManager("Item.txt");
        String[] oldData = {this.getItemCode(),this.getItemName(),this.getItemCategory(),itemUnitPrice,itemStock,this.getSupplier().getSupplierID()};
        String[] newData = {newItem.getItemCode(),newItem.getItemName(),newItem.getItemCategory(),newItemUnitPrice,newItemStock,newItem.getSupplier().getSupplierID()};
        file.editFile(oldData, newData);
    }

    public String toString(){
        return this.getItemCode()+"|"+this.getItemName()+"|"+this.getItemCategory()+"|"+this.getItemUnitPrice()+"|"+this.getItemStock()+"|"+this.getSupplier().getSupplierID();
    }
}
