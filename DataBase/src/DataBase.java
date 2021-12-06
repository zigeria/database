import database.data.*;

import java.sql.*;
import java.time.LocalDateTime;

public class DataBase {
    // Блок объявления констант
    public static final String DB_URL = "jdbc:postgresql://localhost:5595/studs";
    public static final String DB_Driver = "org.postgresql.Driver";
    private static String username = "s264955";
    private static String password = "dna921";
    private Connection connection = getConnection();

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, username, password);
    }

    // Инициализация
    public DataBase() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
    }

   public static void main(String[] args) {
        try {
            DataBase database = new DataBase();
          // database.createTablesAndForeignKeys();
            User user = new User("Вася","jhg");
            user.setId(database.insertUsers(user));
            database.insertMovie(new Movie(
                    "Любовь и голуби",
                    new Coordinates(16,8.9f),
                    LocalDateTime.now(),
                    6,
                    MovieGenre.COMEDY,
                    MpaaRating.G,
                    new Person(
                            "Margo",
                            9,
                            Color.BLUE,
                            new Location(7,9,"vaser")
                            ),
                    user), user);
            database.printSelect();
            //database.getPreparedStatement(SQLQueries.PROCEDURE_delete_movie()).execute();

       }
        catch (SQLException exception) {
           exception.printStackTrace();
           System.out.println("Ошибка SQL !");
       }
        catch (ClassNotFoundException exception) {
           System.out.println("JDBC драйвер для СУБД не найден!");
       }
   }

    private void createTablesAndForeignKeys() throws SQLException{
        getPreparedStatement(
                SQLQueries.createType("MOVIEGENRE","'WESTERN', 'COMEDY', 'TRAGEDY', 'HORROR'") +
                        SQLQueries.createType("MPAARATING","'G', 'PG', 'PG_13', 'R', 'NC_17'") +
                        SQLQueries.createType("COLOR","'RED', 'BLACK', 'BLUE', 'ORANGE', 'BROWN'") +

                        SQLQueries.createTable("users", Ok.createColumnsU) +
                        SQLQueries.createTable("movies",Ok.createColumnsM) +
                        SQLQueries.createTable("people",Ok.createColumnsP) +
                        SQLQueries.createTable("coordinates",Ok.createColumnsC) +
                        SQLQueries.createTable("locations",Ok.createColumnsL) +

                        SQLQueries.createForeignKeys("movies","coordinates_id","coordinates", "coordinates_id") +
                        SQLQueries.createForeignKeys("movies","operator_id","people", "person_id") +
                        SQLQueries.createForeignKeys("movies","user_id","users", "user_id") +

                        SQLQueries.createForeignKeys("people","location_id", "locations", "location_id") +
                        SQLQueries.createExtraConstraints("coordinates", "x", "x <= 871") +
                        SQLQueries.createExtraConstraints("movies","oscars_count", "oscars_count > 0") +
                        SQLQueries.createExtraConstraints("people","height", "height > 0")).execute();
    }
    public PreparedStatement getPreparedStatement(String sqlStatement) throws SQLException {
        PreparedStatement preparedStatement;
        try {
            if (connection == null) throw new SQLException();

            preparedStatement = connection.prepareStatement(sqlStatement);
            return preparedStatement;

        }
        catch (SQLException exception) {
            if (connection == null) System.out.println("Соединение с базой данных не установлено!");
            throw new SQLException(exception);
        }
    }
    public PreparedStatement getPreparedStatement(String sqlStatement, String returning_column_name) throws SQLException {

        String newSqlStatement = sqlStatement + " RETURNING " + returning_column_name;
        return getPreparedStatement(newSqlStatement);

    }
    public void printSelect() throws SQLException{
        ResultSet resultSet = getPreparedStatement(SQLQueries.selectAll("movies")).executeQuery();

        if (resultSet.next()) {
            System.out.println(resultSet);
        }

    }
    public long insertUsers(User user) throws SQLException {

        PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.insertUser(),"user_id");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        if (!preparedStatement.execute()) throw new SQLException();
        ResultSet resultSet = preparedStatement.getResultSet();

        long id;
        if (resultSet.next()) {
            id = resultSet.getLong(1);
        }
        else throw new SQLException();

        System.out.println("Выполнен запрос INSERT_User");
        return id;
    }
    public void insertMovie(Movie movie, User user) throws SQLException{
        LocalDateTime creationTime = LocalDateTime.now();

        PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.insertMovie());

        preparedStatement.setString(1, movie.getName());
        preparedStatement.setLong(2, insertCoordinates(movie.getCoordinates()));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(creationTime));
        preparedStatement.setInt(4, movie.getOscarsCount());
        preparedStatement.setString(5, movie.getGenre().toString());
        preparedStatement.setString(6, movie.getMpaaRating().toString());
        preparedStatement.setLong(7, insertPerson(movie.getOperator()));
        preparedStatement.setLong(8, movie.getUser().getId());

        if (preparedStatement.executeUpdate() == 0) throw new SQLException();

        System.out.println("Выполнен запрос INSERT_MOVIE");

    }
    public long insertCoordinates(Coordinates coordinates) throws SQLException {

        PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.insertCoordinates(), "coordinates_id");
        preparedStatement.setInt(1, coordinates.getX());
        preparedStatement.setFloat(2, coordinates.getY());

        if (!preparedStatement.execute()) throw new SQLException();

        ResultSet resultSet = preparedStatement.getResultSet();

        long id;
        if (resultSet.next()) {
            id = resultSet.getLong(1);
        }
        else throw new SQLException();

        System.out.println("Выполнен запрос INSERT_COORDINATES");
        return id;

    }
    public long insertPerson(Person person) throws SQLException {

        PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.insertPerson(),"person_id");

        preparedStatement.setString(1, person.getName());
        preparedStatement.setLong(2, person.getHeight());
        preparedStatement.setString(3, person.getEyeColor().toString());
        preparedStatement.setLong(4, insertLocation(person.getLocation()));

        if (!preparedStatement.execute()) throw new SQLException();
        ResultSet resultSet = preparedStatement.getResultSet();

        long id;
        if (resultSet.next()) {
            id = resultSet.getLong(1);
        }
        else throw new SQLException();
        System.out.println("Выполнен запрос INSERT_PERSON");

        return id;
    }
    public long insertLocation(Location location) throws SQLException{

        PreparedStatement preparedStatement = getPreparedStatement(SQLQueries.insertLocation(),"location_id");
        preparedStatement.setString(1, location.getName());
        preparedStatement.setInt(2, location.getX());
        preparedStatement.setInt(3, location.getY());

        if (!preparedStatement.execute()) throw new SQLException();
        ResultSet resultSet = preparedStatement.getResultSet();

        long id;
        if (resultSet.next()) {
            id = resultSet.getLong(1);
        }
        else throw new SQLException();
        System.out.println("Выполнен запрос INSERT_LOCATION");

        return id;
    }
}
