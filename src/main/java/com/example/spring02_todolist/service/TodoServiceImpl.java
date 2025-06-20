package com.example.spring02_todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02_todolist.Spring02TodolistApplication;
import com.example.spring02_todolist.dto.TodoDTO;
import com.example.spring02_todolist.entity.TodoEntity;
import com.example.spring02_todolist.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

	private final Spring02TodolistApplication spring02TodolistApplication;

	@Autowired
	private TodoRepository todoRepository;

	public TodoServiceImpl(Spring02TodolistApplication spring02TodolistApplication) {
		this.spring02TodolistApplication = spring02TodolistApplication;
		// TODO Auto-generated constructor stub
	}

	// stream(): 리스트를 스트림으로 변환하여 각 요소에 map()을 적용
	// map(TodoDTO::toDTO): 각 TodoEntity를 TodoDTO로 변환 (정적 메서드 toDTO 사용)
	// collect(Collectors.toList()): 변환된 요소들을 다시 리스트로 수집
	@Override
	@Transactional(readOnly = true)
	public List<TodoDTO> search() throws Exception {
		List<TodoEntity> listTodoEntity = todoRepository.findAll();
		List<TodoDTO> listTodoDTO = listTodoEntity.stream().map(TodoDTO::toDTO).collect(Collectors.toList());
		return listTodoDTO;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insert(TodoDTO dto) throws Exception {
		TodoEntity insert = todoRepository.save(dto.todoEntity());
		log.info("insert => {}", insert);

		// 예외 발생 시 테스트용 코드(주석 해제시 롤백확인 가능)
		// if(true) throw new Exception("강제 예외 발생");
	}
 
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(TodoDTO dto) throws Exception {
		TodoEntity update = todoRepository.save(dto.todoEntity());
		log.info("update+>{}", update);
		// 예외 발생 시 테스트용 코드(주석 해제시 롤백확인 가능)
		//if (true)			throw new Exception("강제 예외 발생");
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(int id) throws Exception {
		if (!todoRepository.existsById(id)) {
			throw new EntityNotFoundException("id"+id+"는 존재하지 않습니다.");
		}
			todoRepository.deleteById(id);
	}
}
