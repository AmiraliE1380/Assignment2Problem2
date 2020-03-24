package Problem2;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegisterAndLoginMenu {
	
	private Matcher getMatcher(String input, String regex) {//works good
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher;
	}
	
	private void register(String[] input) {//works good
		String username = input[1];
		String password = input[2];
		
		if(!getMatcher(username, "([\\w|\\d|_]+)").matches()) {
			System.out.println("username format is invalid");
		}else if(!getMatcher(password, "([\\w|\\d|_]+)").matches()) {
			System.out.println("password format is invalid");
		}else if(Player.doesUserwithThisNameExist(username)){
			System.out.println("a user exists with this username");
		}else {
			new Player(username, password);
			System.out.println("register successful");
		}
	}
	
	private void login(String[] input) {//works good
		String username = input[1];
		String password = input[2];
		
		if(!getMatcher(username, "([\\w|\\d|_]+)").matches()) {
			System.out.println("username format is invalid");
		}else if(!getMatcher(password, "([\\w|\\d|_]+)").matches()) {
			System.out.println("password format is invalid");
		}else if(!Player.doesUserwithThisNameExist(username)){
			System.out.println("no user exists with this username");
		}else if(!Player.getPlayerByName(username).getPassword().equals(password)) {
			System.out.println("incorrect password");
		}else {
			Player player = Player.getPlayerByName(username);
			System.out.println("login successful");
			MainMenu mainMenu = new MainMenu();
			player.login();
			mainMenu.run(player);
		}
	}
	
	private void remove(String[] input) {//works good
		String username = input[1];
		String password = input[2];
		
		if(!getMatcher(username, "([\\w|\\d|_]+)").matches()) {
			System.out.println("username format is invalid");
		}else if(!getMatcher(password, "([\\w|\\d|_]+)").matches()) {
			System.out.println("password format is invalid");
		}else if(!Player.doesUserwithThisNameExist(username)){
			System.out.println("no user exists with this username");
		}else if(!Player.getPlayerByName(username).getPassword().equals(password)) {
			System.out.println("incorrect password");
		}else {
			Player player = Player.getPlayerByName(username);
			System.out.println("removed " + player.getUsername() + " successfully");
			Player.remove(username);
		}
	}
	
	private void help() {//works good
		System.out.println("register [username] [password]\r\n" + 
				"login [username] [password]\r\n" + 
				"remove [username] [password]\r\n" + 
				"list_users\r\n" + 
				"help\r\n" + 
				"exit\r\n" + 
				"");
	}
	
	public void run() {//works good
		Scanner scanner = new Scanner(System.in);
		String input = new String();
	
		while(!(input = scanner.nextLine().trim()).equals("exit")) {
			if(getMatcher(input, "(register .+ .+)").matches()) {
				register(input.split("\\s"));
			}else if(getMatcher(input, "(login .+ .+)").matches()){
				login(input.split("\\s"));
			}else if(input.equals("help")){
				help();
			}else if(getMatcher(input, "(remove .+ .+)").matches()) {
				remove(input.split("\\s"));
			}else if(input.equals("list_users")) {
				Player.showUsers();
			}else {
				System.out.println("invalid command");
			}
		}
		
		scanner.close();
		System.out.println("program ended");
	}

}
