package Problem2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
	
	private Matcher getMatcher(String input, String regex) {//works good
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher;
	}
	
	private void newGame(String[] input, Player firstPlayer) {//works good
		String secondPlayer = input[1];
		int limit = Integer.parseInt(input[2]);
		
		if(!getMatcher(secondPlayer, "([\\w|\\d|_]+)").matches()) {
			System.out.println("username format is invalid");
		}else if(limit < 0) {
			System.out.println("number should be positive to have a limit or 0 for no limit");
		}else if(secondPlayer.contentEquals(firstPlayer.getUsername())) {
			System.out.println("you must choose another player to start a game");
		}else if(!Player.doesUserwithThisNameExist(secondPlayer)) {
			System.out.println("no user exists with this username");
		}else {
			System.out.println("new game started successfully between " + firstPlayer.getUsername()
						+ " and " + secondPlayer + " with limit " + limit);
			
			NewGame newGame = new NewGame();
			newGame.run(firstPlayer, Player.getPlayerByName(secondPlayer), limit);
		}
	}
	
	private void help() {//works good
		System.out.println("new_game [username] [limit]\r\n" + 
				"scoreboard\r\n" + 
				"list_users\r\n" + 
				"help\r\n" + 
				"logout");
	}
	
	public void run(Player player) {//works good
		String input;
		Scanner scanner = new Scanner(System.in);
		
		while(!(input = scanner.nextLine().trim()).equals("logout")) {
			
			if(getMatcher(input, "(new_game .+ -{0,1}\\d+)").matches()) {
				newGame(input.split("\\s"), player);
			}else if(input.contentEquals("scoreboard")) {
				Player.showScoreboard();
			}else if(input.contentEquals("list_users")) {
				Player.showUsers();
			}else if(input.contentEquals("help")) {
				help();
			}else {
				System.out.println("invalid command");
			}
		}
		
		System.out.println("logout successful");
		player.logout();
		scanner.close();
	}
}
