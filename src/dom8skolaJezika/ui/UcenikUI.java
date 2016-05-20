package dom8skolaJezika.ui;

import java.util.ArrayList;
import java.util.List;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.KursDAO;
import dom8skolaJezika.dao.PohadjanjaDAO;
import dom8skolaJezika.dao.UcenikDAO;
import dom8skolaJezika.model.Kurs;
import dom8skolaJezika.model.Ucenik;

public class UcenikUI {

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
				ispisiSveUcenike();
				break;
			case 2:
				dodavanjeUcenikaNaKurs();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
		
	}

	private static void dodavanjeUcenikaNaKurs() {
		System.out.println("Unesite id kursa u koji dodajete polaznike: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Kurs k = KursDAO.getKursById(App.conn, id);
		if(k == null){
			System.out.println("Kurs ne postoji u evidenciji");
			return;
		}
		System.out.println("Unesite jmbg polaznika kojeg dodajete na kurs: ");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		
		Ucenik uc = UcenikDAO.getUcnikByJmbg(App.conn, jmbg);
		while(uc == null){
			System.out.println("Ucenik ne postoji u evidenciji.\nPokusajte ponovo: ");
			jmbg = ScannerWrapper.ocitajCeoBroj();
			uc = UcenikDAO.getUcnikByJmbg(App.conn, jmbg);
		}
		
		PohadjanjaDAO.addPolaznikaUKurs(App.conn, k, jmbg);
		char z = ScannerWrapper.ocitajOdlukuOPotvrdi("dodate još polaznika na kurs");
		
		while(z == 'Y'){
			System.out.println("Unesite jmbg polaznika kojeg dodajete na kurs: ");
			jmbg = ScannerWrapper.ocitajCeoBroj();
			PohadjanjaDAO.addPolaznikaUKurs(App.conn, k, jmbg);
		}
		
		System.out.println("Ucenici koji su dodati na kurs:\n" + k.getUcenici());
	}

	private static void ispisiSveUcenike() {
		List<Ucenik> sviUcenici = new ArrayList<Ucenik>();
		sviUcenici = UcenikDAO.getAllUcenik(App.conn);
		for (int i = 0; i < sviUcenici.size(); i++) {
			System.out.println(sviUcenici.get(i));
		}
		
	}

	private static void ispisiMenu() {
		System.out.println("Ucenik - Osnovne opcije:");
		System.out.println("\tOpcija broj - 1 ispisi sve podatke o ucenicima");
		System.out.println("\tOpcija broj - 2 dodavanje polaznika na kurs");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
	}

}
