package energetskiSistem;

import java.awt.Color;

public abstract class Proizvodjac extends Parcela implements Runnable {

	protected int vreme;
	protected Baterija baterija;
	protected int ukupnoVreme;
	Plac vlasnik;
	Thread nit = new Thread(this);
	
	public Proizvodjac(char oznaka, Color boja, int vreme, Baterija baterija) {
		
		super(oznaka, boja);
		
		this.vreme = vreme;
		this.baterija = baterija;
		this.ukupnoVreme = (int) (Math.random() * 300);
		nit.start();
	}
	
	@Override
	public void run() {
		
		try {
			
			while (!nit.isInterrupted()) {
				
				Thread.sleep(ukupnoVreme);
				
				boolean provera = proizvedi();
				
				Thread.sleep(300);
				
				if (provera) {
					
					this.setForeground(Color.RED);
					Thread.sleep(300);
					this.setForeground(Color.WHITE);
				}
			}
		}
		
		catch (InterruptedException e) {}
	}
	
	public abstract boolean proizvedi();
	
	public void zaustaviProizvodjaca() {
		
		nit.interrupt();
	}
}
