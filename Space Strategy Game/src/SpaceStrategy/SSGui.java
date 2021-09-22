package SpaceStrategy;

public class SSGui {
	
	public BoardFrame boardframe;

	public static void main(String[] args) {
		SSGui gui= new SSGui();
		gui.boardframe= new BoardFrame();
		gui.boardframe.setVisible(true);
	}

}
