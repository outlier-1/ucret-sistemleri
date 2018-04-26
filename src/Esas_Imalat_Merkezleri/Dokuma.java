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
			System.out.println("Hata oluþtu.");
		}
	    finally {
	    	try {
				VeriTabaniIslemleri.conn.close();
			} catch (Exception e2) {
				System.out.println("Açýk olmayan baðlantý kapatýlmaya çalýþtý.");
			}
		}
	 }
	 @Override
	 public String toString(){
		 String value ="Dokuma";
		 return value;
	 }
	 public void bilgileriGoster(){
		System.out.println("Ýmalat Hazýrlýk Süresi : " + hazirlikSuresi+ " Dakikadýr." );
		System.out.println("Standart Günlük Üretim Miktarý: " + standartUretimMiktari + " Adettir.");
		System.out.println("Standart Çalýþma Süresi :" + standartCalismaSuresi + " Saattir.");
		System.out.println("Ýmal Edilen Parça Baþýna Harcanan Süre : " + parcaBasiZaman+ " Dakika/Birim'dir." );
		System.out.println("Standart Saat Baþý Ücret: " + saatUcreti + " TL/Saat'tir.");
		System.out.println("Teþvikli Ücret Sistemleri Ýçin Prim: " + primOrani+ " Oranýndadýr. (Bedaux için 0.70)" );
		System.out.println("-------------------------------------------------------");
	 }
	 public static void DokumaSabitleriDegistir(Scanner s){
			int secim;
			System.out.println("Deðiþtirmek istediðiniz veri türünü seçiniz..\n---------------------------------------\n1-Hazýrlýk Süresi\n2-Standart Çalýþma Süresi\n3-Saat Ücreti\n4-Standart Üretim Miktarý\n5-Prim Oraný");
			try {
				String myDriver = "com.mysql.jdbc.Driver";
			    String db = "jdbc:mysql://localhost/ImalatTesisi";
			    Class.forName(myDriver);
		        Connection conn = DriverManager.getConnection(db,"root","rootparolam");
		        System.out.print("\n--------------------------------------------\nSeçiminiz:");
				secim = s.nextInt();
				switch (secim) {
				case 1:
					System.out.print("Yeni Hazýrlýk Süresini Girin: ");
					float hs=s.nextFloat();
					String updateQuery="UPDATE dokuma "+ "SET Hazirlik_Suresi = '"+hs +"' WHERE ID='SABIT'";
					Statement stmt = conn.createStatement();
				    stmt.executeUpdate(updateQuery);
			        System.out.println("Baþarýyla Güncellendi..");
			        stmt.close();
					break;
				case 2:
					System.out.print("Yeni Standart Çalýþma Süresini Girin: ");
					float stz=s.nextFloat();
					String updateQuery2="UPDATE dokuma "+ "SET St_Calisma_Suresi = '"+stz +"' WHERE ID='SABIT'";
					Statement stmt2 = conn.createStatement();
				    stmt2.executeUpdate(updateQuery2);
			        System.out.println("Baþarýyla Güncellendi..");
			        stmt2.close();
					break;
				case 3:
					System.out.print("Yeni Saat Ücretini Girin: ");
					BigDecimal ucret=s.nextBigDecimal();
					String updateQuery3="UPDATE dokuma "+ "SET Saat_Ucreti = '"+ucret +"' WHERE ID='SABIT'";
					Statement stmt3 = conn.createStatement();
				    stmt3.executeUpdate(updateQuery3);
				    stmt3.close();
			        System.out.println("Baþarýyla Güncellendi..");
					break;
				case 4:
					System.out.print("Yeni Standart Üretim Miktarýný Girin: ");
					int süm=s.nextInt();
					String updateQuery4="UPDATE dokuma "+ "SET St_Uretim_Miktari = '"+süm +"' WHERE ID='SABIT'";
					Statement stmt4 = conn.createStatement();
				    stmt4.executeUpdate(updateQuery4);
				    stmt4.close();
			        System.out.println("Baþarýyla Güncellendi..");
					break;
				case 5:
					System.out.print("Yeni Prim Oranýný Girin: ");
					float po=s.nextFloat();
					String updateQuery5="UPDATE dokuma "+ "SET Prim_Orani = '"+po +"' WHERE ID='SABIT'";
					Statement stmt5 = conn.createStatement();
				    stmt5.executeUpdate(updateQuery5);
				    stmt5.close();
			        System.out.println("Baþarýyla Güncellendi..");
					break;
				default:
					System.out.println("\n-------------------------------------------------------");
					System.out.println("Geçersiz bir sayý girdiniz, lütfen size verilen seçenekler arasýndan bir seçim yapýn.");
					DokumaSabitleriDegistir(s);
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				System.out.println("\n-------------------------------------------------------");
				System.out.println("Hatalý veri giriþi algýlandý. Lütfen seçiminizi 1 ya da 2 olacak þekilde yapýnýz.");
				s.next();
				DokumaSabitleriDegistir(s);
			}
		}
}
