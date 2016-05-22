package dom8skolaJezika.model;

import java.util.ArrayList;
import java.util.List;

public class Ucenik {
	
	protected String ime,prezime;
	protected int jmbg;
	protected List<Uplata> uplate = new ArrayList<Uplata>();
	
	
	public Ucenik(String ime, String prezime, int jmbg) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		uplate = new ArrayList<Uplata>();
	}
	public Ucenik(String ime, String prezime, int jmbg, List<Uplata> uplate) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.uplate = uplate;
	}
	
	@Override
	public String toString() {
		return "Ucenik " + ime + " " + prezime;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public int getJmbg() {
		return jmbg;
	}
	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}
	public List<Uplata> getUplate() {
		return uplate;
	}
	public void setUplate(List<Uplata> uplate) {
		this.uplate = uplate;
	}
	
}
