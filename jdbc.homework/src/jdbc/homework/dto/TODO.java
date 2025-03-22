package jdbc.homework.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // 기본생성자
@AllArgsConstructor
@ToString
public class TODO {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private Date enrollDate;
}
