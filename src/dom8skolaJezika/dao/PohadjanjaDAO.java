package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Kurs;
import dom8skolaJezika.model.Ucenik;
import dom8skolaJezika.model.Uplata;

public class PohadjanjaDAO {
	
	public static Ucenik getUplateByUcenik(Connection conn, int jm){
		List<Uplata> sveUplateUcenika = new ArrayList<Uplata>();
		Ucenik u = UcenikDAO.getUcenikByJmbg(conn, jm);
		Uplata upl = null;
		
		
		
		String s = "select uplate.broj_uplatnice from pohadjanje "+
					"join uplate on pohadjanje.pohadjanje_id = uplate.pohadjanje_id join ucenici on pohadjanje.ucenik_jmbg = ucenici.jmbg "+
					"where ucenici.jmbg = "+jm+";";
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

	public static Kurs getPolazniciKursa(Connection conn,int idKursa){
		Kurs k = KursDAO.getKursById(conn, idKursa);
		List<Ucenik> ucenici = new ArrayList<Ucenik>();
		
		String s = "select u.ime,u.prezime,u.jmbg from ucenici u join pohadjanje po on u.jmbg = po.ucenik_jmbg join kursevi k on po.kurs_id = k.kurs_id where k.kurs_id = "+ idKursa +";";
		try {
			Statement pr = conn.createStatement();
			ResultSet rs = pr.executeQuery(s);
			while(rs.next()){
				String ime = rs.getString(1);
				String pre = rs.getString(2);
				int jm = rs.getInt(3);
				Ucenik u = new Ucenik(ime,pre,jm);
				ucenici.add(u);
				
			}
			k.setUcenici(ucenici);
			pr.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return k;
	}
	
	public static void addPolaznikaUKurs(Connection conn, Kurs k, int jmbg){
		
		Ucenik u = UcenikDAO.getUcenikByJmbg(conn, jmbg);
		
		String s = "insert into pohadjanje (kurs_id,ucenik_jmbg) values (?,?);";
		try{
		PreparedStatement pr = conn.prepareStatement(s);
		pr.setInt(1, k.getIdKursa());
		pr.setInt(2, jmbg);
		
		
		
		if(pr.executeUpdate() == 1){
			System.out.println("Uspesno ste dodali studenta na kurs");
		}else{
			System.out.println("Greska pri dodavanju ucenika na kurs.");
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		k.getUcenici().add(u);
	}

	public static void deletePohadjanje(Connection conn, Kurs k, int jmbg) {
		
		String s = "delete from pohadjanje where kurs_id = ? and ucenik_jmbg = ?;";
		
		try {
			PreparedStatement pr = conn.prepareStatement(s);
			pr.setInt(1, k.getIdKursa());
			pr.setInt(2, jmbg);
			if(pr.executeUpdate() == 1){
				System.out.println("Pohadjanje je uspršno obrisano.");
			}else{
				System.out.println("Greška pri brisaju.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
