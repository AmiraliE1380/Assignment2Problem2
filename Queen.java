package Problem2;

import java.util.ArrayList;

public class Queen extends Piece{
	
	public Queen(String color,int xCoordinate, int yCoordinate) {
		super(color, "P" + color.charAt(0), xCoordinate, yCoordinate);
	}

	@Override
	public boolean  isObsticleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		if(xCoordinate == this.xCoordinate) {
			if(xCoordinate > this.xCoordinate) {
				for(int i = this.xCoordinate + 1; i <= xCoordinate; i++) {
					return true;
					//if()
				}
			}
		}
		
		//complete this shit!!!!!!!!!!!!!
		return false;

	}

}
