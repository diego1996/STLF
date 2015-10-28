/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Vista.PrincipalGUI;
import Controlador.Servidor._Server;
import Controlador.Cliente._Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author diego
 */
public class controladorPrincipal implements ActionListener {
    
    private PrincipalGUI principal;
    private JButton bsend, bgodir, bfile, bpath;
    private JCheckBox cbenviar, cbrecibir;
    private JComboBox cbips;
    private JTextField tportc, tports, tip, tfile, tpath;
    private JFileChooser fcfile, fcfile1;
    private _Server _Server;
    private _Socket socket;
    
    private String path, path1;
    
    
    public controladorPrincipal(PrincipalGUI principal) {
        this.principal = principal;
        fcfile = this.principal.getFcfile();
        fcfile1 = this.principal.getFcfile1();
        tportc = this.principal.getTportc();
        tportc.setEnabled(false);
        tfile = this.principal.getTfile();
        tfile.setEnabled(false);
        tports = this.principal.getTports();
        tip = this.principal.getTip();
        tip.setText(getIP());
        bsend = this.principal.getBsend();
        bsend.setEnabled(false);
        bsend.addActionListener(this);
        bgodir = this.principal.getBgodir();
        bgodir.addActionListener(this);
        bfile = this.principal.getBfile();
        bfile.setEnabled(false);
        bfile.addActionListener(this);
        bpath = this.principal.getBpath();
        bpath.addActionListener(this);
        
        cbips = this.principal.getCbips();
        getDataJCB(cbips);
        cbips.setEnabled(true);
        cbips.addActionListener(this);
        
        cbenviar = this.principal.getCbenviar();
        cbrecibir = this.principal.getCbrecibir();
        cbenviar.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {         
            //System.out.println("Izquierda Checkbox: " 
              //  + (e.getStateChange()==1?"checked":"unchecked"));
                if(e.getStateChange() ==1 ) {
                    bfile.setEnabled(true);
                    cbips.setEnabled(true);
                    tportc.setEnabled(true);
                }else {
                    bsend.setEnabled(false);
                    bfile.setEnabled(false);
                    cbips.setEnabled(false);
                    tportc.setEnabled(false);
                }
            }           
        });

        cbrecibir.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {         
                //System.out.println("Derecha Checkbox: " 
                  //  + (e.getStateChange()==1?"checked":"unchecked"));
                if(e.getStateChange() ==1 ) {
                    if(!tip.getText().equals("") && !tports.getText().equals("") && !bpath.getText().equals("...")) {
                        recibir(tip.getText(), Integer.parseInt(tports.getText()), path1);
                        tip.setEnabled(false);
                        tports.setEnabled(false);
                        bpath.setEnabled(false);
                       
                    }else {
                        JOptionPane.showMessageDialog(null, "No puede dejar campos vacios");
                    }
                }else {
                    ConexionClose();
                    try {
                        if(_Server != null)
                            _Server.close();
                    } catch (IOException ex) {
                        Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    tip.setEnabled(true);
                    tports.setEnabled(true);
                    bpath.setEnabled(true);
                }
            }   
        });
        
        principal.setSize(800, 400);
        principal.setTitle("Usuario- STLF");
        principal.setLocationRelativeTo(null);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    private void getDataJCB(JComboBox cbips) {
        this.cbips = cbips;
        cbips.addItem( getIP().substring(0, getIP().length() - 3) + "1" );
        InetAddress inAdd;
        for (int i = 100; i < 115; i++) {
            
            try {
                if(getIP().substring(getIP().length()-2, getIP().length()).equals(".1") ) {
                    inAdd = InetAddress.getByName(getIP().substring(0, getIP().length() - 1) + i);
                }else {
                    inAdd = InetAddress.getByName(getIP().substring(0, getIP().length() - 3) + i);
                }
                if (inAdd.isReachable(1500)) {
                System.out.println("IP: " + inAdd.getHostAddress());
                cbips.addItem(inAdd.getHostAddress());
                System.out.println("HOST: " + inAdd.getHostName());
                System.out.println();
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
    }
    
    private String getIP(){
     /*  Enumeration e;
        try {
            e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                System.out.println(i.getHostAddress());
            }
        }
        } catch (SocketException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
*/

        try {
         // Cogemos la IP 
            InetAddress address = InetAddress.getLocalHost();

            byte[] bIPAddress = address.getAddress();

            // IP en formato String
            String sIPAddress = "";

            for (int x=0; x<bIPAddress.length; x++) {
              if (x > 0) {
                // A todos los numeros les anteponemos
                // un punto menos al primero    
                sIPAddress += ".";
              }
              // Jugamos con los bytes y cambiamos el bit del signo
              sIPAddress += bIPAddress[x] & 255;	   
            }
            return sIPAddress;
        }catch(UnknownHostException ex) {return null;}
    }
    
    public void ConexionClose() {


    }
    
    public void recibir(String IP, int port, String path) {
        try {
            System.out.println("Starting server...");
            System.out.println(InetAddress.getByName(IP));
            _Server=new _Server(port, 500, InetAddress.getByName(IP), path);
            _Server.init();
           /* try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //if(_Server.isRunning())
            //new _Socket(InetAddress.getByName("127.0.0.1"), 8081).upFile(new File("C:\\bt4-r2.iso.part"));
        } catch (UnknownHostException e) {
             JOptionPane.showMessageDialog(null, "Direccion IP o puerto ya esta en uso");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "El puerto ya esta en uso");
        }
    }

    public void enviar(String IP, int port, String path) {
        try {
            socket = new _Socket(InetAddress.getByName(IP), port);
            socket.upFile(new File(path));
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "No se encuentra un Servidor");
            //e.printStackTrace();
        } catch (IOException e) {
           // e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se encuentra un Servidor");
        }
    }
    
    public String getSelectedIP() {
        //for(int i=0;i<cbips.getItemCount();i++) {
            //System.out.println(cbips.getSelectedItem().toString());
        //}
        return cbips.getSelectedItem().toString();
    }
    
    public void actionPerformed(ActionEvent e) {
        if(bsend == e.getSource()) {
            enviar(getSelectedIP(), Integer.parseInt(tportc.getText()), path);
        }
        if(bgodir == e.getSource()) {
            
        }
        if(bfile == e.getSource()) {
            if(!tportc.getText().equals("") && !getSelectedIP().equals("")) {
                // jfilechooser
                int rta = fcfile.showOpenDialog(null);
                if(rta == JFileChooser.APPROVE_OPTION) {
                    File archivoelegido = fcfile.getSelectedFile();
                    path = archivoelegido.getAbsolutePath();
                    bfile.setText(archivoelegido.getName());
                    bsend.setEnabled(true);
                }
                
            }else {
                JOptionPane.showMessageDialog(null, "Debe completar los campos");
            }
        }
        if(bpath == e.getSource()) {
            fcfile1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int rta = fcfile1.showSaveDialog(null);
                if(rta == JFileChooser.APPROVE_OPTION) {
                    File archivoelegido = fcfile1.getSelectedFile();
                    path1 = archivoelegido.getAbsolutePath();
                    bpath.setText(archivoelegido.getName());
                }
        }
        
        if(cbips == e.getSource()) {
            getSelectedIP();
        }
    }
}
