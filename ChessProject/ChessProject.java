// original
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/

/*
	Commit Comments:
		--------------------
			Initiating #solution_2 - refering to black pawn movement
		---------------------
*/
 
public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;

	int prevPositionY ; //Penuel

	//==== Penuel Code=====
	int printDisplayCount = 0 ;

	
	int blackTurn = 0;
	int whiteTurn = 1;
	int nextTurn = whiteTurn;
	boolean enableTurn = true;
	
 
    public ChessProject(){
		Dimension boardSize = new Dimension(600, 600);
	
 
        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }
 
        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){			
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){			
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);		
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/

	// #Penuel - Methods :  This will print out the methods that has been called
	private void toPrint(String str){
		System.out.println("Activated : " + str);
	}

	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
	}	

	// ==========  Penuel code ========================
	// possible #solution_2

	private Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("White")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
	}

	//======================
 
	/*
		This method is called when we press the Mouse. So we need to find out what piece we have 
		selected. We may also not have selected a piece!
	*/

    public void mousePressed(MouseEvent e){
		
		System.out.println("Log : Program starts...");
		
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) 
			return;
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }
   
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }
     
 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before 
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;
 
		
        chessPiece.setVisible(false);
		Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;

		/*====  Outputs the co-ordination of an object  Penuel =======*/
		int landingX = (e.getX()/75);
		int landingY = (e.getY()/75);
		int xMovement = Math.abs((e.getX()/75)-startX);
		int yMovement = Math.abs((e.getY()/75)-startY);
		boolean isForwardMove ;
		String messageTurn = "";
		boolean enableMessageTurn = false;

		//===== isReverse Method====== -- Penuel
		if(e.getY() < initialY){
			isForwardMove = true ;
		}else{
			isForwardMove = false;
		}
		/*===============*/

	

		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.
			
			So a Pawn is able to move two squares forward one its first go but only one square after that. 
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing 
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one 
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position. 
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for 
			demonstration purposes the Pawn here turns into a Queen.
		*/

		if(pieceName.contains("Bishop")){
			Boolean inTheWay = false ;
			int distance = Math.abs(startX - landingX);

			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7 ))){
				validMove = false;
			}else{
				validMove = true ;
				if(Math.abs(startX - landingX) == Math.abs(startY - landingY)){
					if((startX - landingX < 0) && (startY - landingY < 0 )){
						for(int i = 0; i < distance ; i++){
							if(piecePresent((initialX +(i*75)), (initialY +(i*75)))){
								inTheWay = true ;
							}
						}
					}
				}
			}

			if(( startX - landingX < 0)&& (startY - landingY > 0)){
				for (int i = 0; i < distance ; i++){
					if(piecePresent((initialX + (i * 75)), (initialY - (i*75)))){
						inTheWay = true ;
					}
				}
			}
			if((startX - landingX > 0) && (startY - landingY > 0)){
				for(int i=0 ; i < distance ; i++){
					if(piecePresent((initialX - (i*75)), (initialY - (i*75)))){
						inTheWay = true ;
					}
				}
			}

			if(inTheWay){
				validMove = false;
			}
			else{
				if(piecePresent(e.getX(), (e.getY()))){
					if(pieceName.contains("White")){
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true ;
						}else{
							validMove = false;
						}
					}
					else{
						if(checkBlackOponent(e.getX(), e.getY())){
							validMove = true ;
						}else{
							validMove = false ;
						}

					}
				}
				else{
					validMove = true ;
				}
			
			}

		}

		if(pieceName.contains("Knight")){
			if(((landingX < 0 ) || (landingX > 7)) || ((landingY < 0) || landingY > 7)){
				validMove = false;
			}
			else{
				if(((landingX == startX+1) && (landingY == startY+2)) || ((landingX == startX-1) && (landingY == startY+2)) || ((landingX ==
				startX + 2) && (landingY == startY + 1)) || ((landingX == startX-2) && (landingY == startY + 1 )) || ((landingX == startX + 1) &&
				(landingY == startY-2)) || ((landingX == startX-1) && (landingY == startY-2)) || ((landingX == startX + 2) && (landingY ==
				startY-1)) || ((landingX == startX-2) && (landingY == startY-1))){
					if(piecePresent(e.getX(),(e.getY()))){
						if(pieceName.contains("White")){
							if(checkWhiteOponent(e.getX(), e.getY())){
								validMove = true ;
							}
							else{
								validMove = false;
							}
						}
						else{
							if(checkBlackOponent(e.getX(), e.getY())){
								validMove = true ;
							}
							else{
								validMove = false;
							}
						}
					
					}
					else{
						validMove = true;
					}
				}
				else{
					validMove = false;
				}

			}
		}
	
		if(pieceName.equals("BlackPawn")){  // #solution_2
			if(startY == 6 && isForwardMove == true)
			{
				// If  the  starting position of the x column is the same as the finishing column and the new y coordinate has moved either  one  or  two  squares,  we may  have  a  valid  move
				if(xMovement == 0 && (yMovement == 1 || yMovement == 2) )		// Old:	if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2))
				{
					if((((e.getY()/75)-startY)==2)){
						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){
							validMove = true;					
						}
						else{
							validMove = false;
						}							
					}
					else{
						if((!piecePresent(e.getX(), (e.getY()))))
						{
							validMove = true;					
						}	
						else{
							validMove = false;
						}													
					}
				}
				else{
					validMove = false;					
				}
			}
			// If it moves more 2 steps or move
			else{ 
				int newY = e.getY()/75;
				int newX = e.getX()/75;				
				if (xMovement==0 && yMovement == 1 && isForwardMove == true)	// FROM :  if((startX-1 >=0)||(startX +1 <=7))
				{
					if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
					{
						// #!!!
						if(checkBlackOponent(e.getX(), e.getY())){
							
							//Penuel #solution_1
							if(yMovement >=2){
								validMove = false;
							}else{
								validMove = true; //from : 
							}

							if(startY == 6){
								success = true;
							}						
						}
						else{
							validMove = false;
						}
					}
					else{
						if(!piecePresent(e.getX(), (e.getY()))){
							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
								if(startY == 6){
									success = true;
								}
								validMove = true;
							}
							else{
								validMove = true;
							}				
						}
						else{
							validMove = false;	
						}
					}
				}
				else{
					if(isForwardMove == true){
						if( piecePresent(e.getX(), (e.getY())) && checkBlackOponent(e.getX(), e.getY())){
							
							//Penuel #solution_1
							if(yMovement >=2){
								validMove = false;
							}else{
								validMove = true; //from : 
							}
							
							if(startY == 1){
								success = true ;
							}
						}

					}
					else{
						validMove = false;
					}
				}
						
			}			
		}
		//=== Method End : BlackPawn

		if(pieceName.equals("WhitePawn")){
			if(startY == 1){
				// If  the  starting position of the x column is the same as the finishing column and the new y coordinate has moved either  one  or  two  squares,  we may  have  a  valid  move
				if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2)){
					if((((e.getY()/75)-startY)==2)){
						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){
							validMove = true;					
						}
						else{
							validMove = false;
						}							
					}
					else{
						if((!piecePresent(e.getX(), (e.getY()))))
						{
							validMove = true;					
						}	
						else{
							validMove = false;
						}													
					}
				}
				else{
					validMove = false;					
				}
			}
			else{
				toPrint("b-meth");

				int newY = e.getY()/75;
				int newX = e.getX()/75;				
				if((startX-1 >=0)||(startX +1 <=7))
				{
					if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
					{
						if(checkWhiteOponent(e.getX(), e.getY())){
							
							//Penuel #solution_1
							if(yMovement >=2){
								validMove = false;
							}else{
								validMove = true; //from : 
							}

							if(startY == 6){
								success = true;
							}						
						}
						else{
							validMove = false;
						}
					}
					else{
						if(!piecePresent(e.getX(), (e.getY()))){
							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
								if(startY == 6){
									success = true;
								}
								validMove = true;
							}
							else{
								validMove = false;
							}				
						}
						else{
							validMove = false;	
						}
					}
				}
				else{
					validMove = false;
				}				
			}			
		}
		//=== Method End : WhitePawn


	//=== Start Rook Method: ======
		if(pieceName.contains("Rook")){
			Boolean intheway = false;

			if((( landingX < 0) || (landingX > 7 )) || ((landingY < 0 ) || (landingY > 7))){
					validMove = false;
			}
			else{
				if(((Math.abs(startX - landingX) !=0) && (Math.abs(startY - landingY) == 0 )) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY)!= 0))){				
					if(Math.abs(startX - landingY) != 0){
						xMovement = Math.abs(startX - landingX);
						if(startX - landingX > 0){
							for(int i=0; i < xMovement; i++){
								if(piecePresent(initialX - (i*75), e.getY())){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}
						else{
							for( int i=0 ; i < xMovement ; i++){
								if (piecePresent(initialX + (i*75), e.getY())){
									intheway = true ;
									break ;
								}
								else{
									intheway = false;
								}
							}
						}
					}  //============
					//else{
						yMovement = Math.abs(startY - landingY);
						if(startY - landingY > 0){
							for(int i=0 ; i < yMovement ; i++){
								if(piecePresent(e.getX(), initialY + (i*75))){
									
									if(i == 1){
										intheway = false;
										break ;
									}else{
										intheway = true ;
										break ;
									}
								}
								else{
									intheway = false;
								}
							}
						}
						else{
							for(int i=0 ; i < yMovement ; i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}

					//}

					if(intheway){
						validMove = false ;
					}
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(), e.getY())){
									validMove = true ;
								}
								else{
									validMove = false;
								}
							}
							else{
								if(checkBlackOponent( e.getX() , e.getY())){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
						}
						else{
							validMove = true ;
						}
					}	
				}
				else{
					validMove = false;
				}
			}
		}

	//==== END: Rook Method =========
		
	//==== START Queen ==========
		if(pieceName.contains("Queen")){
			Boolean intheway = false;

			if((( landingX < 0) || (landingX > 7 )) || ((landingY < 0 ) || (landingY > 7))){
					validMove = false;
			}
			else{
				if(((Math.abs(startX - landingX) !=0) && (Math.abs(startY - landingY) == 0 )) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY)!= 0))){				
					if(Math.abs(startX - landingY) != 0){
						xMovement = Math.abs(startX - landingX);

						// This is where it skips...
						if(startX - landingX > 0){
							for(int i=0; i < xMovement; i++){
								if(piecePresent(initialX - (i*75), e.getY())){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}
						else{
							for( int i=0 ; i < xMovement ; i++){
								if (piecePresent(initialX + (i*75), e.getY())){
									intheway = true ;
									break ;
								}
								else{
									intheway = false;
								}
							}
						}
					}  //============
					//else{
						yMovement = Math.abs(startY - landingY);
						if(startY - landingY > 0){
							for(int i=0 ; i < yMovement ; i++){
								if(piecePresent(e.getX(), initialY + (i*75))){
									intheway = true ;
									break ;
								}
								else{
									intheway = false;
								}
							}
						}
						else{
							for(int i=0 ; i < yMovement ; i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}

					//}

					if(intheway){
						validMove = false ;
					}
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(), e.getY())){
									validMove = true ;
								}
								else{
									validMove = false;
								}
							}
							else{
								if(checkBlackOponent( e.getX() , e.getY())){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
						}
						else{
							validMove = true ;
						}
					}	
				}
				else{
					validMove = true;
				}
			}
		}
	//===== END: Queen ============


	//==== START: King Method =======
		if(pieceName.contains("King")){

			System.out.println("king activated");
			if(
				(xMovement == 0 && yMovement ==1) ||
				(xMovement == 1 && yMovement ==0) 
			)
			{

				if(piecePresent(e.getX(), (e.getY()))){
					if(pieceName.contains("White")){
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true ;
						}
						else{
							validMove = false;
						}
					}
					else{
						if(checkBlackOponent( e.getX() , e.getY())){
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
				}
				
				if(piecePresent(e.getX(), (e.getY()))){
					if(pieceName.contains("Black")){
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = false ;
						}
						else{
							validMove = true;
						}
					}
					else{
						if(checkBlackOponent( e.getX() , e.getY())){
							validMove = false ;
						}
						else{
							validMove = true ;
						}
					}
				}
				
				else{
					validMove = true;
				}
			
				
			}
		}

	//=== END: King Meothod ============

	//===START:  Turn Management funtion ===========
		if(enableTurn == true){
			if(pieceName.contains("White") && validMove == true ){
				if(nextTurn == whiteTurn){
					validMove = true ;
					nextTurn = blackTurn ;
				}else{
					validMove = false;
					enableMessageTurn = true;
				}
			}
			if(pieceName.contains("Black") && validMove == true ){
				if(nextTurn == blackTurn){
					validMove = true;
					nextTurn = whiteTurn ;
				}else{
					validMove = false;
					enableMessageTurn = true ;
				}
			}
		}

	//====END==================

	//==== Move Validation Function ===========
		if(!validMove){		
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png"; 
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);			
		}
		else{
			if(success){
				int location ;
				String imgPiece ;

				if (pieceName.equals("WhitePawn")){
					location = 56 + (e.getX()/75);
					imgPiece = "WhiteQueen.png" ;
				}
				else{
					location = 0 + (e.getX()/75);
					imgPiece = "BlackQueen.png" ;
				}

				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon(imgPiece) );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);			
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon(imgPiece) );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);	            	
				}
			}
			else{
				toPrint("e-meth");

				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);									
			}
		}

			prevPositionY = e.getY();

		  /*== My Code  Penuel ===*/
			printDisplayCount ++ ;
			System.out.println("\n \n -------" + "Print #"+ printDisplayCount + "-------------------- \n");
			System.out.println("The piece that is being moved is : " + pieceName);
			System.out.println("The starting coordinates are : " + " (" + startX + "," + startY+ ")") ;
			System.out.println("The xMovement is :" + xMovement);
			System.out.println("The yMovement is " + yMovement);
			System.out.println("The landing coordinates are : " + "(" + landingX + "," + landingY + ")");
			varWatch();

			System.out.println("ValidMove : " + validMove);

			if(enableMessageTurn == true){
				JOptionPane.showMessageDialog(null, "Its the opponent turn");
			}
			
			/* ================================*/
		
	}
	
	/*=== Variable Printer =========================*/
	  	public void varWatch(){
			System.out.println("startX : " + startX);
			System.out.println("startY : " + startY);
		}
	/* ================================*/
 
    public void mouseClicked(MouseEvent e) {
	
    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e) {
	
    }
 	
	/*
		Main method that gets the ball moving.
	*/

    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


