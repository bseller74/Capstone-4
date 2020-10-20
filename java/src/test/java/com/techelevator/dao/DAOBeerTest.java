package com.techelevator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import com.techelevator.model.Beer;


class DAOBeerTest extends DAOIntegrationTest{

	
	private JDBCBeerDAO dao;
	
	 @Before
	    public void setup() {
	        DataSource dataSource = this.getDataSource();
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	        dao = new JDBCBeerDAO(jdbcTemplate);
	    }
   
	@Test
	public void getAllBeer() {
		List<Beer> beerList = new ArrayList<>();
		beerList = dao.getAllBeer();
		int actualResult = beerList.size();
		
		//checking database for current number of beers
		int expectedResult = 29;
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	public void beerByBrewery() {
		List<Beer> beerList = dao.BeerByBrewery(1L);
		int actualSize = beerList.size();
		//check database for current beer count
		int expected = 6;
		
		assertEquals(expected, actualSize);
	
	}

}
