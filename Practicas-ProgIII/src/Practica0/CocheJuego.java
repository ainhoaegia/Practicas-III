package Practica0;

public class CocheJuego extends Coche{
	
	JLabelCoche lCoche = new JLabelCoche("src/Practica0/coche.png", 100, 100);
	
	protected double miVelocidad; // Velocidad en pixels/segundo
	protected double miDireccionActual; // Dirección en la que estoy mirando en grados (de 0 a 360)
	protected double posX; // Posición en X (horizontal)
	protected double posY; // Posición en Y (vertical)
	protected String piloto; // Nombre de piloto

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

	public CocheJuego(String piloto, Double posX, Double posY, Double miVelocidad, Double miDireccionActual) {
		super(piloto, posX, posY, miVelocidad, miDireccionActual);
		this.posX = posX;
		this.posY = posY;
	}
}
