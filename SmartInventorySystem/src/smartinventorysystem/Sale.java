package purchaseordermanagementsystem;

public class Sale {
    
    private String saleDate, saleID;
    private ItemLine itemLine;
    
    public Sale (String saleID, String saleDate, ItemLine itemLine){
        this.saleDate = saleDate;
        this.saleID = saleID;
        this.itemLine = itemLine;
    }

    public Sale(String saleID, String saleDate, String itemID, String quantity) {
        this.saleDate = saleDate;
        this.saleID = saleID;
        
        FileManager file = new FileManager("Item.txt");
        String[] itemData = file.searchByPrimaryKey(itemID);        
        
        
        Item item = new Item(itemData[0], itemData[1], itemData[2], Double.parseDouble(itemData[3]), Integer.parseInt(itemData[4]), itemData[5]);
        this.itemLine = new ItemLine(Integer.parseInt(quantity), item);
    }
    
    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public ItemLine getItemLine() {
        return itemLine;
    }

    public void setItemLine(ItemLine itemLine) {
        this.itemLine = itemLine;
    }

    public void addSale(){
        
        String itemID = this.getItemLine().getItem().getItemCode();
        String itemName = this.getItemLine().getItem().getItemName();
        String quantity = Integer.toString(this.getItemLine().getQuantity());
        String unitPrice = Double.toString(this.getItemLine().getItem().getItemUnitPrice());
        String totalPrice = Double.toString(this.getItemLine().getTotalPrice());
        String[] newSale = {this.getSaleID(),this.getSaleDate(),itemID,itemName,quantity,unitPrice,totalPrice};
        FileManager saleFile = new FileManager("Sale.txt");
        saleFile.addToFile(newSale);
        
        //Update to Item Stock
        FileManager itemFile = new FileManager("Item.txt");
        String itemCategory = this.getItemLine().getItem().getItemCategory();
        String supplierID = this.getItemLine().getItem().getSupplier().getSupplierID();
        String oldItemStock = Integer.toString(this.getItemLine().getItem().getItemStock());
        String newStock = Integer.toString(this.getItemLine().getItem().getItemStock() - this.getItemLine().getQuantity());
        String[] oldItemData = {itemID, itemName, itemCategory, unitPrice, oldItemStock, supplierID};
        String[] newItemData = {itemID, itemName, itemCategory, unitPrice, newStock, supplierID};
        itemFile.editFile(oldItemData, newItemData);

    }
    
    public void removeSale(){
        FileManager file = new FileManager("Sale.txt");
        file.removeLineFromFile(this.getSaleID());
        
        //Update to Item Stock
        FileManager itemFile = new FileManager("Item.txt");
        String itemID = this.getItemLine().getItem().getItemCode();
        String itemName = this.getItemLine().getItem().getItemName();
        String unitPrice = Double.toString(this.getItemLine().getItem().getItemUnitPrice());
        String itemCategory = this.getItemLine().getItem().getItemCategory();
        String supplierID = this.getItemLine().getItem().getSupplier().getSupplierID();
        String oldItemStock = Integer.toString(this.getItemLine().getItem().getItemStock());
        String newStock = Integer.toString(this.getItemLine().getItem().getItemStock() + this.getItemLine().getQuantity());
        String[] oldItemData = {itemID, itemName, itemCategory, unitPrice, oldItemStock, supplierID};
        String[] newItemData = {itemID, itemName, itemCategory, unitPrice, newStock, supplierID};
        itemFile.editFile(oldItemData, newItemData);
    }
    
    public String toString(){
        return this.getSaleID()+"|"+this.getSaleDate()+"|"+this.getItemLine().getItem().getItemCode()+
                "|"+this.getItemLine().getItem().getItemName()+"|"+this.getItemLine().getQuantity()+
                "|"+this.getItemLine().getItem().getItemUnitPrice()+"|"+this.getItemLine().getTotalPrice();
    }
}
