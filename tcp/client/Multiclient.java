/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Multiclient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Socket s=new Socket("127.0.0.1",1234);
        PrintWriter p=new PrintWriter(s.getOutputStream(),true);
        Scanner keybord=new Scanner(System.in);
        receiveclient r=new receiveclient(s);
        r.start();
        System.out.println("enter your name");
        String name=keybord.nextLine();
        p.println(name);
        String namereceiver;
        while(true)
        {
            
            
            
        System.out.println("enter a nbr of message:1)Broadcast,2)message to a specifique person,3)list of all client name");
        String msg = keybord.nextLine();
        if(msg.equals("1"))
        {
            System.out.println("enter your message please :");
            msg=keybord.nextLine();
            p.println("Brodcast:"+msg);
        }
        else if(msg.equals("2"))
        {
             System.out.println("enter the name of receiver:");
            namereceiver=keybord.nextLine();
            System.out.println("enter your message:");
            
            msg=keybord.nextLine();
            p.println("msg:"+namereceiver+":"+msg);
        }
        else if(msg.equals("3"))
        {
            p.println("all");
        }
        else
        {
            System.out.println("please enter a :1 or 2 or 3");
        }
            
        }
    }
    
}
