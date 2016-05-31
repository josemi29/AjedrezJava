package logica.recuerdo;
/*
 * comentario de prueba
 * otra prueba
 * prueba dani
 */

import logica.Fabrica;
import logica.FabricaPiezas;
import logica.Jugador;
import logica.estrategia.Mediador;
import logica.piezas.Pieza;

public class Tablero {
	private Pieza[][] piezas;
	private Mediador mediador;
	Fabrica fabricaPiezas;

	public Tablero(Mediador m) {
		mediador = m;
		fabricaPiezas = FabricaPiezas.getSingleton();
	}

	public void inicializarTableroEstandar(Jugador j1, Jugador j2) {

		piezas = new Pieza[8][8];
		
//		for (int i = 0; i < 8; i++)
//			for (int j = 0; j < 8; j++)
//				piezas[i][j] = new Pieza();

		piezas[0][0] = fabricaPiezas.crear("torre", j1);
		piezas[0][7] = fabricaPiezas.crear("torre", j2);
		piezas[7][0] = fabricaPiezas.crear("torre", j1);
		piezas[7][7] = fabricaPiezas.crear("torre", j2);
		piezas[4][0] = fabricaPiezas.crear("rey", j1);
		piezas[4][7] = fabricaPiezas.crear("rey", j2);
		piezas[3][0] = fabricaPiezas.crear("reina", j1);
		piezas[3][7] = fabricaPiezas.crear("reina", j2);
		for (int i = 0; i < 8; i++) {
			piezas[i][1] = fabricaPiezas.crear("peon", j1);
			piezas[i][6] = fabricaPiezas.crear("peon", j2);
		}
		piezas[1][0] = fabricaPiezas.crear("caballo", j1);
		piezas[6][0] = fabricaPiezas.crear("caballo", j1);
		piezas[1][7] = fabricaPiezas.crear("caballo", j2);
		piezas[6][7] = fabricaPiezas.crear("caballo", j2);
		piezas[2][0] = fabricaPiezas.crear("alfil", j1);
		piezas[5][0] = fabricaPiezas.crear("alfil", j1);
		piezas[2][7] = fabricaPiezas.crear("alfil", j2);
		piezas[5][7] = fabricaPiezas.crear("alfil", j2);
		
		Conserje.getSingleton().add(this.saveStateToMemento());
	}

	public void inicializarTableroInvertido(Jugador j1, Jugador j2) {
		this.inicializarTableroEstandar(j1, j2);
	}

	public void inicializarTableroDamas(Jugador j1, Jugador j2) {
		// a implementar
	}

	// toString para pruebas en consola
	@Override
	public String toString() {
		String out = new String();
		for (int i = 7; i >= 0; i--) {
			out += "\n" + i;
			for (int j = 0; j <= 7; j++)
				out += " " + piezas[j][i];
		}
		out += "\n ";
		for (int j = 0; j <= 7; j++)
			out += " " + j;
		return out;
	}

	public Pieza[][] getPiezas() {
		return piezas;
	}

	public void setPiezas(Pieza[][] piezas) {
		this.piezas = piezas;
	}
	
	public void setPieza(int x, int y, Pieza p) {
		piezas[x][y] = p;
	}
	
	public void quitarPieza(int  x, int y) {
		piezas[x][y] = null;
	}
	
	
	//patr�n Memento
	public Memento saveStateToMemento() {
		Pieza[][] res = new Pieza[8][8];
		for (int i = 0; i <= 7; i++) {			
			for (int j = 0; j <= 7; j++)
				res[i][j] = piezas[i][j];
		}
		return new Memento(res);
	}

	public void getStateFromMemento(Memento Memento) {
		piezas = Memento.getState();
	}
	

	public Mediador getMediador() {
		return mediador;
	}

	public void setMediador(Mediador mediador) {
		this.mediador = mediador;
	}

	public Pieza getPieza(int x, int y) {
		return piezas[x][y];
	}
}