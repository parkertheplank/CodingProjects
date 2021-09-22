package SpaceStrategy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Board extends JComponent {
	static final int panLen=800;
	static final int boardRad=panLen/2;
	static final int innerRad=boardRad/4;
	static final int ringRad=(boardRad-innerRad)/4;
	static final int cenX=panLen/2;
	static final int cenY=panLen/2;
	
	private int purplePoints =0;
	private int greenPoints=0;
	
	private boolean winning=false;
	private int winPoints=20; //set to 36 for annihilation mode
	
	boolean purpTurn=true;
	
	public ArrayList<SpaceVehicle> Purple_Pieces;
	public ArrayList<SpaceVehicle> Green_Pieces;
	public int[][] pieceIndex= {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1},
								{1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1},
								{1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1}};
	
	public SpaceVehicle Active_Piece; //tracks of selected piece
	public Position click;
	
	public void initPieces() {
		Purple_Pieces= new ArrayList<SpaceVehicle>();
		Green_Pieces= new ArrayList<SpaceVehicle>();
		
		Purple_Pieces.add(new SpacePod(new Position(4,14),true,this));
		Purple_Pieces.add(new SpacePod(new Position(4,1),true,this));
		Purple_Pieces.add(new SpacePod(new Position(2,15),true,this));
		Purple_Pieces.add(new SpacePod(new Position(2,0),true,this));
		Purple_Pieces.add(new SpaceModule(new Position(4,15),true,this));
		Purple_Pieces.add(new SpaceModule(new Position(4,0),true,this));
		Purple_Pieces.add(new SpaceModule(new Position(3,14),true,this));
		Purple_Pieces.add(new SpaceModule(new Position(3,1),true,this));
		Purple_Pieces.add(new SpaceCraft(new Position(3,15),true,this));
		Purple_Pieces.add(new SpaceCraft(new Position(3,0),true,this));
		Purple_Pieces.add(new SpaceCraft(new Position(2,14),true,this));
		Purple_Pieces.add(new SpaceCraft(new Position(2,1),true,this));

		Green_Pieces.add(new SpacePod(new Position(4,9),false,this));
		Green_Pieces.add(new SpacePod(new Position(4,6),false,this));
		Green_Pieces.add(new SpacePod(new Position(2,7),false,this));
		Green_Pieces.add(new SpacePod(new Position(2,8),false,this));
		Green_Pieces.add(new SpaceModule(new Position(4,7),false,this));
		Green_Pieces.add(new SpaceModule(new Position(4,8),false,this));
		Green_Pieces.add(new SpaceModule(new Position(3,9),false,this));
		Green_Pieces.add(new SpaceModule(new Position(3,6),false,this));
		Green_Pieces.add(new SpaceCraft(new Position(3,7),false,this));
		Green_Pieces.add(new SpaceCraft(new Position(3,8),false,this));
		Green_Pieces.add(new SpaceCraft(new Position(2,6),false,this));
		Green_Pieces.add(new SpaceCraft(new Position(2,9),false,this));
	}
	
	public Board() {
		initPieces();
	
		this.setBackground(new Color(37,13,84));
	    this.setPreferredSize(new Dimension(panLen, panLen));
	    this.setMinimumSize(new Dimension(panLen, panLen));
	    this.setMaximumSize(new Dimension(1000, 1000));

	    this.addMouseListener(mouseAdapter);
	    //this.addComponentListener(componentAdapter);
	    //this.addKeyListener(keyAdapter);
	        
	     this.setVisible(true);
	     this.requestFocus();
	}
	
	protected void paintComponent(Graphics g) {
		drawBoard(g); //draw board
		for(SpaceVehicle v: Purple_Pieces) { //draw purple pieces
			v.drawVehicle(v.pos,g);
		}
		for(SpaceVehicle v: Green_Pieces) { //draw green pieces
			v.drawVehicle(v.pos,g);
		}
		if(Active_Piece != null) { //highlight selected piece
			Active_Piece.highlight(Active_Piece.pos, g);
		}
		
		if((purplePoints>=winPoints) || (greenPoints>=winPoints)) //check win condition
			winning=true;
		if(winning) //display win screen
			won(g);
	}
	
	public void won(Graphics g) { //displays win screen
		if(purplePoints>=winPoints)
			g.setColor(new Color(255, 0, 255, 80));
		else if(greenPoints>=winPoints)
			g.setColor(new Color(0, 255, 0, 80));
		g.fillRect(0, 0, panLen, panLen);
		g.setColor(Color.white);
		g.drawString("Winner", cenX-60, cenY-10);
	}
	
	public void drawBoard(Graphics g) { //draws board
		//draws background
		g.setColor(Color.black);
		g.fillOval(0,0, 2*boardRad, 2*boardRad);
		//draws rings
		g.setColor(Color.white);
		g.drawOval(cenX-(innerRad), cenY-(innerRad), 2*innerRad, 2*innerRad);
		g.drawOval(cenX-(innerRad+ringRad), cenY-(innerRad+ringRad), 2*(innerRad+ringRad), 2*(innerRad+ringRad));
		g.drawOval(cenX-(innerRad+2*ringRad), cenY-(innerRad+2*ringRad), 2*(innerRad+2*ringRad), 2*(innerRad+2*ringRad));
		g.drawOval(cenX-(innerRad+3*ringRad), cenY-(innerRad+3*ringRad), 2*(innerRad+3*ringRad), 2*(innerRad+3*ringRad));
		//draws radial lines
		int x1; int y1; int x2; int y2;
		for(int rot=0;rot<16;rot++) {
			x1=(int) (cenX+(innerRad)*Math.cos(rot*Math.PI/8));
			y1=(int) (cenY-(innerRad)*Math.sin(rot*Math.PI/8));
			x2=(int) (cenX+boardRad*Math.cos(rot*Math.PI/8));
			y2=(int) (cenY-boardRad*Math.sin(rot*Math.PI/8));
			g.drawLine(x1, y1, x2, y2);
		}
		//displays points
		Font myFont = new Font ("Courier New", 1, 32);
		g.setFont (myFont);
		g.setColor(Color.black);
		g.fillRect(20, 20, 100, 50);
		g.fillRect(panLen-120, 20, 100, 50);
		g.setColor(Color.magenta);
		g.drawString(String.valueOf(purplePoints)+"/"+winPoints, panLen-107, 55);
		g.setColor(Color.green);
		g.drawString(String.valueOf(greenPoints)+"/"+winPoints, 35, 55);
	}
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {       
        }
        @Override
        public void mousePressed(MouseEvent e) {
        	//save position of mouse click
        	click= calcPos(e.getX(),e.getY());
        	
            if(Active_Piece == null) { //piece has not been selected
            	if(purpTurn) { //purples turn
            		for(SpaceVehicle v: Purple_Pieces) {
            			if(Position.equals(v.pos, click)) { 
            				Active_Piece=v; //set selected purple piece to active
            			}
            		}
            	}
            	else if(!purpTurn) {//greens turn
            		for(SpaceVehicle v: Green_Pieces) {
            			if(Position.equals(v.pos, click))
            				Active_Piece=v; //set selected green piece to active
            		}
            	}
            }
            else if(Position.equals(click, Active_Piece.pos)) { // deselect piece
            	Active_Piece=null;
            }
            else if((Active_Piece != null) && Active_Piece.canMove(click.getRad(), click.getRot())) { //piece has been selected
            	if(purpTurn ) { //purples turn
            		for(SpaceVehicle v: Green_Pieces) { //check if capturing a piece
            			if(Position.equals(v.pos, click)) {
            				purplePoints+=v.points; //add points captured piece
            				Green_Pieces.remove(v); //remove captured piece
            				break;
            			}
            		}
            		purpTurn=false; //alternate turns
            	}
            	
            	else if(!purpTurn) { //greens turn
            		for(SpaceVehicle v: Purple_Pieces) { //check if capturing piece
            			if(Position.equals(v.pos, click)) {
            				greenPoints+=v.points; //add points of captured piece
            				Purple_Pieces.remove(v); //remove captured piece
            				break;
            			}
            		}
            		purpTurn=true;
            	}
            	
            	Active_Piece.move(click.getRad(), click.getRot());//move piece
            	Active_Piece=null;
            }  
            repaint(); //refresh board with current pieces
        }
        @Override
        public void mouseDragged(MouseEvent e) {		
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) 
        {
        }	 
    };
    
    private Position calcPos(int x, int y) { //calculate position of the click
    	double sqrSum=Math.pow(x-cenX, 2)+Math.pow(y-cenY, 2);
    	double hypot=Math.pow(sqrSum, .5);
    	int rad=(int) ((hypot-innerRad)/ringRad)+1;
    	
    	int rot=(int) ((8/Math.PI)*Math.asin((y-cenY)/hypot)); //quadrant I: arcsin wont give val over abs pi/2
    	if(((x-cenX)<0) && ((y-cenY)>0)) //adjustment if in quadrant II (y axis inverted)
    		rot=7-rot;
    	else if(((x-cenX)<0) && ((y-cenY)<0))//quadrant III
    		rot=8-rot;
    	else if(((x-cenX)>0) && ((y-cenY)<0))//quadrant IV
    		rot=15+rot;
   
    	return new Position(rad,rot);
    }
    
    public void printArr() {//for testing/debugging to see index array
    	for(int[] r: pieceIndex) {
    		for(int c: r)
    			System.out.print(" "+c);
    		System.out.println();
    	}
    	System.out.println();
    }
}
