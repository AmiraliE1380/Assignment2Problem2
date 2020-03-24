package Problem2;

import java.util.ArrayList;

public class Queen extends Piece{
	
	public Queen(String color,int xCoordinate, int yCoordinate) {
		super(color, "P" + color.charAt(0), xCoordinate, yCoordinate);
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
	
	private boolean isObstacleInWayBishopMove(int xCoordinate, int yCoordinate,
			ArrayList<Piece> allPieces) {//complete this
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
	
	private boolean isObstacleInWayRookMove(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
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
	public boolean isObstacleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(xCoordinate != this.xCoordinate && yCoordinate != this.yCoordinate) {
			return isObstacleInWayBishopMove(xCoordinate, yCoordinate, allPieces);
		}
			
		return isObstacleInWayRookMove(xCoordinate, yCoordinate, allPieces);
	}

	private boolean canPieceMakeRookMove(int xCoordinate, int yCoordinate) {
		if(xCoordinate == this.xCoordinate) {
			return true;
		}
		
		if(yCoordinate == this.yCoordinate) {
			return true;
		}
		
		return false;
	}
	
	private boolean canPieceMakeBishopMove(int xCoordinate, int yCoordinate) {
		if(xCoordinate - yCoordinate == this.xCoordinate - this.yCoordinate) {
			return true;
		}
		
		if(xCoordinate + yCoordinate == this.xCoordinate + this.yCoordinate) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(canPieceMakeRookMove(xCoordinate, yCoordinate)) {
			return true;
		}
		
		if(canPieceMakeBishopMove(xCoordinate, yCoordinate)) {
			return true;
		}
		
		
		return false;
	}
}
