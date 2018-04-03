package gui;

import domain.Vehicle;
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
import javax.swing.JTextField;

/**
 *
 * @author Marco y Maria
 */
public class WindowUpdate extends JInternalFrame implements ActionListener {

    private JLabel jlInfot;

    private JLabel jlName;
    private JLabel jlYear;
    private JLabel jlMileage;
    private JLabel jlAmerican;
    private JLabel jlSerie;

    private JTextField jtfName;
    private JTextField jtfYear;
    private JTextField jtfMileage;
    private JTextField jtfSerie;
    private JTextField jtfAmerican;

    private JButton jbtnLoad;
    private JButton jbtnSave;

    private JComboBox jcbElejir;

    public VehicleFile vehicle;

    public WindowUpdate() throws IOException {
        super("Update Vehicle", true, true, true, true);
        this.setSize(600, 400);
        this.setLayout(null);
        this.setVisible(true);
        this.init();
    }//cosntructor

    public void init() throws IOException {
        File f = new File("Vehicles.dat");
        this.vehicle = new VehicleFile(f);
        Color a = new Color(160, 242, 221);
        Color b = new Color(4, 178, 209);
        setBackground(a);

        this.jcbElejir = new JComboBox();
        this.jcbElejir.setBounds(400, 50, 120, 20);
        this.jcbElejir.addActionListener(this);
        for (int i = 0; i < vehicle.getAllVehicles().size(); i++) {
            this.jcbElejir.addItem(vehicle.getAllVehicles().get(i).getSerie());
        }//for

        this.jlInfot = new JLabel("Choose a serie");
        this.jlInfot.setBounds(410, 0, 100, 60);

        this.jlName = new JLabel("Name:");
        this.jlName.setBounds(40, 10, 100, 20);

        this.jlYear = new JLabel("Year:");
        this.jlYear.setBounds(40, 50, 100, 20);

        this.jlAmerican = new JLabel("American:");
        this.jlAmerican.setBounds(40, 90, 100, 20);

        this.jlMileage = new JLabel("Mileage:");
        this.jlMileage.setBounds(40, 130, 100, 20);

        this.jlSerie = new JLabel("Serie:");
        this.jlSerie.setBounds(40, 170, 100, 20);

        this.jtfName = new JTextField(25);
        this.jtfName.setBounds(150, 10, 200, 30);

        this.jtfYear = new JTextField(25);
        this.jtfYear.setBounds(150, 50, 200, 30);

        this.jtfAmerican = new JTextField(25);
        this.jtfAmerican.setBounds(150, 90, 200, 30);

        this.jtfMileage = new JTextField(25);
        this.jtfMileage.setBounds(150, 130, 200, 30);

        this.jtfSerie = new JTextField(25);
        this.jtfSerie.setBounds(150, 170, 200, 30);

        this.jbtnLoad = new JButton("Show Information");
        this.jbtnLoad.setBounds(120, 280, 150, 40);

        this.jbtnLoad.setBackground(b);
        this.jbtnLoad.addActionListener(this);

        this.jbtnSave = new JButton("Save");
        this.jbtnSave.setBounds(290, 280, 100, 40);
        this.jbtnSave.setBackground(b);
        this.jbtnSave.addActionListener(this);
        this.jbtnSave.setVisible(false);

        this.add(this.jcbElejir);
        this.add(this.jlName);
        this.add(this.jlAmerican);
        this.add(this.jlYear);
        this.add(this.jlMileage);
        this.add(this.jlSerie);
        this.add(this.jtfName);
        this.add(this.jtfAmerican);
        this.add(this.jtfYear);
        this.add(this.jtfMileage);
        this.add(this.jtfSerie);
        this.add(this.jbtnLoad);
        this.add(this.jbtnSave);
        this.add(this.jlInfot);

        this.jtfSerie.setVisible(false);
        this.jlSerie.setVisible(false);

        this.jtfMileage.setVisible(false);
        this.jlMileage.setVisible(false);
    }//startComponents

    public void load() throws IOException {
        for (int i = 0; i < vehicle.getAllVehicles().size(); i++) {
            if (this.jcbElejir.getSelectedItem().equals(vehicle.getAllVehicles().get(i).getSerie())) {
                this.jtfName.setText(vehicle.getAllVehicles().get(i).getName());
                this.jtfYear.setText(vehicle.getAllVehicles().get(i).getYear() + "");
                this.jtfMileage.setText(vehicle.getAllVehicles().get(i).getMileage() + "");
                this.jtfAmerican.setText(vehicle.getAllVehicles().get(i).isAmerican() + "");
                this.jtfSerie.setText(vehicle.getAllVehicles().get(i).getSerie() + "");

                try {
                    vehicle.deleteVehicle(vehicle.getAllVehicles().get(i).getSerie());
                    //vehicle.deleteVehicle(vehicle.getAllVehicles().get(i).getSerie() + "");
                } catch (IOException ex) {
                    Logger.getLogger(WindowUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }//if
        }//for
    }//load

    public void save() {

        Vehicle vh = new Vehicle(
                jtfName.getText(),
                Integer.parseInt(jtfYear.getText()),
                Float.parseFloat(jtfMileage.getText()),
                Boolean.parseBoolean(jtfAmerican.getText()),
                Integer.parseInt(jtfSerie.getText()));
        try {
            vehicle.addEndRecord(vh);
        } catch (IOException ex) {
            Logger.getLogger(WindowUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(rootPane, "¡Vehicle successfully updated!");
    }//save

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtnLoad) {
            try {
                load();
            } catch (IOException ex) {
                Logger.getLogger(WindowUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.jbtnSave.setVisible(true);
        }
        if (e.getSource() == this.jbtnSave) {
            save();
        }

    }//action

}//class
