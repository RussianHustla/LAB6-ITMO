package commands;

import app.InvalidInputException;
import app.UserInterface;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Save extends Command {
    //private static final String path = "flats.xml"; //нужно брать путь с командной строки!

    public Save() {
        command = "save";
        description = "Сохранить коллекцию в файл";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, ClassNotFoundException {
//        if (CommandsManager.getInstance().confirmExecution(userInterface,"Файл с коллекцией будет перезаписан. Продолжить? y/n")) {
//            String str = collection.toXml();
//            collection.save(str.replaceFirst("UTF-16", "UTF-8"), path);
//            System.out.println("Коллекция успешно сохранена в файл " + path);
//            collection.setHasUnsavedChanges(false);
//            File temp = new File("temp.xml");
//            temp.delete();
//        }
        if (args.length < 1) {
            throw new InvalidInputException("У вызываемой команды отсутствует аргумент");
        }
        String path;
        try {
            path = (String) args[0];
        } catch (NumberFormatException e) {
            throw new InvalidInputException("У вызываемой команды некорректный аргумент (требуется число)");
        }
        String str = collection.toXml();
        collection.save();
    }
}
