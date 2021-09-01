

public class Transaction {
    private final String itemName;
    private final String price;
    private final String date;
    public Transaction(String itemName, String price, String date){
         this.date = date;
         this.price = price;
         this.itemName = itemName;
    }

    public String getPrice(){
        return this.price;
    }
    public String getItemName(){
        return this.itemName;
    }
    public String getDate(){
        return this.date;
    }

}
