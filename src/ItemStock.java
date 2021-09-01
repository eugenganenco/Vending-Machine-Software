public class ItemStock {
    private final String name;
    private final String price;
    private Long amount;
    public ItemStock(String name, Long amount, String price){
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public String getPrice(){
        return price;
    }
    public Long getAmount(){
        return amount;
    }
    public void addItems(Long amount){
        this.amount += amount;
    }
    public ItemStockCopy getItemStockCopy(){
        return new ItemStockCopy(this.name,this.price,this.amount);
    }
    public void setNewAmount(Long newAmount){
        amount = newAmount;
    }
}
