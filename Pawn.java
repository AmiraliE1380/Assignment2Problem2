package Problem2;

import java.util.ArrayList;
//I'm not sure if I should use the trim method for input..........
public class Pawn extends Piece{
	
	public Pawn(String color,int xCoordinate, int yCoordinate) {
		super(color, "P" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean isObstacleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(NewGame.isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces)) {
			if(yCoordinate == this.yCoordinate) {
				return true;
			}
		}
		
		if(this.color.equals("white")) {//for the situation it wants to jump two steps
			if(xCoordinate == this.xCoordinate + 2 && 
					NewGame.isInCoordinationAPiece(xCoordinate - 1, yCoordinate, allPieces)) {
				return true;
			}
		}
		
		if(this.color.equals("black")) {
			if(xCoordinate == this.xCoordinate - 2 && 
					NewGame.isInCoordinationAPiece(xCoordinate + 1, yCoordinate, allPieces)) {
				return true;
			}			
		}

		return false;
	}

	@Override
	public boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(yCoordinate == this.yCoordinate) {
			if(color.equals("white")) {
				if(this.xCoordinate == 2 && xCoordinate == 4) {
					return true;
				}
				
				if(this.xCoordinate + 1 == xCoordinate) {
					return true;
				}
			}
			
			if(color.equals("black")) {
				if(this.xCoordinate == 7 && xCoordinate == 5) {
					return true;
				}
				
				if(this.xCoordinate - 1 == xCoordinate) {
					return true;
				}
			}
		}
		
		if(yCoordinate == this.yCoordinate + 1 || yCoordinate == this.yCoordinate - 1 ) {
			if(color.equals("white")) {
				if(xCoordinate == this.xCoordinate + 1) {
					if(NewGame.isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces) &&
						NewGame.getAlivePieceByCoordination(xCoordinate, yCoordinate, allPieces) != null) {
						if(!NewGame.DoesPieceInDestinationHaveSameColor(xCoordinate,
								yCoordinate,allPieces, this)) {
							return true;
						}
					}
				}
			}
			
			if(color.equals("black")) {
				if(xCoordinate == this.xCoordinate - 1) {
					if(NewGame.isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces) && 
						NewGame.getAlivePieceByCoordination(xCoordinate, yCoordinate, allPieces) != null) {
						if(!NewGame.DoesPieceInDestinationHaveSameColor(xCoordinate,
								yCoordinate,allPieces, this)) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
		
	}

}
