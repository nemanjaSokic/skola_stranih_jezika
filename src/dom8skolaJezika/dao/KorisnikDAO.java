package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Korisnik;

public class KorisnikDAO {

	
	public static List<String> getAllTip(Connection conn){
		List<String> listaTipova = new ArrayList<String>();
		String s = "select tip_korisnika from korisnici group by tip_korisnika;";
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			
			while (rs.next()){
				String tipKorisnika = rs.getString(1);
				listaTipova.add(tipKorisnika);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaTipova;
		
		
	}

	public static Korisnik pronadjiKorisnika(Connection conn, Korisnik kor) {
		Korisnik k = null;
		String s = "select tip_korisnika,korisnicko_ime,sifra_korisnika from korisnici where tip_korisnika ='" + kor.getTipKorisnika() +"' and korisnicko_ime = '" + kor.getKorisnickoIme() + "' and sifra_korisnika = '" + kor.getSifraKorisnika() + "';";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			if(rs.next()){
				
				
				k = kor;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
		
	}

	public static void dodajKorisnika(Connection conn, Korisnik noviK) {
		
		String s = "insert into korisnici (tip_korisnika,korisnicko_ime,sifra_korisnika) values (?,?,?);";
		
		try{
		PreparedStatement pr = conn.prepareStatement(s);
		pr.setString(1, noviK.getTipKorisnika());
		pr.setString(2, noviK.getKorisnickoIme());
		pr.setString(3, noviK.getSifraKorisnika());
		
		if(pr.executeUpdate() == 1){
			System.out.println("Korisnik je uspesno registrovan.");
		}else{
			System.out.println("Greska pri registraciji.");
		}
		
		pr.close();
		}catch(SQLException e){
			System.out.println("Korisnik sa ovim korisnickim imenom postoji.");
		}
	}

	public static void deleteKorisnik(Connection conn, String korIme) {
		
		if(korIme.equals("admin")){
			System.out.println("Zabranjeno brisanje admina.");
			return;
		}
		
		String s = "delete from korisnici where korisnicko_ime = '" + korIme + "';";
		
		try {
			PreparedStatement st = conn.prepareStatement(s);
			
			if(st.executeUpdate() == 1){
				System.out.println("Uspesno obrisan korisnik.");
			}else{
				System.out.println("Greska pri brisanju.\nKorisnik sa tim korisnickim imenom ne postoji.");
			}
			
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
