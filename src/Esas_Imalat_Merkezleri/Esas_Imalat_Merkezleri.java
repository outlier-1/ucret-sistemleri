package Esas_Imalat_Merkezleri;

import java.math.BigDecimal;

public abstract class Esas_Imalat_Merkezleri {
	
	 protected float hazirlikSuresi;
	 protected float standartCalismaSuresi;
	 protected BigDecimal saatUcreti;
	 protected int standartUretimMiktari;
	 protected float parcaBasiZaman;
	 protected BigDecimal primOrani;
	 public String toString() {
		return null;
		 
	 }
    public void bilgileriGoster(){
		 
	 }


	public float getHazirlikSuresi() {
		return hazirlikSuresi;
	}
	
	public void setHazirlikSuresi(float hazirlikSuresi) {
		this.hazirlikSuresi = hazirlikSuresi;
	}


	public float getStandartCalismaSuresi() {
		return standartCalismaSuresi;
	}


	public void setStandartCalismaSuresi(int standartCalismaSuresi) {
		this.standartCalismaSuresi = standartCalismaSuresi;
	}


	public BigDecimal getSaatUcreti() {
		return saatUcreti;
	}


	public void setSaatUcreti(BigDecimal saatUcreti) {
		this.saatUcreti = saatUcreti;
	}


	public int getStandartUretimMiktari() {
		return standartUretimMiktari;
	}


	public void setStandartUretimMiktari(int standartUretimMiktari) {
		this.standartUretimMiktari = standartUretimMiktari;
	}


	public float getParcaBasiZaman() {
		return parcaBasiZaman;
	}


	public void setParcaBasiZaman(float parcaBasiZaman) {
		this.parcaBasiZaman = parcaBasiZaman;
	}


	public BigDecimal getPrimOrani() {
		return primOrani;
	}


	public void setPrimOrani(BigDecimal primOrani) {
		this.primOrani = primOrani;
	}
	
	
}
