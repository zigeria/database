public class Ok {
    //movies
    private static String movie_id = "movie_id BIGSERIAL PRIMARY KEY";
    private static String name = "name VARCHAR NOT NULL";
    private static String coordinates_id = "coordinates_id BIGINT NOT NULL";
    private static String creation_date = "creation_date TIMESTAMP NOT NULL";
    private static String oscars_count = "oscars_count INTEGER NOT NULL";
    private static String genre = "genre MOVIEGENRE NOT NULL";
    private static String mpaa_rating = "mpaa_rating MPAARATING NULL";
    private static String operator_id = "operator_id BIGINT NULL";
    private static String user_id = "user_id BIGINT NOT NULL";
    public static String createColumnsM = movie_id + "," + name + "," + coordinates_id + "," + creation_date + "," + oscars_count + "," + genre + "," + mpaa_rating + "," + operator_id + "," + user_id;

    //coordi
    private static String coordinates_id_1 = "coordinates_id BIGSERIAL PRIMARY KEY";
    private static String x = "x INTEGER NOT NULL";
    private static String y = "y FLOAT4 NOT NULL";
    public static String createColumnsC = coordinates_id_1 + "," + x + "," + y;

    //people
    private static String person_id = "person_id BIGSERIAL PRIMARY KEY";
    private static String namePerson = "name VARCHAR NOT NULL";
    private static String height = "height BIGINT NOT NULL";
    private static String eye_color = "eye_color COLOR NULL";
    private static String location_id = "location_id BIGINT NOT NULL";
    public static String createColumnsP = person_id + "," + namePerson + "," + height + "," + eye_color + "," + location_id ;

    //locations
    private static String location_id_1 = "location_id BIGSERIAL PRIMARY KEY";
    private static String nameL = "name VARCHAR(253) NOT NULL";
    private static String xL = "x INTEGER NOT NULL";
    private static String yL = "y INTEGER NOT NULL";
    public static String createColumnsL = location_id_1 + "," + nameL + "," + xL + "," + yL;

    //users
    private static String user_id_1 = "user_id BIGSERIAL PRIMARY KEY";
    private static String nameU = "name VARCHAR NOT NULL";
    private static String password = "password VARCHAR NOT NULL";
    public static String createColumnsU = user_id_1 + ", " + nameU + ", " + password;

}
