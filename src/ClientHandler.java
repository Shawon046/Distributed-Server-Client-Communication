import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable{

    // All client names as a Hash
    private static Set<String> names = new HashSet<>();

    // all the print writers for all the clients, used for broadcast.
    private static Set<PrintWriter> writers = new HashSet<>();

    private String name;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;

    /**
     * Constructs a handler thread, squirreling away the socket.
     */
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(socket.getOutputStream(), true);
    }


    /**
     * Services this thread's client by repeatedly requesting a screen name until a
     * unique one has been submitted, then acknowledges the name and registers the
     * output stream for the client in a global set, then repeatedly gets inputs and
     * broadcasts them.
     */
    @Override
    public void run() {
        try {
            // Keep requesting a name until we get a unique one.
            while (true) {
                out.println("SUBMITNAME");
                name = br.readLine();
                if (name == null) {
                    return;
                }
                synchronized (names) {
                    if (!name.isBlank() && !names.contains(name)) {
                        names.add(name);
                        break;
                    }
                }
            }

            // Now that a successful name has been chosen, add the socket's print writer
            // to the set of all writers so this client can receive broadcast messages.
            // But BEFORE THAT, let everyone else know that the new person has joined!
            out.println("NAMEACCEPTED " + name);
            for (PrintWriter writer : writers) {
                writer.println("MESSAGE " + name + " has joined");
            }
            writers.add(out);

            // Accept messages from this client and broadcast them.
            while (true) {
                String input = br.readLine();;
                if (input.toLowerCase().startsWith("/quit")) {
                    return;
                }
                for (PrintWriter writer : writers) {
                    writer.println("MESSAGE " + name + ": " + input);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (out != null) {
                writers.remove(out);
            }
            if (name != null) {
                System.out.println(name + " is leaving");
                names.remove(name);
                for (PrintWriter writer : writers) {
                    writer.println("MESSAGE " + name + " has left");
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

}
