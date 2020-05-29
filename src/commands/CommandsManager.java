package commands;

import app.NoSuchCommandException;
import app.Reader;
import app.UserInterface;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс "Invoker" хранящий в себе команды.
 */
public class CommandsManager {
    //Invoker

    private static CommandsManager instance;

    public static CommandsManager getInstance() {
        if (instance == null) {
            instance = new CommandsManager();
        }
        return instance;
    }

    private Map<String, Command> commands = new HashMap<>();
    private ArrayList<Command> history = new ArrayList<>();

    public CommandsManager() {
        addCommand(new Add());
        addCommand(new Exit());
        addCommand(new Clear());
        addCommand(new Help());
        addCommand(new Remove_first());
        addCommand(new Show());
        addCommand(new History());
        addCommand(new Remove_by_id());
        addCommand(new Info());
        addCommand(new Update());
        addCommand(new Filter_by_furnish());
        addCommand(new Count_greater_than_house());
        addCommand(new Execute_script());

    }

    private void addCommand(Command command) {
        commands.put(command.getCommand(), command);
    }

    /**
     * Метод для парсинга команды из строки.
     * @param s
     * @return Команда
     * @throws NoSuchCommandException
     */
    public Command getCommand(String s) throws NoSuchCommandException {
        if (!commands.containsKey(s)) {
            throw new NoSuchCommandException();
        }
        return commands.getOrDefault(s, null);
    }

    /**
     * Метод для получения коллекции всех команд.
     * @return Коллекция комманд.
     */
    public List<Command> getAllCommands() {
        return commands.keySet().stream().map(x -> (commands.get(x))).collect(Collectors.toList());
    }

    /**
     * Метод для получения истории последних выполненных команд.
     * @param count Колличество запрашиваемых команд из истории.
     * @return Коллекция последних выполненных команд.
     */
    public List<Command> getHistory(int count) {
        ArrayList<Command> requestedHistory = new ArrayList<>();
        if (count > history.size()) count = history.size();
        for (int i = history.size() - count; i < history.size(); i++) {
            requestedHistory.add(history.get(i));
        }
        return requestedHistory;
    }

    public boolean contains(String str) {
        String[] parsedCommand = parseCommand(str);
        try {
            Command command = getCommand(parsedCommand[0]);
        } catch (NoSuchCommandException e) {
            return false;
        }
        return true;
    }

    /**
     * Метод для выполнения команды. Разделяет полученную строку на команду и аргумент,
     * добавляет выполненную команду в историю.
     * @param collection
     * @param s
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void executeCommand(UserInterface userInterface, CollectionManager collection, String s) throws IOException, ParserConfigurationException, TransformerException, ClassNotFoundException {
        String[] parsedCommand = parseCommand(s);
        Command command = getCommand(parsedCommand[0]);
        String[] args = Arrays.copyOfRange(parsedCommand, 1, parsedCommand.length);
        history.add(command);
        command.execute(userInterface, collection, args);
    }

    public void executeCommandWithObj(UserInterface userInterface, CollectionManager collection, Command command, String s[]) throws ClassNotFoundException, ParserConfigurationException, TransformerException, IOException {
        String[] args = Arrays.copyOfRange(s, 0, s.length);
        history.add(command);
        command.execute(userInterface, collection, args);
    }

    /**
     * Метод для запроса подтверждения выполнения команды от пользователя.
     * @param question Сообщение для пользователя.
     * @return true - подтверждение, false - отмена.
     * @throws IOException
     */
    public boolean confirmExecution(UserInterface userInterface, String question) throws IOException, ClassNotFoundException {
        String answer = userInterface.request(question).toString().toLowerCase();
        if (answer.equals("y") || answer.equals("д") || answer.equals("+") || answer.equals("yes") || answer.equals("да")) {
            return true;
        } else return false;
    }

    /**
     * Метод для разделения строки на лексемы по пробелу.
     * @param line строка для разделения.
     * @return Массив лексем.
     */
    public String[] parseCommand(String line) {
        return line.split(" ");
    }

}
