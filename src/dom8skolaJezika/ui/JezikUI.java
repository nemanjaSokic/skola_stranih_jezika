package dom8skolaJezika.ui;

import java.util.ArrayList;
import java.util.List;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.JezikDAO;
import dom8skolaJezika.model.Jezik;

public class JezikUI {

	public static void meni() {
		int odluka = -1;
		
		while(odluka!=0){
			ispisiMeni();
			System.out.println("...izaberi opciju");
			odluka = ScannerWrapper.ocitajCeoBroj();
			
			switch(odluka){
			case 1:
				ispisiPodatkeJezika();
				break;
			case 2:
				pronadjiJezik();
				break;
			case 3:
				dodajNoviJezik();
				break;
			case 4:
				obrisiJezik();
				break;
			case 5:
				izmenaJezika();
				break;
			default: 
				System.out.println("Nepostojeæa opcija.");
				break;
			}
			
		}
		
	}

	private static void izmenaJezika() {
		System.out.println("Unesite id jezika: ");
		String id = ScannerWrapper.ocitajTekst();
		Jezik j = JezikDAO.getJezikById(App.conn, id);
		if(j == null){
			System.out.println("Jezik ne postoji.");
			return;
		}
		System.out.println("Unesite novi naziv jezika: ");
		String noviNaziv = ScannerWrapper.ocitajTekst();
		JezikDAO.izmenaJezika(App.conn, id, noviNaziv);
		
	}

	private static void obrisiJezik() {
		System.out.println("Unesite id jezika: ");
		String id = ScannerWrapper.ocitajTekst();
		Jezik j = JezikDAO.getJezikById(App.conn, id);
		if(j == null){
			System.out.println("Jezik ne postoji.");
			return;
		}
		JezikDAO.deleteJezik(App.conn, id);
		
	}

	private static void dodajNoviJezik() {
		System.out.println("Unesite sifru jezika: ");
		String sif = ScannerWrapper.ocitajTekst();
		Jezik j = JezikDAO.getJezikById(App.conn, sif);
		if(j != null){
			System.out.println("Jezik sa sifrom " + sif + " postoji u evidenciji.");
			return;
		}
		
		System.out.println("Unesite naziv jezika: ");
		String naziv = ScannerWrapper.ocitajTekst();
		
		Jezik noviJ = new Jezik(sif,naziv);
		
		JezikDAO.addJezik(App.conn, noviJ);
	}

	private static void pronadjiJezik() {
		System.out.println("Upisite sifru jezika: ");
		String sifra = ScannerWrapper.ocitajTekst();
		Jezik j = JezikDAO.getJezikById(App.conn, sifra);
		if(j == null)
			System.out.println("Jezik ne postoji u evidenciji.");
		else
			System.out.println(j);
	}

	private static void ispisiPodatkeJezika() {
		List<Jezik> jezici = new ArrayList<Jezik>();
		jezici = JezikDAO.getAllJezik(App.conn);
		for (int i = 0; i < jezici.size(); i++) {
			System.out.println(jezici.get(i) + ", id - " + jezici.get(i).getIdJezika());
		}
		
	}

	private static void ispisiMeni() {
		System.out.println("Jezik - Osnovne opcije:");
		System.out.println("\tOpcija broj - 1 ispisi sve podatke o jezicima");
		System.out.println("\tOpcija broj - 2 pronadji jezik");
		System.out.println("\tOpcija broj - 3 dodaj novi jezik");
		System.out.println("\tOpcija broj - 4 obrisi jezik");
		System.out.println("\tOpcija broj - 5 izmeni jezik");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
	}

}
