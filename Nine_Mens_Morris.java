
public class Nine_Mens_Morris {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password = "hack me";
		Scanner text_scan = new Scanner(System.in);
		String test_passcode = text_scan.nextLine();
		System.out.print("<" + test_passcode + ">"); // for testing
		if (test_passcode == password) {
			Test_Cases test_run = new Test_Cases();
		}
		else {
			Board test = new Board(9);
			Game_Logic gameTest = new Game_Logic();
		}
	}

}
