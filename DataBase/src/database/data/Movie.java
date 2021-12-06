package database.data;

import java.time.LocalDateTime;

/**
 * Фильм
 */
public class Movie implements Comparable<Movie>{
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person operator; //Поле может быть null
    private User user;

    /**
     * Минимальное значение количества оскаров
     */
    private static final int MIN_OSCARS_COUNT = 1;

    /**
     * Конструктор фильма
     * @param name - название
     * @param coordinates - координаты
     * @param creationDate - дата инициализации
     * @param oscarsCount - количество оскаров
     * @param genre - жанр
     * @param mpaaRating - рейтинг
     * @param operator - оператор
     */
    public Movie(String name, Coordinates coordinates, LocalDateTime creationDate, int oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person operator, User user){
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
        this.user = user;
    }

    /**
     * @return Минимальное значение количества оскаров
     */
    public static int getMIN_OSCARS_COUNT() {
        return MIN_OSCARS_COUNT;
    }

    /**
     * @return Владельца
     */
    public User getUser() {
        return user;
    }

    /**
     * @return Возвращает название фильма
     */
    public String getName() {
        return name;
    }

    /**
     * @return Возвращает координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Возвращает дату инициализации
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return Возвращает количество оскаров
     */
    public int getOscarsCount() {
        return oscarsCount;
    }

    /**
     * @return Возвращает жанр
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * @return Возвращает рейтинг
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * @return Возвращает рейтинг
     */
    public Person getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "\nНазвание : " + name +
                "\nМестоположение : " + coordinates +
                "\nДата создания : " + creationDate +
                "\nКоличество оскаров : " + oscarsCount +
                "\nЖанр : " + genre +
                "\nРейтинг американской ассоциации кинематографистов : " + mpaaRating +
                "\nОперетор : " + operator ;
    }


    @Override
    public int compareTo(Movie movie) {
        return name.compareTo(movie.name);
    }
}
