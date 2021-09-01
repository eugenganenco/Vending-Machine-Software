import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ButtonsPanel extends JPanel implements ActionListener{
    private JLabel outputLabel = new JLabel();
    private JButton selectButton;
    private JButton clearButton;
    private List<JButton> buttonsList;
    private ControlPanel controlPanel;
    public ButtonsPanel(ControlPanel controlPanel){
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5),
                new LineBorder(Color.BLACK,1)));
        this.buttonsList = new ArrayList<>();
        this.controlPanel = controlPanel;
        this.add(outputLabel);


        JPanel numbersPanel = new JPanel();
        numbersPanel.setLayout(new GridLayout(3,3));
        for (int i = 1; i < 10; i++){
            JButton numberButton = new JButton(String.valueOf(i));
            numberButton.addActionListener(new CustomActionListener(numberButton,this));
            numbersPanel.add(numberButton);
            buttonsList.add(numberButton);
        }
        this.add(numbersPanel);

        JPanel lettersPanel = new JPanel();
        lettersPanel.setLayout(new GridLayout(1,4));
        for (char letter = 'a'; letter <= 'd'; letter++){
            JButton letterButton = new JButton(String.valueOf(letter).toUpperCase(Locale.ROOT));
            letterButton.addActionListener(new CustomActionListener(letterButton,this));
            lettersPanel.add(letterButton);
            buttonsList.add(letterButton);
        }
        this.add(lettersPanel);


        JPanel outputControlPanel = new JPanel();
        outputControlPanel.setLayout(new GridLayout(1,2));
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        outputControlPanel.add(clearButton);
        outputControlPanel.add(selectButton);
        this.add(outputControlPanel);


    }
    public void addToOutputString(String addString){
        outputLabel.setText(outputLabel.getText() + addString);
    }
    public String getOutputString(){
        return outputLabel.getText();
    }
    public void disableButtons(){
        for (JButton button:buttonsList){
            button.setEnabled(false);
        }
    }
    private void enableButtons(){
        for (JButton button:buttonsList){
            button.setEnabled(true);
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton){
            outputLabel.setText("");
            enableButtons();

        }
        if (e.getSource() == selectButton){
            if (!controlPanel.updateSelectedItemPanel(outputLabel.getText()))
                JOptionPane.showMessageDialog(null,"There is no such product.");
            clearButton.doClick();
        }
    }
}
