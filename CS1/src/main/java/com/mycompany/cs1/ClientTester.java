package com.mycompany.cs1;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author mzohaib
 */
public class ClientTester {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("started");
        Socket clientSocket = new Socket("localhost", 3335);

        ClientReader clientReader = new ClientReader(clientSocket);
        Thread readerThread = new Thread((Runnable) clientReader);
        readerThread.start();

        ClientWriter clientWriter = new ClientWriter(clientSocket);
        Thread writerThread = new Thread(clientWriter);
        writerThread.start();
    }
}
