package metro;

public class Estacion {

	private String nombre;
	private Estacion ePadre;
	private double x;
	private double y;
	private double g;
	private double h;
	private String[] eConectadas;

	public Estacion(String nombre, double x, double y,  String[] eConectadas) {
		this.nombre = nombre;
		this.x = x;
		this.y = y;
		this.g = 0.0;
		this.h = 0.0;
		this.eConectadas = eConectadas;
	}

	public String getNombre() {
		return nombre;
	}

	public Estacion getPadre() {
		return ePadre;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getG() {
		return g;
	}

	public double getH() {
		return h;
	}
	
	public double getF() {
		return g+h;
	}
	
	public String[] getConectadas() {
		return eConectadas;
	}
	
	public void setPadre(Estacion ePadre) {
		this.ePadre = ePadre;
	}
	
	public void setG(double g) {
		this.g = g;
	}

	public void setH(double h) {
		this.h = h;
	}
}

