package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Jezik;
import dom8skolaJezika.model.Kurs;
import dom8skolaJezika.model.Nastavnik;
import dom8skolaJezika.model.Nivo;
import dom8skolaJezika.model.Skola;

public class KursDAO {

	
	public static Kurs getKursById(Connection conn, int idK){
		Kurs k = null;
		String s = "select jezik,nivo,cena,nastavnik from kursevi where kurs_id = '" + idK + "';";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			
			if(rs.next()){
				Skola skola = SkolaDAO.getSkola(conn);
				String jezik = rs.getString(1);
				Jezik j = JezikDAO.getJezikById(conn, jezik);
				int nivoId = rs.getInt(2);
				Nivo nivo = NivoDAO.getNivoById(conn, nivoId);
				int cena = rs.getInt(3);
				int jmbg = rs.getInt(4);
				Nastavnik nast = NastavnikDAO.getNastavnikById(conn, jmbg);
				
				k = new Kurs(idK,cena,skola,nast,j,nivo);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Greška pri ocèitavanju kursa.");
			e.printStackTrace();
		} 
		
		
		
		return k;
	}
	
	public static List<Kurs> getAllKurs(Connection conn){
		List<Kurs> kursevi = new ArrayList<Kurs>();
		
		String s = "select kurs_id from kursevi;";
		try{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			while(rs.next()){
				int id = rs.getInt(1);
				Kurs k = getKursById(conn, id);
				kursevi.add(k);
			}
			st.close();
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return kursevi;
	}
	
	public static boolean addKurs(Connection conn, Kurs k){
		boolean ret = false;
		
		String s = "insert into kursevi (skola_pib,jezik,nivo,cena,nastavnik) values (?,?,?,?,?);";
		
		try {
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setInt(1,k.getSkola().getPib());
			ps.setString(2, k.getJezik().getIdJezika());
			ps.setInt(3, k.getNivo().getIdNivo());
			ps.setInt(4, k.getCena());
			ps.setInt(5, k.getNastavnik().getJmbg());
			
			if(ps.executeUpdate() == 1){
				System.out.println("Kurs je uspesno dodat.");
			}else{
				System.out.println("Greška kod dodavanja u SQL bazu.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ret;
	}

	public static void deleteKurs(Connection conn, Kurs k) {
		
		String s = "delete from kursevi where kurs_id = ?;";
		try {
			PreparedStatement pr = conn.prepareStatement(s);
			pr.setInt(1, k.getIdKursa());
			if(pr.executeUpdate() == 1){
				System.out.println("Kurs je uspešno obrisan.");
			}else{
				System.out.println("Greska SQL kod brisanja kursa.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
