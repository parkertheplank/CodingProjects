package SpaceStrategy;

public class Position {
	private int rad;
	private int rot;
	
	public Position(int ra, int ro) {
		rad=ra;
		rot=ro;
	}
	public void setPos(int ra, int ro) {
		rad=ra;
		rot=ro;
	}
	public int getRad() {
		return rad;
	}
	public int getRot() {
		return rot;
	}
	public static boolean equals(Position i, Position j) {
		if(i.getRad()!=j.getRad())
			return false;
		else if(i.getRot()!=j.getRot())
			return false;
		else
			return true;
	}
}
