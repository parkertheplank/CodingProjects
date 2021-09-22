package SpaceStrategy;
import java.awt.Color;
import java.awt.Graphics;

public abstract class SpaceVehicle {
	protected Position pos; //tracks piece position
	protected int x; //x cord of piece
	protected int y; //y cord of piece
	
	protected boolean purple; //tracks whether purple or green player
	protected int points; //tracks point value of piece
	private int pW=60; //pieceWidth
	
	protected Board board;
	
	public SpaceVehicle(Position p, boolean team, Board b) {
		pos=p;
		purple=team;
		board=b;
	}
	
	public abstract boolean canMove(int rad, int rot);
	
	public void move(int rad, int rot) {
		board.pieceIndex[pos.getRad()-1][pos.getRot()]=0;
		board.pieceIndex[pos.getRad()-1][pos.getRot()+16]=0;
		board.pieceIndex[pos.getRad()-1][pos.getRot()+32]=0;
		
		pos.setPos(rad, rot);
		board.pieceIndex[rad-1][rot]=1;
		board.pieceIndex[rad-1][rot+16]=1;
		board.pieceIndex[rad-1][rot+32]=1;
		
		board.printArr();
	}
	
	public void drawVehicle(Position pos, Graphics g) {
		if(purple) //assigns piece color based on player
				g.setColor(Color.magenta);
		else if(!purple)
				g.setColor(Color.green);
		
		//calculates x and y cord from position
		int radius=Board.innerRad+Board.ringRad/2+(Board.ringRad*(pos.getRad()-1));
		double degrees=(Math.PI/16+pos.getRot()*Math.PI/8);
		x=(int)(Board.cenX+radius*Math.cos(degrees));
		y=(int)(Board.cenX+radius*Math.sin(degrees));
		
		g.fillOval(x-pW/2,y-pW/2,pW,pW);
	}

	public void highlight(Position pos, Graphics g) {
		int radius=Board.innerRad+Board.ringRad/2+(Board.ringRad*(pos.getRad()-1));
		double degrees=(Math.PI/16+pos.getRot()*Math.PI/8);
		x=(int)(Board.cenX+radius*Math.cos(degrees));
		y=(int)(Board.cenX+radius*Math.sin(degrees));
		
		g.setColor(new Color(220, 50, 0, 200));
		g.fillOval(x-pW/2,y-pW/2,pW,pW);
	}
}
