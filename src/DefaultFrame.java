import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DefaultFrame extends JFrame implements ActionListener {
    private final JButton loadButton;
    private final JButton buyButton;
    private final JButton auditButton;
    private final View view;

    public DefaultFrame(View view){
        this.view = view;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.setSize(250,75);

        loadButton = new JButton("Load");
        buyButton = new JButton("Buy");
        auditButton = new JButton("Audit");
        loadButton.addActionListener(this);
        buyButton.addActionListener(this);
        auditButton.addActionListener(this);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,3));
        buttonsPanel.add(loadButton);
        buttonsPanel.add(buyButton);
        buttonsPanel.add(auditButton);
        this.add(buttonsPanel);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                if (this.isJsonFile(file)){
                    JOptionPane.showMessageDialog(null,"File uploaded successfully.");
                    this.view.getController().supplyItems(file);
                }
                else JOptionPane.showMessageDialog(null,"Error. Wrong file extension. '.json' file required.");
            }
        }
        if (e.getSource() == buyButton){
            if (!view.getController().storageIsEmpty()){
                new VendingMachineFrame(view);
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog(null,"The vending machine is empty.");
        }
        if (e.getSource() == auditButton){
            new AuditFrame(view);
            this.dispose();
        }
    }
    private boolean isJsonFile(File file) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            String extension = fileName.substring(index + 1);
            return extension.equals("json");
        }
        return false;
    }
}
