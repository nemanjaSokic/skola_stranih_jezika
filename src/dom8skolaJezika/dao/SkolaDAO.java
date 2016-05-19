package dom8skolaJezika.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dom8skolaJezika.model.Skola;

public class SkolaDAO {

	
	public static Skola getSkola(Connection conn){
		Skola sk = null;
		String s = "select naziv,adresa,telefon,email,internet,pib,maticni_broj,broj_racuna from skola;";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			while(rs.next()){
				String naziv = rs.getString(1);
				String adresa = rs.getString(2);
				int telefon = rs.getInt(3);
				String email = rs.getString(4);
				String internet = rs.getString(5);
				int pib = rs.getInt(6);
				int maticni = rs.getInt(7);
				String brRacuna = rs.getString(8);
				sk = new Skola(naziv,adresa,email,internet,brRacuna,telefon,pib,maticni);
				
			}
			
			st.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("Greška pri kreiranju objekta skole.");
			e.printStackTrace();
		}
		
		
		
		return sk;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
