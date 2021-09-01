public class ItemStockCopy extends ItemStock{
    private final String name;
    private final String price;
    private final Long amount;

    public ItemStockCopy(String name,String price,Long amount) {
        super(name, amount, price);
        this.amount = amount;
        this.price = price;
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }
    public String getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
}
