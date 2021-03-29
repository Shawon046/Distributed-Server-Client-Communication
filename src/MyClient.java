import java.net.*;  
import java.io.*;  
import java.util.*;  

class MyClient{
	private static final String serverIP = "localhost";

//	private String getName() {
//		return "Choose a screen name:", "Screen name selection";
//	}

	public static void main(String args[])throws Exception{
		int PORT = 3333;
		Socket socket=new Socket(serverIP,PORT);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader input = new BufferedReader (new InputStreamReader (socket.getInputStream())) ;
		BufferedReader keyboard = new BufferedReader (new InputStreamReader (System.in));

		while(true)
		{
			System.out.println ("(Type 'quit' to end)> ");
			String clientResponse = keyboard.readLine();
			if(clientResponse.equals("quit"))
			{
				break ;
			}
//			System.out.println ("Server says: "+ serverResponse);
			out.println(clientResponse);

			String serverResponse = input.readLine ();
			System.out.println ("Server says: "+ serverResponse);
		}


		socket.close();
		System.exit(0);
		//MyClient client = new MyClient();
	}
}  