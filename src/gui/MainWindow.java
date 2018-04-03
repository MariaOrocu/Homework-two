package gui;

import gui.WindowDelete;
import gui.WindowInsert;
import gui.WindowShow;
import gui.WindowUpdate;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Marco y Maria
 */
public class MainWindow extends JFrame implements ActionListener {

    private JDesktopPane jdp;
    private JMenuBar jmenubar;
    private JMenuItem jmiInsert;
    private JMenuItem jmiUpDate;
    private JMenuItem jmiDelete;
    private JMenuItem jmiShow;

    public MainWindow() {
        super("HOMEWORK TWO, VEHICLES");
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        startComponents();

    }//fin constructor

    public void startComponents() {
        this.jdp = new JDesktopPane();
        this.jdp.setLayout(null);
        this.jdp.setSize(1000, 600);
        Color c = new Color(160, 242, 221);
        this.jdp.setBackground(c);
        this.jmenubar = new JMenuBar();

        this.jmiInsert = new JMenuItem("Insert Vehicle");
        this.jmiInsert.addActionListener(this);
        this.jmenubar.add(this.jmiInsert);

        this.jmiUpDate = new JMenuItem("Update Vehicle");
        this.jmiUpDate.addActionListener(this);
        this.jmenubar.add(this.jmiUpDate);

        this.jmiDelete = new JMenuItem("Delete Vehicle");
        this.jmiDelete.addActionListener(this);
        this.jmenubar.add(this.jmiDelete);

        this.jmiShow = new JMenuItem("Show Vehicles");
        this.jmiShow.addActionListener(this);
        this.jmenubar.add(this.jmiShow);

        this.setJMenuBar(jmenubar);
        this.getContentPane().add(this.jdp);

    }//startComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jmiInsert) {
            WindowInsert insert = new WindowInsert();
            this.jdp.add(insert);
            insert.setVisible(true);
        }//jmiInsert

        if (e.getSource() == this.jmiDelete) {
            try {
                WindowDelete delete = new WindowDelete();
                this.jdp.add(delete);
            } //jmiDelete
            catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == this.jmiUpDate) {
            try {
                WindowUpdate upDate = new WindowUpdate();
                this.jdp.add(upDate);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == this.jmiShow) {
            try {
                WindowShow show = new WindowShow();
                this.jdp.add(show);
                show.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//action

}//class
