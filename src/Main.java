import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

  private static final String URL = "jdbc:postgresql://localhost/lesson2";
  private static final String username = "postgres";
  private static final String password = "";

  private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";

  public static void main(String[] args) {
    try {
      Class.forName(POSTGRESQL_DRIVER);
      try(Connection conn = DriverManager.getConnection(URL, username, password)) {
        System.out.println("Successfully connected to the db lesson2");

        String queryString = "select * from private.teacher";
        try (PreparedStatement statement = conn.prepareStatement(queryString)) {
          ResultSet resultSet = statement.executeQuery();

          System.out.println("Teacher names with ids are:");
          while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") +
                " " + resultSet.getInt("id"));
          }
        }
      }
    } catch (ClassNotFoundException e) {
      System.out.println("No driver library in classpath: " + e.getMessage());
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}