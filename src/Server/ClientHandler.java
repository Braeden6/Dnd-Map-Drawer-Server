package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// ClientHandler class
public class ClientHandler extends Thread {

    public static final int INTERVAL = 40;

    final DataInputStream input;
    final DataOutputStream output;
    final Socket socket;
    private Character character;
    private boolean[][] blackPixels;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, boolean[][] pixels, Character character) {
        blackPixels = pixels;
        this.character = character;
        this.socket = s;
        this.input = dis;
        this.output = dos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                boolean mainProgram = input.readBoolean();
                boolean sendInfo = input.readBoolean();
                boolean characterLocation = input.readBoolean();
                if (mainProgram && sendInfo) {
                    getBlackPixels();
                } else if (!mainProgram){
                    giveBlackPixels();
                }
                if (characterLocation) {
                    //receive character from viewers
                }
                if (mainProgram) {
                    //send character info to main program
                }
            } catch (IOException e) {
                closeClientHandler();
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: closes DataInputStream, DataOutputStream, and Socket
    private void closeClientHandler() {
        try {
            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: send the 2D array for the blackPixels screen to the sever
    private void giveBlackPixels() {
        try {
            for (int w = 0; w < Server.ARRAY_WIDTH; w++) {
                for (int h = 0; h < Server.ARRAY_HEIGHT; h++) {
                    output.writeBoolean(blackPixels[w][h]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: get the 2D array for the blackPixels screen from the server
    private void getBlackPixels() {
        try {
            for (int w = 0; w < Server.ARRAY_WIDTH; w++) {
                for (int h = 0; h < Server.ARRAY_HEIGHT; h++) {
                    blackPixels[w][h] = input.readBoolean();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}