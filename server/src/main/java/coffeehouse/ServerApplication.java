package coffeehouse;

import java.io.*;
import java.net.*;

public class ServerApplication {
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("Insufficient arguments provides, usage: program <port>");
		}

		int port = Integer.parseInt(args[0]);

		Socket s = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		String str;

		try {
			ServerSocket server = new ServerSocket(port);
			s = server.accept();
			pw = new PrintWriter(s.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println("Connected");
		} catch (java.io.IOException ex) {
			System.out.println("Not connected");
		}

		try {
			while ((str = br.readLine()) != null) {
				System.out.println("The message: " + str);

				if (str.equals("bye")) {
					pw.println("bye");
					break;
				} else {
					str = "Server returns " + str;
					pw.println(str);
				}
			}
			pw.close();
			br.close();

			s.close();
		} catch (java.io.IOException ex) {

		}
	}
}
