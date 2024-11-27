import java.sql.*;
public class ConnectionFactory{
  //string de conexão
  //jdbc:postgresql://localhost:5432/pessoas
  //exemplo: https://www.google.com:80/search
  private static final String HOST = "localhost";
  private static final String PORT = "5432";
  private static final String DB = "20242_fatec_ipi_poo_pessoas_v2";
  private static final String USER = "postgres";
  //pesqureturn c; você resolve a questão da senha chumbada no código
  private static final String PASSWORD = "12345678";
  

  public static Connection conectar(){
    try{
      var s = String.format(
        "jdbc:postgresql://%s:%s/%s",
        HOST, PORT, DB
      );
      Connection c = DriverManager.getConnection(
        s, USER, PASSWORD
      );
      return c;
    }
    catch(Exception e){
      e.printStackTrace();
      return null;
    }
  }
}