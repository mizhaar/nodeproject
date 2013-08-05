/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cs1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mzohaib
 */
public class ClientReader implements Runnable {

    private final Socket clientSocket;
    private final BufferedReader serverReader;
    
    
    
    public ClientReader(Socket socket) throws IOException {
        this.clientSocket = socket;
        serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
        
       
        
    }

   
   
    @Override
    public void run() {
        try {
            recieve();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void recieve() throws IOException {
        System.out.println("listening..");
        String s;
        while ((s = serverReader.readLine()) != null) {
            //if(s.equals("ping"))
            //{
            System.err.println(s);
            //}
        }
        System.out.println("end");
    }
}
