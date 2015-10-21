/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Servidor;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author diego
 */
public class _Server extends ServerSocket implements Runnable{
    private boolean running = false;
    private String path;
     
    public _Server(int port, int backlog, InetAddress bindAddr, String path)
            throws IOException {
        super(port, backlog, bindAddr);
        this.path=path;
        running=true;
        System.out.println(">Server started on port: "+port);
    }
    public void init(){
        new Thread(this).start();
    }
    private class Connection extends Thread{
         
        public static final int DEFAULT_BUFFER_SIZE = 1024;
        private String path;
        private DataInputStream dataInputStream;
        private Socket socket;
         
        public Connection(Socket socket, String path){
            this.path = path;
            try {
                this.socket=socket;
                dataInputStream=new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run(){
            
            try{
                
                String fileName = dataInputStream.readUTF();
                System.out.println(">>Receiving file: "+fileName);
                System.out.println(">>Please wait...");
                FileOutputStream fileOutputStream=new FileOutputStream(path+"/"+fileName);
                byte b[]=new byte[DEFAULT_BUFFER_SIZE];
                int len=0,off=0;
                while((len=dataInputStream.read(b))>0) {
                    fileOutputStream.write(b, off, len);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                System.out.println(">>Task completed!");
                } catch (IOException e) {
                    e.printStackTrace();
            }finally{
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public boolean isRunning(){
        return running;
    }
    @Override
    public void run() {
        System.out.println(">Waiting for connections...");
        Socket socket=null;
        while(running){
            try {
                socket=accept();
                System.out.println(">>New Connection Received: "+socket.getInetAddress());
                
                Connection connection=new Connection(socket, path);
                System.out.println(">>Starting thread for connection...");
                connection.start();
            } catch (IOException e) {
                //
            }
        }
    }
}