package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread
{
    private boolean running = true;
    private final Socket socket;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        while (running)
        {
            try (socket)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                if (request.equals("exit"))
                {
                    running = false;
                    GameServer.stop();
                    continue;
                }
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String answer = "Hello " + request + "!";
                out.println(answer);
                out.flush();
            }
            catch (IOException | InterruptedException e)
            {
                System.err.println("Communication error... " + e);
            }
        }
    }

    public void stopClient()
    {
        running = false;
    }
}
