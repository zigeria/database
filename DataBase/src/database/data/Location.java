package database.data;

import java.io.Serializable;

/**
 * Место рождения
 */
public class Location implements Serializable {
    private Integer x; //Поле не может быть null
    private Integer y; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 253, Поле не может быть null

    /**
     * Максимально допустимое значение длины строки с названием населенного пункта
     */
    private static final int MAX_LENGTH_LOCATION_NAME = 253;

    /**
     * Конструктор места рождения
     * @param x - координата х
     * @param y - координата у
     * @param name - название населенного пункта
     */
    public Location(Integer x, Integer y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * @return Максимально допустимое значение длины строки с названием населенного пункта
     */
    public static int getMAX_LENGTH_LOCATION_NAME() {
        return MAX_LENGTH_LOCATION_NAME;
    }

    /**
     * @return Возвращает координату х
     */
    public Integer getX() {
        return x;
    }

    /**
     * @return Возвращает координату у
     */
    public Integer getY() {
        return y;
    }

    /**
     * @return Возвращает название населенного пункта
     */
    public String getName() {
        return name;
    }

    /**
     * Строковое представление информации места рождения
     * @return Возвращает строковое представление информации места рождения
     */
    @Override
    public String toString() {
        return "\n\t\tX : " + x +
                "\n\t\tY : " + y +
                "\n\t\tНаселенный пункт : " + name;
    }



}
