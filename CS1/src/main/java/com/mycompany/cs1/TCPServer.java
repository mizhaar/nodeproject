/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cs1;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mzohaib
 */
public class TCPServer implements Runnable {

    public static final int PORT = 3335;
    public static final int MAX_CLIENTS = 5;
    private final ExecutorService executor = Executors.newFixedThreadPool(MAX_CLIENTS);

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            for (;;) {
                Socket clientSocket = serverSocket.accept();
                App tcpSubscriber = new App(clientSocket);
                executor.submit(tcpSubscriber);


            }
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String args[]) {
        TCPServer server = new TCPServer();
        server.run();
    }

    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
