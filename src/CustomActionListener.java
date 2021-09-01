import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomActionListener implements ActionListener {
    private JButton jButton;
    private ButtonsPanel buttonsPanel;
    public CustomActionListener(JButton jButton,ButtonsPanel buttonsPanel){
        this.jButton = jButton;
        this.buttonsPanel = buttonsPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton){
            if (buttonsPanel.getOutputString().length() < 2)
                buttonsPanel.addToOutputString(jButton.getText());
            if (buttonsPanel.getOutputString().length() == 2)
                buttonsPanel.disableButtons();
        }
    }
}
