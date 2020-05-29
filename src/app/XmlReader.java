package app;

import collection.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Класс, отвечающий за считывание коллекции из файла в формате xml
 */
public class XmlReader {

    /**
     * Метод для считывания коллекции из файла xml
     * @param path Путь к файлу
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static void read(String path) throws IOException, ParserConfigurationException, SAXException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        XMLHandler handler = new XMLHandler();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(path);
    }

    private static class XMLHandler extends DefaultHandler {


        private Flat currentFlat;
        private Coordinates currentFlatCoords;
        private House currentFlatHouse;
        private String currentElement;
        private CollectionManager collection;

        CollectionManager getCollection() {
            return collection;
        }

        public void startDocument() {
            System.out.println("Загрузка коллекции из XML...");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            currentElement = qName;

            switch (currentElement) {
                case "flats": {
                    collection = CollectionManager.getInstance();
                }

                case "flat": {
                    currentFlat = new Flat();
                } break;

                case "coordinates": {
                    currentFlatCoords = new Coordinates();
                } break;

                case "house": {
                    currentFlatHouse = new House();
                } break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {

            switch (qName) {
                case "flat": {
                    try {
                        collection.add(currentFlat);
                    } catch (Exception e) {
                        System.err.println("Ошибка при добавлении элемента в коллекцию из файла");
                        e.printStackTrace();
                    }

                    currentFlat = null;
                    currentFlatHouse = null;
                    currentFlatCoords = null;
                } break;
            }
            currentElement = null;
        }

        @Override
        public void endDocument() {
            System.out.println("Загрузка коллекции завершена.");
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String text = new String(ch, start, length);

            if (text.contains("<") || currentElement == null) {
                return;
            }

            switch (currentElement) {
                case "id": {
                    currentFlat.setId(Integer.parseInt(text));
                }

                case "name": {
                    currentFlat.setName(text);
                } break;

                case "creationDate": {
                    DateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);

                    try {
                        Date date = dateFormat.parse(text);
                        currentFlat.setCreationDate(date);
                    } catch (ParseException e) {
                        System.err.println("Встречен неправильный формат даты при загрузки коллекции" + text);
                        e.printStackTrace();
                    }
                } break;

                case "x": {
                    currentFlatCoords.setX(Double.parseDouble(text));

                } break;

                case "y": {
                    currentFlatCoords.setY(Double.parseDouble(text));
                    currentFlat.setCoordinates(currentFlatCoords);

                } break;

                case "area": {
                    currentFlat.setArea(Double.valueOf(text));
                } break;

                case "numberOfRooms": {
                    currentFlat.setNumberOfRooms(Long.valueOf(text));

                } break;

                case "kitchenArea": {
                    try {
                        currentFlat.setKitchenArea(Integer.parseInt(text));

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                } break;

                case "timeToMetroOnFoot": {
                    currentFlat.setTimeToMetroOnFoot(Double.valueOf(text));

                } break;

                case "furnish": {
                    currentFlat.setFurnish(Furnish.valueOf(text));

                } break;

                case "houseName": {
                    currentFlatHouse.setName(text);

                } break;

                case "year": {
                    try {
                        currentFlatHouse.setYear(Integer.valueOf(text));

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                } break;

                case "numberOfFlatsOnFloor": {
                    currentFlatHouse.setNumberOfFlatsOnFloor(Long.parseLong(text));
                    currentFlat.setHouse(currentFlatHouse);
                } break;
            }
        }
    }
}
