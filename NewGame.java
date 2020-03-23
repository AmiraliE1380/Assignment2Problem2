package Problem2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewGame {
	
	private Matcher getMatcher(String input, String regex) {//works good
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher;
	}
	
	private void constructPieces(ArrayList<Piece> allPieces) {
		for(int i = 0; i < 8; i++){
			allPieces.add(new Pawn("white", 2, i + 1));
			allPieces.add(new Pawn("black", 7, i + 1));
		}
		
		allPieces.add(new Queen("white", 1, 4));
		allPieces.add(new Queen("black", 8, 4));
		
		allPieces.add(new King("white", 1, 5));
		allPieces.add(new King("black", 8, 5));
		
		allPieces.add(new Rook("white", 1, 1));
		allPieces.add(new Rook("white", 1, 8));
		allPieces.add(new Rook("black", 8, 1));
		allPieces.add(new Rook("black", 8, 8));
		
		allPieces.add(new Bishop("white", 1, 3));
		allPieces.add(new Bishop("white", 1, 6));
		allPieces.add(new Bishop("black", 8, 3));
		allPieces.add(new Bishop("black", 8, 6));
		
		allPieces.add(new Knight("white", 1, 2));
		allPieces.add(new Knight("white", 1, 7));
		allPieces.add(new Knight("black", 8, 2));
		allPieces.add(new Knight("black", 8, 7));
	}
	
	public boolean isInCoordinationAPiece(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		for(Piece piece: allPieces) {
			if(piece.xCoordinate == xCoordinate && piece.yCoordinate == yCoordinate) {
				return true;
			}
		}
		
		return false;
	}
	
	public Piece getPieceByCoordination(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		for(Piece piece: allPieces) {
			if(piece.xCoordinate == xCoordinate && piece.yCoordinate == yCoordinate) {
				return piece;
			}
		}
		
		return null;
	}
	
	public boolean isDestinationCorrect(int xCoordinate, int yCoordinate) {
		if(xCoordinate > 8 || xCoordinate < 1 || yCoordinate > 8 || xCoordinate < 1) {
			return false;
		}
		
		return true;
	}
	
	private void changeColor(String color) {
		
		if(color.equals("white")) {
			color = "black";
		}else {
			color = "white";
		}
		
	}
	
	private Piece select(String input, ArrayList<Piece> allPieces, String color) {
		int xCoordinate = Integer.parseInt(input.split(",")[0]);
		int yCoordinate = Integer.parseInt(input.split(",")[1]);
	
		if(!isDestinationCorrect(xCoordinate, yCoordinate)) {
			System.out.println("wrong coordination");
		}else if(!getPieceByCoordination(xCoordinate, yCoordinate, allPieces).getColor().equals(color)){
			System.out.println("you can only select one of your pieces");
		}else if(!isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces)) {
			System.out.println("no piece on this spot");
		}else {//this may cause a problem due to the fact that is said in the instructions of the problem
			System.out.println("selected");
			return getPieceByCoordination(xCoordinate, yCoordinate, allPieces);
		}
		
		return null;
	}
	
	public void deselect(Piece piece) {
		if(piece == null) {
			System.out.println("no piece is selected");
		}else {
			piece = null;
			System.out.println("deselected");
		}
	}
	
	public boolean move(String input, Piece piece, boolean playerHasMoved, ArrayList<Piece> allPieces) {
		int xCoordinate = Integer.parseInt(input.split(",")[0]);
		int yCoordinate = Integer.parseInt(input.split(",")[1]);
		
		if(playerHasMoved) {
			System.out.println("already moved");
			return true;
		}else if(piece == null) {
			System.out.println("do not have any selected piece");
		}else if(!piece.isObsticleInWay(xCoordinate, yCoordinate, allPieces)) {//write the method....
			System.out.println("cannot move to the spot");
		}else if(getPieceByCoordination(xCoordinate, yCoordinate, allPieces)
				.getColor().equals(piece.getColor())) {
			System.out.println("cannot move to the spot");
		}else {
			if(isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces)) {
				allPieces.remove(getPieceByCoordination(xCoordinate, yCoordinate, allPieces));
				System.out.println("rival piece destroyed");
			}else {
				System.out.println("moved");
			}
			
			piece.changeCoordinate(xCoordinate, yCoordinate);
			return true;
		}
		
		return false;
	
	}
	
	public void run(Player firstPlayer, Player secondplayer, int limit) {

		Scanner scanner = new Scanner(System.in);
		ArrayList<Piece> allPieces = new ArrayList<Piece>();
		constructPieces(allPieces);
		String input = new String();
		String color = "black";
		Piece piece = null;
		boolean playerHasMoved = false;
		
		while(true){//change the SHART!!!!!!!!!
			input = scanner.nextLine().trim();
//			changeColor(color);		
			if(getMatcher(input, "(select \\d+,\\d+)").matches()) {
				piece = select(input.split("\\s")[1], allPieces, color);
			}else if(input.equals("deselect")) {
				deselect(piece);
			}else if(getMatcher(input, "(move \\d+,\\d+)").matches()) {
				playerHasMoved = move(input.split("\\s")[1], piece, playerHasMoved, allPieces);
			}else if(input.equals("forfeit")) {
				System.out.println();
				break;
				//complete this
			}else {
				System.out.println("invalid command");
			}
		}
		
		scanner.close();
		
	}

}
