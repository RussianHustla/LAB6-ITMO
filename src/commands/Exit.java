package commands;

import app.UserInterface;
import collection.CollectionManager;

import java.io.File;
import java.io.IOException;

public class Exit extends Command {

    public Exit() {
        command = "exit";
        description = "Завершить программу (без сохранения в файл)";
    }

    public String getCommand() {
        return command;
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ClassNotFoundException {
        if (collection.isHasUnsavedChanges()) {
            if (CommandsManager.getInstance().confirmExecution(userInterface,"Имеются несохраненные изменения, Вы действительно хотите завершить программу?" + '\n' + "Все несохраненные данные будут утеряны. y/n")) {
                collection.setHasUnsavedChanges(false);
                File temp = new File("temp.xml");
                temp.delete();
                System.exit(0);
            }
        } else {
            if (CommandsManager.getInstance().confirmExecution(userInterface, "Вы действительно хотите завершить программу? y/n")) {
                File temp = new File("temp.xml");
                temp.delete();
                System.exit(0);
            }

        }

    }
}
