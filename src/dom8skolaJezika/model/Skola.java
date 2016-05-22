package dom8skolaJezika.model;

import java.util.ArrayList;
import java.util.List;

public class Skola {
	protected String naziv, adresa, email, internet, brRacuna;
	protected int telefon,pib,maticniBroj;
	protected List<Kurs> kursevi = new ArrayList<Kurs>();
	
	public Skola(String naziv, String adresa, String email, String internet, String brRacuna, int telefon, int pib,
			int maticniBroj) {
		
		this.naziv = naziv;
		this.adresa = adresa;
		this.email = email;
		this.internet = internet;
		this.brRacuna = brRacuna;
		this.telefon = telefon;
		this.pib = pib;
		this.maticniBroj = maticniBroj;
		kursevi = new ArrayList<Kurs>();
	}
	public Skola(String naziv, String adresa, String email, String internet, String brRacuna, int telefon, int pib,
			int maticniBroj, List<Kurs> kursevi) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.email = email;
		this.internet = internet;
		this.brRacuna = brRacuna;
		this.telefon = telefon;
		this.pib = pib;
		this.maticniBroj = maticniBroj;
		this.kursevi = kursevi;
	}
	@Override
	public String toString() {
		return "\nSkola " + naziv + "\nadresa: " + adresa + "\nbroj telefona: " + telefon + "\nWEB: " + internet + "\n";
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInternet() {
		return internet;
	}
	public void setInternet(String internet) {
		this.internet = internet;
	}
	public String getBrRacuna() {
		return brRacuna;
	}
	public void setBrRacuna(String brRacuna) {
		this.brRacuna = brRacuna;
	}
	public int getTelefon() {
		return telefon;
	}
	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}
	public int getPib() {
		return pib;
	}
	public void setPib(int pib) {
		this.pib = pib;
	}
	public int getMaticniBroj() {
		return maticniBroj;
	}
	public void setMaticniBroj(int maticniBroj) {
		this.maticniBroj = maticniBroj;
	}
	public List<Kurs> getKursevi() {
		return kursevi;
	}
	public void setKursevi(List<Kurs> kursevi) {
		this.kursevi = kursevi;
	}
	
	
}
