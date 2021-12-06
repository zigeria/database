import database.data.Coordinates;
import database.data.Movie;
import database.data.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SQLQueries {
    private static String INSERT = "INSERT INTO %s (%s) VALUES (%s);";
    public static String createTable(String table_name, String columns){
        return String.format("CREATE TABLE %s (%s);",table_name,columns);
    }

    public static String createType(String type_name, String enumeration){
        return String.format("CREATE TYPE %s AS ENUM(%s);",type_name,enumeration);
    }

    public static String insertMovie(){
        return (String.format(INSERT,"movies","name, coordinates_id, creation_date, oscars_count, genre, mpaa_rating, operator_id, user_id", "?, ?, ?, ?, ?::MOVIEGENRE, ?::MPAARATING, ?, ?"));
    }
    public static String insertCoordinates() {
        return (String.format(INSERT,"coordinates", "x, y", "?,?"));
    }
    public static String insertUser() {
        return (String.format(INSERT, "users", "name, password", "?,?"));
    }
    public static String insertPerson() {
        return (String.format(INSERT,"people", "name, height, eye_color, location_id", "?, ?, ?::COLOR, ?"));
    }
    public static String insertLocation() {
        return (String.format(INSERT,"locations", "name, x, y", "?,?,?"));
    }

    public static String PROCEDURE_delete_movie_bigger(){
        return "CREATE PROCEDURE delete_movie_bigger(p_id BIGINT UNSIGNED, str VARCHAR(30))" +
                "BEGIN" +
                "CREATE TEMPORARY TABLE PCID (SELECT person_id, coordinates_id FROM movies WHERE person_id = p_id AND name > str);" +
                "DELETE FROM locations WHERE id IN SELECT location_id FROM people WHERE people.id IN (SELECT person_id FROM PCID);" +
                "DELETE FROM coordinates WHERE id IN (SELECT coordinates_id FROM PCID);" +
                "DROP TEMPORARY TABLE PID;" +
                "END;";
    }

    public static String PROCEDURE_delete_movie(){
        return "CREATE FUNCTION delete_movie(m_id BIGINT)" +
                "BEGIN " +
                "DECLARE p_id BIGINT UNSIGNED DEFAULT (SELECT person_id FROM movies WHERE movie_id = m_id);" +
                "DECLARE l_id BIGINT UNSIGNED DEFAULT (SELECT location_id FROM people WHERE location_id = p_id);" +
                "DECLARE c_id BIGINT UNSIGNED DEFAULT (SELECT coordinates_id FROM movies WHERE coordinates_id = m_id);" +
                "DELETE FROM locations WHERE location_id = l_id; " +
                "DELETE FROM coordinates WHERE coordinates_id = c_id; " +
                "END;" +
                "$$ LANGUAGE 'plpgsql';";
    }
    /*public String delete_movie_bigger(p_id BIGINT UNSIGNED, str VARCHAR(30){

    }*/
    /*public String kmlkjn(){
        CREATE OR REPLACE FUNCTION pgpro_edition_safe() RETURNS TEXT AS $$
        DECLARE
        ver TEXT;
        BEGIN
        SELECT pgpro_edition() INTO ver;
        RETURN ver;
        EXCEPTION WHEN OTHERS THEN
        RAISE NOTICE 'pgpro_edition() procedure doesn''t exist';
        RETURN 'standard';
        END
        $$ LANGUAGE 'plpgsql';
    }*/
    public static String delete_movie(long movie_id){
        return  String.format("CALL delete_movie(%s);",movie_id);
    }

    public static String createForeignKeys(String table_name,String table_column, String references_table_name, String references_table_column){
        return String.format("ALTER TABLE %s ADD FOREIGN KEY (%s) REFERENCES %s(%s);",table_name, table_column, references_table_name, references_table_column);
    }

    public static String createExtraConstraints(String table_name,String parameter_name, String condition) {
        return String.format("ALTER TABLE %s ADD CONSTRAINT check_%s CHECK(%s)",table_name, parameter_name, condition);
    }
    public static String selectAll(String table_name){
        return "SELECT * FROM " + table_name;
    }

}
