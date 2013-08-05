/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cs1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mzohaib
 */
public class ClientWriter implements Runnable {

    private final PrintWriter writer;
    private final Socket clientSocket;
    private final BufferedReader reader;

    public ClientWriter(Socket socket) throws IOException {
        this.clientSocket = socket;
        writer = new PrintWriter(clientSocket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public BufferedReader getWriterThread() {
        return reader;
    }

    @Override
    public void run() {
        try {
            send();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void send() throws IOException {
        System.out.println("ready to send..");
        while (!writer.checkError()) {
            String msg = reader.readLine();
            writer.println(msg);
            if(msg.equals("ping"))
            {
                System.out.println("Server say: pong");
            }
        }
        System.out.println("Interrupted");
    }
}
