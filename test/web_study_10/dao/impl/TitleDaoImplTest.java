package web_study_10.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import web_study_10.dao.TitleDao;
import web_study_10.dto.Title;


public class TitleDaoImplTest {
	private TitleDao dao;

	@Before
	
	public void setUp() throws Exception {
		dao = TitleDaoImpl.getInstance();
	}
	
	

	@After
	public void tearDown() throws Exception {
		
		dao = null;
	}
	
	@Test
	
	public void testSelectTitleByAll() {
		List<Title> list = dao.selectTitleByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

}
