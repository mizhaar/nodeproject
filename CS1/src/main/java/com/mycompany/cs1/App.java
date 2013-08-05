package com.mycompany.cs1;

/**
 * Hello world!
 *
 */
import java.io.BufferedReader;
//import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.util.Scanner;


public class App implements Runnable
{
    private Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private static final String ERROR = "ERROR";
    
    
    public App(Socket socket) throws IOException{
        this.socket = socket;
        
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //constructs socket writer with autoFlush
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
    }
    
    public void run(){
       try{
           String userInput = reader.readLine();
       
         // if (userInput == null || writer.checkError()) {
           //    break;
           //}    
       
           if (validateClientMessage(userInput)) {
           send("You said: " + userInput);
           Scanner in = new Scanner(System.in);
//                          System.out.println("Enter a string");
           String s = in.nextLine();
           if(s.equals("ping"))
           sendResponse("Client replies pong");       
           }
        
           else 
           {
           sendErrorMessage("Invalid Message Format");
            }
                    
         } catch (SocketTimeoutException timeoutEx){
             System.err.println("not responding");
             System.exit(-1);
            }
       
       catch (IOException ex) {
            //logger.log(Level.SEVERE, "Socket I/O Exception", ex);
            System.out.println("some thing is wrong");     
       }
       
    }

        private synchronized void send(String message) {
        StringBuilder builder = new StringBuilder();
        builder.append(message);
        //builder.append(CARRIAGE);
        String serverMsg = builder.toString();
        writer.println(serverMsg);
        System.out.println(serverMsg);
    }

        private synchronized void sendResponse(String message) {
        StringBuilder builder = new StringBuilder("+");
        builder.append(message);
       // builder.append(CARRIAGE);
        writer.println(builder.toString());
        System.out.println(message);

    }
        
        
        private void sendErrorMessage(String message) {
        StringBuilder builder = new StringBuilder(ERROR);
        builder.append(" ");
        builder.append(message);
        //builder.append(CARRIAGE);
        send(builder.toString());
    }

        
         private static boolean validateClientMessage(String message) {
        return true;
       // return message.endsWith(CARRIAGE);
    }

          public synchronized void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (reader != null) {
            reader.close();
        }
        if (writer != null) {
            writer.close();
        }
    }

           public static void main(String args[]) {
           
               System.out.println("Enter your message");
               
           }          
          
}



