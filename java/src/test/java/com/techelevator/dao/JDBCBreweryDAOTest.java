package com.techelevator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.Brewery;

class JDBCBreweryDAOTest {

	@Autowired
	JDBCBreweryDAO dao;
	private static SingleConnectionDataSource dataSource;
	JdbcTemplate jdbcTemplate;

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dao.setJdbcTemplate(jdbcTemplate);
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	void getAllBreweryTest() {
		String sql = "INSERT INTO Breweries (name, address, description, image, brewer_id, brewery_url, phone, hours, active) VALUES " + 
				"('TRVE Brewing Company', '227 Broadway, Denver, CO 80203', 'Small local brewery & taproom featuring eclectic housemade beers in heavy metal–inspired environs.', 'https://pbs.twimg.com/profile_images/707978949037436929/MItXQjYg.jpg', 101, 'https://www.trvebrewing.com/ontap/', '3033511021', '3pm to 11pm', true) ";
		List <Brewery> output1 = dao.getAllBrewerys();
		jdbcTemplate.update(sql);
		List <Brewery> output2 = dao.getAllBrewerys();
		assertEquals(output2.size(), output1.size() + 1);
	}

}
