package com.busanit501.jsp_server_project1._0205_todo.controller;

import com.busanit501.jsp_server_project1._0205_todo.dto._0205_2_TodoDTO;
import com.busanit501.jsp_server_project1._0205_todo.service._0205_1_TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name="_0205_11_TodoUpdateController", urlPatterns = "/todo/update_0205")
public class _0205_11_TodoUpdateController extends HttpServlet {

    private _0205_1_TodoService todoService = _0205_1_TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // 수정 폼 화면
    // 이미 글은 작성이 되어 있고, DB 서버에 저장이 된상태.
    // 하나조회 화면(상세보기 화면), 수정/삭제 버튼 클릭해서, 수정화면으로 이동
    // -> 수정 화면 -> 기존의 데이터를 ,화면에 불러오기.
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 글쓰기 화면 제공 코드를 복붙, 수정해서 사용. 반복되는 코드가 있으므로
        // 받을 때 한글 설정,
        req.setCharacterEncoding("UTF-8");

        log.info("todo/update_0205 수정 화면입니다.");
        //서비스에 전달.
        try {
            // 하나 조회 했고, 수정 화면으로 넘갈 때, 우리는 조회하는 todo의 tno 번호를 알고 있다.
            // 하나조회(상세보기) , URL : http://localhost:8080/todo/read_0205?tno=15
            // 여기서, tno 번호를 서버에 전달하기.
            // 서버입장에서, tno 번호를 가져오기. 문자열 -> 숫자 타입 변경, 파싱 : Long.parseLong()
            Long tno = Long.parseLong(req.getParameter("tno"));

            // DB 서버에게 일시키기, 우리는 서비스를 고용해서, 일 시키자.
            // DB로 하나 조회 된 데이터를 가지고 왔음.
            _0205_2_TodoDTO todoDTO = todoService.get(tno);

            // 화면에 전달.
            req.setAttribute("dto", todoDTO);
            req.getRequestDispatcher("/WEB-INF/_0205_todo/modify.jsp").forward(req,resp);

            // 보낼 때 한글 설정,
            // 나중에, 서버에 한번만 설정해서, 따로 설정 없이, 이용만 하면됨.
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 수정 처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
