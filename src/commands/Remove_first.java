package commands;

import app.UserInterface;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Remove_first extends Command {

    public Remove_first() {
        command = "remove_first";
        description = "Удалить первый элемент из коллекции";
    }
    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ClassNotFoundException, ParserConfigurationException {
//        if (CommandsManager.getInstance().confirmExecution(userInterface,"Вы действительно хотите удалить первый элемент из коллекции? y/n")) {
//            try {
//                collection.removeByIndex(0);
//                System.out.println("Первый элемент удален из коллекции");
//                collection.HasUnsavedChanges();
//            } catch (IndexOutOfBoundsException | ParserConfigurationException e) {
//                System.err.println("Коллекция пуста");
//            }
//        }

        try {
            collection.removeByIndex(0);
            userInterface.send("Первый элемент удален из коллекции");
            collection.HasUnsavedChanges();
        } catch (IndexOutOfBoundsException | ParserConfigurationException e) {
            System.err.println("Коллекция пуста");
            userInterface.send("Коллекция пуста");
        }
        collection.save();

    }
}
