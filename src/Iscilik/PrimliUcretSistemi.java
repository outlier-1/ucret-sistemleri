package Iscilik;

import java.util.InputMismatchException;
import java.util.Scanner;

abstract class PrimliUcretSistemi {
	protected int fiiliUretimMiktari;
	void fiiliUretimMiktariniOgren(Isci i,Scanner s){
		System.out.print("���inin Fiili �retim Miktar�n� Giriniz: ");
		try {
			fiiliUretimMiktari=s.nextInt();
			if (fiiliUretimMiktari<i.calistigiMerkez.getStandartUretimMiktari()) {
				System.out.println("\n-------------------------------------------------------");
				System.out.println("Fiili �retim Miktar� Standart �retim Miktar�ndan D���k Olamaz. Tekrar Deneyin!");
				fiiliUretimMiktariniOgren(i,s);
			}
		} catch (InputMismatchException e) {
			System.out.println("\n-------------------------------------------------------");
			System.out.println("Hatal� veri giri�i alg�land�. Fiili �retim miktar� olarak say� girmeniz gerekmektedir.");
			s.next();
			fiiliUretimMiktariniOgren(i,s);
		}
	}
	
}
