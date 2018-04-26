package Esas_Imalat_Merkezleri;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Ek.VeriTabaniIslemleri;

public class Dokuma extends Esas_Imalat_Merkezleri {
	
	 public Dokuma() {
		initializeVariables(); 
	 }
	 private void initializeVariables(){	 
	    String searchQuery="SELECT ID,Hazirlik_Suresi,St_Calisma_Suresi,Saat_Ucreti,St_Uretim_Miktari,Prim_Orani FROM dokuma";
	    ResultSet rSet =null;
	    try {
	    	rSet=VeriTabaniIslemleri.runSelectQuery(searchQuery);
	    	while (rSet.next()) {
	        	hazirlikSuresi=rSet.getFloat("Hazirlik_Suresi");
	    		standartCalismaSuresi=rSet.getFloat("St_Calisma_Suresi");
	    		saatUcreti=rSet.getBigDecimal("Saat_Ucreti").divide(new BigDecimal(1),2,RoundingMode.HALF_EVEN);
	    		standartUretimMiktari=rSet.getInt("St_Uretim_Miktari");
	    		primOrani=rSet.getBigDecimal("Prim_Orani").divide(new BigDecimal(1),2,RoundingMode.HALF_EVEN);
	    		parcaBasiZaman=(standartCalismaSuresi*60)/standartUretimMiktari;
			}
		} catch (Exception e) {
			System.out.println("Hata olu�tu.");
		}
	    finally {
	    	try {
				VeriTabaniIslemleri.conn.close();
			} catch (Exception e2) {
				System.out.println("A��k olmayan ba�lant� kapat�lmaya �al��t�.");
			}
		}
	 }
	 @Override
	 public String toString(){
		 String value ="Dokuma";
		 return value;
	 }
	 public void bilgileriGoster(){
		System.out.println("�malat Haz�rl�k S�resi : " + hazirlikSuresi+ " Dakikad�r." );
		System.out.println("Standart G�nl�k �retim Miktar�: " + standartUretimMiktari + " Adettir.");
		System.out.println("Standart �al��ma S�resi :" + standartCalismaSuresi + " Saattir.");
		System.out.println("�mal Edilen Par�a Ba��na Harcanan S�re : " + parcaBasiZaman+ " Dakika/Birim'dir." );
		System.out.println("Standart Saat Ba�� �cret: " + saatUcreti + " TL/Saat'tir.");
		System.out.println("Te�vikli �cret Sistemleri ��in Prim: " + primOrani+ " Oran�ndad�r. (Bedaux i�in 0.70)" );
		System.out.println("-------------------------------------------------------");
	 }
	 public static void DokumaSabitleriDegistir(Scanner s){
			int secim;
			System.out.println("De�i�tirmek istedi�iniz veri t�r�n� se�iniz..\n---------------------------------------\n1-Haz�rl�k S�resi\n2-Standart �al��ma S�resi\n3-Saat �creti\n4-Standart �retim Miktar�\n5-Prim Oran�");
			try {
				String myDriver = "com.mysql.jdbc.Driver";
			    String db = "jdbc:mysql://localhost/ImalatTesisi";
			    Class.forName(myDriver);
		        Connection conn = DriverManager.getConnection(db,"root","rootparolam");
		        System.out.print("\n--------------------------------------------\nSe�iminiz:");
				secim = s.nextInt();
				switch (secim) {
				case 1:
					System.out.print("Yeni Haz�rl�k S�resini Girin: ");
					float hs=s.nextFloat();
					String updateQuery="UPDATE dokuma "+ "SET Hazirlik_Suresi = '"+hs +"' WHERE ID='SABIT'";
					Statement stmt = conn.createStatement();
				    stmt.executeUpdate(updateQuery);
			        System.out.println("Ba�ar�yla G�ncellendi..");
			        stmt.close();
					break;
				case 2:
					System.out.print("Yeni Standart �al��ma S�resini Girin: ");
					float stz=s.nextFloat();
					String updateQuery2="UPDATE dokuma "+ "SET St_Calisma_Suresi = '"+stz +"' WHERE ID='SABIT'";
					Statement stmt2 = conn.createStatement();
				    stmt2.executeUpdate(updateQuery2);
			        System.out.println("Ba�ar�yla G�ncellendi..");
			        stmt2.close();
					break;
				case 3:
					System.out.print("Yeni Saat �cretini Girin: ");
					BigDecimal ucret=s.nextBigDecimal();
					String updateQuery3="UPDATE dokuma "+ "SET Saat_Ucreti = '"+ucret +"' WHERE ID='SABIT'";
					Statement stmt3 = conn.createStatement();
				    stmt3.executeUpdate(updateQuery3);
				    stmt3.close();
			        System.out.println("Ba�ar�yla G�ncellendi..");
					break;
				case 4:
					System.out.print("Yeni Standart �retim Miktar�n� Girin: ");
					int s�m=s.nextInt();
					String updateQuery4="UPDATE dokuma "+ "SET St_Uretim_Miktari = '"+s�m +"' WHERE ID='SABIT'";
					Statement stmt4 = conn.createStatement();
				    stmt4.executeUpdate(updateQuery4);
				    stmt4.close();
			        System.out.println("Ba�ar�yla G�ncellendi..");
					break;
				case 5:
					System.out.print("Yeni Prim Oran�n� Girin: ");
					float po=s.nextFloat();
					String updateQuery5="UPDATE dokuma "+ "SET Prim_Orani = '"+po +"' WHERE ID='SABIT'";
					Statement stmt5 = conn.createStatement();
				    stmt5.executeUpdate(updateQuery5);
				    stmt5.close();
			        System.out.println("Ba�ar�yla G�ncellendi..");
					break;
				default:
					System.out.println("\n-------------------------------------------------------");
					System.out.println("Ge�ersiz bir say� girdiniz, l�tfen size verilen se�enekler aras�ndan bir se�im yap�n.");
					DokumaSabitleriDegistir(s);
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				System.out.println("\n-------------------------------------------------------");
				System.out.println("Hatal� veri giri�i alg�land�. L�tfen se�iminizi 1 ya da 2 olacak �ekilde yap�n�z.");
				s.next();
				DokumaSabitleriDegistir(s);
			}
		}
}
