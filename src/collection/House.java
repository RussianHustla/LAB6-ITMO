package collection;

import java.io.Serializable;

/**
 * Класс дома.
 */
public class House implements Comparable<House>, Serializable {
    private String name; //Поле не может быть null
    private Integer year; //Поле не может быть null, Значение поля должно быть больше 0
    private long numberOfFlatsOnFloor; //Значение поля должно быть больше 0

    @Override
    public String toString() {
        return "House{" + '\n' +
                "name='" + name + '\'' +
                ", " + '\n' + "year = " + year +
                ", " + '\n' + "numberOfFlatsOnFloor = " + numberOfFlatsOnFloor +
                '}';
    }

    public House(String name, Integer year, long numberOfFlatsOnFloor) {
        this.name = name;
        this.year = year;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public House() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public void setNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) {
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    /**
     * Сравнение выполняется по возрасту дома.
     * @param o
     * @return
     */
    @Override
    public int compareTo(House o) {
        return this.year - o.year;
    }
}