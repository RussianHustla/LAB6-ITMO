package commands;

import app.Reader;
import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Add_if_min extends Command {

    public Add_if_min() {
        command = "add_if_min";
        description = "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
        object = null;
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException {
        Flat flat = (Flat) getObject();
        if (collection.getMinimal().compareTo(flat) > 0) {
            collection.add(flat);
            System.out.println("Новый элемент добавлен в коллекцию");
            userInterface.send("Новый элемент добавлен в коллекцию");
            collection.HasUnsavedChanges();
        }
    }

    @Override
    public Object getObject() {
        return super.getObject();
    }
}
