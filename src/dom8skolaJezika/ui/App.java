package dom8skolaJezika.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dom8skolaJezik.utilis.ScannerWrapper;



public class App {
	
	static String sp = System.getProperty("file.separator");
	static File baza = new File("." + sp + "data" + sp + "baza.txt");

public static Connection conn;
	
	static {
		try {
			citanjeBaze();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Greška u uèitavanju fajla ." + baza.getPath());
			System.out.println("Promenite svoje parametre uèitavanja baze.");
			
		}
	}
	
	public static void main(String[] args) {
		char z;
		do{
		KorisnikUI.meni();
			z = ScannerWrapper.ocitajOdlukuOPotvrdi("želite ponovo da se logujete");
		}while(z == 'Y');
		
		System.out.println("Kraj programa ");

				
	
	}
		
		
	public static void meniZapo() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenuZap();
			
			System.out.print("opcija:");
			odluka = ScannerWrapper.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Log out");
				break;
			case 1:
				SkolaUI.meni();
				break;
			case 2:
				UcenikUI.meni();
				break;
			case 3:
				NastavnikUI.meni();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
		
		
	


	public static void meniAdmin() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenuAdmin();
			
			System.out.print("opcija:");
			odluka = ScannerWrapper.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Log out");
				break;
			case 1:
				SkolaUI.meni();
				break;
			case 2:
				UcenikUI.meni();
				break;
			case 3:
				UplataUI.meni();
				break;
			case 4:
				JezikUI.meni();
				break;
			case 5:
				NastavnikUI.meni();
				break;
			case 6:
				KursUI.meni();
				break;
			case 10:
				KorisnikUI.dodavanjeKorisnika();
				break;
			case 11:
				KorisnikUI.brisanjeKorisnika();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
		
	




	static void ispisiMenuZap() {
		System.out.println("Skola Stranih Jezika - Osnovne opcije:");
		System.out.println("\tBroj 1 - rad sa školom");
		System.out.println("\tBroj 2 - rad sa ucenicima");
		System.out.println("\tBroj 3 - rad sa nastavnicima");
		
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - Log out");
		
		
	}




	static void ispisiMenuAdmin() {
		System.out.println("Skola Stranih Jezika - Osnovne opcije:");
		System.out.println("\tBroj 1 - rad sa školom");
		System.out.println("\tBroj 2 - rad sa ucenicima");
		System.out.println("\tBroj 3 - rad sa uplatama");
		System.out.println("\tBroj 4 - rad sa jezicima");
		System.out.println("\tBroj 5 - rad sa nastavnicima");
		System.out.println("\tBroj 6 - rad sa kursevima");
		System.out.println();
		System.out.println("\tBroj 10 - registracija korisnika");
		System.out.println("\tBroj 11 - brisanje korisnika");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - Log out");
		
	}



	private static Connection citanjeBaze() throws SQLException, ClassNotFoundException {
		
		String s = null;
		String s1 = null,s2 = null,s3 = null;
		Class.forName("com.mysql.jdbc.Driver");
		try {
			BufferedReader in = new BufferedReader(new FileReader(baza));
						
						
						
			s = in.readLine();
			String [] tok = s.split(",");
			 s1 = tok[0];
			 s2 = tok[1];
			s3 = tok[2];
			
			conn = DriverManager.getConnection(s1,s2,s3);
			
			
			in.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom uèitavanja fajla.");
			e.printStackTrace();
		}
		return conn;
		
		
	}

}
