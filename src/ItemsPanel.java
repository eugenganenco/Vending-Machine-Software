import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ItemsPanel extends JPanel {
    private HashMap<String, ItemPanel> itemPanelHashMap;
    private View view;
    private VendingMachineFrame vendingMachineFrame;
    public ItemsPanel(View view,VendingMachineFrame vendingMachineFrame) {
        this.vendingMachineFrame = vendingMachineFrame;
        this.view = view;
        this.itemPanelHashMap = new HashMap<>();
        this.setLayout(new GridLayout(4, 8));

        NumberToCoordinateConverter numberToCoordinateConverter = new NumberToCoordinateConverter();
        HashMap<String,ItemStock> itemStockHashMap = view.getController().getItemStockHashMap();
        int i = 0;
        for (String itemName : itemStockHashMap.keySet()) {
            System.out.println(i);
            String itemCoordinate = numberToCoordinateConverter.getCoordinate(i);
            ItemPanel itemPanel = new ItemPanel(itemName,itemStockHashMap.get(itemName).getPrice(),itemCoordinate);
            itemPanelHashMap.put(itemCoordinate,itemPanel);
            this.add(itemPanel);
            i++;
        }
        while (i<32){
            System.out.println(i);
            String itemCoordinate = numberToCoordinateConverter.getCoordinate(i);
            ItemPanel itemPanel = new ItemPanel("Empty"," ",itemCoordinate);
            itemPanelHashMap.put(itemCoordinate,itemPanel);
            this.add(itemPanel);
            i++;
        }
    }
    public ItemPanel getItemPanel(String coordinate){
        return itemPanelHashMap.get(coordinate);
    }
    public boolean validCoordinates(String coordinate){
        return itemPanelHashMap.containsKey(coordinate);
    }
    public boolean isItemInStorage(String itemName){
        return view.getController().getItemStockHashMap().containsKey(itemName);
    }
    public void actualizePurchase(String itemName, String itemCoordinate){
        System.out.println(itemName);
        System.out.println(view.getController().getItemStockHashMap());
        if (view.getController().getItemStockHashMap().get(itemName).getAmount() == 1)
            itemPanelHashMap.get(itemCoordinate).setOutOfStock();



        view.getController().actualizePurchase(itemName);
    }
    public void disposeWithTheFrame(){
        vendingMachineFrame.disposeWithTheFrame();
    }




}
