package mission;

import javax.swing.JLabel;
import javax.swing.JPanel;

final class Point_Panel extends JPanel{
	private JLabel j;
	public Point_Panel()
	{
		j = new JLabel();
		add(j);
	}
	public void point_update(String t)
	{
		j.setText(t);
	}
	public JLabel getJ() {
		return j;
	}
	public void setJ(JLabel j) {
		this.j = j;
	}

	
	
}
