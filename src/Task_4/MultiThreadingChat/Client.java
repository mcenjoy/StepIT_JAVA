package Task_4.MultiThreadingChat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
    Socket socket;
    BufferedReader idc;
    PrintStream odc;
    Scanner scanner = new Scanner(System.in);

    public Client() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 7051);
            // Socket socket = new Socket("SERVER_NAME", 7051);
            odc = new PrintStream(socket.getOutputStream());
            idc = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            /*   Output all data */
            ClientDataSender dataSender = new ClientDataSender();
            dataSender.start();
            String inputString = "";
            while (!inputString.equals("exit")) {
                inputString = scanner.nextLine();
                if (!inputString.isEmpty()) {
                    odc.println(inputString);
                }
            }
            dataSender.setStopped(true);
        } catch (UnknownHostException e) {
            System.err.printf("Address is not available: %s%n", e);
        } catch (IOException e) {
            System.err.printf("Error I/О: %s%n", e);
        }
    }

    public static void main(String[] args) {
        new Client();
    }

    private class ClientDataSender extends Thread {
        private boolean stopped = false;

        public void setStopped(boolean stopped) {
            this.stopped = stopped;
        }

        @Override
        public void run() {
            try {
                while (!stopped) {
                    String str = idc.readLine();
                    if (str != null) {
                        System.out.println(str);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while receiving data");
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }
    }

    public void disconnect() {
        try {
            if (odc != null) odc.close();
            if (idc != null) idc.close();
            if (socket != null) socket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}