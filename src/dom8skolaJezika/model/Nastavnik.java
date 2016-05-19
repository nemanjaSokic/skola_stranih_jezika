package dom8skolaJezika.model;

public class Nastavnik {

	protected String ime,prezime;
	protected int jmbg;
	public Nastavnik(String ime, String prezime, int jmbg) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
	}
	@Override
	public String toString() {
		return "Nastavnik [ime=" + ime + ", prezime=" + prezime + "]";
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
	
	
}
