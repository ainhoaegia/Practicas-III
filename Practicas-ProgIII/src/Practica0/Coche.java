package Practica0;

public class Coche {
	
	private double miVelocidad; // Velocidad en pixels/segundo
	protected double miDireccionActual; // Dirección en la que estoy mirando en grados (de 0 a 360)
	protected double posX; // Posición en X (horizontal)
	protected double posY; // Posición en Y (vertical)
	private String piloto; // Nombre de piloto
	
	public Coche( String piloto, Double posX, Double posY, Double miVelocidad, Double miDireccionActual ) {
		super();
		this.piloto = piloto;
		this.posX = posX;
		this.posY = posY;
		this.miVelocidad = miVelocidad;
		this.miDireccionActual = miDireccionActual;
	}

	public double getMiVelocidad() {
		return miVelocidad;
	}

	public void setMiVelocidad(double miVelocidad) {
		this.miVelocidad = miVelocidad;
	}

	public double getMiDireccionActual() {
		return miDireccionActual;
	}

	public void setMiDireccionActual(double miDireccionActual) {
		this.miDireccionActual = miDireccionActual;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public String getPiloto() {
		return piloto;
	}

	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}
	
	// toString
	
	@Override
	public String toString() {
		return "Coche: " + piloto + ", " + posX + ", " + posY + ", " + miVelocidad + ", " + miDireccionActual ;
	}
}
