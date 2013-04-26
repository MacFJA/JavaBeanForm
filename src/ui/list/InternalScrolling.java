package ui.list;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

public class InternalScrolling extends JPanel implements Scrollable {
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private int rowCount = 0;
	
	public InternalScrolling() {
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		setLayout(gridBagLayout);
	}
	
	public void addLine(Component c) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = rowCount;
		add(c, gbc);

		rowCount++;
		int[] rowHeights = new int[rowCount];
		for(int tour=0; tour < rowCount; tour++) rowHeights[tour] = 0;
		gridBagLayout.rowHeights = rowHeights;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#removeAll()
	 */
	@Override
	public void removeAll() {
		super.removeAll();
		rowCount = 0;
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return this.getPreferredSize();
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return (orientation == SwingConstants.VERTICAL) ?
				visibleRect.height : visibleRect.width;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 10;
	}
	
}