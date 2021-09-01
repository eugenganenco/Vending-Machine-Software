import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AuditFrame extends JFrame implements ActionListener {
    private final View view;
    private final JPanel transactionsPanel = new JPanel();
    private final List<Transaction> transactionList;
    private final JButton backButton = new JButton("Back");
    public AuditFrame(View view){
        transactionList = view.getController().getTransactionList();
        this.view = view;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(250,650);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
        createTransactionsPanel();
        createInformationPanel();
    }
    private void createTransactionsPanel(){
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel,BoxLayout.PAGE_AXIS));

        for (Transaction transaction:transactionList){
            JPanel transactionPanel = new JPanel();
            transactionPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5),
                    new LineBorder(Color.BLACK,1)));;
            transactionPanel.setLayout(new GridLayout(3,1));
            transactionPanel.add(new JLabel(transaction.getItemName()));
            transactionPanel.add(new JLabel(transaction.getPrice()));
            transactionPanel.add(new JLabel(transaction.getDate()));
            transactionsPanel.add(transactionPanel);
        }
        this.add(transactionsPanel);
    }

    private void createInformationPanel(){
        this.add(new JLabel("Items sold: " + transactionList.size()));
        this.add(new JLabel("Total revenue: " + view.getController().getTotalRevenue() + "$"));
        backButton.addActionListener(this);
        this.add(backButton);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            new DefaultFrame(view);
            this.dispose();
        }
    }
}
