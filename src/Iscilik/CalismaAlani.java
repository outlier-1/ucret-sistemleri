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
		String secimMesaji ="Ho�geldiniz.. L�tfen sisteme giri� yaparken kullanaca��n�z kullan�c� rol�n� se�iniz.\n\n1-Y�netici\n2-Operat�r";
		System.out.println("--------------------------------------------");
		System.out.println(secimMesaji);
		System.out.print("--------------------------------------------\nSe�iminiz: ");
		secim=s.nextInt();
		switch (secim) {
		case 1: // y�netici
			yoneticiKarsila();
			break;
		case 2:// operator
			operatorKarsila();
			break;
		default: System.out.println("Hatal� bir se�im yapt�n�z. L�tfen tekrar denemek i�in 0 yaz�p enter'a bas�n.");
			s.next();
			rolSecimi();
			break;
		}
	}
	private void operatorKarsila() throws IOException{
		int secim;
		String id;
		String operatorKarsilamaMesaji="Sisteme operat�r rol� ile giri� yapt�n�z..\nOperat�r rol� ile yapabilece�iniz i�lemler a�a��da listelenmi�tir..\n"
				+ "1-Yeni ���i Olu�tur.\n2-Primli �cret Hesaplama ( Kay�tl� i��i �zerinden )";
		Isci iscim= new Isci(1);
		System.out.println("--------------------------------------------");
		System.out.println(operatorKarsilamaMesaji);
		System.out.print("--------------------------------------------\nSe�iminiz: ");
		secim=s.nextInt();
		switch (secim) {
		case 1:
			System.out.println("--------------------------------------------");
			@SuppressWarnings("unused") Isci isci= new Isci();
			System.out.println("��leminiz ba�ar� ile tamamland�.. Kar��lama Ekran�na D�nmek ��in 0 yaz�p Enter'a bas�n..");
			s.next();
			operatorKarsila();
			break;
		case 2:
			System.out.print("L�tfen �cret hesaplama i�lemi yapaca��n�z i��inin kay�tl� ID numaras�n� girin : ");
			id=s.next();
			boolean kayitDurumu = IsciBulveAta(id, iscim);
			if (!kayitDurumu) {
				System.out.println("Kay�t Bulunamad�! Ana ekrana d�nmek i�in 0 yaz�p Enter'a bas�n..");
				s.next();
				operatorKarsila();
			}
			else {
				System.out.println("\n\nL�tfen �cret hesaplarken kullanaca��n�z primli �cret sistemini se�iniz..\n1-Bedaux Primli �cret Sistemi\n2-Akord �cret Sistemi");
				System.out.print("--------------------------------------------\nSe�iminiz: ");
				secim=s.nextInt();
				switch (secim) {
				case 1:
					System.out.println("--------------------------------------------");
					Bedaux bedaux = new Bedaux(iscim);
					System.out.println("\n�cretler Hesaplan�yor...\n\n���inin ��plak �creti: " +bedaux.ciplakUcretHesapla() + " TL'dir.\n���inin Primli �creti: " + bedaux.PrimliUcretHesapla() + " TL'Dir.");
					System.out.println("--------------------------------------------");
					break;

				case 2:
					System.out.println("--------------------------------------------");
					Akord akord = new Akord(iscim);
					System.out.println("\n�cret Hesaplan�yor...\n\n���inin akord �creti: " + akord.akordUcretHesapla() + " TL'dir.");
					System.out.println("--------------------------------------------");
					break;
				}
			}		
			break;
		default:
			System.out.println("Hatal� bir se�im yapt�n�z. L�tfen tekrar denemek i�in 0 yaz�p enter'a bas�n.");
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
		System.out.println(ID + " ID Numaral� i��i kay�tlarda aran�yor...");
		try {
			rSet=VeriTabaniIslemleri.runSelectQuery(searchQuery);
			System.out.println("ID Numaras�         Ad�          Soyad�         TC NO               �al��t��� Merkez");
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
				System.out.println("���inin �al��t��� merkez bilgisi al�namad�.");
			}

		} catch (SQLException e) {
			System.out.println("Hata olu�tu.");
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
		String kimlikKontrolu="--------------------------------------------\nY�netici rol� ile giri� yapmaya �al���yorsunuz.. Giri� yapabilmek i�in l�tfen �ifrenizi girin.\n--------------------------------------------\n�ifreniz: ";
		System.out.print(kimlikKontrolu);;
		data=yScanner.nextLine();
		if (!sifre.equals(data)) {
			System.out.println("Hatal� �ifre giri�i yapt�n�z. Tekrar denemek i�in 0 yaz�p enter'a bas�n...");
			yScanner.next();
			yoneticiKarsila();
		}
		else{
			String yoneticiKarsilamaMesaji="\nSisteme y�netici rol� ile giri� yapt�n�z..\nY�netici rol� ile yapabilece�iniz i�lemler a�a��da listelenmi�tir..\n--------------------------------------------"
					+ "\n1-Dokuma Merkezinin Standart Verilerini De�i�tir\n2-Terbiye Merkezinin Standart Verilerini De�i�tir\n--------------------------------------------\nSe�iminiz: ";
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
