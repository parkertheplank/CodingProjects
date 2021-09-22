package SpaceStrategy;
import java.awt.Color;
import java.awt.Graphics;

public class SpacePod extends SpaceVehicle {
	
	public SpacePod(Position p, boolean team, Board b) {
		super(p, team, b);
		points=1; //sets point value of SpacePod
	} 
	public boolean canMove(int rad, int rot) {
		boolean sameRad= rad==pos.getRad();
		boolean sameRot= rot==pos.getRot();
		boolean dest=false; //whether destination is legal
		boolean path=true; //whether path is legal
		//boolean clockwise = true;
		//boolean counterwise =true;
		
		//int clockDest;
		//int counterDest;
		
		//checks whether dest is legal
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
		
		if(sameRad  && !sameRot) {
			dest= true;
		}
		else if(!sameRad && sameRot) {
			dest= true;
		}
		
		//checks whether path is legal
		if(sameRot && (rad>pos.getRad())) {
			for(int n=pos.getRad(); n<rad-1; n++) {
				if(board.pieceIndex[n][rot]==1)
					path=false;
			}
		}
		else if(sameRot && (rad<pos.getRad())) {
			for(int n=rad; n<pos.getRad()-1; n++) {
				if(board.pieceIndex[n][rot]==1)
					path=false;
			}
		}
		/*
		else if(sameRad){
			if(pos.getRot()>rot) {
				clockDest= rot+16;
				counterDest= pos.getRad();
			}
			else {
				clockDest= rot;
				counterDest= pos.getRad()+16;
			}
			
			for(int n=pos.getRot()+1;n< clockDest ;n++) {
				if(board.pieceIndex[rad-1][n]==1)
					clockwise = false;
				System.out.println(clockwise);
			}
			for(int n=rot+1;n< counterDest ;n++){
				if(board.pieceIndex[rad-1][n]==1)
					counterwise=false;
				System.out.println(clockwise);
			}
			if(!clockwise && !counterwise)
				path=false;
		}*/
		
		//returns whether move is valid
		if(dest && path)
			return true;
		else
			return false;
	}
	public void drawVehicle(Position pos, Graphics g) {
		super.drawVehicle(pos, g);
		g.setColor(Color.black);
		g.drawString("1",x-5,y+10);
	}
}
