package dom8skolaJezika.ui;

import java.util.List;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.KorisnikDAO;
import dom8skolaJezika.model.Korisnik;

public class KorisnikUI {
	static List<String> listaTipova = KorisnikDAO.getAllTip(App.conn);
	
	public static void meni() {
		System.out.print("DOBRODOŠLI\n");
		
		System.out.println("Izaberite tip korisnika: ");
		ispisTipova();
		int odluka = ScannerWrapper.ocitajCeoBroj();
		switch(odluka){
		case 1:
			if(adminLog())
			App.meniAdmin();
			break;
		case 2:
			if(zapoLog())
			App.meniZapo();
			break;
		}
	
	}
	
	

	private static boolean zapoLog() {
		boolean uspesno = true;
		String tip = "zapo";
		System.out.println("Unesite korisnicko ime: ");
		String korIme = ScannerWrapper.ocitajTekst();
		System.out.println("Unesite lozinku: ");
		String loz = ScannerWrapper.ocitajTekst();
		
		Korisnik kor = new Korisnik(tip,korIme,loz);
		Korisnik k = KorisnikDAO.pronadjiKorisnika(App.conn, kor);
		if(k == null){
			System.out.println("Pogrešno korisnièko ime ili lozinka.");
			return false;
		}
		System.out.println(k);
		return uspesno;
		
	}

	private static boolean adminLog() {
		boolean uspesno = true;
		String tip = "admin";
		System.out.println("Unesite korisnicko ime: ");
		String korIme = ScannerWrapper.ocitajTekst();
		System.out.println("Unesite lozinku: ");
		String loz = ScannerWrapper.ocitajTekst();
		
		Korisnik kor = new Korisnik(tip,korIme,loz);
		Korisnik k = KorisnikDAO.pronadjiKorisnika(App.conn, kor);
		if(k == null){
			System.out.println("Pogrešno korisnièko ime ili lozinka.");
			return false;
		}
		System.out.println(k);
		return uspesno;
		
	}
		
	

	private static void ispisTipova() {
		
		for (int i = 0; i < listaTipova.size(); i++) {
			System.out.println((i+1) + ". " + listaTipova.get(i));
		}
	}



	public static void dodavanjeKorisnika() {
		String tip = "zapo";
		System.out.println("Unesite korisnicko ime zaposlenog: ");
		String ime = ScannerWrapper.ocitajTekst();
		System.out.println("unesite lozinku: ");
		String loz = ScannerWrapper.ocitajTekst();
		
		Korisnik noviK = new Korisnik(tip,ime,loz);
		
		KorisnikDAO.dodajKorisnika(App.conn,noviK);
	}
	
	public static void brisanjeKorisnika(){
		System.out.println("Unesite korisnicko ime zaposlenog: ");
		String korIme = ScannerWrapper.ocitajTekst();
		KorisnikDAO.deleteKorisnik(App.conn, korIme);
	}
	
	
}