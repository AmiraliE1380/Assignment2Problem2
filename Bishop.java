package Problem2;

public class Bishop extends Piece {
	
	public Bishop(String color,int xCoordinate, int yCoordinate) {
		super(color, "B" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean canMoveToDestination(int xCoordinate, int yCoordinate) {//complete this
		// TODO Auto-generated method stub
		return false;
	}

}
