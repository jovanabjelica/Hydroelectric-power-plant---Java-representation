package energetskiSistem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

public class Plac extends Panel {
	
	int redovi;
	int kolone;
	Parcela kliknutaParcela;
	Parcela[][] matrica;
	
	public Plac(int kolone, int redovi) {
		
		this.redovi = redovi;
		this.kolone = kolone;
		
		setLayout(new GridLayout(0, kolone, 3, 3));
		
		matrica = new Parcela [redovi][kolone];
		
		int i = 0;
		
		while (i < redovi) {
			
			int j = 0;
			
			while (j < kolone) {
				
				int verovatnoca = (int) (Math.random() * 100);
				Parcela parcela;
				
				if (verovatnoca > 70) parcela = new VodenaPovrs('~', Color.CYAN);
				else parcela = new TravnataPovrs('"', Color.GREEN);
				
				matrica[i][j] = parcela;
				
				add(parcela);
				
				j++;
			}
			
			i++;
		}
	} 
	
	public void klikniNaParcelu(Parcela p) {
		
		if(kliknutaParcela == null) kliknutaParcela = p;
		
		Parcela staraParcela = kliknutaParcela;
		kliknutaParcela = p;
		
		staraParcela.setFont(new Font("Serif", Font.BOLD,14));
		kliknutaParcela.setFont(new Font("Serif", Font.BOLD, 20));
	
	}
	
	public void dodajProizvodjaca(Proizvodjac proizvodjac, Parcela parcela) {

		if (kliknutaParcela == null) return;
		kliknutaParcela = parcela;
		
		int i = 0;
		
		while (i < redovi) {
		
			int j = 0;
			
			while (j < kolone) {
						
				if (matrica[i][j] != kliknutaParcela) { j++; continue; }
				
				matrica[i][j] = proizvodjac;
				remove(i * matrica[0].length + j);
				add(proizvodjac, i * matrica[0].length + j);
				
				if (proizvodjac instanceof Hidroelektrana) {
					
					racunajVodenePovrsine((Hidroelektrana)proizvodjac);
				}
				
				j++;
			}
			
			i++;
		}
		
		revalidate();
	}
	
	
	private void racunajVodenePovrsine(Hidroelektrana p) {
		
		int ipoz = -1, jpoz = -1;
		
		for (int i = 0; i < redovi; i++) {
			
			for (int j = 0; j < kolone; j++) {
				
				if (matrica[i][j] == p) {
					
					ipoz = i;
					jpoz = j;
					break;
				}
			}
		}
		
		if (ipoz == -1 || jpoz == -1) return;
		
		int r1 = ipoz-1, r2 = ipoz + 1;
		int k1 = jpoz - 1, k2 = jpoz + 1;
		
		if (r1 < 0) r1 = 0;
		if (k1 < 0) k1 = 0;
		if (r2 >= redovi) r2 = redovi - 1;
		if (k2 >= kolone) k2 = kolone - 1;
			
		if (matrica[r1][k1] instanceof VodenaPovrs) p.brVodenihPovrsi++;
		if (matrica[r1][jpoz] instanceof VodenaPovrs) p.brVodenihPovrsi++;
		if (matrica[r1][k2] instanceof VodenaPovrs) p.brVodenihPovrsi++;
		
		if (matrica[ipoz][k1] instanceof VodenaPovrs) p.brVodenihPovrsi++;
		if (matrica[ipoz][k2] instanceof VodenaPovrs) p.brVodenihPovrsi++;
		
		if (matrica[r2][k1] instanceof VodenaPovrs) p.brVodenihPovrsi++;
		if (matrica[r2][jpoz] instanceof VodenaPovrs) p.brVodenihPovrsi++;
		if (matrica[r2][k2] instanceof VodenaPovrs) p.brVodenihPovrsi++;
		
	}
	
	void zaustaviElektranu() {
		
		for (int i = 0; i < redovi; i++) {
			
			for (int j = 0; j < kolone; j++) {
				
				if (matrica[i][j] instanceof Proizvodjac) ((Proizvodjac) matrica[i][j]).zaustaviProizvodjaca();
			}
		}
	}
}
