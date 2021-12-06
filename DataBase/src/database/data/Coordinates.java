package database.data;

import java.io.Serializable;

/**
 * Координаты
 */
public class Coordinates implements Serializable {
    private Integer x; //Максимальное значение поля: 871, Поле не может быть null
    private float y;

    /**
     * Максимальное значение координаты Х
     */
    private static final Integer MAX_X = 871;

    /**
     * Конструктор координат
     * @param x - координата х
     * @param y - координата у
     */
    public Coordinates(Integer x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return Максимальное значение координаты Х
     */
    public static Integer getMAX_X() {
        return MAX_X;
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
    public float getY() {
        return y;
    }

    /**
     * Строковое представление координат
     * @return Возвращает строковое представление координат
     */
    @Override
    public String toString() {
        return "\n\tX : " + x + "\n\tY : " + y;
    }

    public String getValues(){
        return x + ", " + y;
    }

}
