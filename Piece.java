package Problem2;

import java.util.ArrayList;

public abstract class Piece {
	protected String color;
	protected String type;
	protected int xCoordinate;
	protected int yCoordinate;
	
	public Piece(String color, String type,int xCoordinate, int yCoordinate) {
		this.color = color;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public int getXCoordinate() {
		return xCoordinate;
	}
	
	public int getYCoordinate() {
		return yCoordinate;
	}
	
	public String getColor() {
		return color;
	}
	
	public void changeCoordinate(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	public String getType(Piece piece) {
		return type;
	}
	
	public abstract boolean isObstacleInWay(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces);
	//write this method for each....
	public abstract boolean canPieceMakeSuchMove(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces);
	//write this method for each...
}
