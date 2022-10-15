/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiserv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author HP
 */
public class Multiserv {
public static Vector<handle> clients;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket welcome=new ServerSocket(1234);
        clients=new Vector<handle>(); 
        while(true)
        {
            System.out.println("Server begin working");
            Socket s=welcome.accept();
            System.out.println("connection receive");
            handle h=new handle(s,clients);
            h.start();
            clients.add(h);
        }
        
    }
    
}
