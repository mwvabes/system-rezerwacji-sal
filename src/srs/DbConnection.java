package srs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

  String url;
  String user;
  String password;

  public DbConnection() {
    this.url = "jdbc:mysql://localhost:3306/srs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    this.user = "root";
    this.password = "";
  }

  public Connection connect() {
    try {
      return DriverManager.getConnection(url,user,password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

}
