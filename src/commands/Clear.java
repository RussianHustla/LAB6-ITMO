package commands;

import app.UserInterface;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Clear extends Command {

    public Clear() {
        command = "clear";
        description = "Отчистить коллекцию";
    }
    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, ClassNotFoundException {
        int count = collection.size();
        collection.clear();
        System.out.println("Коллекция успешно очищенна! Элементов удалено: " + count);
        userInterface.send("Коллекция успешно очищенна! Элементов удалено: " + count);
        collection.HasUnsavedChanges();
        collection.save();

    }
}
