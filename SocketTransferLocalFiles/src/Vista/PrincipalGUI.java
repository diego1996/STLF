/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author diego
 */
public class PrincipalGUI extends JFrame {
    
    private JLabel ltitle, lsend, lrecevice, lfile, lfiler, lipc, lips, lportc, lports, lpath;
    private JCheckBox cbenviar, cbrecibir;
    private JFileChooser fcfile,fcfile1;
    private JComboBox cbips;
    private JButton bsend, bgodir, bfile, bpath;
    private JTextField tip, tportc, tports, tpath, tfile, tips;
    
    public PrincipalGUI() {
        setLayout(null);
        ltitle = new JLabel("<html><h1><center>Socket Transfer Local Files</h1></html>");
        ltitle.setBounds(220, 20, 360, 30);
        
        lsend = new JLabel("Enviar: ");
        lsend.setBounds(50, 100, 100, 30);
        
        cbenviar = new JCheckBox(" (iniciar Cliente) ");
        cbenviar.setBounds(150, 100, 150, 30);
        
        lrecevice = new JLabel("Recibir: ");
        lrecevice.setBounds(500, 100, 100, 30);
        
        cbrecibir = new JCheckBox(" (iniciar Servidor) ");
        cbrecibir.setBounds(600, 100, 150, 30);
        
        lipc = new JLabel("IP: ");
        lipc.setBounds(50, 150, 50, 30);
        
        
        cbips = new JComboBox();
        cbips.setBounds(150, 150, 150, 30);
  
        cbips.addItem("127.0.0.1");
    
        
        //tips = new JTextField(50);
        //tips
        
        lips = new JLabel("IP: ");
        lips.setBounds(500, 150, 50, 30);
        
        tip = new JTextField(50);
        tip.setBounds(600, 150, 150, 30);
        
        lportc = new JLabel("Puerto: ");
        lportc.setBounds(50, 200, 100, 30);
        
        tportc = new JTextField(50);
        tportc.setBounds(150, 200, 100, 30);
        
        lports = new JLabel("Puerto: ");
        lports.setBounds(500, 200, 100, 30);
        
        tports = new JTextField(50);
        tports.setBounds(600, 200, 100, 30);
        
        lfile = new JLabel("Archivo: ");
        lfile.setBounds(50, 250, 100, 30);
        
        bfile = new JButton(" ... ");
        bfile.setBounds(150, 250, 150, 30);
        
        lpath = new JLabel("Guardar en: ");
        lpath.setBounds(500, 250, 100, 30);
        
        bpath = new JButton("...");
        bpath.setBounds(600, 250, 150, 30);
        
        lfiler = new JLabel("Archivo: ");
        lfiler.setBounds(500, 300, 100, 30);
        
        tfile = new JTextField(30);
        tfile.setBounds(600, 300, 100, 30);
        
        bgodir = new JButton("Abrir");
        bgodir.setBounds(700, 300, 50, 30);
        
        bsend = new JButton("Enviar Archivo");
        bsend.setBounds(150, 300, 150, 30);
        
        
        
        fcfile = new JFileChooser();
        fcfile1 = new JFileChooser();
        
        add(ltitle);
        add(lsend);
        add(cbenviar);
        add(lrecevice);
        add(cbrecibir);
        
        add(lipc);
        add(cbips);
        add(lips);
        add(tip);
        
        add(lportc);
        add(tportc);
        add(lports);
        add(tports);
        
        add(lfile);
        add(bfile);
        add(lpath);
        add(bpath);
        add(lfiler);
        add(bsend);
        
        add(lfiler);
        add(tfile);
        add(bgodir);
    }

    public JButton getBsend() {
        return bsend;
    }

    public JButton getBgodir() {
        return bgodir;
    }

    public JButton getBfile() {
        return bfile;
    }

    public JButton getBpath() {
        return bpath;
    }

    public JCheckBox getCbenviar() {
        return cbenviar;
    }

    public JCheckBox getCbrecibir() {
        return cbrecibir;
    }

    public JComboBox getCbips() {
        return cbips;
    }

    public JTextField getTportc() {
        return tportc;
    }

    public JTextField getTports() {
        return tports;
    }

    public JTextField getTip() {
        return tip;
    }

    public JTextField getTpath() {
        return tpath;
    }

    public JTextField getTfile() {
        return tfile;
    }
    
  

    public JFileChooser getFcfile() {
        return fcfile;
    }
    
    public JFileChooser getFcfile1() {
        return fcfile1;
    }
    
    
}
