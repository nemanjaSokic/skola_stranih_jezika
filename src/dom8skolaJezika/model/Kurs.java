package dom8skolaJezika.model;

import java.util.ArrayList;
import java.util.List;

public class Kurs {
	
	protected int idKursa,cena;
	protected Skola skola;
	protected Nastavnik nastavnik;
	protected Jezik jezik;
	protected Nivo nivo;
	protected List<Ucenik> ucenici = new ArrayList<Ucenik>();
	public Kurs(int idKursa, int cena, Skola skola, Nastavnik nastavnik, Jezik jezik, Nivo nivo) {
		super();
		this.idKursa = idKursa;
		this.cena = cena;
		this.skola = skola;
		this.nastavnik = nastavnik;
		this.jezik = jezik;
		this.nivo = nivo;
		ucenici = new ArrayList<Ucenik>();
	}
	public Kurs(int idKursa, int cena, Skola skola, Nastavnik nastavnik, Jezik jezik, Nivo nivo, List<Ucenik> ucenici) {
		super();
		this.idKursa = idKursa;
		this.cena = cena;
		this.skola = skola;
		this.nastavnik = nastavnik;
		this.jezik = jezik;
		this.nivo = nivo;
		this.ucenici = ucenici;
	}
	public List<Ucenik> getUcenici() {
		return ucenici;
	}
	public void setUcenici(List<Ucenik> ucenici) {
		this.ucenici = ucenici;
	}
	@Override
	public String toString() {
		return "Kurs " + cena + ", jezik " + jezik.naziv + nivo.nazivNivoa +  ", " + skola.naziv + ", "
				+ nivo + "nivo.";
	}
	public int getIdKursa() {
		return idKursa;
	}
	public void setIdKursa(int idKursa) {
		this.idKursa = idKursa;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public Skola getSkola() {
		return skola;
	}
	public void setSkola(Skola skola) {
		this.skola = skola;
	}
	public Nastavnik getNastavnik() {
		return nastavnik;
	}
	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}
	public Jezik getJezik() {
		return jezik;
	}
	public void setJezik(Jezik jezik) {
		this.jezik = jezik;
	}
	public Nivo getNivo() {
		return nivo;
	}
	public void setNivo(Nivo nivo) {
		this.nivo = nivo;
	}
	
	
	
	
	
	
	
	
	
	
	
}
