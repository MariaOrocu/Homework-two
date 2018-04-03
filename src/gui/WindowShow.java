package gui;

import file.VehicleFile;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Marco y Maria
 */
public class WindowShow extends JInternalFrame {

    private JTextArea jtaShow;
    public VehicleFile vehicle;

    public WindowShow() throws IOException {
        super("List of Vehicles", true, true, true, true);
        this.setBackground(Color.WHITE);
        this.setSize(800, 400);
        this.setLayout(null);
        this.setVisible(true);
        init();
    }//constructor

    public void init() throws IOException {
        File f = new File("Vehicles.dat");
        this.vehicle = new VehicleFile(f);

        String showAll = "";

        for (int i = 0; i < vehicle.getAllVehicles().size(); i++) {
            showAll += vehicle.getAllVehicles().get(i);
            showAll += "\n";
        } //for

        this.jtaShow = new JTextArea(showAll);
        this.jtaShow.setBounds(0, 10, 700, 400);
        this.jtaShow.setEditable(false);
        this.add(this.jtaShow);

    }//startComponents   
}//class
