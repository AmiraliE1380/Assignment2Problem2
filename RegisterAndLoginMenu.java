package Problem2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegisterAndLoginMenu {
	
	private Matcher getMatcher(String input, String regex) {//works good
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher;
	}
	
	private void register(String[] input) {
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
	
	private void login(String[] input) {
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
			Player.getPlayerByName(username).login();
			System.out.println("login successful");
		}
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		String input = new String();
		
		while(!(input = scanner.nextLine().trim()).equals("exit")) {
			
			if(getMatcher(input, "(register .+ .+)").matches()) {
				register(input.split("\\s"));
			}else if(getMatcher(input, "(login .+ .+)").matches()){
				login(input.split("\\s"));
			}else {
				System.out.println("invalid command");
			}
		}
		
		System.out.println("program ended");
	}

}
