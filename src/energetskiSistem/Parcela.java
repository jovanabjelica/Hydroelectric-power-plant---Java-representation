package energetskiSistem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label {
	
	protected char oznaka;
	protected Color boja;
	
	public Parcela(char oznaka, Color boja) {
			
		this.oznaka = oznaka;
		this.boja = boja;
		
		setFont(new Font("Serif", Font.BOLD, 14));
		setForeground(Color.WHITE);
		setBackground(boja);
		setAlignment(CENTER);
		setText(((Character)oznaka).toString());
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Plac kontejner = (Plac)getParent();
				kontejner.klikniNaParcelu((Parcela)e.getComponent());
			}
		});
		
	}
	
	public void promeniBoju(Color boja) { this.boja = boja; }
	
}
