import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ItemPanel extends JPanel {
    private final JLabel nameLabel;
    private final JLabel priceLabel;
    private final String itemCoordinates;
    private String panelName;
    private String itemPrice;



    public ItemPanel(String itemName,String itemPrice, String itemCoordinates){
        this.itemCoordinates = itemCoordinates;
        this.panelName = itemName;
        this.itemPrice = itemPrice;
        nameLabel = new JLabel(panelName);
        priceLabel = new JLabel(itemPrice);
        this.setLayout(new GridLayout(2,1));

        this.add(nameLabel);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2,1));
        infoPanel.add(new JLabel(itemCoordinates));
        infoPanel.add(priceLabel);
        this.add(infoPanel);
        this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5),  new EtchedBorder()));

    }

    public String getName(){
        return panelName;
    }
    public String getPrice(){
        return itemPrice;
    }
    public String getCoordinates(){return itemCoordinates;}
    public void setOutOfStock(){
        nameLabel.setText("Empty");
        priceLabel.setText("");

        this.revalidate();

    }
}
