package dom8skolaJezika.ui;

import java.util.ArrayList;
import java.util.List;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.NastavnikDAO;
import dom8skolaJezika.model.Nastavnik;

public class NastavnikUI {
	
	public static void meni() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			
			System.out.print("opcija:");
			odluka = ScannerWrapper.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				ispisiSveNastavnike();
				break;
			case 2:
				dodajNovogNastavnika();
				break;
			case 3:
				obrisiNastavnika();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
		
	}

	private static void obrisiNastavnika() {
		Nastavnik n = pronadjiNastavnika();
		if(n == null){
			System.out.println("Nastavnik ne postoji u evidenciji.");
			return;
		}
		
		NastavnikDAO.deleteNastavnik(App.conn, n.getJmbg());
		
		
	}

	private static void dodajNovogNastavnika() {

		System.out.println("Upisite jmbg nastavnika.");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		Nastavnik n = NastavnikDAO.getNastavnikById(App.conn, jmbg);
		if(n != null){
			System.out.println("Nastavnik sa tim jmbg-om postoji.");
			return;
		}
		System.out.println("Upisite ime novog nastavnika: ");
		String ime = ScannerWrapper.ocitajTekst();
		System.out.println("Upisite prezime novog nastavnika: ");
		String prezime = ScannerWrapper.ocitajTekst();
		
		n = new Nastavnik(ime,prezime,jmbg);
		NastavnikDAO.addNastavnik(App.conn, n);
	}

	private static Nastavnik pronadjiNastavnika() {
		Nastavnik n = null;
		System.out.println("Upisite jmbg nastavnika.");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		n = NastavnikDAO.getNastavnikById(App.conn, jmbg);
		
		return n;
	}

	private static void ispisiSveNastavnike() {

		List<Nastavnik> sviNastavnici = new ArrayList<Nastavnik>();
		sviNastavnici = NastavnikDAO.getAllNastavnik(App.conn);
		for (int i = 0; i < sviNastavnici.size(); i++) {
			System.out.println(sviNastavnici.get(i) + ", jmbg -  " + sviNastavnici.get(i).getJmbg());
		}
		
	}

	private static void ispisiMenu() {
		System.out.println("Nastavnik - Osnovne opcije:");
		System.out.println("\tOpcija broj - 1 ispisi sve podatke o nastavnicima");
		System.out.println("\tOpcija broj - 2 dodaj novoe podatke o nastavniku");
		System.out.println("\tOpcija broj - 3 obrisi podatke o nastavniku");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
		
	}
}
