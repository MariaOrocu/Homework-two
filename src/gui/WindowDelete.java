package gui;

import file.VehicleFile;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco y Maria
 */
public class WindowDelete extends JInternalFrame implements ActionListener {

    private JLabel jlBanner;
    private JComboBox jcbSearch;
    private JButton jbtnSearch;
    public VehicleFile vehicle;

    public WindowDelete() throws IOException {
        super("Delete Vehicle", true, true, true, true);
        this.setSize(400, 400);
        this.setLayout(null);
        setVisible(true);
        init();

    }//cosntructor

    public void init() throws IOException {
        File f = new File("Vehicles.dat");
        vehicle = new VehicleFile(f);

        Color a = new Color(160, 242, 221);
        Color b = new Color(4, 178, 209);
        setBackground(a);

        this.jlBanner = new JLabel("Choose a serie");
        this.jlBanner.setBounds(65, 15, 400, 20);
        this.jcbSearch = new JComboBox();
        this.jcbSearch.setBounds(60, 40, 100, 20);
        for (int i = 0; i < vehicle.getAllVehicles().size(); i++) {
            this.jcbSearch.addItem(vehicle.getAllVehicles().get(i).getSerie());
        }//for
        this.jbtnSearch = new JButton("Delete");
        this.jbtnSearch.setBounds(180, 40, 110, 30);
        this.jbtnSearch.setBackground(b);
        this.jbtnSearch.addActionListener(this);

        this.add(this.jbtnSearch);
        this.add(this.jcbSearch);
        this.add(this.jlBanner);

    }//starcomponents

    public void delete() throws IOException {
        for (int i = 0; i < vehicle.getAllVehicles().size(); i++) {
            if (this.jcbSearch.getSelectedItem().equals(vehicle.getAllVehicles().get(i).getSerie())) {
                try {
                    vehicle.deleteVehicle(vehicle.getAllVehicles().get(i).getSerie());
                } catch (IOException ex) {
                    Logger.getLogger(WindowDelete.class.getName()).log(Level.SEVERE, null, ex);
                }
            }//try-catch
        }//for
        JOptionPane.showMessageDialog(null, "Vehicle successfully deleted");
    }//delete

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbtnSearch) {
            try {
                delete();
            } catch (IOException ex) {
                Logger.getLogger(WindowDelete.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//action
}//class
