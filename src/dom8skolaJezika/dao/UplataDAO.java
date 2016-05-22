package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Ucenik;
import dom8skolaJezika.model.Uplata;

public class UplataDAO {

	public static Uplata getUplataById(Connection conn, int uplata){
		Uplata u = null;
		
		String s = "select datum,kolicina from uplate where broj_uplatnice = '" + uplata + "'";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			
			if(rs.next()){
				Date datum = rs.getDate(1);
				int uplaceno = rs.getInt(2);
				
				u = new Uplata(uplata,uplaceno,datum);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {

			System.out.println("Greška kod uèitavanja uplate.");
			e.printStackTrace();
			
		}
		
		
		return u;
	}
	
	public static Ucenik getUplateByUcenik(Connection conn, int jm){
		List<Uplata> sveUplateUcenika = new ArrayList<Uplata>();
		Ucenik u = UcenikDAO.getUcenikByJmbg(conn, jm);
		Uplata upl = null;
		
		
		
		String s = "select uplate.broj_uplatnice, ku.kurs_id, pohadjanje.pohadjanje_id from pohadjanje "
				+ "join uplate on pohadjanje.pohadjanje_id = uplate.pohadjanje_id"
				+ " join kursevi ku on pohadjanje.kurs_id = ku.kurs_id "
				+ "join ucenici on pohadjanje.ucenik_jmbg = ucenici.jmbg where ucenici.jmbg = "+jm+";";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			while(rs.next()){
				
				int brUpl = rs.getInt(1);
			
				
				
				
				upl = UplataDAO.getUplataById(conn, brUpl);
				sveUplateUcenika.add(upl);
				
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		u.setUplate(sveUplateUcenika);
		return u;
	}

	public static void addUplata(Connection conn, Uplata up, int id, int jmbg) {
		
		String s = "insert into uplate (pohadjanje_id,broj_uplatnice,datum,kolicina) values(?,?,?,?);";
		try {
			PreparedStatement pr = conn.prepareStatement(s);
			pr.setInt(1, PohadjanjaDAO.getIdPohadjanja(conn, id, jmbg));
			pr.setInt(2, up.getUplatnicaBr());
			pr.setDate(3, up.getDatum());
			pr.setInt(4, up.getUplata());
			if(pr.executeUpdate() == 1){
				System.out.println("Uspešno izvršena uplata.");
				
			}else{
				System.out.println("Greška pri izvršavanju uplate.");
			}
			pr.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
