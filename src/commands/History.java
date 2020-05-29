package commands;

import app.UserInterface;
import collection.CollectionManager;

import java.io.IOException;

public class History extends Command {

    public History() {
        command = "history";
        description = "Вывести последние 13 команд (без их аргументов)";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException {
        String str = "";
        for (Command command : CommandsManager.getInstance().getHistory(13)) {
            //System.out.println(command.getCommand());
            str += command.getCommand() + '\n';
        }
        userInterface.send(str);
    }
}
