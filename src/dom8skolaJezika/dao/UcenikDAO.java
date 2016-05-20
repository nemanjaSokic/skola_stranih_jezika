package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Ucenik;

public class UcenikDAO {

	public static Ucenik getUcnikByJmbg(Connection conn, int jmbg){
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
}
