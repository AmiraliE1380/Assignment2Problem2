package Problem2;

public class Queen extends Piece{
	
	public Queen(String color,int xCoordinate, int yCoordinate) {
		super(color, "P" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean canMoveToDestination(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}

}
