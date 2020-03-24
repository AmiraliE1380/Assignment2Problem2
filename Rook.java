package Problem2;

import java.util.ArrayList;

public class Rook extends Piece{
	
	public Rook(String color,int xCoordinate, int yCoordinate) {
		super(color, "R" + color.charAt(0), xCoordinate, yCoordinate);
	}
	
	private boolean isObstacleInDirectPath(int origin, int destination,
			int constCoordinate, ArrayList<Piece> allPieces) {
		for(int i = origin + 1; i < destination; i++) {
			if(constCoordinate == this.xCoordinate) {
				if(NewGame.isInCoordinationAPiece(constCoordinate, i, allPieces)) {
					return true;
				}
			}else {
				if(NewGame.isInCoordinationAPiece(i, constCoordinate, allPieces)) {
					return true;
				}
			}
		}
		
		return false;
	}
//I guess the isObsticle in way for rokh is completed	
	@Override
	public boolean isObstacleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(xCoordinate == this.xCoordinate && yCoordinate > this.yCoordinate) {
			return isObstacleInDirectPath(this.yCoordinate, yCoordinate, xCoordinate, allPieces);
		}
		
		if(xCoordinate == this.xCoordinate && yCoordinate < this.yCoordinate) {
			return isObstacleInDirectPath(this.yCoordinate, yCoordinate, xCoordinate, allPieces);
		}
		
		if(xCoordinate > this.xCoordinate && yCoordinate == this.yCoordinate){
			return isObstacleInDirectPath(this.xCoordinate, xCoordinate, yCoordinate, allPieces);			
		}
		
		if(xCoordinate < this.xCoordinate && yCoordinate == this.yCoordinate){
			return isObstacleInDirectPath(xCoordinate, this.xCoordinate, yCoordinate, allPieces);			
		}
		
		return false;
	}

	@Override
	public boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate) {
		// 
		return false;
	}

}
