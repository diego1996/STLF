/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author diego
 */
import Controlador.controladorPrincipal;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class ProgressSample {
    
    public ArrayList IPS;
    private JProgressBar progressBar;
    
  public ProgressSample() {
      IPS = new ArrayList<String>();
    JFrame f = new JFrame("JProgressBar Sample");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLocationRelativeTo(null);
    Container content = f.getContentPane();
    progressBar = new JProgressBar();
    
    InetAddress inAdd;
        int Aux = 0;
        for (int i = 100; i < 115; i++) {
            Aux=((i-99)*100)/15;
            progressBar.setValue(Aux);
            progressBar.setStringPainted(true);
            Border border = BorderFactory.createTitledBorder("Listando IPs...");
            progressBar.setBorder(border);
                    content.add(progressBar, BorderLayout.NORTH);
            try {
                if(getIP().substring(getIP().length()-2, getIP().length()).equals(".1") ) {
                    inAdd = InetAddress.getByName(getIP().substring(0, getIP().length() - 1) + i);
                }else {
                    inAdd = InetAddress.getByName(getIP().substring(0, getIP().length() - 3) + i);
                }
                if (inAdd.isReachable(1000)) {
                System.out.println("IP: " + inAdd.getHostAddress());
                    IPS.add(inAdd.getHostAddress());
                    
                    f.setSize(300, 100);
                    f.setVisible(true);
                
                System.out.println("HOST: " + inAdd.getHostName());
                System.out.println();
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        }
        progressBar.setValue(100);

        f.dispose();
  }

    public ArrayList getIPS() {
        return IPS;
    }
  
  
  
  private String getIP(){

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
}

