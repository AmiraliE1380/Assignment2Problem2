package Problem2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewGame {
	
	Piece piece = null;
	
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
	
	public static boolean isInCoordinationAnAlivePiece(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		for(Piece piece: allPieces) {
			if(piece.xCoordinate == xCoordinate && piece.yCoordinate == yCoordinate) {
				if(!piece.hasBeenKilled) {
					return true;
				}
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
	
	public static Piece getAlivePieceByCoordination(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces) {
		for(Piece piece: allPieces) {
			if(piece.xCoordinate == xCoordinate && piece.yCoordinate == yCoordinate) {
				if(!piece.getHasBeenKilled()) {
					return piece;
				}
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
		}else if(isInCoordinationAnAlivePiece(xCoordinate, yCoordinate, allPieces)) {
			if(!getAlivePieceByCoordination(xCoordinate, yCoordinate, allPieces).hasBeenKilled) {
				if(!getAlivePieceByCoordination(xCoordinate, yCoordinate, allPieces).
						getColor().equals(color)){
					System.out.println("you can only select one of your pieces");
				}else {//this may cause a problem due to the fact that is said in the instructions of the problem
					System.out.println("selected");
					return getAlivePieceByCoordination(xCoordinate, yCoordinate, allPieces);
				}
			}else {
				System.out.println("selected");
				return getAlivePieceByCoordination(xCoordinate, yCoordinate, allPieces);
			}
		}else {
			System.out.println("no piece on this spot");
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
	
	public static boolean doesPieceInDestinationHaveSameColor(int xCoordinate,int  yCoordinate,
			ArrayList<Piece> allPieces, Piece piece) {
		if(isInCoordinationAnAlivePiece(xCoordinate, yCoordinate, allPieces)) {
			return getAlivePieceByCoordination(xCoordinate, yCoordinate, allPieces)
					.getColor().equals(piece.getColor());
		}
		return false;
	}
	
	private void moveSuccessfully(int xCoordinate, int yCoordinate, ArrayList<Piece> allPieces,
			Piece piece, MovesAndKilledPieces movesAndKilledPieces) {
		String typeOfKilledPiece = null;
		
		if(isInCoordinationAPiece(xCoordinate, yCoordinate, allPieces) &&//look at this, this may make a problem
				!getPieceByCoordination(xCoordinate, yCoordinate, allPieces).getHasBeenKilled()) {
			getPieceByCoordination(xCoordinate, yCoordinate, allPieces).setHasbeenKilled(true);
			typeOfKilledPiece = getPieceByCoordination(xCoordinate, yCoordinate, allPieces).getType();
			movesAndKilledPieces.addToKilledPieces(typeOfKilledPiece, xCoordinate, yCoordinate);
			System.out.println("rival piece destroyed");
		}else {
			System.out.println("moved");
		}
		
		movesAndKilledPieces.addMove(piece.getType(), piece.getXCoordinate(), piece.getYCoordinate(),
				xCoordinate, yCoordinate, typeOfKilledPiece);
		piece.changeCoordinate(xCoordinate, yCoordinate);
	}
	
	public boolean move(String input, Piece piece, boolean playerHasMoved, ArrayList<Piece> allPieces,
			MovesAndKilledPieces movesAndKilledPieces) {
		
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
		}else if(doesPieceInDestinationHaveSameColor(xCoordinate, yCoordinate,allPieces, piece)) {
			System.out.println("cannot move to the spot");
		}else if(piece.isObstacleInWay(xCoordinate, yCoordinate, allPieces)) {//write the method....
			System.out.println("cannot move to the spot");
		}else {
			moveSuccessfully(xCoordinate, yCoordinate, allPieces, piece, movesAndKilledPieces);
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
	
	private boolean goToNextTurn(Color color, boolean playerHasMoved, Undo undoObject, int[] limit) {
		if(playerHasMoved) {
			changeColor(color);
			undoObject.setFirstPlayersUndoChioseThisTurn(1);
			undoObject.setSecondPlayersUndoChioseThisTurn(1);
			System.out.println("turn completed");
			limit[0]--;
			piece = null;
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
					String loosersColor = piece.getColor();
					String winnerColor;
					
					if(loosersColor.equals("white")) {
						winnerPlayer = secondPlayer.getUsername();
						secondPlayer.addNumOfWins();
						firstPlayer.addNumOfLooses();
						winnerColor = "black";
					}else {
						winnerPlayer = firstPlayer.getUsername();
						firstPlayer.addNumOfWins();
						secondPlayer.addNumOfLooses();
						winnerColor = "white";
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
	
	private boolean forfeit(Player firstPlayer, Player secondPlayer, Color color) {
		String winnersName;
		String winnersColor;
		
		if(color.getColor().equals("white")) {
			firstPlayer.addNumberOfOwnForfeits();
			secondPlayer.addNumberOfRivalsForfeits();
			winnersName = secondPlayer.getUsername();
			winnersColor = "black";
		}else {
			firstPlayer.addNumberOfRivalsForfeits();
			secondPlayer.addNumberOfOwnForfeits();
			winnersName = firstPlayer.getUsername();
			winnersColor = "white";
		}
		
		System.out.println("you have forfeited");
		System.out.println("player " + winnersName + " with color " + winnersColor + " won");
		
		return true;
	}
	
	private void showTurn(Player firstPlayer, Player secondPlayer, Color color) {
		if(color.getColor().equals("white")) {
			System.out.println("it is player " + firstPlayer.getUsername()
			+ " turn with color " + color.getColor());
		}else {
			System.out.println("it is player " + secondPlayer.getUsername()
			+ " turn with color " + color.getColor());
		}
	}
	
	private void checkLimitInCaseOfDraw(Player firstPlayer, Player secondPlayer, int[] limit) {
		if(limit[0] == 0) {
			firstPlayer.addNumOfDraws();
			secondPlayer.addNumOfDraws();
			System.out.println("draw");
		}
	}
	
	public void run(Player firstPlayer, Player secondPlayer, int limit, Scanner scanner) {

	//	Scanner scanner = new Scanner(System.in);
		ArrayList<Piece> allPieces = new ArrayList<Piece>();
		constructPieces(allPieces);
		String input = new String();
		Color color = new Color("white");
		Undo undoObject = new Undo();
		boolean playerHasMoved = false;
		boolean playerHasForfeited = false;
		int[] limitInArray = {limit = isGameLimitless(limit)};
		MovesAndKilledPieces movesAndKilledPieces = new MovesAndKilledPieces();
		
		while(limitInArray[0] != 0 && !playerHasForfeited){//change the SHART!!!!!!!!!
			input = scanner.nextLine();
			
			if(getMatcher(input, "(select -{0,1}\\d+,-{0,1}\\d+)").matches()) {
				piece = select(input.split("\\s")[1], allPieces, color.getColor());
			}else if(input.equals("deselect")) {
				piece = deselect(piece);
			}else if(getMatcher(input, "(move -{0,1}\\d+,-{0,1}\\d+)").matches()) {
				playerHasMoved = move(input.split("\\s")[1], piece, playerHasMoved, allPieces, movesAndKilledPieces);
			}else if(input.equals("next_turn")) {
				playerHasMoved = goToNextTurn(color, playerHasMoved, undoObject, limitInArray);
				if(!playerHasMoved && gameIsOver(firstPlayer, secondPlayer, allPieces)) {
					break;
				 }
			}else if(input.equals("show_board")) {
				showBoard(allPieces);
			}else if(input.equals("show_killed")) {
				movesAndKilledPieces.showKilled(color);
			}else if(input.equals("show_killed -all")) {
				movesAndKilledPieces.showAllKilledPiecs();
			}else if(input.equals("show_moves")){
				movesAndKilledPieces.showMoves(color);
			}else if(input.equals("show_moves -all")){
				movesAndKilledPieces.showAllMoves();
			}else if(input.equals("show_turn")) {
				showTurn(firstPlayer, secondPlayer, color);
			}else if(input.equals("undo")) {
				playerHasMoved = undoObject.undo(color.getColor(), movesAndKilledPieces, playerHasMoved, allPieces);
			}else if(input.equals("undo_number")) {
				undoObject.showUndoNumber(color);
			}else if(isDraw(firstPlayer, secondPlayer, limitInArray)) {
				break;
			}else if(input.equals("forfeit")) {
				playerHasForfeited = forfeit(firstPlayer, secondPlayer, color);
			}else if(input.equals("help")) {
				help();
			}else {
				System.out.println("invalid command");
			}
		}
		
		checkLimitInCaseOfDraw(firstPlayer, secondPlayer, limitInArray);
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
				moveReport = moveReport + " destroyed " + typeOfKilledPiece;
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
	
	private class Undo{
		private int firstPlayersTotalUndoChoises; 
		private int secondPlayersTotalUndoChoises;
		private int firstPlayersUndoChioseThisTurn;
		private int secondPlayersUndoChioseThisTurn;
		
		public Undo() {
			firstPlayersTotalUndoChoises = 2; 
			secondPlayersTotalUndoChoises = 2;
			firstPlayersUndoChioseThisTurn = 1;
			secondPlayersUndoChioseThisTurn = 1;
		}
		
		public void setFirstPlayersUndoChioseThisTurn(int firstPlayersUndoChioseThisTurn) {
			this.firstPlayersUndoChioseThisTurn = firstPlayersUndoChioseThisTurn;
		}
		
		public void setSecondPlayersUndoChioseThisTurn(int secondPlayersUndoChioseThisTurn) {
			this.secondPlayersUndoChioseThisTurn = secondPlayersUndoChioseThisTurn;
		}
		
		public void showUndoNumber(Color color) {
			if(color.getColor().equals("white")) {
				System.out.println("you have " + firstPlayersTotalUndoChoises + " undo moves");
			}else {
				System.out.println("you have " + secondPlayersTotalUndoChoises + " undo moves");
			}			
		}
		
		public boolean undo(String color, MovesAndKilledPieces movesAndKilledPieces,
				boolean playerHasMoved, ArrayList<Piece> allPieces) {
			
			if(color.contentEquals("white")) {
				if(firstPlayersTotalUndoChoises == 0) {
					System.out.println("you cannot undo anymore");
					return playerHasMoved;
				}
			}
			
			if(color.contentEquals("black")) {
				if(secondPlayersTotalUndoChoises == 0) {
					System.out.println("you cannot undo anymore");
					return playerHasMoved;
				}
			}
			
			if(!playerHasMoved) {
				System.out.println("you must move before undo");
				return playerHasMoved;
			}
			
			if(color.contentEquals("white") && firstPlayersUndoChioseThisTurn == 0) {
				System.out.println("you have used your undo for this turn");
				return playerHasMoved;
			}
			
			if(color.contentEquals("black") && secondPlayersUndoChioseThisTurn == 0) {
				System.out.println("you have used your undo for this turn");
				return playerHasMoved;
			}
			
			if(color.equals("white")) {
				firstPlayersTotalUndoChoises--;
				firstPlayersUndoChioseThisTurn--;
			}else {
				secondPlayersTotalUndoChoises--;
				secondPlayersUndoChioseThisTurn--;
			}
			
			undoSuccessfully(color, movesAndKilledPieces, allPieces);
			
			return false;	
		}
		
		private void undoSuccessfully(String color, MovesAndKilledPieces movesAndKilledPieces,
				ArrayList<Piece> allPieces) {
			String lastMove = movesAndKilledPieces.getMoves()
					.get(movesAndKilledPieces.getMoves().size() - 1);
			
			Matcher matcher = getMatcher(lastMove, "(\\d+)");
			matcher.find();//fix this
			int xOrigin = Integer.parseInt(matcher.group(1));
			matcher.find();
			int yOrigin = Integer.parseInt(matcher.group(1));
			matcher.find();
			int xDestination = Integer.parseInt(matcher.group(1));
			matcher.find();
			int yDestination = Integer.parseInt(matcher.group(1));
			matcher.find();
			
			for(Piece piece: allPieces) {
				if(piece.getXCoordinate() == xDestination && piece.getYCoordinate() == yDestination) {
					if(!piece.hasBeenKilled) {
						piece.changeCoordinate(xOrigin, yOrigin);
					}
				}
			}
			
			if(lastMove.contains("destroyed")) {
				String pieceType = lastMove.split("\\s")[5];
				for(Piece piece: allPieces) {
					if(piece.hasBeenKilled) {
						if(pieceType.equals(piece.getType())) {
							if(piece.getXCoordinate() == xDestination && 
									piece.getYCoordinate() == yDestination) {
								piece.setHasbeenKilled(false);
								movesAndKilledPieces.killedPieces.
								remove(movesAndKilledPieces.killedPieces.size() - 1);
								break;
							}
						}
					}
				}
			}
			
			//see if the below action causes error or not
			movesAndKilledPieces.moves.remove(movesAndKilledPieces.moves.size() - 1);
			System.out.println("undo completed");
		}
	}

}
