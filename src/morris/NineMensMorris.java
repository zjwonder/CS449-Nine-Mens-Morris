package morris;
import java.util.*;

public class NineMensMorris {

	public static void main(String[] args) {
		
		Scanner user_input = new Scanner(System.in);
		System.out.println("Welcome to Nine Men's Morris! Press Enter to begin.");
		String pw_attempt = user_input.nextLine();
		
		if (pw_attempt.equals("test")) { // enter correct code to initialize automated testing
			TestCases test_obj = new TestCases();
			test_obj.initializer();
		}
		
		else {
		
		// TODO Auto-generated method stub
		GameLogic gameTest = new GameLogic();
		/*Board test = new Board(9);
		System.out.println(test.spaces);
		System.out.println(test.mills.get(5)[2]);
		System.out.print(test.whitePieces);*/
		}
	}
}
