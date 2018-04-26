package Iscilik;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Akord extends PrimliUcretSistemi {
	Isci isci;
	
	public Akord(Isci i){
		this.isci=i;
		Scanner s = new Scanner(System.in);
		System.out.println("Akord Primli Ücret Hesaplama Sistemine Hoþgeldiniz!..");
		System.out.println("Ýþçinin Tüm Bilgileri Biliniyor. Yalnýzca Fiili Üretim Miktarýný Girmeniz Yeterli.");
		fiiliUretimMiktariniOgren(isci,s);
		s.close();
	}
	
	private BigDecimal akordFaktoruHesapla() {
		BigDecimal akordFaktoru = isci.calistigiMerkez.getSaatUcreti().multiply(isci.calistigiMerkez.getPrimOrani()).add(isci.calistigiMerkez.getSaatUcreti()).divide(new BigDecimal(60),5,RoundingMode.HALF_EVEN);
		return akordFaktoru; //0,08
	}

	private float akordZamaniHesapla() {
		float akordZamani = (isci.calistigiMerkez.getHazirlikSuresi() + (isci.calistigiMerkez.getStandartUretimMiktari())*(isci.calistigiMerkez.getParcaBasiZaman()))/isci.calistigiMerkez.getStandartUretimMiktari();
		return akordZamani;
	}

	public BigDecimal akordUcretHesapla(){
		BigDecimal akordUcret= (akordFaktoruHesapla().multiply(new BigDecimal(akordZamaniHesapla())).multiply(new BigDecimal(fiiliUretimMiktari))).divide(new BigDecimal(1),2,RoundingMode.HALF_EVEN);
		return akordUcret;
	}
	
}
