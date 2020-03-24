package Problem2;

import java.util.ArrayList;

public class Knight extends Piece{
	
	public Knight(String color,int xCoordinate, int yCoordinate) {
		super(color, "N" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean  isObstacleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		return false;
	}

	@Override
	public boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(xCoordinate == this.xCoordinate + 2 || xCoordinate == this.xCoordinate - 2) {
			if(yCoordinate == this.yCoordinate + 1 || yCoordinate == this.yCoordinate - 1) {
				return true;
			}
		}
		
		if(yCoordinate == this.yCoordinate + 2 || yCoordinate == this.yCoordinate - 2) {
			if(xCoordinate == this.xCoordinate + 1 || xCoordinate == this.xCoordinate - 1) {
				return true;
			}
		}
		
		return false;
	}


}
