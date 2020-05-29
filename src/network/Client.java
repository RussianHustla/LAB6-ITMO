package network;

import app.Reader;
import app.Request;
import app.Serialization;
import app.UserInterface;
import collection.Coordinates;
import collection.Flat;
import collection.House;
import commands.Command;
import commands.CommandsManager;

import javax.jws.soap.SOAPBinding;
import javax.security.auth.callback.TextInputCallback;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final int PORT = 8000;
        final String ADDR = "127.0.0.1";




//        SocketAddress clientSocket = new InetSocketAddress("127.0.0.1",8000);
//        SocketChannel socketChannel = SocketChannel.open(clientSocket);

        //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //Coordinates testCoords = new Coordinates(12.5,54.2);
        //byte b[] = {0,1,2,3,4,5,6,7,8,9};
        String b = "Иисус пожалуйста помоги!!";



        while (true) {
            String answer = "";
            try {
                answer = Reader.request();
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода");
                e.printStackTrace();
            }
            try {
            Socket s = new Socket(ADDR, PORT);
            OutputStream out = s.getOutputStream();
            InputStream in = s.getInputStream();
            UserInterface userInterface = new UserInterface(in, out);
            //System.out.println(answer.length());

                if (answer != null){

                    if (answer.length() > 0) {
                        String[] parsedCommand = answer.split(" ");
                        if (CommandsManager.getInstance().contains(parsedCommand[0])) {
                            Command command = CommandsManager.getInstance().getCommand(parsedCommand[0]);
                            if (command.getCommand().equals("add") || command.getCommand().equals("add_if_min")) {
                                //System.out.println("это add! или add_if_min?");
                                String arg[] = new String[]{"-","-"};
                                command.setArgs(arg); //заглушка от NPE
                                Flat flat = Reader.requestForFlat();
                                command.setObject(flat);
                                userInterface.send(command);
                            } else if (command.getCommand().equals("update")){
                                String arg[] = new String[1];
                                arg[0] = parsedCommand[1];
                                command.setArgs(arg);
                                //System.out.println("это update!");
                                Flat flat = Reader.requestForFlat();
                                command.setObject(flat);
                                userInterface.send(command);

                            } else if (command.getCommand().equals("count_greater_than_house")) {
                                //System.out.println("это count_greater_than_house");
                                String arg[] = new String[]{"-","-"};
                                command.setArgs(arg); //заглушка от NPE
                                House house = Reader.requestForHouse();
                                command.setObject(house);
                                userInterface.send(command);
                            } else if (command.getCommand().equals("execute_script")) {
                                //System.out.println("это xecute_script");
                                userInterface.send(answer);
                                boolean EOF = false;
//                            do {
//                                System.out.println(userInterface.receive());
//                            } while (!userInterface.receive().equals("EOF"));
                                while(EOF == false) {
                                    Object o = userInterface.receive();
                                    System.out.println(o);
                                    if (o.equals("EOF")) EOF = true;
                                }
                            } else if (command.getCommand().equals("exit")){
                                System.out.println("Выход из программы");
                                System.exit(0);
                            } else {
                                //System.out.println("обычная команда");
                                userInterface.send(answer);
                                //System.out.println(answer);
                            }
//                Socket s = new Socket(ADDR, PORT);
//                OutputStream out = s.getOutputStream();
//                InputStream in = s.getInputStream();
//                UserInterface userInterface = new UserInterface(in, out);
                            System.out.println(userInterface.receive());
                        } else {
                            System.err.println("Неизвестная команда, используйте команду help, чтобы посмотреть список всех доступных команд.");
                        }
                    }

                }
                userInterface.send(null);

                in.close();
                out.close();
                s.close();
            } catch (ConnectException e) {
                System.err.println("Сервер недоступен : (");
//                System.out.println("Выйти из программы? (y/n)");
//                String conf = Reader.request();
//                if (conf.equals("y")) {
//
//                }

            }
            String exit = "exit";
            if (exit.equals(answer)) {
                System.out.println("Выход из программы");
                System.exit(0);
            }
        }





        //byte[] test = Serialization.SerializeObject(testCoords);
        //ByteBuffer buffer = ByteBuffer.wrap(new Serialization().SerializeObject(testCoords));
//        ByteBuffer buffer = ByteBuffer.wrap(new Serialization().SerializeObject(b));
//        for (int i = 0; i < 10; i++) {
//            System.out.println(buffer.array()[i]);
//        }
//        buffer.flip();
//
//        Object o = new Serialization().DeserializeObject(buffer.array());
//        System.out.println(o);
//
//        ByteBuffer bufferr = ByteBuffer.wrap(new Serialization().DeserializeObject(buffer.array()));
//        for (int i = 0; i < 10; i++) {
//            System.out.println(bufferr.array()[i]);
//        }

//        socketChannel.write(buffer);
//        buffer.clear();
//
//        socketChannel.close();

//        bufferedWriter.write(test.toString());
//        bufferedWriter.newLine();
//        bufferedWriter.flush();

//        bufferedReader.close();
//        bufferedWriter.close();
        //clientSocket.close();
    }

//    private void sendCommand(Command command) throws IOException{
//        if (command != null){
//            if (command.getCommand().contains("exit")) {
//                System.out.println("Удачи!");
//                System.exit(0);
//            }
//        }
//        ByteBuffer buffer = ByteBuffer.wrap(new Serialization().SerializeObject(command));
//    }

//    public static void write(byte message[]) throws IOException {
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        buffer.clear();
//        Request request = new Request(message);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
//        objectOutputStream.writeObject(request);
//        buffer.put(out.toByteArray());
//        buffer.flip();
//    }

//    public static void send(Object o, OutputStream out) throws IOException {
//        Request request = new Request(new Serialization().SerializeObject(o));
//        byte msg[] = new Serialization().SerializeObject(request);
//        out.write(msg);
//    }
//
//    public static Object receive(InputStream in) throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(in);
//        Request request = (Request) ois.readObject();
//        Object o = new Serialization().DeserializeObject(request.getContent());
//        return o;
//    }
}
