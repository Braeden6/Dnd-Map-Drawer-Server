package Server;// Java implementation of  Server side


// It contains two classes : Server and ClientHandler 
// Save file as Server.java 

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Server class 
public class Server {
    public static final int ARRAY_WIDTH = 256;
    public static final int ARRAY_HEIGHT = 144;

    private static boolean[][] blackPixels;
    private static ArrayList<Character> characters;

    public static void main(String[] args) throws IOException {
        characters = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(5000);
        blackPixels = new boolean[ARRAY_WIDTH][ARRAY_HEIGHT];
        // client request 
        while (true) {
            Socket s = null;
            Character character = new Character();

            try {
                // socket object to receive incoming client requests 
                s = serverSocket.accept();
                System.out.println("A new client is connected : " + s);
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");
                characters.add(character);
                // create a new thread object 
                Thread t = new ClientHandler(s, dis, dos, blackPixels, character);

                // Invoking the start() method 
                t.start();
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}

