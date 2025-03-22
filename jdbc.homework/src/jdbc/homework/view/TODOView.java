package jdbc.homework.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import jdbc.homework.service.TODOService;

public class TODOView {

	private Scanner sc = new Scanner(System.in);
	private TODOService service = new TODOService();

	public void mainMenu() {

		int input = 0;

		do {
			
			try {
				
			} catch (InputMismatchException e) {
				System.out.println("\n******잘못 입력 하셨습니다.******\n");
				
				input = -1;
				sc.nextLine();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}while(input != 0);

	}

}
