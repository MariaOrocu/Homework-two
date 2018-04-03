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
 * @author Marco Y Maria
 */
public class WindowInsert extends JInternalFrame implements ActionListener {

    private JLabel jlName;
    private JLabel jlYear;
    private JLabel jlMileage;
    private JLabel jlAmerican;
    private JLabel jlSerie;

    private JTextField jtfName;
    private JTextField jtfYear;
    private JTextField jtfMileage;
    private JTextField jtfSerie;

    private JButton jbtnInsert;

    private JComboBox jcbChoose;

    public WindowInsert() {

        super("Add Vehicle", true, true, true, true);
        this.setSize(500, 500);
        this.setLayout(null);
        setVisible(true);
        init();
    }//constructor

    public void init() {

        Color a = new Color(160, 242, 221);
        Color b = new Color(4, 178, 209);
        setBackground(a);

        this.jcbChoose = new JComboBox();
        this.jcbChoose.setBounds(170, 130, 200, 30);
        this.jcbChoose.addActionListener(this);
        this.jcbChoose.addItem("true");
        this.jcbChoose.addItem("false");

        this.jlName = new JLabel("Name:");
        this.jlName.setBounds(40, 10, 100, 20);

        this.jlYear = new JLabel("Year:");
        this.jlYear.setBounds(40, 50, 100, 20);

        this.jlMileage = new JLabel("Mileage:");
        this.jlMileage.setBounds(40, 90, 100, 20);

        this.jlAmerican = new JLabel("American:");
        this.jlAmerican.setBounds(40, 130, 100, 20);

        this.jlSerie = new JLabel("Serie:");
        this.jlSerie.setBounds(40, 170, 100, 20);

        this.jtfName = new JTextField(25);
        this.jtfName.setBounds(170, 10, 200, 30);

        this.jtfYear = new JTextField(25);
        this.jtfYear.setBounds(170, 50, 200, 30);

        this.jtfMileage = new JTextField(25);
        this.jtfMileage.setBounds(170, 90, 200, 30);

        this.jtfSerie = new JTextField(25);
        this.jtfSerie.setBounds(170, 170, 200, 30);
        //hacia al lado,hacia abajo,largo,ancho
        this.jbtnInsert = new JButton("Insert");
        this.jbtnInsert.setBounds(270, 220, 100, 35);

        this.jbtnInsert.setBackground(b);
        this.jbtnInsert.addActionListener(this);

        this.add(this.jlName);
        this.add(this.jlYear);
        this.add(this.jlMileage);
        this.add(this.jlAmerican);
        this.add(this.jlSerie);
        this.add(this.jtfName);
        this.add(this.jtfYear);
        this.add(this.jtfMileage);
        this.add(this.jtfSerie);
        this.add(this.jbtnInsert);
        this.add(this.jcbChoose);

    }//startComponents

    public void clean() {
        this.jtfYear.setText("");
        this.jtfName.setText("");
        this.jtfMileage.setText("");
        this.jtfSerie.setText("");
    }//clean

    public void insertVehicle() throws IOException {

        File f = new File("Vehicles.dat");
        VehicleFile vehicle = new VehicleFile(f);
        for (int i = 0; i < vehicle.getAllVehicles().size(); i++) {
            if (this.jtfSerie.getText().equals(vehicle.getAllVehicles().get(i).getSerie() + "")) {
                JOptionPane.showMessageDialog(rootPane, "Serie already exists", "ERROR", JOptionPane.ERROR_MESSAGE);
                String p = JOptionPane.showInputDialog(rootPane, "Insert a new serie", "Update", JOptionPane.WARNING_MESSAGE);
                this.jtfSerie.setText(p);
            }
        }
        Vehicle v = new Vehicle(
                jtfName.getText(),
                Integer.parseInt(jtfYear.getText() + ""),
                Float.parseFloat(jtfMileage.getText() + ""),
                Boolean.parseBoolean((String) jcbChoose.getSelectedItem() + ""),
                Integer.parseInt(jtfSerie.getText() + ""
                ));
        try {
            vehicle.addEndRecord(v);
        } catch (IOException ex) {
            Logger.getLogger(WindowInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(rootPane, "¡Vehicle saved successfully!");
        clean();

    }//insertVehicle

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtnInsert) {
            try {
                insertVehicle();
            } catch (IOException ex) {
                Logger.getLogger(WindowInsert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//actionPerformed

}//class
