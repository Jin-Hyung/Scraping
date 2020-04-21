package com.jinbro.common.scraper.test;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.jinbro.common.scraper.runner.ScrapRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class CommonScarperApplicationTests {
	@Autowired
	private ScrapRunner scrapRunner;
	
	@Test
//	@Ignore
	public void testHrdScraper() {
		try {
		  scrapRunner.execute("Hrd", true);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}