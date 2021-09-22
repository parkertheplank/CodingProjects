package SpaceStrategy;

import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class BoardFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	Component component;
	
	public BoardFrame() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Space Strategy");
		this.setResizable(false);
		component = new Board();
		this.add(component, BorderLayout.CENTER);
		
		this.setLocation(200,50);
		this.pack();
		this.setVisible(true);
	}

}
