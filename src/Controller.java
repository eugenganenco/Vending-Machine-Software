import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Controller {
    private Model model;
    private View view;
    public Controller(){
        this.model = null;
        this.view = null;
    }

    public void addView(View view){
        this.view = view;
    }
    public void addModel(Model model){
        this.model = model;
    }
    public boolean supplyItems(File file){
        return model.supplyItems(file);
    }
    public boolean storageIsEmpty(){
        return model.storageIsEmpty();
    }
    public HashMap<String,ItemStock> getItemStockHashMap(){
        return model.getItemStockHashMap();
    }
    public void actualizePurchase(String itemName){
        model.actualizePurchase(itemName);
    }
    public List<Transaction> getTransactionList(){
        return model.getTransactionList();
    }
    public double getTotalRevenue(){
        return model.getTotalRevenue();
    }
}
