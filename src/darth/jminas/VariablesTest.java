package darth.jminas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class VariablesTest {

	@Test
	void checkAllImagesExist() {
		for (String img : Variables.archivos) {
			Assert.assertNotNull(getClass().getResource("/darth/img/" + img));
			
		}
	}

}
