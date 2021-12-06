package database.data;

import java.io.Serializable;

/**
 * Жанр
 */
public enum MovieGenre implements Serializable {
    WESTERN,
    COMEDY,
    TRAGEDY,
    HORROR;

    /**
     * Список жанров
     * @return возвращает список жанров
     */
    public static String genreList(){
        String genreList = "";
        for(MovieGenre movieGenre : MovieGenre.values()){
            genreList += movieGenre.name() + "\n";
        }
        return genreList;
    }
}
