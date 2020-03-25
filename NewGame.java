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
	
	public static boolean isInCoordinationAPiece(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		for(Piece piece: allPieces) {
			if(piece.xCoordinate == xCoordinate && piece.yCoordinate == yCoordinate) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Piece getPieceByCoordination(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
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
	
	private void changeColor(Color color) {
		
		if(color.getColor().equals("white")) {
			color.setColor("black");
		}else {
			color.setColor("white");
		}
		
	}
	
	private Piece select(String input, ArrayList<Piece> allPieces, String color) {
		int xCoordinate = Integer.parseInt(input.split(",")[0]);
		int yCoordinate = Integer.parseInt(input.split(",")[1]);
	
		if(!isDestinationCorrect(xCoordinate, yCoordinate)) {
			System.out.println("wrong coordination");
		}else if(isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces) && 
				!getPieceByCoordination(xCoordinate, yCoordinate, allPieces).getColor().equals(color)){
			System.out.println("you can only select one of your pieces");
		}else if(!isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces)) {
			System.out.println("no piece on this spot");
		}else {//this may cause a problem due to the fact that is said in the instructions of the problem
			System.out.println("selected");
			return getPieceByCoordination(xCoordinate, yCoordinate, allPieces);
		}
		
		return null;
	}
	
	public Piece deselect(Piece piece) {
		if(piece == null) {
			System.out.println("no piece is selected");
		}else {
			System.out.println("deselected");
		}
		return null;
	}
	
	public static boolean DoesPieceInDestinationHaveSameColor(int xCoordinate,int  yCoordinate,
			ArrayList<Piece> allPieces, Piece piece) {
		if(isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces)) {
			return getPieceByCoordination(xCoordinate, yCoordinate, allPieces)
					.getColor().equals(piece.getColor());
		}
		return false;
	}
	
	private void moveSuccessfully(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces,
			int[] limit, Piece piece, MovesAndKilledPieces movesAndKilledPieces) {
		String typeOfKilledPiece = null;
		
		if(isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces)) {
			getPieceByCoordination(xCoordinate, yCoordinate, allPieces).setHasbeenKilled(true);
			typeOfKilledPiece = getPieceByCoordination(xCoordinate, yCoordinate, allPieces).getType();
			movesAndKilledPieces.addToKilledPieces(piece.getType(), xCoordinate, yCoordinate);
			System.out.println("rival piece destroyed");
		}else {
			System.out.println("moved");
		}
		
		movesAndKilledPieces.addMove(piece.getType(), piece.getXCoordinate(), piece.getYCoordinate(),
				xCoordinate, yCoordinate, typeOfKilledPiece);
		limit[0]--;
		piece.changeCoordinate(xCoordinate, yCoordinate);
	}
	
	public boolean move(String input, Piece piece, boolean playerHasMoved,
			ArrayList<Piece> allPieces, int[] limit, MovesAndKilledPieces movesAndKilledPieces) {
		
		int xCoordinate = Integer.parseInt(input.split(",")[0]);
		int yCoordinate = Integer.parseInt(input.split(",")[1]);
		
		if(playerHasMoved) {
			System.out.println("already moved");
			return true;
		}else if(!isDestinationCorrect(xCoordinate, yCoordinate)) {
			System.out.println("wrong coordination");
		}else if(piece == null) {
			System.out.println("do not have any selected piece");
		}else if(!piece.canPieceMakeSuchMove(xCoordinate, yCoordinate, allPieces)) {
			System.out.println("cannot move to the spot");
		}else if(DoesPieceInDestinationHaveSameColor(xCoordinate, yCoordinate,allPieces, piece)) {
			System.out.println("cannot move to the spot");
		}else if(piece.isObstacleInWay(xCoordinate, yCoordinate, allPieces)) {//write the method....
			System.out.println("cannot move to the spot");
		}else {
			moveSuccessfully(xCoordinate, yCoordinate, allPieces, limit, piece, movesAndKilledPieces);
			return true;
		}
		
		return false;
	}
	
	private void help() {
		System.out.println("select [x],[y]\r\n" + 
				"deselect\r\n" + 
				"move [x],[y]\r\n" + 
				"next_turn\r\n" + 
				"show_turn\r\n" + 
				"undo\r\n" + 
				"undo_number\r\n" + 
				"show_moves [-all]\r\n" + 
				"show_killed [-all]\r\n" + 
				"show_board\r\n" + 
				"help\r\n" + 
				"forfeit");
	}
	
	private boolean goToNextTurn(Color color, boolean playerHasMoved) {
		if(playerHasMoved) {
			changeColor(color);
			System.out.println("turn completed");
			return false;
		}
		
		System.out.println("you must move then proceed to next turn");
		return false;
	}
	
	private void showBoard(ArrayList<Piece> allPieces) {
		String[][] board = new String[8][8];
		
		for(Piece piece: allPieces) {
			if(!piece.getHasBeenKilled()) {
				board[8 - piece.getXCoordinate()][piece.getYCoordinate() - 1] = piece.getType();
			}
		}
		
		for(String[] pieceTypeRow: board) {
			for(String pieceType: pieceTypeRow) {
				if(pieceType == null) {
					System.out.print("  ");
				}else {
					System.out.print(pieceType);
				}
				System.out.print("|");
			}
			System.out.println("");
		}
	}
	
	private boolean gameIsOver(Player firstPlayer, Player secondPlayer, ArrayList<Piece> allPieces) {
		for(Piece piece: allPieces) {
			if(piece.getType().charAt(0) == 'K') {
				if(piece.getHasBeenKilled()) {
					String winnerPlayer;
					String winnerColor = piece.getColor();
					
					if(winnerColor.equals("white")) {
						winnerPlayer = firstPlayer.getUsername();
						firstPlayer.addNumOfWins();
						secondPlayer.addNumOfLooses();
					}else {
						winnerPlayer = secondPlayer.getUsername();
						firstPlayer.addNumOfLooses();
						secondPlayer.addNumOfWins();
					}
					
					
					System.out.println("player " + winnerPlayer + " with color " + winnerColor + " won");
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	private boolean isDraw(Player firstPlayer, Player secondPlayer, int[] limitInArray) {
		if(limitInArray[0] == 0) {
			firstPlayer.addNumOfDraws();
			secondPlayer.addNumOfDraws();
			System.out.println("draw");
			return true;
		}
		
		return false;
	}
	
	private int isGameLimitless(int limit) {
		if(limit == 0) {
			return -1;
		}else {
			return limit;
		}
	}
	
	private void forfeit(Player firstPlayer, Player secondPlayer, Color color) {
		String winnersName;
		
		if(color.getColor().equals("white")) {
			winnersName = firstPlayer.getUsername();
		}else {
			winnersName = secondPlayer.getUsername();
		}
		
		System.out.println("player " + winnersName + " with color " + color.getColor() + " won");
		System.out.println("you have forfeited");
	}
	
	public void run(Player firstPlayer, Player secondPlayer, int limit) {

		Scanner scanner = new Scanner(System.in);
		ArrayList<Piece> allPieces = new ArrayList<Piece>();
		constructPieces(allPieces);
		String input = new String();
		Color color = new Color("white");
		Piece piece = null;
		boolean playerHasMoved = false;
		int[] limitInArray = {limit = isGameLimitless(limit)};
		MovesAndKilledPieces movesAndKilledPieces = new MovesAndKilledPieces();
		
		while(true){//change the SHART!!!!!!!!!
			input = scanner.nextLine().trim();
			//write the undo thing
			
			if(getMatcher(input, "(select \\d+,\\d+)").matches()) {
				piece = select(input.split("\\s")[1], allPieces, color.getColor());
			}else if(input.equals("deselect")) {
				piece = deselect(piece);
			}else if(getMatcher(input, "(move \\d+,\\d+)").matches()) {
				playerHasMoved = move(input.split("\\s")[1], piece, playerHasMoved,
						allPieces, limitInArray, movesAndKilledPieces);
			}else if(input.equals("next_turn")) {
				playerHasMoved = goToNextTurn(color, playerHasMoved);
			}else if(input.equals("show_board")) {
				showBoard(allPieces);
			}else if(input.equals("show_killed")) {
				movesAndKilledPieces.showKilled(color);
			}else if(input.equals("show_killed")) {
				movesAndKilledPieces.showAllKilledPiecs();
			}else if(input.equals("show_moves")){
				movesAndKilledPieces.showMoves(color);
			}else if(input.equals("show_moves -all")){
				movesAndKilledPieces.showAllMoves();
			}else if(!playerHasMoved && gameIsOver(firstPlayer, secondPlayer, allPieces)) {
				break;
			}else if(isDraw(firstPlayer, secondPlayer, limitInArray)) {
				break;
			}else if(input.equals("forfeit")) {
				forfeit(firstPlayer, secondPlayer, color);
				break;
			}else if(input.equals("help")) {
				help();
			}else {
				System.out.println("invalid command");
			}
				
		}
	}
	
	private class Color{
		private String color;
		
		public Color(String color) {
			this.color = color;
		}
		
		public String getColor() {
			return color;
		}
		
		public void setColor(String color) {
			this.color = color;
		}
	}
	
	private class MovesAndKilledPieces{
		private ArrayList<String> killedPieces;
		private ArrayList<String> moves;
		
		public MovesAndKilledPieces(){
			killedPieces = new ArrayList<String>();
			moves = new ArrayList<String>();
		}
		
		public ArrayList<String> getKilledPieces(){
			return killedPieces;
		}
		
		public void addToKilledPieces(String killedPieceType, int xCoordinate, int yCoordinate) {
			killedPieces.add(killedPieceType + " killed in spot " + xCoordinate + "," + yCoordinate);
		}
		
		public ArrayList<String> getMoves(){
			return moves;
		} 
		
		public void addMove(String pieceType, int xOrigin, int yOrigin,
				int xDestination, int yDestination, String typeOfKilledPiece) {
			String moveReport = pieceType + " " + xOrigin + "," + yOrigin + " to " + xDestination
					+ "," + yDestination;
			if(typeOfKilledPiece != null) {
				moveReport = moveReport + " destroyed typeOfKilledPiece";
			}
			moves.add(moveReport);
		}
	
		public void showKilled(Color color) {
			for(String string: killedPieces) {
				if(string.charAt(1) == color.getColor().charAt(0)) {
					System.out.println(string);
				}
			}
		}
		
		public void showAllKilledPiecs() {
			for(String string: killedPieces) {
				System.out.println(string);
			}
		}
		
		public void showMoves(Color color) {
			for(String string: moves) {
				if(string.charAt(1) == color.getColor().charAt(0)) {
					System.out.println(string);
				}
			}
		}
		
		public void showAllMoves() {
			for(String string: moves) {
				System.out.println(string);
			}
		}
	}

}
