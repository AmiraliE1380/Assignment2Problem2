package Problem2;

import java.util.ArrayList;

public class Rook extends Piece{
	
	public Rook(String color,int xCoordinate, int yCoordinate) {
		super(color, "R" + color.charAt(0), xCoordinate, yCoordinate);
	}
	
	@Override
	public boolean  isObsticleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		// TODO Auto-generated method stub
		return false;
	}

}
