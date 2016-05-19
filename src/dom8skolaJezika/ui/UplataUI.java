package dom8skolaJezika.ui;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.PohadjanjaDAO;
import dom8skolaJezika.model.Ucenik;

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
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
		
	}

	private static void ispisiSveUplateUcenika() {
		System.out.println("Unesite jmbg ucenika: ");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		Ucenik u = PohadjanjaDAO.getUplateByUcenik(App.conn, jmbg);
		System.out.println(u + " izvršene uplate:\n" + u.getUplate());
	}

	private static void ispisiMenu() {
		System.out.println("Uplate - Osnovne opcije:");
		System.out.println("\tOpcija broj - 1 ispisi sve uplate ucenika");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
	}
}
