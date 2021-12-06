package database.data;

import java.io.Serializable;

/**
 * Цвет
 */
public enum Color implements Serializable {
    RED,
    BLACK,
    BLUE,
    ORANGE,
    BROWN;

    /**
     * Список всех цветов
     * @return Возвращает список цветов
     */
    public static String colorList(){
        String colorList = "";
        for(Color color : Color.values()){
            colorList += color.name() + "\n";
        }
        return colorList;
    }

}

