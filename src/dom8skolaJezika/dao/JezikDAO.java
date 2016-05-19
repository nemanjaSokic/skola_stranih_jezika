package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Jezik;

public class JezikDAO {

	
	public static Jezik getJezikById(Connection conn, String id){
		Jezik j = null;
		
		try {
			String s = "SELECT naziv FROM jezici WHERE jezik_id = '" + id + "';";
			Statement prst = conn.createStatement();
			ResultSet rs = prst.executeQuery(s);
			if(rs.next()){
				
				String naz = rs.getString(1);
				j = new Jezik(id,naz);
			}
			rs.close();
			prst.close();
		} catch (SQLException e) {
			System.out.println("Greška pri citanju baze u tabeli jezici.");
			e.printStackTrace();
		}
		
		return j;
	}
	
	public static List<Jezik> getAllJezik(Connection conn){
		List<Jezik> listaJezika = new ArrayList<Jezik>();
		
		String s = "select jezik_id,naziv from jezici;";
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			while(rs.next()){
				String id = rs.getString(1);
				String naziv = rs.getString(2);
				
				Jezik j = new Jezik(id,naziv);
				listaJezika.add(j);
				
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaJezika;
	}
	
	
	public static boolean addJezik(Connection conn,Jezik j){
		boolean ret = false;
		
		String s = "INSERT INTO jezici (jezik_id,naziv) values(?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(s);
			
			ps.setString(1, j.getIdJezika());
			ps.setString(2, j.getNaziv());
			
			if(ps.executeUpdate() == 1){
				System.out.println("Uspešno je dodat jezik u evidenciju.");
			}else{
				System.out.println("Greska pri dodavanju jezika.");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void deleteJezik(Connection conn, String id){
		
		String s = "DELETE FROM jezici WHERE jezik_id = ?;";
		try {
			
			
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setString(1, id);
			
			if(ps.executeUpdate()== 1){
				System.out.println("Jezik uspesno obrisan.");
			}else{
				System.out.println("Greska pri brisanju.");
			}
			ps.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void izmenaJezika(Connection conn,String id,String noviNaziv){
		
		String s = "update jezici set naziv = ? where jezik_id = '" + id + "';";
		try {
		
			PreparedStatement ps = conn.prepareStatement(s);
				ps.setString(1, noviNaziv);
				if(ps.executeUpdate() == 1){
					System.out.println("Uspesno izmenjen naziv jezika " + noviNaziv);
				}else{
					System.out.println("Greška pri izmeni.");
				}
				ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
