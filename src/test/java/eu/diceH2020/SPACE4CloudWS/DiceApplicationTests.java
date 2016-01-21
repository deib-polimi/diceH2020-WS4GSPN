package eu.diceH2020.SPACE4CloudWS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import it.polimi.diceH2020.WS4GSPN.demo.DiceApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DiceApplication.class)
@WebAppConfiguration
public class DiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
