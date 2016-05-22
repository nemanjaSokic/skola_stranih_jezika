package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Ucenik;

public class UcenikDAO {

	public static Ucenik getUcenikByJmbg(Connection conn, int jmbg){
		Ucenik u = null;
		
		String s = "select ime,prezime from ucenici where jmbg = '" + jmbg + "';";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			
			if(rs.next()){
				String ime = rs.getString(1);
				String prezime = rs.getString(2);
				
				u = new Ucenik(prezime,ime,jmbg);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {

			System.out.println("Greška kod uèitavanja ucenika.");
			e.printStackTrace();
			
		}
		
		
		return u;
	}
	
	
	public static List<Ucenik> getAllUcenik(Connection conn) {
		List<Ucenik> listaUcenika = new ArrayList<Ucenik>();
		
		String upitUcenika = "SELECT ime,prezime,jmbg from ucenici;";
		try{
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(upitUcenika);
		while(rs.next()){
			String ime = rs.getString(1);
			String prezime = rs.getString(2);
			int jmbg = rs.getInt(3);
			
			
			Ucenik uc = new Ucenik(ime,prezime,jmbg);
			
			listaUcenika.add(uc);
			
			}st.close();
			rs.close();
		
		}catch(SQLException e){
			System.out.println("Greska kod ucenika.");
			e.printStackTrace();
		}
	
		return listaUcenika;
	}
	
	public static boolean addUcenik(Connection conn,Ucenik n){
		boolean ret = false;
		
		String s = "insert into ucenici (ime,prezime,jmbg) values (?,?,?);";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(s);
			ps.setString(1, n.getIme());
			ps.setString(2, n.getPrezime());
			ps.setInt(3, n.getJmbg());
			if(ps.executeUpdate() == 1){
				System.out.println("Uspešno je dadat ucenik.");
			}else{
				System.out.println("Greška pri dodavanju.");
			}
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("SQL greška ucenika");
			e.printStackTrace();
		}
		
		return ret;
	}
	
}
