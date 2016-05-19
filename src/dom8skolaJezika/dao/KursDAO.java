package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dom8skolaJezika.model.Jezik;
import dom8skolaJezika.model.Kurs;
import dom8skolaJezika.model.Nastavnik;
import dom8skolaJezika.model.Nivo;
import dom8skolaJezika.model.Skola;

public class KursDAO {

	
	public static Kurs getKursById(Connection conn, int idK){
		Kurs k = null;
		String s = "select jezik,nivo,cena,nastavnik from kurs where kurs_id = '" + idK + "';";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			Skola skola = SkolaDAO.getSkola(conn);
			String jezik = rs.getString(1);
			Jezik j = JezikDAO.getJezikById(conn, jezik);
			int nivoId = rs.getInt(2);
			Nivo nivo = NivoDAO.getNivoById(conn, nivoId);
			int cena = rs.getInt(3);
			int jmbg = rs.getInt(4);
			Nastavnik nast = NastavnikDAO.getNastavnikById(conn, jmbg);
			
			k = new Kurs(idK,cena,skola,nast,j,nivo);
			
		} catch (SQLException e) {
			System.out.println("Greška pri ocèitavanju kursa.");
			e.printStackTrace();
		} 
		
		
		
		return k;
	}
	
}
