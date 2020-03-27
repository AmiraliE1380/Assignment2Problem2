package Problem2;

import java.util.ArrayList;

public class King extends Piece{
	
	public King(String color,int xCoordinate, int yCoordinate) {
		super(color, "K" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean isObstacleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		return false;
		//there is no piece with the same color in the destination so the king has no obstacle and if there is a piece he can kill it
	}

	@Override
	public boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(yCoordinate == this.yCoordinate + 1 || yCoordinate == this.yCoordinate - 1) {
			if(xCoordinate == this.xCoordinate) {
				return true;
			}
			
			if(xCoordinate == this.xCoordinate + 1 || xCoordinate == this.xCoordinate - 1) {
				return true;
			}
		}
		
		if(yCoordinate == this.yCoordinate) {
			if(xCoordinate == this.xCoordinate + 1 || xCoordinate == this.xCoordinate - 1) {
				return true;
			}
		}
		
		return false;
		
	}



}
