package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		} catch (SQLException e) {

			System.out.println("Greška kod uèitavanja uplate.");
			e.printStackTrace();
			
		}
		
		
		return u;
	}
	
}
