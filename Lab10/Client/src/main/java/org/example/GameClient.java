package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient
{
    private static Reader reader;
    private static boolean running = true;

    public static void main(String[] args) throws IOException
    {
        while (running)
        {
            reader = new InputStreamReader(System.in);
            String serverAddress = "127.0.0.1";
            int PORT = 8100;
            try (
                    Socket socket = new Socket(serverAddress, PORT);
                    PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream())))
            {
                String request;
                System.out.println("Enter your request: ");
                request = new BufferedReader(reader).readLine();
                out.println(request);
                String response = in.readLine();
                System.out.println(response);
            }
            catch (UnknownHostException e)
            {
                System.err.println("No server listening... " + e);
            }
        }
    }
}
