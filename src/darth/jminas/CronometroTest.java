package darth.jminas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CronometroTest {

	@Test
	void testCronometroNaoAtivo() {

		PanelSuperior p = new PanelSuperior();
		
		System.out.println(p.lblTime.getText());
				
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			fail(e);
		}


		Assert.assertEquals("00:00", p.lblTime.getText());
	}
	
	@Test
	void testCronometroAtivo() {

		PanelSuperior p = new PanelSuperior();

		p.UpdateTime(0, 5);
				
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			fail(e);
		}


		Assert.assertEquals("00:05", p.lblTime.getText());
	}

}
