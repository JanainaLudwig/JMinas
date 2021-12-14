package darth.jminas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class VariablesTest {

	@Test
	void testAllImagesExist() {
		for (String img : Variables.archivos) {
			Assert.assertNotNull(getClass().getResource("/darth/img/" + img));
			
		}
	}
	
	@Test
	void testSetNivel() {
		Variables.setNivel(0);
		Assert.assertEquals(0, Variables.getNivel());
		Assert.assertEquals(9, Variables.numeroMinas);

		Variables.setNivel(1);
		Assert.assertEquals(1, Variables.getNivel());
		Assert.assertEquals(18, Variables.numeroMinas);

		Variables.setNivel(2);
		Assert.assertEquals(2, Variables.getNivel());
		Assert.assertEquals(40, Variables.numeroMinas);

		Variables.setNivel(3);
		Assert.assertEquals(3, Variables.getNivel());
		Assert.assertEquals(99, Variables.numeroMinas);
	}
	
	@Test
	void testGetColor() {
		Assert.assertNotNull(Variables.getColorCantidad(0));
		Assert.assertNotNull(Variables.getColorCantidad(1));
		Assert.assertNotNull(Variables.getColorCantidad(8));
		Assert.assertNotNull(Variables.getColorCantidad(9));
		Assert.assertNotNull(Variables.getColorCantidad(99));
	}

}
