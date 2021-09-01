import javax.swing.*;


public class VendingMachineFrame extends JFrame {
    private final View view;
    public VendingMachineFrame(View view){
        this.view = view;
        this.getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1250,500);
        this.setResizable(false);
        this.setVisible(true);
        ItemsPanel itemsPanel = new ItemsPanel(view,this);
        this.add(itemsPanel);
        this.add(new ControlPanel(itemsPanel));

    }
    public void disposeWithTheFrame(){
        new DefaultFrame(view);
        this.dispose();
    }


}
