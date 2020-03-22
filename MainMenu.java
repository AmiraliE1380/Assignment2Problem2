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
	
	private void newGame(String[] input) {
		
	}
	
	private void help() {
		System.out.println("new_game [username] [limit]\r\n" + 
				"scoreboard\r\n" + 
				"list_users\r\n" + 
				"help\r\n" + 
				"logout");
	}
	
	public void run(Player player) {
		String input;
		Scanner scanner = new Scanner(System.in);
		
		while(!(input = scanner.nextLine().trim()).equals("logout")) {
			
			if(getMatcher(input, "(new_game .+ -{0,1}\\d+)").matches()) {
				newGame(input.split("\\s"));
			}else if(input.contentEquals("scoreboard")) {
				Player.showScoreboard();
			}else if(input.contentEquals("list_users")) {
				Player.showUsers();
			}else if(input.contentEquals("help")) {
				help();
			}
		}
		
		System.out.println("logout successful");
		player.logout();
	}
}
