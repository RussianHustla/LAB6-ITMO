package commands;

import app.UserInterface;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Info extends Command {

    public Info() {
        command = "info";
        description = "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException {
        //System.out.println(collection.info());
        userInterface.send(collection.info());
    }
}
