import java.io.*;
import java.net.*;
import java.util.*;

/*************************/
class MyServer
{
    ArrayList al=new ArrayList();
    ArrayList users=new ArrayList();
    ServerSocket ss;
    Socket s;

    public final static int PORT=10;
    public final static String UPDATE_USERS="updateuserslist:";
    public final static String LOGOUT_MESSAGE="@@logoutme@@:";
    public MyServer()
    {
        System.out.println("dorkar ki");
    }

    /////////////////////////
    public static void main(String [] args) throws IOException
    {
        System.out.println("Hello Java");
        // MyServer ms = new MyServer();
		
		// creating list of process
        List<String> list = new ArrayList<String>();
        list.add("notepad.exe");
 
        // create the process
        ProcessBuilder build = new ProcessBuilder(list);
 
        // checking the command i list
        System.out.println("command: " + build.command());
    }
/////////////////////////
}
