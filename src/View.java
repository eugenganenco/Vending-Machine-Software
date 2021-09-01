import javax.swing.*;
import java.io.File;

public class View extends JFrame{
    private Controller controller;
    public View(Controller controller){
        this.controller = controller;
        new DefaultFrame(this);
    }
    public boolean supplyItems(File file){
        return controller.supplyItems(file);
    }
    public Controller getController(){
        return controller;
    }




}
