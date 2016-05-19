package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dom8skolaJezika.model.Ucenik;
import dom8skolaJezika.model.Uplata;

public class PohadjanjaDAO {
	
	public static Ucenik getUplateByUcenik(Connection conn, int jm){
		List<Uplata> sveUplateUcenika = new ArrayList<Uplata>();
		Ucenik u = UcenikDAO.getUcnikByJmbg(conn, jm);
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
	
}
