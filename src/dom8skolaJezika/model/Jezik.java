package dom8skolaJezika.model;

public class Jezik {

	protected String idJezika,naziv;

	public Jezik(String idJezika, String naziv) {
		super();
		this.idJezika = idJezika;
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return "Jezik [naziv=" + naziv + "]";
	}

	public String getIdJezika() {
		return idJezika;
	}

	public void setIdJezika(String idJezika) {
		this.idJezika = idJezika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}
