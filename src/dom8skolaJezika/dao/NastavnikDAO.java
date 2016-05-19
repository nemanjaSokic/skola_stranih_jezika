package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dom8skolaJezika.model.Nastavnik;

public class NastavnikDAO {

	
	public static Nastavnik getNastavnikById(Connection conn, int jmbg){
		Nastavnik n = null;
		
		String s = "select ime,prezime from nastavnici where jmbg = '" + jmbg + "';";
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			rs.next();
			String ime = rs.getString(1);
			String prezime = rs.getString(2);
			n = new Nastavnik(ime,prezime,jmbg);
			st.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Greška kod ucitavanja nastavnika.");
			e.printStackTrace();
		}
		
		return n;
	}
}
