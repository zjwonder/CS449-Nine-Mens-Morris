import java.util.*;

public class Nine_Mens_Morris {

	public static void main(String[] args) {
		
		Scanner user_input = new Scanner(System.in);
		System.out.println("Welcome to Nine Men's Morris! Press Enter to begin.");
		String pw_attempt = user_input.nextLine();
		
		if (pw_attempt.equals("test")) { // enter correct code to initialize automated testing
			Test_Cases test_obj = new Test_Cases();
			test_obj.initializer();
		}
		
		else {
		
		// TODO Auto-generated method stub
		Game_Logic gameTest = new Game_Logic();
		/*Board test = new Board(9);
		System.out.println(test.spaces);
		System.out.println(test.mills.get(5)[2]);
		System.out.print(test.whitePieces);*/
		}
	}
}
