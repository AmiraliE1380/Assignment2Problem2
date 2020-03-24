package Problem2;

import java.util.ArrayList;

public class Bishop extends Piece {
	
	public Bishop(String color,int xCoordinate, int yCoordinate) {
		super(color, "B" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean isObstacleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {//complete this
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}

}
