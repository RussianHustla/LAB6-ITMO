package commands;

import app.InvalidInputException;
import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;
import collection.Furnish;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter_by_furnish extends Command {

    public Filter_by_furnish() {
        command = "filter_by_furnish";
        description = "Вывести элементы, значение поля furnish которых равно заданному";
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException {
        if (args.length < 1) {
            throw new InvalidInputException("У вызываемой команды отсутствует аргумент");
        }
        Furnish referenceFurnish;
        try {
            String str = (String) args[0];
            referenceFurnish = Furnish.valueOf(str.toUpperCase());
        } catch ( NumberFormatException e ) {
            throw new InvalidInputException("У вызываемой команды некорректный аргумент (требуется число)");
        } catch (IllegalArgumentException e) {
            throw  new InvalidInputException("Несуществующее значение furnish");
        }
        //ArrayList<Flat> answer = new ArrayList<>();
//        for (Flat flat : collection.toList()) {
//            if (flat.getFurnish().equals(referenceFurnish)) {
//                //System.out.println(flat);
//                answer.add(flat);
//            }
//        }
        List<Flat> answer = collection.toList().stream()
                .filter(flat -> flat.getFurnish().equals(referenceFurnish))
                .collect(Collectors.toList());

        userInterface.send(answer);
        }
}
