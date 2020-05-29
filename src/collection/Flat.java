package collection;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Класс квартир.
 */
public class Flat implements Comparable<Flat>, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double area; //Значение поля должно быть больше 0
    private Long numberOfRooms; //Значение поля должно быть больше 0
    private int kitchenArea; //Значение поля должно быть больше 0
    private Double timeToMetroOnFoot; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле не может быть null
    private House house; //Поле не может быть null

    public Flat(String name, Coordinates coordinates, Date creationDate, Double area, Long numberOfRooms, int kitchenArea, Double timeToMetroOnFoot, Furnish furnish, House house) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.kitchenArea = kitchenArea;
        this.timeToMetroOnFoot = timeToMetroOnFoot;
        this.furnish = furnish;
        this.house = house;
    }

    public Flat(int id, String name, Coordinates coordinates, Date creationDate, Double area, Long numberOfRooms, int kitchenArea, Double timeToMetroOnFoot, Furnish furnish, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.kitchenArea = kitchenArea;
        this.timeToMetroOnFoot = timeToMetroOnFoot;
        this.furnish = furnish;
        this.house = house;
    }

    public Flat() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flat)) return false;
        Flat flat = (Flat) o;
        return area == flat.area &&
                numberOfRooms == flat.numberOfRooms &&
                Objects.equals(id, flat.id) &&
                Objects.equals(name, flat.name) &&
                Objects.equals(coordinates, flat.coordinates) &&
                Objects.equals(creationDate, flat.creationDate) &&
                Objects.equals(kitchenArea, flat.kitchenArea) &&
                Objects.equals(timeToMetroOnFoot, flat.timeToMetroOnFoot) &&
                furnish == flat.furnish &&
                Objects.equals(house, flat.house);
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id = " + id +
                ", " + '\n' + "name = '" + name + '\'' +
                ", " + '\n' + "coordinates = " + coordinates +
                ", " + '\n' + "creationDate = " + creationDate +
                ", " + '\n' + "area = " + area +
                ", " + '\n' + "numberOfRooms = " + numberOfRooms +
                ", " + '\n' + "kitchenArea = " + kitchenArea +
                ", " + '\n' + "timeToMetroOnFoot = " + timeToMetroOnFoot +
                ", " + '\n' + "furnish = " + furnish +
                ", " + '\n' + "house = " + house +
                '}' + '\n';
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area, numberOfRooms, kitchenArea, timeToMetroOnFoot, furnish, house);
    }

    public Flat(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double getX() {return getCoordinates().getX(); }

    public double getY() {return getCoordinates().getY(); }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(int kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public Double getTimeToMetroOnFoot() {
        return timeToMetroOnFoot;
    }

    public void setTimeToMetroOnFoot(Double timeToMetroOnFoot) {
        this.timeToMetroOnFoot = timeToMetroOnFoot;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public House getHouse() {
        return house;
    }

    public String getHouseName() {return getHouse().getName(); }

    public Integer getHouseYear() {return getHouse().getYear(); }

    public long getHouseNumberOfFlatsOnFloor() {return getHouse().getNumberOfFlatsOnFloor(); }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Сравнение квартир выполняется по времени до метро пешком.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Flat o) {
//        if ((this.timeToMetroOnFoot - o.timeToMetroOnFoot) > 0) return 1;
//        if ((this.timeToMetroOnFoot - o.timeToMetroOnFoot) < 0) return -1;
//        return 0;
        return this.name.compareTo(o.name);
    }
}