package dom8skolaJezika.model;

import java.sql.Date;

public class Uplata {

	protected int uplatnicaBr,uplata;
	Date datum;
	public Uplata(int uplatnicaBr, int uplata, Date datum) {
		super();
		this.uplatnicaBr = uplatnicaBr;
		this.uplata = uplata;
		this.datum = datum;
	}
	
	@Override
	public String toString() {
		return "Uplata " + uplata + "\tdatum " + datum + "\n";
	}
	public int getUplatnicaBr() {
		return uplatnicaBr;
	}
	public void setUplatnicaBr(int uplatnicaBr) {
		this.uplatnicaBr = uplatnicaBr;
	}
	public int getUplata() {
		return uplata;
	}
	public void setUplata(int uplata) {
		this.uplata = uplata;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
}
