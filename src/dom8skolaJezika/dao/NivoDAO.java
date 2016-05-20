package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Nivo;

public class NivoDAO {


	public static Nivo getNivoById(Connection conn, int id){
		Nivo n = null;
		
		String s = "SELECT nivo_naziv FROM nivoi WHERE nivo_id = '" + id + "';";
		try{
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		if(rs.next()){
			String naziv = rs.getString(1);
			n = new Nivo(id,naziv);
			st.close();
			rs.close();			
		}
		}catch(SQLException e){
			System.out.println("Greška pri oèitavanju nivoa.");
		}
		
		return n;
	}
	public static List<Nivo> getAllNivo(Connection conn){
		List<Nivo> listaJezika = new ArrayList<Nivo>();
		
		String s = "select nivo_id,nivo_naziv from nivoi;";
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			while(rs.next()){
				int id = rs.getInt(1);
				String naziv = rs.getString(2);
				
				Nivo j = new Nivo(id,naziv);
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
}
