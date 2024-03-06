package energetskiSistem;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac {

	int brVodenihPovrsi = 0;
	
	public Hidroelektrana(Baterija baterija) {
		
		super('H', Color.BLUE, 1500, baterija);	
	}

	@Override
	public boolean proizvedi() {
		
		if (brVodenihPovrsi == 0) return false;
		
		baterija.dodajEnergiju(brVodenihPovrsi);
		return true;
	}
}
