package energetskiSistem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {

	Button dodaj = new Button("Dodaj");
	Plac plac;
	Parcela parcela = null;
	Baterija baterija;
	private int i, j, kap;
	
	public void populateWindow() {
		
		Panel zaDugme = new Panel();
		zaDugme.add(dodaj,BorderLayout.CENTER);
		
		add(zaDugme, BorderLayout.NORTH);
		
		plac = new Plac(i, j);
		baterija = new Baterija(kap);
		
		dodaj.addActionListener((ae) ->{
			
			plac.dodajProizvodjaca(new Hidroelektrana(baterija), plac.kliknutaParcela);
		});
		
		int dim1 = getWidth();
		int dim2 = getHeight();
		
		int dim = (dim1 > dim2 ? dim1 : dim2);
		
		plac.setPreferredSize(new Dimension(dim, dim));
		
		Panel zaPlac = new Panel();
		zaPlac.add(plac);
		
		add(zaPlac, BorderLayout.CENTER);
		
		pack();
	}
	
	public EnergetskiSistem(int i, int j, int kap) {
		
		this.i = i; this.j = j; this.kap = kap;
		
		setBounds(new Rectangle(500, 500));
		setResizable(false);
		setTitle("Energetski sistem");
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
		
			@Override
			public void windowClosing(WindowEvent e) { 
				
				plac.zaustaviElektranu();
				dispose(); 
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		int i = (int) (Math.random()*10 + 3);
		int j = (int) (Math.random()*10 + 3);
		
		int kap = (int) (Math.random()*200 + 50);
		
		new EnergetskiSistem(i, j, kap);
	}
}

