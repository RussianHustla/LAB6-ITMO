package network;

import app.*;
import collection.CollectionManager;
import collection.Coordinates;
import commands.Command;
import commands.CommandsManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final int PORT = 8000;

        final Path pathToTempFile = Paths.get("temp.xml");
        CollectionManager collection = CollectionManager.getInstance();
        File temp = new File(pathToTempFile.toAbsolutePath().toString());
        //boolean hasTemp = temp.exists();
        boolean hasTemp = false; //заглушка

        if (hasTemp == false) {
            if (args.length > 0) {
                Path pathToInitFile = Paths.get(args[0]);
                collection.setPathToInitFile(pathToInitFile);
                try {
                    XmlReader.read(pathToInitFile.toAbsolutePath().toString());
                } catch (ParserConfigurationException | SAXException e) {
                    System.err.println("Ошибка при считывании данных из файла");
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    System.err.println("Файл не найдет либо недостаточно прав доступа");
                } catch (IOException e) {
                    System.err.println("Ошибка ввода/вывода");
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.err.println("Пустая строка");
                }
            } else {
                System.err.println("Файл для инициализации коллекции не введён. Имя файла следует ввести в аргументах запуска программы.");
            }
        }


        ServerSocket ss = new ServerSocket(PORT);

        int clientCount = 0;


        System.out.println("Server started");
        while(true) {

            try {
                Socket s = ss.accept();
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream();
                UserInterface userInterface = new UserInterface(in, out);

                System.out.println("client accepted #" + ++clientCount);

                Object o = userInterface.receive();

                //System.out.println(o.getClass().getName());
                if (o != null) {
                    System.out.println(o);
                    try {
                        Command command = (Command) o;
                        //System.out.println("команда " + command);
                        String arg[] = command.getArgs();
//                        for (int i = 0; i < arg.length; i++) {
//                            System.out.println(arg[i]);
//                        }
                        //System.out.println(command.getObject());
                        CommandsManager.getInstance().executeCommandWithObj(userInterface, collection, command, arg);
                    } catch (ParserConfigurationException | TransformerException | NoSuchCommandException e) {
                        userInterface.send("Неизвестная команда, используйте команду help, чтобы посмотреть список всех доступных команд.");
                    } catch (InvalidInputException e) {
                        userInterface.send("Некорректный ввод команды");
                    } catch (IOException e) {
                        System.err.println("Ошибка ввода/вывода");
                        e.printStackTrace();
                    } catch (ClassCastException e) {
                        //System.out.println("Команда из строки");
                        try {
                            CommandsManager.getInstance().executeCommand(userInterface, collection, o.toString());
                        } catch (ParserConfigurationException | TransformerException | NoSuchCommandException ex) {
                            userInterface.send("Неизвестная команда, используйте команду help, чтобы посмотреть список всех доступных команд.");
                        } catch (InvalidInputException ex) {
                            userInterface.send("Некорректный ввод команды");
                        } catch (IOException ex) {
                            System.err.println("Ошибка ввода/вывода");
                            e.printStackTrace();
                        } catch (Exception ex) {
                            System.err.println("Неизвестная ошибка команды из строки");
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        System.err.println("Неизвестная ошибка");
                        e.printStackTrace();
                    }
                }


                in.close();
                out.close();
                s.close();
            } catch (SocketException | EOFException e) {
                System.err.println("Потеряна связь с клиентом #" + clientCount);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Кажется что то пошло не так, но я все еще работаю!");
            }



        }
    }

}
