package Problem2;

public class Knight extends Piece{
	
	public Knight(String color,int xCoordinate, int yCoordinate) {
		super(color, "N" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean canMoveToDestination(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}


}
