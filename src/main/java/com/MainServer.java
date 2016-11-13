package com;

/**
 * Created by Main Server on 13.11.2016.
 */


        import java.io.IOException;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.ArrayList;
        import java.util.List;

/**
 * @author artem.ptonchakov@calisto.email
 */

public class MainServer {

    private static List<ServerThread> threadList = new ArrayList<ServerThread>();

    volatile public static boolean isOccupied;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9999);

        System.out.println("Server started");
        System.out.println("Socket listening");


        while (true) {
            Socket socket = ss.accept();

            ServerThread serverThread = new ServerThread(socket);
            MainServer.threadList.add(serverThread);
            serverThread.start();
        }

    }
}
