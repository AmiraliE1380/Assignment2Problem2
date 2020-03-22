package Problem2;

import java.util.ArrayList;
import java.util.TreeSet;

public class Player {
	private static ArrayList<Player> allPlayers = new ArrayList<Player>();
	private String username;
	private String password;
	private int numOfWins;
	private int numOfDraws;
	private int numOfLooses;
	private int score;
	private boolean login;
	
	public Player(String username, String password) {
		allPlayers.add(this);
		this.username = username;
		this.password = password;
		this.numOfWins = 0;
		this.numOfDraws = 0;
		this.numOfLooses = 0;
		this.score = 0;
		this.login = false;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getNumOfWins() {
		return numOfWins;
	}
	
	public int getNumOfDraws() {
		return numOfDraws;
	}
	
	public int getNumOfLooses() {
		return numOfLooses;
	}
	
	public void addNumOfWins() {
		numOfWins++;
	}
	
	public void addNumOfDraws() {
		numOfDraws++;
	}
	
	public void addNumOfLooses() {
		numOfLooses++;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void login() {
		login = true;
	}
	
	public void logout() {
		login = false;
	}
	
  	public static Player getPlayerByName(String username) {
		for(Player player: allPlayers) {
			if(player.getUsername().equals(username)) {
				return player;
			}
		}
		
		return null;
	}
	
	public static boolean doesUserwithThisNameExist(String username) {
		
		for(Player player: allPlayers) {
			if(username.equals(player.getUsername())) {
				return true;
			}
		}
		
		return false;
	}

	public static void remove(String username) {
		allPlayers.remove(getPlayerByName(username));
	}

	public static void showUsers() {//I don't know if the way it sorts upercase and lowercase is correct 
		TreeSet<String> sortedListOfPlayers = sortUsernames();
		
		for(String username: sortedListOfPlayers) {
			System.out.println(username);
		}
	}

	public static void showScoreboard() {
		TreeSet<String> sortedListOfPlayers = sortUsernames();
		
		for(String username: sortedListOfPlayers) {
			Player player = getPlayerByName(username);
			System.out.printf("%s %s %s % %s\n", username, player.getScore(), player.getNumOfWins(), 
					player.getNumOfDraws(), player.getNumOfLooses());
		}
	}
	
	public static TreeSet sortUsernames() {
		TreeSet<String> sortedListOfPlayers = new TreeSet<String>();
		
		for(Player player: allPlayers) {
			sortedListOfPlayers.add(player.username);
		}
		
		return sortedListOfPlayers;
	}
}
