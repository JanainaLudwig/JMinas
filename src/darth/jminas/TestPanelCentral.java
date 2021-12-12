package darth.jminas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class TestPanelCentral {

	@Test
	void testCanChangeNivel() {
		Variables.setNivel(2);
		PanelCentral p = new PanelCentral(new JMinasMain());
		Assert.assertEquals(Variables.numeroMinas, p.getContMinasMarcadas());
	}
}
