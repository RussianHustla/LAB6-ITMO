package commands;

import app.InvalidInputException;
import app.UserInterface;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class Execute_script extends Command{
    private HashSet<Path> scriptsHistory = new HashSet<>();

    public Execute_script() {
        command = "execute_script";
        description = "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException, ClassNotFoundException {
        if (args.length < 1) {
            throw new InvalidInputException("Need argument");
        }
        String scriptPath = (String) args[0];
        Path pathToScript = Paths.get(scriptPath);
        System.out.println("Выполнение скрипта из файла " + pathToScript.getFileName());
        //userInterface.send("Выполнение скрипта из файла " + pathToScript.getFileName());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToScript.toFile()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println("Считана строка " + line);
                String[] listScriptPath = line.split(" ");
                if (listScriptPath[0].equals("execute_script")) {
                    if (!scriptsHistory.contains(pathToScript)) {
                        scriptsHistory.add(pathToScript);
                        CommandsManager.getInstance().executeCommand(userInterface, collection, line);
                    } else {
                        System.err.println("Обнаружена попытка вызова скрипта, который уже был вызван ранее.");
                        userInterface.send("Обнаружена попытка вызова скрипта, который уже был вызван ранее.");
                    }
                } else {
                    CommandsManager.getInstance().executeCommand(userInterface, collection, line);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
            userInterface.send("Файл не найден");
        } catch (Exception e) {
            System.err.println("Ошибка во время выполнения скрипта.");
            userInterface.send("Ошибка во время выполнения скрипта.");
            throw e;
        }
        scriptsHistory.removeAll(scriptsHistory);
        System.out.println("Считывание скрипта из файла " + pathToScript + " завершено");
        userInterface.send("EOF");
        userInterface.send("Считывание скрипта из файла " + pathToScript + " завершено");
    }
}
