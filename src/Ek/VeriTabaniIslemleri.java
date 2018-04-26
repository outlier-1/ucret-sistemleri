package Ek;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeriTabaniIslemleri {
	public static Connection conn;
	public static final String driverName="com.mysql.jdbc.Driver";
	public static final String dbPath="jdbc:mysql://localhost/ImalatTesisi";
	public static void connectDatabase(String userID,String userPassword){
		try{
		    Class.forName(driverName);
	        conn = DriverManager.getConnection(dbPath,userID,userPassword);
		}
		catch (ClassNotFoundException e) {
			System.out.println("Veritaban� ba�lant�s� i�in kulland���n�z driver ad� hatal�. Veritaban� sorumlusu ile g�r���n.");
		}
		catch (SQLException e) {
			System.out.println("Sisteme belirtilen veritaban� ad� veya kullan�c� ad�,�ifreniz hatal�.\n Kullan�c� bilgilerinizden eminseniz veritaban� ad�n�n do�ru tan�t�lmas� i�in veritaban� sorumlusu ile g�r���n.");
		}
	}
	public static ResultSet runSelectQuery(String selectQuery) throws SQLException{
		ResultSet rSet=null;
		connectDatabase("root", "rootparolam");
        Statement statement = conn.createStatement();
        rSet = statement.executeQuery(selectQuery);
        return rSet;
	}

}
