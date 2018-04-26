package Iscilik;

import java.util.InputMismatchException;
import java.util.Scanner;
import Esas_Imalat_Merkezleri.Dokuma;
import Esas_Imalat_Merkezleri.Esas_Imalat_Merkezleri;
import Esas_Imalat_Merkezleri.Terbiye;

import java.sql.*;
public class Isci extends Calisan {
	
	protected Esas_Imalat_Merkezleri calistigiMerkez;
	protected float fiiliUretimMiktari;
	private void kimlikBilgileriniAl(Scanner s){
		System.out.println("Lütfen Ýþçinin Kimlik Bilgilerini Ýstenen Verilere Uygun Olacak Þekilde Doldurunuz.");
		
		System.out.print("Ad: ");
		ad=s.nextLine();
		
		System.out.print("Soyad: ");
		soyad=s.nextLine();
		
		System.out.print("T.C No: ");
		tcNo=s.nextLine();
		
		System.out.print("Baba Adý: ");
		babaAdi=s.nextLine();
		
		System.out.print("Ana Adý: ");
		anaAdi=s.nextLine();
		
		System.out.print("Doðum Yeri: ");
		dogumYeri=s.nextLine();
		
		System.out.print("Doðum Tarihi: ");
		dogumTarihi=s.nextLine();
		
		System.out.print("Telefon Numarasý: ");
		telNo=s.nextLine();
		
		System.out.print("Adres: ");
		adres=s.nextLine();
		
		System.out.print("Kimlik Bilgileriyle Baþarý Ýle Alýndý!\n");
	}
	private void VeriTabaninaEkle(){
		try{
			String myDriver = "com.mysql.jdbc.Driver";
		    String db = "jdbc:mysql://localhost/ImalatTesisi";
		    Class.forName(myDriver);
	        Connection conn = DriverManager.getConnection(db,"root","rootparolam");
	        String insertQuery = "INSERT INTO isci" + "(`Isci_ID`,`TC_No`,`Adi`,`Soyadi`,`Baba_Adi`,`Ana_Adi`,`Dogum_Yeri`,`Dogum_Tarihi`,`Telefon_No`,`Adres`,`Calistigi_Merkez`) VALUES" + "(?,?,?,?,?,?,?,?,?,?,?)";
	        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	        preparedStatement.setString(1, IsciKoduUret());
	        preparedStatement.setString(2, tcNo);
	        preparedStatement.setString(3, ad);
	        preparedStatement.setString(4, soyad);
	        preparedStatement.setString(5, babaAdi);
	        preparedStatement.setString(6, anaAdi);
	        preparedStatement.setString(7, dogumYeri);
	        preparedStatement.setString(8, dogumTarihi);
	        preparedStatement.setString(9, telNo);
	        preparedStatement.setString(10, adres);
	        preparedStatement.setString(11, calistigiMerkez.toString());
	        preparedStatement.executeUpdate();
	        
	        conn.close();
	        preparedStatement.close();
	        
	        System.out.println("Ýþçi Kaydý Veritabanýna Baþarý Ýle Eklendi!");
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	private void imalatMerkeziSec(Scanner s){
		int secim;
		System.out.println("\n-------------------------------------------------------");
		System.out.println("Ýþçinin çalýþtýðý imalat merkezini seçiniz..\n");
		System.out.print("1- Dokuma\n2- Terbiye\nSeçiminiz: ");
		try {
			secim = s.nextInt();
			switch (secim) {
			case 1:
				calistigiMerkez=new Dokuma();
				System.out.println("-------------------------------------------------------");
				System.out.println("Ýþçi Dokuma Esas Ýmalat Merkezine Atandý..");
				System.out.println("Dokuma Esas Ýmalat Merkezindeki Ýþçilerin Verileri Þu Þekildedir...\n");
				calistigiMerkez.bilgileriGoster();
				break;
			case 2:
				calistigiMerkez= new Terbiye();
				System.out.println("-------------------------------------------------------");
				System.out.println("Ýþçi Terbiye Esas Ýmalat Merkezine Atandý..");
				System.out.println("Terbiye Esas Ýmalat Merkezindeki Ýþçilerin Verileri Þu Þekildedir...\n");
				calistigiMerkez.bilgileriGoster();
				break;
			default:
				System.out.println("\n-------------------------------------------------------");
				System.out.println("Geçersiz bir sayý girdiniz, lütfen dokuma için 1, terbiye için 2 yazýp enter tuþuna basýn.");
				imalatMerkeziSec(s);
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("\n-------------------------------------------------------");
			System.out.println("Hatalý veri giriþi algýlandý. Lütfen seçiminizi 1 ya da 2 olacak þekilde yapýnýz.");
			s.next();
			imalatMerkeziSec(s);
		}
	}
	private String IsciKoduUret(){
		String kod;
		if (calistigiMerkez instanceof Dokuma) {
		kod="5011"+tcNo.substring(0,2)+tcNo.substring(9, 11);
		}
		else if (calistigiMerkez instanceof Terbiye) {
		kod="5012"+tcNo.substring(0,2)+tcNo.substring(9, 11);			
		}
		else {
			kod="Null";
		}
		return kod;
	}
	
	public Isci(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hoþgeldiniz.\nYeni bir iþçi tanýmlanýyor..\n");
		kimlikBilgileriniAl(scanner);
		imalatMerkeziSec(scanner);
		VeriTabaninaEkle();
		}
	public Isci(int a){
		
	}

}
	