/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclient;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class receiveclient extends Thread{
    Socket s;
    Scanner input;
    public receiveclient(Socket s) throws IOException
    {
        this.s=s;
        input=new Scanner(s.getInputStream());
        
    }
    public void run()
    {
     while(true)
     {
         System.out.println(input.nextLine());
     }
    }
}
