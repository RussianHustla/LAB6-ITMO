package commands;

import app.InvalidInputException;
import app.Reader;
import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Update extends Command {

    public Update() {
        command = "update";
        description = "Обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, ClassNotFoundException {
        String arg[] = getArgs(); //костыль
        if (arg.length < 1) {
            throw new InvalidInputException("У вызываемой команды отсутствует аргумент");
        }
        int id;
        try {
            id = Integer.parseInt(arg[0]);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("У вызываемой команды некорректный аргумент (требуется число)");
        }

        Flat flat = (Flat) getObject();
        flat.setId(id);
        
            collection.removeById(id);
            collection.add(flat);
            System.out.println("Элемент с id = " + id + " обновлен");
            userInterface.send("Элемент с id = " + id + " обновлен");
            collection.HasUnsavedChanges();
        collection.save();
    }

    @Override
    public String[] getArgs() {
        return super.getArgs();
    }

    @Override
    public Object getObject() {
        return super.getObject();
    }
}
