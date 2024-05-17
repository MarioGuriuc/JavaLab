package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class GameServer
{
    public static final int PORT = 8100;
    private static LinkedList<ClientThread> clients;
    private static boolean running = true;

    public GameServer() throws IOException
    {
        clients = new LinkedList<>();
        try (ServerSocket serverSocket = new ServerSocket(PORT))
        {
            while (running)
            {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                clients.add(new ClientThread(socket));
                clients.getLast().start();
            }
        }
        catch (IOException e)
        {
            System.err.println("Oops... " + e);
        }
    }

    public static void stop() throws InterruptedException
    {
        for (ClientThread client : clients)
        {
            client.stopClient();
            ClientThread.currentThread().join();
        }
        running = false;
    }

    public static void main(String[] args) throws IOException
    {
        GameServer server = new GameServer();
    }
}
