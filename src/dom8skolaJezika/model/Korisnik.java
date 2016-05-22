package dom8skolaJezika.model;

public class Korisnik {

	protected String tipKorisnika,korisnickoIme,sifraKorisnika;

	

	public Korisnik(String tipKorisnika, String korisnickoIme, String sifraKorisnika) {
		super();
		this.tipKorisnika = tipKorisnika;
		this.korisnickoIme = korisnickoIme;
		this.sifraKorisnika = sifraKorisnika;
	}

	public String getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifraKorisnika() {
		return sifraKorisnika;
	}

	public void setSifraKorisnika(String sifraKorisnika) {
		this.sifraKorisnika = sifraKorisnika;
	}
	
	@Override
	public String toString() {
		return "tip korisnika: " + tipKorisnika + "\n korisnicko ime: " + korisnickoIme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((korisnickoIme == null) ? 0 : korisnickoIme.hashCode());
		result = prime * result + ((sifraKorisnika == null) ? 0 : sifraKorisnika.hashCode());
		result = prime * result + ((tipKorisnika == null) ? 0 : tipKorisnika.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		if (sifraKorisnika == null) {
			if (other.sifraKorisnika != null)
				return false;
		} else if (!sifraKorisnika.equals(other.sifraKorisnika))
			return false;
		if (tipKorisnika == null) {
			if (other.tipKorisnika != null)
				return false;
		} else if (!tipKorisnika.equals(other.tipKorisnika))
			return false;
		return true;
	}
	
	
}
