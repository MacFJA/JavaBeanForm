package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import event.AddEvent;
import event.UpdateEvent;

public class ColorUI extends JButton implements UI {
	private Color couleur = Color.RED;
	private UpdateEvent updateEvent;

	/**
	 * Create the panel.
	 */
	public ColorUI() {
		setText("Choose...");
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				couleur = JColorChooser.showDialog(ColorUI.this, "Select a color", couleur);
				setIcon(getColorIcon());
				if(updateEvent != null) updateEvent.onChange(couleur);
			}
		});
		setIcon(getColorIcon());
	}
	
	private ImageIcon getColorIcon() {
		int size = 14;
		BufferedImage ii = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = ii.createGraphics();
		g2.setColor(couleur);
		g2.fillRect(0, 0, size, size);
		g2.setColor(couleur.darker());
		g2.drawRect(0, 0, size-1, size-1);
		
		return new ImageIcon(ii);
		
	}

	@Override
	public void setUpdateEvent(UpdateEvent updateEvent) {
		this.updateEvent = updateEvent;
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void setValueOfUI(Object value) {
		couleur = (Color) value;
		setIcon(getColorIcon());
	}

	@Override
	public void setAddEvent(AddEvent addEvent) {}

}
