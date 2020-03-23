package Problem2;

public class Rook extends Piece{
	
	public Rook(String color,int xCoordinate, int yCoordinate) {
		super(color, "R" + color.charAt(0), xCoordinate, yCoordinate);
	}
	
	@Override
	public boolean canMoveToDestination(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}

}
