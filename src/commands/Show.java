package commands;

import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;
import network.Server;

import java.io.IOException;

public class Show extends  Command {

    public Show() {
        command = "show";
        description = "Вывести в стандартный поток вывода все элементы коллекции в строков представлении";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException {
        userInterface.send(collection.show());
    }
}
