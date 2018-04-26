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
			System.out.println("Veritabaný baðlantýsý için kullandýðýnýz driver adý hatalý. Veritabaný sorumlusu ile görüþün.");
		}
		catch (SQLException e) {
			System.out.println("Sisteme belirtilen veritabaný adý veya kullanýcý adý,þifreniz hatalý.\n Kullanýcý bilgilerinizden eminseniz veritabaný adýnýn doðru tanýtýlmasý için veritabaný sorumlusu ile görüþün.");
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
