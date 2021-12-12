package darth.jminas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class PanelCentralTest {

	@Test
	void testCanChangeNivel() {
		Variables.setNivel(2);
		PanelCentral p = new PanelCentral(new JMinasMain());
		Assert.assertEquals(Variables.numeroMinas, p.getContMinasMarcadas());
	}

	@Test
	void testExplodirMina() {
		class MapaMockComMina extends Mapa {
			public boolean TieneMina(int x, int y) {
				return true;
			}
			public boolean Abrir(int x, int y) {
				return false;
			}
		}
		JMinasMain main = new JMinasMain();
		JMinasMain.Ganador = false;
		JMinasMain.Perdedor = false;
		
		PanelCentral p = new PanelCentral(main);
		p.setMapa(new MapaMockComMina());
		
		JMinasMain.StartGame();
		
		p.abrir(0, 0, 0);
		
		Assert.assertTrue(JMinasMain.Perdedor);
	}

	@Test
	void testGanharJogo() {
		class MapaMockSemMina extends Mapa {
			public boolean TieneMina(int x, int y) {
				return false;
			}
		
			public int getCeldasAbiertas() {
				return (Variables.ancho*Variables.alto - Variables.numeroMinas);
			}
		}
		
		PanelCentral p = new PanelCentral(new JMinasMain());
		p.setMapa(new MapaMockSemMina());
		
		JMinasMain.StartGame();
		
		p.abrir(0, 0, 0);
		
		Assert.assertTrue(JMinasMain.Ganador);
	}
}
