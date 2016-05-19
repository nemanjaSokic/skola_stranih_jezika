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

public static Connection conn;
	
	static {
		try {
			citanjeBaze();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		int odluka = -1;
		while (odluka != 0) {
			App.ispisiMenu();
			
			System.out.print("opcija:");
			odluka = ScannerWrapper.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				SkolaUI.meni();
				break;
			case 2:
				UcenikUI.meni();
				break;
		
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
		
		
		
	private static void ispisiMenu() {
		System.out.println("Skola Stranih Jezika - Osnovne opcije:");
		System.out.println("\tBroj 1 - rad sa školom");
		System.out.println("\tBroj 2 - rad sa ucenicima");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
		
	}



	private static Connection citanjeBaze() throws SQLException, ClassNotFoundException {
		String sp = System.getProperty("file.separator");
		File baza = new File("." + sp + "data" + sp + "baza.txt");
		String s = null;
		String s1 = null,s2 = null,s3 = null;
		Class.forName("com.mysql.jdbc.Driver");
		try {
			BufferedReader in = new BufferedReader(new FileReader(baza));
			
			// ucitavanje MySQL drajvera
						

						// konekcija
						
						
						
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
