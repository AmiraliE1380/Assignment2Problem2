package Problem2;

import java.util.ArrayList;

public class Bishop extends Piece {
	
	public Bishop(String color,int xCoordinate, int yCoordinate) {
		super(color, "B" + color.charAt(0), xCoordinate, yCoordinate);
	}

	private boolean isObstacleInUndirectLine(int xOrigin, int yOrigin, int xDestination,
			int yDestination, ArrayList<Piece> allPieces) {
		
		for(int i = xOrigin + 1, j = yOrigin + 1; i < xDestination; i++, j++) {
			if(NewGame.isInCoordinationAPiece(i, j, allPieces)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	private boolean isObstacleInUndirectLineMainDiameterOfMatrix(int xOrigin, int yOrigin, 
			int xDestination, int yDestination, ArrayList<Piece> allPieces) {
		//searches the main diameter in the matrix
		for(int i = xOrigin + 1, j = yOrigin - 1; i < xDestination; i++, j--) {
			if(NewGame.isInCoordinationAPiece(i, j, allPieces)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isObstacleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {//complete this
		if(xCoordinate > this.xCoordinate && yCoordinate > this.yCoordinate) {
			return isObstacleInUndirectLine(this.xCoordinate, this.yCoordinate, xCoordinate, 
					yCoordinate, allPieces);
		}
		
		if(xCoordinate > this.xCoordinate && yCoordinate < this.yCoordinate) {
			return isObstacleInUndirectLineMainDiameterOfMatrix(this.xCoordinate,  this.yCoordinate, 
					xCoordinate, yCoordinate, allPieces);
		}
		
		if(xCoordinate < this.xCoordinate && yCoordinate > this.yCoordinate) {
			return isObstacleInUndirectLineMainDiameterOfMatrix(xCoordinate, yCoordinate, 
					this.xCoordinate, this.yCoordinate, allPieces);
		}
		
		if(xCoordinate < this.xCoordinate && yCoordinate < this.yCoordinate) {
			return isObstacleInUndirectLine(xCoordinate, yCoordinate, this.xCoordinate, 
					this.yCoordinate, allPieces);
		}
		
		return false;
	}

	@Override
	public boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}

}
