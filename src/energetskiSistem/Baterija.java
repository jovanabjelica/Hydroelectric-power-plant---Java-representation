package energetskiSistem;

public class Baterija {
	
	int kolicinaEnergije;
	int maksimalanKapacitet;
	
	public Baterija(int maksimalanKapacitet) {
		
		this.maksimalanKapacitet = maksimalanKapacitet;
		this.kolicinaEnergije = maksimalanKapacitet;
	}
	
	public boolean dodajEnergiju(int kolicina) {
		
		kolicinaEnergije += kolicina;
		if (kolicinaEnergije > maksimalanKapacitet) { kolicinaEnergije = maksimalanKapacitet; return false; }
		return true;
	}
	
	public void isprazniBateriju() { kolicinaEnergije = 0; }
	public boolean daLiJePuna() { return kolicinaEnergije == maksimalanKapacitet; }
}

