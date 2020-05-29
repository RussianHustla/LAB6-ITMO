package commands;

import app.InvalidInputException;
import app.Reader;
import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;
import collection.House;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Count_greater_than_house extends Command {

    public Count_greater_than_house() {
        command = "count_greater_than_house";
        description = "Вывести количество элементов, значение поля house которых больше заданного";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException {
        House referenceHouse = (House) getObject();
//        int count = 0;
//        for (Flat flat : collection.toList()) {
//            if (flat.getHouse().compareTo(referenceHouse) < 0) {
//                count++;
//            }
//        }
        long count = collection.toList().stream().filter(flat -> flat.getHouse().compareTo(referenceHouse)<0).count();
        userInterface.send("В коллекции " + count + " элементов, у которых дом круче данного");

    }
}
