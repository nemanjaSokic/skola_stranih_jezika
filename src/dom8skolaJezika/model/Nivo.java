package dom8skolaJezika.model;

public class Nivo {
	protected int idNivo;
	protected String nazivNivoa;
	public Nivo(int idNivo, String nazivNivoa) {
		super();
		this.idNivo = idNivo;
		this.nazivNivoa = nazivNivoa;
	}
	@Override
	public String toString() {
		return "Nivo [nazivNivoa=" + nazivNivoa + "]";
	}
	public int getIdNivo() {
		return idNivo;
	}
	public void setIdNivo(int idNivo) {
		this.idNivo = idNivo;
	}
	public String getNazivNivoa() {
		return nazivNivoa;
	}
	public void setNazivNivoa(String nazivNivoa) {
		this.nazivNivoa = nazivNivoa;
	}
	
	
}
