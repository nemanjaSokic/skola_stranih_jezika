package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
}
