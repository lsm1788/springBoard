package com.kb.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kb.domain.MemberCriteria;
import com.kb.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {

	@Setter(onMethod_ = @Autowired)
	MemberMapper mapper;
	
	public void getListTest() {
		log.info(mapper.getList());
	}

	@Test
	public void testRead() {
		MemberVO vo = mapper.readLogin("admin9");
		log.info(vo);
	}
	
	public void getListWithPaging() {
		MemberCriteria cri = new MemberCriteria();
		cri.setPageNum(1);
		cri.setAmount(20);
		cri.setType("uid");
		cri.setKeyword("01012345678");
		List<MemberVO> list = mapper.getListWithPaging(cri);
		list.forEach(member -> log.info(member));
	}
	
	public void insert() {
		MemberVO member = new MemberVO();
		member.setUname("새 이름2");
		member.setSchoolname("새 학교이름2");
		member.setGradeclass("새 학년,반2");
		member.setUid("새 아이디2");
		member.setUpw("새 비번2");
		member.setRoute("새 경로2");
		member.setBoardingplace("새 탑승장소2");
		mapper.insert(member);
	}
	
	public void read() {
		log.info(mapper.read(26));
	}
	
	
	public void update() {
		MemberVO member = new MemberVO();
		member.setNum(26);
		member.setUname("수정한 이름");
		member.setSchoolname("수정한 학교이름");
		member.setGradeclass("수정한 학년,반");
		member.setUid("수정한 아이디");
		member.setUpw("수정한 비번");
		member.setRoute("수정한 경로");
		member.setBoardingplace("수정한 탑승장소");
		mapper.update(member);
	}
	
	
	public void delete() {
		mapper.delete(26);
	}
}
