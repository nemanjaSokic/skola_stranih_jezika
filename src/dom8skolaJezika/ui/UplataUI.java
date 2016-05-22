package dom8skolaJezika.ui;

import java.sql.Date;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.KursDAO;
import dom8skolaJezika.dao.PohadjanjaDAO;
import dom8skolaJezika.dao.UplataDAO;
import dom8skolaJezika.model.Kurs;
import dom8skolaJezika.model.Ucenik;
import dom8skolaJezika.model.Uplata;

public class UplataUI {

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
				ispisiSveUplateUcenika();
				break;
			case 2:
				unesiNovuUplatu();
				break;
			case 3:
				ispisOdredjeneUplate();
				break;
			case 4:
				obrisiUplatu();
			case 5:
				evidencijaIzvrsenihUplata();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
		
	}

	private static void evidencijaIzvrsenihUplata() {
		System.out.println("Unesite id kursa: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Kurs k = KursDAO.getKursById(App.conn, id);
		k = PohadjanjaDAO.getPolazniciKursa(App.conn, id);
		for (Ucenik u : k.getUcenici()) {
			int suma = UplataDAO.getSumaUplataZaKursUcenika(App.conn, k,u);
			if(k.getCena() == suma){
				System.out.println("Ucenik " + u.getIme() + " " + u.getPrezime() + " je isplatio kurs.");
			}else if(k.getCena() < suma){
				System.out.println("Ucenik " + u.getIme() + " " + u.getPrezime() + " je pretplatio kurs za " + (suma-k.getCena()));
			}else{
				System.out.println("Ucenik " + u.getIme() + " " + u.getPrezime() + " treba još da plati - " + (k.getCena() - suma));
			}
		}
		
	}

	private static void obrisiUplatu() {
		Uplata u = pronadjiUplatu();
		if(u == null){
			System.out.println("Uplata sa tim brojem ne postoji.");
			return;
		}
		
		UplataDAO.deleteUplata(App.conn,u.getUplatnicaBr());
		
	}

	private static void ispisOdredjeneUplate() {
		Uplata u = pronadjiUplatu();
		if(u == null){
			System.out.println("Uplatnica ne postoji u evidenciji.");
			return;
		}
		System.out.println(u);
	}

	private static Uplata pronadjiUplatu() {
		Uplata u = null;
		System.out.println("Unesite broj uplatinice:");
		int br = ScannerWrapper.ocitajCeoBroj();
		u = UplataDAO.getUplataById(App.conn, br);
		return u;
	}

	private static void unesiNovuUplatu() {
		System.out.println("Unesite broj uplatinice:");
		int br = ScannerWrapper.ocitajCeoBroj();
		Uplata u = UplataDAO.getUplataById(App.conn, br);
		if(u != null){
			System.out.println("Uplata sa tim brojem postoji u evidenciji.");
			return;
		}
		System.out.println("Unesite id kursa za koji se uplata izvrsava: ");
		int idKursa = ScannerWrapper.ocitajCeoBroj();
		Kurs k = KursDAO.getKursById(App.conn, idKursa);
		if(k == null){
			System.out.println("Kurs ne postoji.");
			return;
		}
		System.out.println("Unesite jmbg ucenika za kojeg se upata izvrsava: ");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		int i = PohadjanjaDAO.getIdPohadjanja(App.conn, idKursa, jmbg);
		if(i == 0){
			return;
		}
		
		System.out.println("Unesite datum uplate(yyyy-MM-dd): ");
		
		String datum = ScannerWrapper.ocitajTekst();
		Date date = Date.valueOf(datum);
		
		System.out.println("Unesite iznos uplate: ");
		int iznos = ScannerWrapper.ocitajCeoBroj();
		
		Uplata up = new Uplata (br,iznos,date);
		UplataDAO.addUplata(App.conn,up,idKursa,jmbg);
		
	}

	private static void ispisiSveUplateUcenika() {
		System.out.println("Unesite jmbg ucenika: ");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		Ucenik upl = UplataDAO.getUplateByUcenik(App.conn, jmbg);
	
				System.out.println(upl.getIme() + upl.getPrezime() + " izvršene uplate:\n" + upl.getUplate());
				
			
			
		
	}

	private static void ispisiMenu() {
		System.out.println("Uplate - Osnovne opcije:");
		System.out.println("\tOpcija broj - 1 ispisi sve uplate ucenika");
		System.out.println("\tOpcija broj - 2 unos nove uplate");
		System.out.println("\tOpcija broj - 3 ispis odredjene uplate");
		System.out.println("\tOpcija broj - 4 storniraj uplatu");
		System.out.println("\tOpcija broj - 5 evidencija izvrsenih uplata");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
	}
}
