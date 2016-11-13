package com;

/**
 * Created by Server Thread on 13.11.2016.
 */

        import java.io.*;
        import java.net.Socket;
        import java.util.Scanner;



public class ServerThread extends Thread {

    private Socket socket;


    public ServerThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

        try {
            Scanner scanner = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("waiting for query");
            printWriter.flush();

            String msgFromClient;

            while (true) {


                msgFromClient = reader.readLine();

                if (MainServer.isOccupied) {
                    printWriter.println("Server is occupied. Please wait");
                    printWriter.flush();
                } else {
                    MainServer.isOccupied = true;
                    System.out.println("Accepted socked from " + socket.getInetAddress() + ":" + socket.getPort());

                    System.out.println("msg from client = " + msgFromClient);

                    String serverMsg = scanner.nextLine();

                    printWriter.println(serverMsg);


                    System.out.println("Socket listening");

                    MainServer.isOccupied = false;

                    printWriter.flush();
                }

            }
        } catch (
                IOException e) {
            System.out.println("Server stopped.....");
            System.exit(0);
        }
    }
}


