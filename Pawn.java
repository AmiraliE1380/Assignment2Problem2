package Problem2;

import java.util.ArrayList;

public class Pawn extends Piece{
	
	public Pawn(String color,int xCoordinate, int yCoordinate) {
		super(color, "P" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean isObsticleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(NewGame.isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces)) {
			if(yCoordinate == this.yCoordinate) {
				return true;
			}
		}
		//the case that the pawn wants to hop two times at the begining of the game
		return false;
	}

}
