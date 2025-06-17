package com.example.spring02_todolist.dto;

import com.example.spring02_todolist.entity.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TodoDTO {

	private int id;
	private int completed;
	private String todoname;

	// TodoDTO => TodoEntity 바꾸는 작업
	// id때문에 static불가능
public TodoEntity todoEntity() {
	return TodoEntity.builder()
			.id(id)
			.completed(completed)
			.todoname(todoname)
			.build();
}

	// TodoEntity => TodoDTO 바꾸는 작업
	//TodoEntity todoEntity받아 사용하기 때문에 static 사용가능
	public static TodoDTO toDTO(TodoEntity todoEntity) {
		return TodoDTO.builder()
				.id(todoEntity.getId())
				.completed(todoEntity.getCompleted())
				.todoname(todoEntity.getTodoname())
				.build();
	}
} // end class
