package web_study_10.ds;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class JndiDSTest {

	@Test
	public void testGetConnection() {
		Connection con = JndiDS.getConnection();
		Assert.assertNotNull(con);
		System.out.println(con);
		
	}

	@Test
	public void testGetConnection2() {
		
		
		Connection con = JdbcUtil.getConnection();
		Assert.assertNotNull(con);
		System.out.println(con);
		
	}

}
