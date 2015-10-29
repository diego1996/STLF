/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author diego
 */
public class LoginGUI extends JFrame {
    
    private JLabel ltitle, luser, lpass;
    private JTextField tuser, tpass;
    private JButton blogin, bclear, bregister;
    
    public LoginGUI() {
        setLayout(null);
        
        ltitle = new JLabel("<html><h1><center>Socket Transfer Local Files</h1></html>");
        ltitle.setBounds(70, 20, 360, 30);
        
        luser = new JLabel("Usuario: ");
        luser.setBounds(100, 100, 100, 30);
        
        tuser = new JTextField(30);
        tuser.setBounds(200, 100, 200, 30);
        
        lpass = new JLabel("Contraseña: ");
        lpass.setBounds(100, 150, 100, 30);
        
        tpass = new JTextField(30);
        tpass.setBounds(200, 150, 200, 30);
        
        bclear = new JButton("Borrar Campos");
        bclear.setBounds(100, 200, 140, 30);
        
        blogin = new JButton("Conectarme");
        blogin.setBounds(280, 200, 120, 30);
        
        bregister = new JButton("Registarme");
        bregister.setBounds(200, 240, 120, 30);
        
        
        add(ltitle);
        
        add(luser);
        add(tuser);
        add(lpass);
        add(tpass);
        
        add(blogin);
        add(bclear);
        add(bregister);
    }
    
    public JButton getblogin() {
        return blogin;
    }
    
}
