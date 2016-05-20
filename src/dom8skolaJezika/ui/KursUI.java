package dom8skolaJezika.ui;

import java.util.ArrayList;
import java.util.List;

import dom8skolaJezik.utilis.ScannerWrapper;
import dom8skolaJezika.dao.JezikDAO;
import dom8skolaJezika.dao.KursDAO;
import dom8skolaJezika.dao.NastavnikDAO;
import dom8skolaJezika.dao.NivoDAO;
import dom8skolaJezika.dao.PohadjanjaDAO;
import dom8skolaJezika.dao.SkolaDAO;
import dom8skolaJezika.model.Jezik;
import dom8skolaJezika.model.Kurs;
import dom8skolaJezika.model.Nastavnik;
import dom8skolaJezika.model.Nivo;

public class KursUI {

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
					ispisiSveKurseve();
					break;
				case 2:
					dodavanjeKursa();
					break;
				case 3:
					brisanjeKursa();
					break;
				case 4:
					ispisPolaznikaKursa();
					break;
				default:
					System.out.println("Nepostojeca komanda");
					break;
				}
			}
			
		}

	private static void ispisPolaznikaKursa() {
		System.out.println("Upisite id kursa:");
		int id = ScannerWrapper.ocitajCeoBroj();
		Kurs k = KursDAO.getKursById(App.conn, id);
		Kurs kurs = PohadjanjaDAO.getPolazniciKursa(App.conn, id);
		
		if(kurs.getUcenici().isEmpty()){
			System.out.println("Kurs nema polaznike.");
			return;
		}
		
		System.out.println("Polaznici kursa " + k.getJezik() + " " + k.getNivo());
		for (int i = 0; i < kurs.getUcenici().size(); i++) {
			System.out.println(kurs.getUcenici().get(i));
		}
		
	}

	private static void brisanjeKursa() {
		System.out.println("Unesite id kursa: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Kurs k = KursDAO.getKursById(App.conn, id);
		
		if(k == null){
			System.out.println("Kurs ne postoji.");
			return;
		}
		KursDAO.deleteKurs(App.conn,k);
	}

	private static void dodavanjeKursa() {
		System.out.println("Unesite id novog kursa: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Kurs k = KursDAO.getKursById(App.conn, id);
		if(k != null){
			System.out.println("Kurs sa id-jem " + id + " postoji u evidenciji.");
			return;
		}
		
		System.out.println("Unesite id jezika koji se uci na kursu: ");
		String idJez = ScannerWrapper.ocitajTekst();
		Jezik j = JezikDAO.getJezikById(App.conn, idJez);
		while(j == null){
			System.out.println("Jezik sa id-jem " + idJez + " ne postoji.\nPokusajte ponovo:");
			idJez = ScannerWrapper.ocitajTekst();
			j = JezikDAO.getJezikById(App.conn, idJez);
		}
		System.out.println("Izaberite nivo kursa: ");
		List<Nivo> nivoi = NivoDAO.getAllNivo(App.conn);
		for (Nivo nivo : nivoi) {
			System.out.println(nivo.getIdNivo() + " - " + nivo.getNazivNivoa() + "\n");
		}
		Nivo n = null;
		int idNivo = ScannerWrapper.ocitajCeoBroj();
		n = NivoDAO.getNivoById(App.conn, idNivo);
		while(n == null){
			System.out.println("Morate navesti id nivoa...\nPokusajte ponovo:");
			idNivo = ScannerWrapper.ocitajCeoBroj();
			for (int i = 0; i < nivoi.size(); i++) {
				if(nivoi.get(i).getIdNivo() == idNivo){
					n = NivoDAO.getNivoById(App.conn, idNivo);
					break;
				}
			}
		}
		System.out.println("Unesite cenu kursa:");
		int c = ScannerWrapper.ocitajCeoBroj();
		System.out.println("Unesite jmbg nastavnika na kursu:");
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		Nastavnik nast = NastavnikDAO.getNastavnikById(App.conn, jmbg);
		while(nast == null){
			System.out.println("Nastavnik sa jmbg-om " + jmbg + " ne postoji u evidenciji. Pokusajte ponovo: ");
			jmbg = ScannerWrapper.ocitajCeoBroj();
			nast = NastavnikDAO.getNastavnikById(App.conn, jmbg);
		}
		
		k = new Kurs(0,c,SkolaDAO.getSkola(App.conn),nast,j,n);
		KursDAO.addKurs(App.conn, k);
	}

	private static void ispisiSveKurseve() {
		List<Kurs> sviKursevi = new ArrayList<Kurs>();
		System.out.println(SkolaDAO.getSkola(App.conn).getNaziv());
		sviKursevi = KursDAO.getAllKurs(App.conn);
		for (int i = 0; i < sviKursevi.size(); i++) {
			Kurs k = sviKursevi.get(i);
			System.out.println("\nid kursa: " + k.getIdKursa() + "\n" + k.getJezik().getNaziv() + " - " + k.getNivo().getNazivNivoa() + " - " + k.getCena() + "RSD\n");
		}
		
	}

	private static void ispisiMenu() {
		System.out.println("Kurs - Osnovne opcije:");
		System.out.println("\tOpcija broj - 1 ispisi podatke o kursevima");
		System.out.println("\tOpcija broj - 2 daodavanje kursa");
		System.out.println("\tOpcija broj - 3 brisanje kursa");
		System.out.println("\tOpcija broj - 4 ispis polaznika kursa");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
		
	}
		
	

}
