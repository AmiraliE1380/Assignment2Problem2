package Problem2;

import java.util.ArrayList;

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
	public boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}

}
