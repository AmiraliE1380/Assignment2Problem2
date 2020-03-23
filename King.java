package Problem2;

import java.util.ArrayList;

public class King extends Piece{
	
	public King(String color,int xCoordinate, int yCoordinate) {
		super(color, "K" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean isObsticleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(NewGame.isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces)) {
			Piece piece = NewGame.getPieceByCoordination(xCoordinate, yCoordinate, allPieces);
			if(piece.getColor().equals(color)) {
				return true;
			}
		}
		
		return false;
		
	}



}
