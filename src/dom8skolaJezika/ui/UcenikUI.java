package dom8skolaJezika.ui;

import java.util.ArrayList;
import java.util.List;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.UcenikDAO;
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
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
		
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
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
	}

}
