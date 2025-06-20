package com.example.spring02_todolist.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
//빌더를 사용해주기 위해서 @AllArgsConstructor,@NoArgsConstructor가 필요하다.
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
//entity에서 사용할 테이블 이름 
@Table(name="todotbl")
//entity를 사용하려면 반드시 선언을 해야한다.
@Entity
public class TodoEntity {
	@Id
	//오라클에서 받는 시퀀스값
	//GenerationType. 작성 후 mysql->identity, oracle->SEQUENCE에 따라서 선택 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
	generator = "todo_seq_generator")
	@SequenceGenerator(name="todo_seq_generator", sequenceName ="todotbl_id_seq", allocationSize = 1)
	//oracle에 있는 값을 받기위한 작업 / 1개씩 증가한다.
	private int id;
	private int completed;
	private String todoname;
	
}
