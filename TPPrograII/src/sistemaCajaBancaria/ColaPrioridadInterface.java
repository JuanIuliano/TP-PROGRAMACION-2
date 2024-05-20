package sistemaCajaBancaria;

public interface ColaPrioridadInterface {
	
	public void inicializar();
	
	public void acolarPrioridad(int x, int y);
	
	public void desacolar();
	
	public int primero();
	
	public int prioridad();
	
	public boolean colaVacia();

	public void printArray();
	
	public void invertirCola();
	
}
