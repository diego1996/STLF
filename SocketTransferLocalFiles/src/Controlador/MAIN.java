/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.LoginGUI;

/**
 *
 * @author diego
 */


public class MAIN {
    private static LoginGUI login;
    private static controladorLogin clogin;
    
    public static void main(String args[]) {
        login = new LoginGUI();
        clogin = new controladorLogin(login);
    }
}
