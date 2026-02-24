package com.busanit501.jsp_server_project1.springex_new_0219_keep.mapper;

import com.busanit501.jsp_server_project1.springex_new_0219_keep.domain.TodoVO;
import com.busanit501.jsp_server_project1.springex_new_0219_keep.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();

    //추가
    // 화면에서, 입력된 정보를 받기 -> DTO담기 -> VO 변환 -> DB 에 전달.
    void insert(TodoVO todoVO);

    // 전체 목록 조회
    List<TodoVO> selectAll();

    // 하나 조회
    TodoVO selectOne(Long tno);

    // 삭제
    void delete(Long tno);

    //수정
    void update(TodoVO todoVO);

    // 페이지네이션 처리가 된 목록 조회
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);
}
