package commands;

import app.UserInterface;
import collection.Flat;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Add extends Command {

    public Add() {
        command = "add";
        description = "Добавить новый элемент в коллекцию";
        object = null;
        withObj = true;
    }

    @Override
    public void execute(UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException, ClassNotFoundException {
        Flat flat = (Flat) getObject();
        collection.add(flat);
        System.out.println(collection.getLast());
        System.out.println("Новый элемент добавлен в коллекцию");
        userInterface.send("Новый элемент добавлен в коллекцию");
        collection.HasUnsavedChanges();
        collection.save();
        //CommandsManager.getInstance().executeCommand(userInterface, collection, "save flats.xml");
    }



    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public Object getObject() {
        return super.getObject();
    }

    @Override
    public String[] getArgs() {
        return super.getArgs();
    }

    @Override
    public void setArgs(String[] args) {
        super.setArgs(args);
    }
}
