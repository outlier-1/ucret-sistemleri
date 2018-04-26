package Iscilik;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Ek.VeriTabaniIslemleri;
import Esas_Imalat_Merkezleri.Dokuma;
import Esas_Imalat_Merkezleri.Terbiye;

public class CalismaAlani {
	Scanner s= new Scanner(System.in);
	private void rolSecimi() throws IOException{
		int secim;
		String secimMesaji ="Hoþgeldiniz.. Lütfen sisteme giriþ yaparken kullanacaðýnýz kullanýcý rolünü seçiniz.\n\n1-Yönetici\n2-Operatör";
		System.out.println("--------------------------------------------");
		System.out.println(secimMesaji);
		System.out.print("--------------------------------------------\nSeçiminiz: ");
		secim=s.nextInt();
		switch (secim) {
		case 1: // yönetici
			yoneticiKarsila();
			break;
		case 2:// operator
			operatorKarsila();
			break;
		default: System.out.println("Hatalý bir seçim yaptýnýz. Lütfen tekrar denemek için 0 yazýp enter'a basýn.");
			s.next();
			rolSecimi();
			break;
		}
	}
	private void operatorKarsila() throws IOException{
		int secim;
		String id;
		String operatorKarsilamaMesaji="Sisteme operatör rolü ile giriþ yaptýnýz..\nOperatör rolü ile yapabileceðiniz iþlemler aþaðýda listelenmiþtir..\n"
				+ "1-Yeni Ýþçi Oluþtur.\n2-Primli Ücret Hesaplama ( Kayýtlý iþçi üzerinden )";
		Isci iscim= new Isci(1);
		System.out.println("--------------------------------------------");
		System.out.println(operatorKarsilamaMesaji);
		System.out.print("--------------------------------------------\nSeçiminiz: ");
		secim=s.nextInt();
		switch (secim) {
		case 1:
			System.out.println("--------------------------------------------");
			@SuppressWarnings("unused") Isci isci= new Isci();
			System.out.println("Ýþleminiz baþarý ile tamamlandý.. Karþýlama Ekranýna Dönmek Ýçin 0 yazýp Enter'a basýn..");
			s.next();
			operatorKarsila();
			break;
		case 2:
			System.out.print("Lütfen ücret hesaplama iþlemi yapacaðýnýz iþçinin kayýtlý ID numarasýný girin : ");
			id=s.next();
			boolean kayitDurumu = IsciBulveAta(id, iscim);
			if (!kayitDurumu) {
				System.out.println("Kayýt Bulunamadý! Ana ekrana dönmek için 0 yazýp Enter'a basýn..");
				s.next();
				operatorKarsila();
			}
			else {
				System.out.println("\n\nLütfen ücret hesaplarken kullanacaðýnýz primli ücret sistemini seçiniz..\n1-Bedaux Primli Ücret Sistemi\n2-Akord Ücret Sistemi");
				System.out.print("--------------------------------------------\nSeçiminiz: ");
				secim=s.nextInt();
				switch (secim) {
				case 1:
					System.out.println("--------------------------------------------");
					Bedaux bedaux = new Bedaux(iscim);
					System.out.println("\nÜcretler Hesaplanýyor...\n\nÝþçinin Çýplak Ücreti: " +bedaux.ciplakUcretHesapla() + " TL'dir.\nÝþçinin Primli Ücreti: " + bedaux.PrimliUcretHesapla() + " TL'Dir.");
					System.out.println("--------------------------------------------");
					break;

				case 2:
					System.out.println("--------------------------------------------");
					Akord akord = new Akord(iscim);
					System.out.println("\nÜcret Hesaplanýyor...\n\nÝþçinin akord ücreti: " + akord.akordUcretHesapla() + " TL'dir.");
					System.out.println("--------------------------------------------");
					break;
				}
			}		
			break;
		default:
			System.out.println("Hatalý bir seçim yaptýnýz. Lütfen tekrar denemek için 0 yazýp enter'a basýn.");
			s.next();
			operatorKarsila();
			break;
		}
	}
	private Boolean IsciBulveAta(String ID,Isci i){
		int kayitSayisi=0;
		ResultSet rSet;
		String calistigiMerkez=null;
		String searchQuery="select Isci_ID, Adi,Soyadi,TC_No,Calistigi_Merkez from Isci where Isci_ID='"+ID+"'";
		System.out.println(ID + " ID Numaralý iþçi kayýtlarda aranýyor...");
		try {
			rSet=VeriTabaniIslemleri.runSelectQuery(searchQuery);
			System.out.println("ID Numarasý         Adý          Soyadý         TC NO               Çalýþtýðý Merkez");
	        System.out.println("--------------------------------------------------------------------------------------");
			while (rSet.next()) {
		        kayitSayisi++;
		        calistigiMerkez=rSet.getString("Calistigi_Merkez");
		        System.out.println(rSet.getString("Isci_ID")+"	    "+ rSet.getString("Adi")+"	 "+ rSet.getString("Soyadi")+"    "+ rSet.getString("TC_No")+"	    "+ rSet.getString("Calistigi_Merkez"));
			}
			if (calistigiMerkez.equals(new String("Dokuma")))
			{
				i.calistigiMerkez=new Dokuma();
				i.calistigiMerkez.bilgileriGoster();
			}
			else if(calistigiMerkez.equals(new String("Terbiye")))
			{
				i.calistigiMerkez= new Terbiye();
				i.calistigiMerkez.bilgileriGoster();
			}
			else{
				System.out.println("Ýþçinin çalýþtýðý merkez bilgisi alýnamadý.");
			}

		} catch (SQLException e) {
			System.out.println("Hata oluþtu.");
		}
	
		if (kayitSayisi==0) {
			return false;
		} else {
			return true;
		}
	}
	private void yoneticiKarsila(){
		int secim;
		Scanner yScanner=new Scanner(System.in);
		String sifre="1234";
		String data;
		String kimlikKontrolu="--------------------------------------------\nYönetici rolü ile giriþ yapmaya çalýþýyorsunuz.. Giriþ yapabilmek için lütfen þifrenizi girin.\n--------------------------------------------\nÞifreniz: ";
		System.out.print(kimlikKontrolu);;
		data=yScanner.nextLine();
		if (!sifre.equals(data)) {
			System.out.println("Hatalý þifre giriþi yaptýnýz. Tekrar denemek için 0 yazýp enter'a basýn...");
			yScanner.next();
			yoneticiKarsila();
		}
		else{
			String yoneticiKarsilamaMesaji="\nSisteme yönetici rolü ile giriþ yaptýnýz..\nYönetici rolü ile yapabileceðiniz iþlemler aþaðýda listelenmiþtir..\n--------------------------------------------"
					+ "\n1-Dokuma Merkezinin Standart Verilerini Deðiþtir\n2-Terbiye Merkezinin Standart Verilerini Deðiþtir\n--------------------------------------------\nSeçiminiz: ";
			System.out.println(yoneticiKarsilamaMesaji);
			secim=yScanner.nextInt();
			switch (secim) {
			case 1:
				Dokuma.DokumaSabitleriDegistir(yScanner);
				break;
			case 2:
				Terbiye.TerbiyeSabitleriDegistir(yScanner);
				break;
			default:
				break;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		CalismaAlani alan = new CalismaAlani();
		alan.rolSecimi();
	}
}
