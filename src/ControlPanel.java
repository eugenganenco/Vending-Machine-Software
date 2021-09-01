import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener {
    private final ItemsPanel itemsPanel;
    private final JLabel selectedItemNameLabel = new JLabel("Name: ");
    private final JLabel selectedItemPriceLabel = new JLabel("Price: ");
    private final JButton buyButton = new JButton("Buy");
    private final JButton cancelSelectionButton = new JButton("Cancel Selection");
    private final JButton backButton = new JButton("Back");

    private String selectedItemCoordinate;
    private String selectedItemName;
    private String selectedItemPrice;

    public ControlPanel(ItemsPanel itemsPanel){
        selectedItemName = null;
        selectedItemPrice = null;
        selectedItemCoordinate = null;
        this.itemsPanel = itemsPanel;
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        ButtonsPanel buttonsPanel = new ButtonsPanel(this);
        this.add(buttonsPanel);
        JPanel selectedItemPanel = new JPanel();
        selectedItemPanel.setLayout(new GridLayout(5,1));
        selectedItemPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5),
                new LineBorder(Color.BLACK,1)));
        selectedItemPanel.add(selectedItemNameLabel);
        selectedItemPanel.add(selectedItemPriceLabel);
        buyButton.addActionListener(this);
        cancelSelectionButton.addActionListener(this);
        backButton.addActionListener(this);
        selectedItemPanel.add(buyButton);
        selectedItemPanel.add(cancelSelectionButton);
        selectedItemPanel.add(backButton);
        this.add(selectedItemPanel);



    }
    public boolean updateSelectedItemPanel(String coordinate){
        if (itemsPanel.validCoordinates(coordinate)) {
            selectedItemCoordinate = coordinate;
            selectedItemName = itemsPanel.getItemPanel(coordinate).getName();
            selectedItemPrice = itemsPanel.getItemPanel(coordinate).getPrice();

            selectedItemNameLabel.setText("Name: " + selectedItemName);
            selectedItemPriceLabel.setText("Price: " + selectedItemPrice);

            return true;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton){
            System.out.println("clicked");
            System.out.println(selectedItemNameLabel.getText());
            if (selectedItemName != null) {
                if (itemsPanel.isItemInStorage(selectedItemName)) {
                    itemsPanel.actualizePurchase(selectedItemName, selectedItemCoordinate);
                    JOptionPane.showMessageDialog(null, "You have successfully purchased a " + selectedItemName +
                            " for " + selectedItemPrice + "$.");
                    cancelSelectionButton.doClick();
                }
                else {
                    JOptionPane.showMessageDialog(null,"This slot is empty. Select another one.");
                    cancelSelectionButton.doClick();
                }

            }
            else
                JOptionPane.showMessageDialog(null,"You must first select a product.");
        }
        if (e.getSource() == cancelSelectionButton){
            selectedItemName = null;
            selectedItemPrice = null;
            selectedItemCoordinate = null;
            selectedItemNameLabel.setText("Name: ");
            selectedItemPriceLabel.setText("Price: ");

        }
        if (e.getSource() == backButton){
            itemsPanel.disposeWithTheFrame();
        }
    }
}
