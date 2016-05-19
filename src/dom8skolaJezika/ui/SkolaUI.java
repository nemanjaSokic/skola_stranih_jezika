package dom8skolaJezika.ui;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.SkolaDAO;
import dom8skolaJezika.model.Skola;

public class SkolaUI {

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
				ispisiPodatkeSkole();
				break;
			case 2:
				ispisiKurseve();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void ispisiKurseve() {
		
		
	}

	private static void ispisiPodatkeSkole() {
		Skola sk = SkolaDAO.getSkola(App.conn);
		if(sk == null){
			System.out.println("Skola ne postoji");
			return;
		}
		System.out.println(sk);
		
	}

	private static void ispisiMenu() {
		
		System.out.println("Skola - Osnovne opcije:");
		System.out.println("\tOpcija broj - 1 ispisi podatke o skoli");
		System.out.println("\tOpcija broj - 2 ispisi sve kurseve");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}

		
}

	
	
	

