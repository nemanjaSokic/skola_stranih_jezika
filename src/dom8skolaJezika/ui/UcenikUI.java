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
			case 3: 
				dodavanjeUcenikaUEvidenciju();
				break;
			case 4:
				brisanjeUcenikaSaKursa();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
		
	}

	private static void brisanjeUcenikaSaKursa() {
		System.out.println("Unesite id kursa u koji dodajete polaznike: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Kurs k = KursDAO.getKursById(App.conn, id);
		if(k == null){
			System.out.println("Kurs ne postoji u evidenciji");
			return;
		}
		System.out.println("Unesite jmbg polaznika kojeg dodajete na kurs: ");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		PohadjanjaDAO.deletePohadjanje(App.conn,k,jmbg);
		
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
		proveraUcenika(jmbg);
		
		if(proveraKursa(k,jmbg) == false){
			PohadjanjaDAO.addPolaznikaUKurs(App.conn, k, jmbg);
		}	
		char z = ScannerWrapper.ocitajOdlukuOPotvrdi("dodate još polaznika na kurs");
		
		while(z == 'Y'){
			
			System.out.println("Unesite jmbg polaznika kojeg dodajete na kurs: ");
			jmbg = ScannerWrapper.ocitajCeoBroj();
			proveraUcenika(jmbg);
			if(proveraKursa(k,jmbg) == false){
				PohadjanjaDAO.addPolaznikaUKurs(App.conn, k, jmbg);
			}	
			z = ScannerWrapper.ocitajOdlukuOPotvrdi("dodate još polaznika na kurs");
		}
		
		System.out.println("Ucenici koji su dodati na kurs:\n" + k.getUcenici());
	}

	private static boolean proveraKursa(Kurs k, int jmbg) {
		
		k = PohadjanjaDAO.getPolazniciKursa(App.conn, k.getIdKursa());
		
		boolean postojiNaKursu = false;
		for (int i = 0; i < k.getUcenici().size(); i++) {
			if(k.getUcenici().get(i).getJmbg() == jmbg){
				System.out.println("Ucenik sa unetim jmbg-om " + jmbg + " veæ pohadja ovaj kurs.");
				postojiNaKursu = true;
			}
		}
		return postojiNaKursu;
	}

	private static Ucenik proveraUcenika(int jmbg) {
		Ucenik uc = UcenikDAO.getUcenikByJmbg(App.conn, jmbg);
		
		while(uc == null){
			System.out.println("Ucenik ne postoji u evidenciji. ");
			char e = ScannerWrapper.ocitajOdlukuOPotvrdi("da unesete unesete novog studenta u evidenciju");
			if(e == 'Y'){
				dodavanjeUcenikaUEvidenciju();
				break;
			}else{
				System.out.println("Morate uneti postojeæi jmbg: ");
				jmbg = ScannerWrapper.ocitajCeoBroj();
				uc = UcenikDAO.getUcenikByJmbg(App.conn, jmbg);
			}	
		}
		return uc;
	}

	private static void dodavanjeUcenikaUEvidenciju() {
		System.out.println("Unesite jmbg novog ucenika:");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		Ucenik u = UcenikDAO.getUcenikByJmbg(App.conn, jmbg);
		while(u != null){
			System.out.println("Ucenik sa jmbg-om " + jmbg + " postoji u evidenciji.\nPokusajte ponovo:");
			jmbg = ScannerWrapper.ocitajCeoBroj();
			u = UcenikDAO.getUcenikByJmbg(App.conn, jmbg);
		}
		
		System.out.println("Unesite ime novog ucenika:");
		String ime = ScannerWrapper.ocitajTekst();
		System.out.println("Unesite prezime novog ucenika:");
		String prezime = ScannerWrapper.ocitajTekst();
		
		u = new Ucenik(ime,prezime,jmbg);
		UcenikDAO.addUcenik(App.conn, u);
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
		System.out.println("\tOpcija broj - 3 dodavanje polaznika u evidenciju");
		System.out.println("\tOpcija broj - 4 brisanje polaznika sa kursa.");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
	}

}
