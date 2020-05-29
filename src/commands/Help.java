package commands;

import app.UserInterface;
import collection.CollectionManager;

import java.io.IOException;

public class Help extends Command {

    public Help() {
        command = "help";
        description = "Вывести справку по доступным командам";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException {
        String str = "";
        for (Command command : CommandsManager.getInstance().getAllCommands()) {
            str += command.getCommand() + ": " + command.getDescription() + '\n';
        }
        userInterface.send(str);
    }
}
