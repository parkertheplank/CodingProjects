package SpaceStrategy;
import java.awt.Color;
import java.awt.Graphics;

public class SpaceCraft extends SpaceVehicle{

	public SpaceCraft(Position p, boolean team, Board b) {
		super(p,team,b);
		points= 5; //set point value of SpaceCraft
	}
	public boolean canMove(int rad, int rot) {
		boolean sameRad= rad==pos.getRad();
		boolean sameRot= rot==pos.getRot();
		boolean diagonal= Math.abs(rad-pos.getRad())==Math.abs(rot-pos.getRot());
		boolean diag2=false;
		boolean path=false;
		boolean dest=false;
		
		//checks if destination is valid
		if(purple) {
			for(SpaceVehicle v: board.Purple_Pieces) {
    			if(Position.equals(v.pos, board.click)) { 
    				return false;
    			}
    		}
		}
		else if(!purple) {
			for(SpaceVehicle v: board.Green_Pieces) {
    			if(Position.equals(v.pos, board.click)) { 
    				return false;
    			}
    		}
		}
		//checks whether moving diagonally
		if( rot>=12 && pos.getRot()<=3)
			diag2=Math.abs(rad-pos.getRad())==Math.abs(rot-(pos.getRot()+16));
		else if(rot<=3 && pos.getRot()>=12)
			diag2=Math.abs(rad-pos.getRad())==Math.abs((rot+16)-(pos.getRot()));
		
		if(diagonal || diag2) {
			dest= true;
		}
		else if(sameRad  && !sameRot) {
			dest= true;
		}
		else if((!sameRad) && sameRot) {
			dest= true;
		}
		else
			return false;
		
		//checks whether path is legal
		path=true;
						
		//returns whether move is valid
		if(dest && path)
			return true;
		else
			return false;
	}
	
	public void drawVehicle(Position pos, Graphics g) {
		super.drawVehicle(pos, g);
		g.setColor(Color.black);
		g.drawString("5",x-2,y+5);
	}
}
