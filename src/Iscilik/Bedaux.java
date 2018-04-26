package Iscilik;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Bedaux extends PrimliUcretSistemi {
	
	Isci isci;
	
	public Bedaux(Isci i){
		this.isci=i;
		Scanner s = new Scanner(System.in);
		System.out.println("Bedaux Primli Ücret Hesaplama Sistemine Hoþgeldiniz!..");
		System.out.println("Ýþçinin Tüm Bilgileri Biliniyor. Yalnýzca Fiili Üretim Miktarýný Girmeniz Yeterli.");
		fiiliUretimMiktariniOgren(isci,s);
	}
	
	public float zamanTasarrufuHesapla() {
		return ((fiiliUretimMiktari*isci.calistigiMerkez.getParcaBasiZaman())-(isci.calistigiMerkez.getStandartCalismaSuresi()*60))/60;
	}

	BigDecimal ciplakUcretHesapla(){
		BigDecimal ciplakUcret;
		ciplakUcret= isci.calistigiMerkez.getSaatUcreti().multiply(new BigDecimal(isci.calistigiMerkez.getStandartCalismaSuresi()));
		return ciplakUcret;
	}
	
	BigDecimal PrimliUcretHesapla(){
		BigDecimal primliucret;
		primliucret= (isci.calistigiMerkez.getSaatUcreti().multiply(new BigDecimal(0.70)).multiply(new BigDecimal(zamanTasarrufuHesapla())).add(ciplakUcretHesapla())).divide(new BigDecimal(1),2,RoundingMode.HALF_EVEN);
		return primliucret;
	}

}
