import java.net.*;
import java.io.*;
import java.util.*;


public class MyServer {

    public static void main(String args[])throws Exception{
        int PORT = 3333 ;
        ServerSocket listener = new ServerSocket (PORT);

        System.out.println(" [SERVER] The server is running...");
        Socket client= listener.accept();
        System.out.println(" [SERVER] Connected to client! ");

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedReader keyboard = new BufferedReader (new InputStreamReader (System.in));

        try {
            while (true) {
                String req = in.readLine();
                System.out.println ("Client says: "+ req);
                String serverResponse = keyboard.readLine();
                out.println(serverResponse);
            }
        } finally {
            in.close();
            out.close();
        }



    }
}
