package com.busanit501.jsp_server_project1._0203_todo.dao;

import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class _0203_4_TodoDAO {
    // 여기 클래스, 데이터베이스에 연결하는 기능을 모아둔 클래스.

    // 디비 서버에 연결해서,
    // 현재 시간을 조회하는 쿼리를 전달하고,
    // 현재 시간을 받아서, 가져오기.
    public String getTime() {
        // 현재 시간을 받아둘 임시 변수.
        String now = null;
        // try ~ resource with, 자동 닫기 내포되어 있음.
        // DB 서버에 연결하는 도구(디비 서버 주소, 계정 정보, 나머지 캐시 옵션이 설정되어 있는 객체)
        try(Connection connection = _0203_3_ConnectionUtil.INSTANCE.getConnection();
            // 현재 시간을 조회하는 쿼리를 전달하고
            PreparedStatement preparedStatement = connection.prepareStatement("select now()");
            // 디비 서버에 전달하고, 결과를 받아와서, 담아두기.
            ResultSet resultSet = preparedStatement.executeQuery();
                ){
              // resultSet, 담겨진 시간을 조회
            resultSet.next();
            now = resultSet.getString(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    } //getTime 닫기

    // try ~ catch 구문 대신에, 어노테이션을 이용해서, @Cleanup
    public String getTime2() throws Exception {
            // @Cleanup와 같은 효과 = connection.close()
            @Cleanup Connection connection = _0203_3_ConnectionUtil.INSTANCE.getConnection();
            // 현재 시간을 조회하는 쿼리를 전달하고
            // @Cleanup와 같은 효과 = preparedStatement.close()
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
            // 디비 서버에 전달하고, 결과를 받아와서, 담아두기.
            // @Cleanup와 같은 효과 = resultSet.close()
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

            // resultSet, 담겨진 시간을 조회
            resultSet.next();
            String now = resultSet.getString(1);

        return now;
    }


} //_0203_4_TodoDAO 닫기
