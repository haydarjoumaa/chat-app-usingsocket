/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiserv;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author HP
 */
public class handle extends Thread{
    Socket s;
   Vector<handle> clients;
    PrintWriter p;
    Scanner input;
    String name;
    
    public handle(Socket s,Vector<handle> clients) throws IOException{
        this.clients=clients;
        this.s=s;
        p=new PrintWriter(s.getOutputStream(),true);
        input=new Scanner(s.getInputStream());
    }
    public void run(){
         String m=input.nextLine();
         name=m;
            sendmessage(m+" has joined the conversation");
        while(true)
        {
           
             m=input.nextLine();
       StringTokenizer st = new StringTokenizer(m, ":");
       String type=st.nextToken();
       
       if(type.equals("Brodcast")){
           sendmessage(this.name+" > "+st.nextToken());
       }
       else if(type.equals("msg"))
       {
           String receiver=st.nextToken();
         
           sendto(receiver,this.name+" > "+st.nextToken()+" (this is private message)");
       }
       else
       {
           allname();
       }
        }
    }
    public void sendmessage(String m)
    {
        for(int i=0;i<clients.size();i++)
        {
            clients.get(i).p.println(m);
        }
    }
     public void sendto(String receiver,String m)
    {
        boolean exsits=false;
        for(int i=0;i<clients.size();i++)
        {
            if(clients.get(i).name.equals(receiver))
            {
                clients.get(i).p.println(m);
                exsits=true;
            }
        }
        if(!exsits)
            p.println("the name is wrong please check the name the receiver");
    }
     public void allname()
     {
         ArrayList<String> names=new ArrayList();
         for(int i=0;i<clients.size();i++)
        {
            
              names.add(clients.get(i).name);
            
        }
         String message=String.join(",", names);
         this.p.println(message);
     }
}
