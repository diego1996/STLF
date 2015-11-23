/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.LoginGUI;
import Vista.PrincipalGUI;
import Vista.Progress;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingWorker;

/**
 *
 * @author diego
 */

public class controladorLogin implements ActionListener {

    private LoginGUI login;
    private JButton blogin;
    private PrincipalGUI principal;
    private controladorPrincipal cprincipal;
    private Progress progress;

    public controladorLogin(LoginGUI login) {
        this.login = login;
        blogin = this.login.getblogin();
        blogin.addActionListener(this);
        this.login.setSize(500, 350);
        this.login.setTitle("Log in - STLF");
        this.login.setLocationRelativeTo(null);
        this.login.setVisible(true);
        this.login.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            login.dispose();
            System.exit(EXIT_ON_CLOSE);
         }        
      });    
        //login.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(blogin == e.getSource()) {
            final SwingWorker worker = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception {
                    
                    progress = new Progress();
                    login.dispose();
                    principal = new PrincipalGUI();
                    cprincipal = new controladorPrincipal(principal, progress.getIPS());
		return null;
		}	
            };
            worker.execute();
            
            
            
            
        }
    }
    
}
