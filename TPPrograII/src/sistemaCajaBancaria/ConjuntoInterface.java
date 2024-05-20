package sistemaCajaBancaria;

public interface ConjuntoInterface {
	
	public String getArray();
	
	public void inciarConjunto();
	
	public void agregar(int x);
	
	public void sacar(int x);
	
	public int elegir();
	
	public boolean pertenece(int x);
	
	public boolean estaVacio();
}
