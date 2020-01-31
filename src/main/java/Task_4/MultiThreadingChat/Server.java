package Task_4.MultiThreadingChat;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class Server {
    private static ArrayList<Connection> clients = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private static Map<Integer, String> sonnets = new HashMap<>();
    private static ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(7051);
            System.out.println("The chat server is running...");
            while (!server.isClosed()) {
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress().getHostName() + " connected");
                Connection thread = new Connection(socket);
                clients.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        } finally {
            closeAll();
        }
    }

    private static void closeAll() {
        try {
            server.close();
            synchronized (clients) {
                for (Connection client : clients) {
                    client.disconnect();
                }
            }
        } catch (Exception e) {
            System.err.println("Connections are not closed properly");
        }
    }

    private static class Connection extends Thread {
        private String nameOfUser;       // user name
        private PrintStream ods;    // output data
        private BufferedReader ids; // input data
        private InetAddress addr;
        private Socket sk;

        public Connection(Socket s) {
            this.sk = s;
            this.addr = s.getInetAddress();
            try {
                ids = new BufferedReader(new InputStreamReader(sk.getInputStream()));
                ods = new PrintStream(sk.getOutputStream(), true);

            } catch (IOException e) {
                e.printStackTrace();
                disconnect();
            }
        }

        @Override
        public void run() {
            try {
                while (!sk.isClosed()) {
                    ods.println("Enter NickName: ");
                    nameOfUser = ids.readLine();
                    if (nameOfUser.isBlank()) {
                        ods.println("NickName cann't be empty! Try one more time :)");
                        continue;
                    } else if (names.contains(nameOfUser)) {
                        ods.println("This NickName has registered already! Try one more time :)");
                        continue;
                    }
                    synchronized (names) {
                        names.add(nameOfUser);
                        ods.println("Welcome " + nameOfUser);
                    }
                    synchronized (clients) {
                        for (Connection ct : clients
                        ) {
                            if (clients.size() == 1) {
                                ct.ods.println("NO users online");
                                break;
                            }
                            ct.ods.println("******\nUsers Online - " + clients.size() + "\n******");
                            ct.ods.print(countUsersOnline(clients));
                            ct.ods.println("Private Messaging - \"send:{number in list}:{message}\"");
                            ct.ods.println("Get Shakespeare Sonnet - \"getsonnet\"");
                            ct.ods.println("Exit - \"exit\"");
                        }
                    }
                    String chatSession;
                    while (true) {
                        chatSession = ids.readLine();
                        if (chatSession.equals("exit")) {
                            sk.close();
                            clients.remove(this);
                            break;
                        }
                        onClientMessage(this, chatSession);
                    }
                    synchronized (clients) {
                        for (Connection client : clients) {
                            client.ods.println(nameOfUser + " has left conversation");
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Disconnect");
            } finally {
                disconnect(); // destroy thread
            }
        }

        public String countUsersOnline(ArrayList<Connection> listNames) {
            StringBuilder usersList = new StringBuilder();
            int number = 1;
            for (Connection listName : listNames) {
                usersList.append(number)
                        .append(". ")
                        .append(listName.nameOfUser)
                        .append("\n");
                number++;
            }
            return usersList.toString();
        }

        public void onClientMessage(Connection clientThread, String message) {
            if (message.startsWith("send:")) {
                int msgPos = message.indexOf(":", 5);
                String onlyMessage = message.substring(msgPos + 1);
                System.out.println(message.substring(5, msgPos));
                ods.println(clientThread.nameOfUser + ": *PM* " + onlyMessage + "* sent!");
                try {
                    int clientIndex = Integer.parseInt(message.substring(5, msgPos));
                    if (clientIndex < 1) {
                        ods.println("Wrong number");
                        return; //send:1:test
                    }
                    clients.get(clientIndex - 1).ods.println(String.format("%s: *PM* %s", clientThread.nameOfUser, message.substring(msgPos + 1)));
                    System.out.println("PM from [" + clientThread.nameOfUser + "]: \"" + onlyMessage + "\" to [" + clients.get(clientIndex - 1).nameOfUser + "]");
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormatException: " + nfe.getMessage());
                    ods.println(nfe.getMessage() + " is not a number");
                }
            } else if (message.startsWith("getsonnet")) {
                ods.println(sonnets.get(getSonnet() - 1));
            } else {
                // Send message to all in this chat room
                synchronized (clients) {
                    for (Connection client : clients) {
                        if (clients.size() == 1) {
                            client.ods.println("NO users online");
                            break;
                        }
                        client.ods.println(nameOfUser + ": " + message);
                    }
                }
            }
        }

        public int getSonnet() {
            try {
                String filePath = String.format("%s/src/main/java/Task_4/MultiThreadingChat/sonnets/sonnets.txt", new File("").getAbsolutePath());
                var lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
                StringBuilder sonnet = new StringBuilder();
                int counter = 0;
                for (String nc : lines) {
                    if (nc.matches("\\d+") || nc.matches("x x x")) {
                        if (!sonnet.toString().equals("")) {
                            if (!nc.matches("x x x")) {
                                int df = Integer.parseInt(nc) - 1;
                                nc = Integer.toString(df);
                            }
                            sonnets.put(counter++, nc + "\n" + sonnet.toString().trim());
                            sonnet = new StringBuilder();
                        }
                    } else {
                        sonnet.append(nc);
                        sonnet.append("\n");
                    }
                }
                sonnets.remove(0);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return new Random().nextInt(sonnets.size() - 1);
        }

        public void disconnect() {
            try {
                if (ods != null) {
                    ods.close();
                }
                if (ids != null) {
                    ids.close();
                }
                System.out.println(addr.getHostName() + " disconnecting");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.interrupt();
            }
        }
    }
}