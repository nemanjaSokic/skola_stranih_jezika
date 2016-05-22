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

public class PohadjanjaDAO {
	
	public static int getIdPohadjanja(Connection conn,int idK,int jmbg){
		int index = 0;
		Kurs k = getPolazniciKursa(conn, idK);
		if(k.getUcenici() == null){
			System.out.println("Na ovom kursu nema polaznika.");
			return 0;
		}
		Ucenik u = null;
		boolean provera = false;
		for (int i = 0; i < k.getUcenici().size(); i++) {
			if(k.getUcenici().get(i).getJmbg() == jmbg){
				u = UcenikDAO.getUcenikByJmbg(conn, jmbg);
				provera = true;
			}
		}if(provera == false){
			System.out.println("Ucenik sa jmng-om " + jmbg + " nije na ovom kursu.");
			return 0;
		}
		
		
		String s = "select pohadjanje_id from pohadjanje where kurs_id = " + k.getIdKursa() + " and ucenik_jmbg = " + u.getJmbg() + ";";
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			if(rs.next()){
				index = rs.getInt(1);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return index;
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
		pr.close();
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
			pr.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public static List<Kurs> getKursByPolaznik(Connection conn, int jmbg) {
		
		Kurs k = null;
		List<Kurs> kursevi = new ArrayList<Kurs>();
		String s = "select kurs_id from pohadjanje where ucenik_jmbg = " + jmbg +";";
		try{
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		while(rs.next()){
			int id = rs.getInt(1);
			
			k = KursDAO.getKursById(conn, id);
			
			kursevi.add(k);
		}
		st.close();
		rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return kursevi;
	}
	
	
}
