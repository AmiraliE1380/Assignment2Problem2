package Problem2;

public class King extends Piece{
	
	public King(String color,int xCoordinate, int yCoordinate) {
		super(color, "K" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean canMoveToDestination(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}



}
