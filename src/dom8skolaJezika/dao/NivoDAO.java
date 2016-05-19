package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dom8skolaJezika.model.Nivo;

public class NivoDAO {


	public static Nivo getNivoById(Connection conn, int id){
		Nivo n = null;
		
		String s = "SELECT nivo_naziv FROM nivoi WHERE nivo_id = '" + id + "';";
		try{
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		rs.next();
		String naziv = rs.getString(1);
		n = new Nivo(id,naziv);
		st.close();
		rs.close();
		}catch(SQLException e){
			System.out.println("Greška pri oèitavanju nivoa.");
		}
		
		return n;
	}
	
}
