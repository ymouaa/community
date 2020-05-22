//package com.ang.springboot_es;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.ang.springboot_es.dao.AuthorityMapper;
//import com.ang.springboot_es.entity.Authority;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes = SpringbootEsApplication.class)
//public class TestMapper {
//	@Autowired
//	private TagService tagService;
//
//	@Autowired
//	private AuthorityMapper mapper;
//	@Test
//	public void testTag() {
////		print(tagService.findTagExist("电影"));
////		print(tagService.findTagExist("飞机"));
////		print(tagService.findTagExist("大炮"));
////		print(tagService.findTagExist("互联网"));
//		List<Authority> allAuthority = mapper.selectAllAuthority();
//
//		for (Authority authority : allAuthority) {
//			System.out.println(authority.getAuthority()+":"+authority.getId());
//		}
//		System.out.println("==================");
//
//		List<Authority> userId = mapper.selectAuthoritysByUserId(111);
//		for (Authority authority : userId) {
//			System.out.println("userId:111 is a"+authority.getAuthority());
//		}
//		System.out.println("==================");
//		userId = mapper.selectOtherAuthority(111);
//		for (Authority authority : userId) {
//			System.out.println("userId is not a"+authority.getAuthority());
//		}
//
//
//	}
//
//	private void print(Object o) {
//		System.out.println(o);
//	}
//}
