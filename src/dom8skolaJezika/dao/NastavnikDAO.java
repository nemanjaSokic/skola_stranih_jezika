package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Nastavnik;

public class NastavnikDAO {

	
	public static Nastavnik getNastavnikById(Connection conn, int jmbg){
		Nastavnik n = null;
		
		String s = "select ime,prezime from nastavnici where jmbg = '" + jmbg + "';";
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			
			if(rs.next()){
				String ime = rs.getString(1);
				String prezime = rs.getString(2);
				n = new Nastavnik(ime,prezime,jmbg);
			}
			
			st.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Greška kod ucitavanja nastavnika.");
			e.printStackTrace();
		}
		
		return n;
	}
	
	
	public static List<Nastavnik> getAllNastavnik(Connection conn){
		List<Nastavnik> nastavnici = new ArrayList<Nastavnik>();
		
		String s = "select ime,prezime,jmbg from nastavnici;";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			while(rs.next()){
				String ime = rs.getString(1);
				String prezime = rs.getString(2);
				int jmbg = rs.getInt(3);
				Nastavnik n = new Nastavnik(ime,prezime,jmbg);
				nastavnici.add(n);
				
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Greška pri ucitavanju nastavnika.");
			e.printStackTrace();
		}
		
		
		return nastavnici;
	}
	
	
	public static boolean addNastavnik(Connection conn,Nastavnik n){
		boolean ret = false;
		
		String s = "insert into nastavnici (ime,prezime,jmbg) values (?,?,?);";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(s);
			ps.setString(1, n.getIme());
			ps.setString(2, n.getPrezime());
			ps.setInt(3, n.getJmbg());
			if(ps.executeUpdate() == 1){
				System.out.println("Uspešno je dadat nastavnik.");
			}else{
				System.out.println("Greška pri dodavanju.");
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQL greška nastavnika");
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	public static void deleteNastavnik(Connection conn, int jmbg){
		
		String s = "delete from nastavnici where jmbg = ? ;";
		try {
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setInt(1, jmbg);
			if(ps.executeUpdate() == 1){
				System.out.println("Uspesno obrisan");
			}else{
				System.out.println("Nece valjati.");
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQL greska nastavnika.");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
