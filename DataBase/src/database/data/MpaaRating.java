package database.data;

import java.io.Serializable;

/**
 * Рейтинг MPAA
 */
public enum MpaaRating implements Serializable {
    G,
    PG,
    PG_13,
    R,
    NC_17;

    /**
     * Список рейтингов
     * @return Возвращает список рейтингов
     */
    public static String mpaaRatingList(){
        String mpaaRatingList = "";
        for(MpaaRating mpaaRating : MpaaRating.values()){
            mpaaRatingList += mpaaRating.name() + "\n";
        }
        return mpaaRatingList;
    }
}
