package Iscilik;

import java.util.InputMismatchException;
import java.util.Scanner;

abstract class PrimliUcretSistemi {
	protected int fiiliUretimMiktari;
	void fiiliUretimMiktariniOgren(Isci i,Scanner s){
		System.out.print("Ýþçinin Fiili Üretim Miktarýný Giriniz: ");
		try {
			fiiliUretimMiktari=s.nextInt();
			if (fiiliUretimMiktari<i.calistigiMerkez.getStandartUretimMiktari()) {
				System.out.println("\n-------------------------------------------------------");
				System.out.println("Fiili Üretim Miktarý Standart Üretim Miktarýndan Düþük Olamaz. Tekrar Deneyin!");
				fiiliUretimMiktariniOgren(i,s);
			}
		} catch (InputMismatchException e) {
			System.out.println("\n-------------------------------------------------------");
			System.out.println("Hatalý veri giriþi algýlandý. Fiili üretim miktarý olarak sayý girmeniz gerekmektedir.");
			s.next();
			fiiliUretimMiktariniOgren(i,s);
		}
	}
	
}
