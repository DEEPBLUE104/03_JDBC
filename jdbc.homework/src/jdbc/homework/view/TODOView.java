package jdbc.homework.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jdbc.homework.dto.TODO;
import jdbc.homework.service.TODOService;

public class TODOView {

	private Scanner sc = new Scanner(System.in);
	private TODOService service = new TODOService();

	public void mainMenu() {

		int input = 0;

		do {
			
			try {
				
				System.out.println("\n===== User 관리 프로그램 =====\n");
				System.out.println("1. User 등록(INSERT)");
				System.out.println("2. User 전체 조회(SELECT)");
				System.out.println("3. User 중 이름에 검색어가 포함된 회원 조회 (SELECT)");
				System.out.println("4. USER_NO를 입력 받아 일치하는 User 조회(SELECT)");
				System.out.println("5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE)");
				System.out.println("6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE)");
				System.out.println("7. User 등록(아이디 중복 검사)");
				System.out.println("8. 여러 User 등록하기");
				System.out.println("0. 프로그램 종료");

				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 버퍼에 남은 개행문자 제거

				switch (input) {
				case 1: insertUser(); break;
				case 2: selectAll(); break;
				case 3: selectName(); break;
				case 4: selectUser(); break;
				case 5: deleteUser(); break;
				case 6: updateName(); break;
		//		case 7: insertUser2(); break;
		//		case 8: multiInsertUser(); break;

				case 0:
					System.out.println("\n[프로그램 종료]\n");
					break;
				default:
					System.out.println("\n[메뉴 번호만 입력하세요]\n");
				}

				System.out.println("\n-------------------------------------\n");
				
			} catch (InputMismatchException e) {
				System.out.println("\n******잘못 입력 하셨습니다.******\n");
				
				input = -1;
				sc.nextLine();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}while(input != 0);

	}






	private void insertUser() throws Exception {
		
		System.out.println("\n======유저 등록=======\n");
		
		System.out.print("ID :");
		String userId = sc.next();
		
		System.out.print("PW :");
		String userPw = sc.next();
		
		System.out.print("Name :");
		String userName = sc.next();
		
		TODO Todouser = new TODO();
		
		Todouser.setUserId(userId);
		Todouser.setUserPw(userPw);
		Todouser.setUserName(userName);
		
		int result = service.insertUser(Todouser);
		
		if (result > 0) {
			System.out.println("\n" + userId + "사용자가 등록되었습니다.\n");
			
		} else {
			System.out.println("\n===등록 실패===\n");
		}
		
	}

	private void selectAll() throws Exception {
		System.out.println("\n============2. User 전체 조화============\n");
		
		List<TODO> todoList = service.selectALL();
		
		if(todoList.isEmpty()) {
			System.out.println("\n===조회된 결과가 없습니다.===\n");
			return;
		} 
		
		for (TODO todo : todoList) {
			System.out.println(todo);
		}
	}
	private void selectName() throws Exception {
		System.out.println("\n=====User 중 이름에 검색어가 포함된 회원 조회=====\n");

		System.out.print("검색어 입력 : ");
		String keyword = sc.next();
		
		List<TODO> todoList = service.selectName(keyword);
		
		if (todoList.isEmpty()) {
			System.out.println("검색 결과 없음");
			return;
		}
			for(TODO todo : todoList) {
				System.out.println(todo);
			}
		
	}
	
	private void selectUser() throws Exception {
		System.out.println("\n===4. USER_NO를 입력 받아 일치하는 User 조회 ===\n");

		System.out.print("사용자 번호 입력 : ");
		int input = sc.nextInt();
		
		TODO todo = service.selectUser(input);
		
		if(todo == null) {
			System.out.println("User_NO가 일치하는 회원 없음");
			return;
		}
		System.out.println(todo);
	}
	
	
	private void deleteUser() throws Exception {
		System.out.println("\n==========5. USER_NO을 입력 받아 일치하는 User 삭제=============\n");
		
		System.out.println("삭제할 사용자 번호 입력:");
		int input = sc.nextInt();
		
		int result = service.deleteUser(input);
		
		if(result > 0) {
			System.out.println(input + "번을 삭제했습니다.");
		
		} else {
			System.out.println("사용자 번호가 일치하는 User 가 존재하지 않습니다.");
		}
		
	}
	
	private void updateName() throws Exception {
		
		System.out.println("\n=======ID, PW가 일치하는 회원이 있을 경우 이름 수정========\n");
		
		System.out.println("ID :");
		String userId = sc.next();
		
		System.out.println("PW : ");
		String userPw = sc.next();
		
		int memberNo = service.selectmemberNo(userId, userPw);
		
		if(memberNo == 0) {
			System.out.println("아이디, 비밀번호가 일치하는 사람이 없습니다");
			return;
		
		}
		
		System.out.println("수정할 이름 입력:");
		String memberName = sc.next();
		
		int result = service.updateName(memberName, memberNo);
		
		if(result > 0 ) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
		
	}
	
}
