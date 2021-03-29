import java.net.*;  
import java.io.*;  
import java.util.*;
import java.util.concurrent.*;


public class Server {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();


	public static void main(String args[])throws Exception{
        int PORT = 3333 ;
        ServerSocket listener = new ServerSocket (PORT);
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        int nProcesses = br.read();
//        pool = Executors.newFixedThreadPool(nProcesses = 4);

        System.out.println("Enter the number of Processes / Nodes \n");
        Scanner sc = new Scanner(System.in);
        int nProcesses = sc.nextInt();
        ExecutorService pool = Executors.newFixedThreadPool(nProcesses) ;

        while(true) {
            System.out.println("The server is running...");
            Socket client= listener.accept();
            System.out.println(" [SERVER] Connected to client! ");
            ClientHandler clientThread = new ClientHandler(listener.accept()) ;
            clients.add(clientThread);
            pool.execute(clientThread);
        }


	}


}  