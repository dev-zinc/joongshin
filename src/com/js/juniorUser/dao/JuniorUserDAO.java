/*필요한 서비스 메서드 창고*/
package com.js.juniorUser.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;


import com.js.juniorUser.domain.JuniorUserDTO;
import com.js.juniorUser.domain.JuniorUserDTO2;
import com.js.mybatis.config.MyBatisConfig;

public class JuniorUserDAO {
	
	public SqlSession sqlSession;
	
	public JuniorUserDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	public List<JuniorUserDTO> selectJuniorAll() {
		return sqlSession.selectList("juniorUser.selectJuniorAll");
	} 

//	회원가입
	public String insert(JuniorUserDTO juniorUserDTO, JuniorUserDTO2 juniorUserDTO2) {
//		sqlSession.insert("juniorUser.insert", juniorUserDTO);
//			  return new SQL() {{
//			    INSERT_INTO("tbl_user");
//			    VALUES("user_email", "#{userEmail}");
//			    VALUES("user_password", "#{userPassword}");
//			    VALUES("user_name", "#{userName}");
//			    VALUES("user_address", "#{userAddress}");
//			    VALUES("user_phonenumber", "#{userPhonenumber}");
//			  }}.toString() + ";" +
//			  new SQL() {{
//			    INSERT_INTO("tbl_junior_user");
//			    VALUES("user_id", "LAST_INSERT_ID()");
//			    VALUES("user_career_years", "#{userCareerYears}");
//			    VALUES("user_resume", "#{userResume}");
//			  }}.toString();
		
		  sqlSession.insert("juniorUser.insertUser", juniorUserDTO2);
		  sqlSession.insert("juniorUser.insertJuniorUser", juniorUserDTO);
		  sqlSession.commit();
		  return "success";
			  
	}
	
//	이메일 중복검사
	public String selectEmail(String userEmail) {
		return sqlSession.selectOne("juniorUser.selectEmail", userEmail);
	}
	
//	로그인
	public Long login(String userEmail, String userPassword) {
		HashMap<String, String> loginMap = new HashMap<String, String>();
		loginMap.put("userEmail", userEmail);
		loginMap.put("userPassword", userPassword);
		
		return sqlSession.selectOne("juniorUser.login", loginMap);
	}


}






















