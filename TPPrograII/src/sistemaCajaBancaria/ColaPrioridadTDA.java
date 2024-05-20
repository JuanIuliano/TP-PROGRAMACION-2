package sistemaCajaBancaria;

import java.util.Arrays;

public class ColaPrioridadTDA implements ColaPrioridadInterface {
	
	public class elementos{
		int numero;
		int prioridad;
	}
	
	//crear un array que contenga la clase elementos
	
	elementos[] Elemento;
	int index;
	
	@Override
	public void inicializar() {
		
		Elemento = new elementos[15];
		index = 0;
		
	}

	@Override
	public void acolarPrioridad(int x, int y) {
		// X = VALOR
		// Y = PRIORIDAD
		int indexPrio = index;
		elementos Element = new elementos();
		Element.numero = x;
		Element.prioridad = y;
		
		// recorremos el array buscando una prioridad mas alta que Y
		for (int i=0; i<=index-1; i++) {
			
			if (Elemento[i].prioridad > y) {
				 indexPrio = i;
				 break;
			}
		}
		//si no encontramos ninguna prioridad mayor a la de Y, acolamos al final
		if (indexPrio == index) {
			Elemento[index] = Element;
			index++;
		}
		
		//si encontramos una prioridad mayor, procedemos a mover todas los elementos 1 casila a la derecha
		else {
			for (int j=index; j>indexPrio; j--) {
				Elemento[j] = Elemento[j-1]; 
			}
			//insertamos el nuevo elemento en su posicion correpondiente
			Elemento[indexPrio] = Element;
			index++;
		}
		
	}

	@Override
	public void desacolar() {
		index--;
		
	}

	@Override
	public int primero() {
		
		return Elemento[index-1].numero;
	}

	@Override
	public int prioridad() {


		return Elemento[index-1].prioridad;
	}

	@Override
	public boolean colaVacia() {
		
		return (index == 0);
	}


	@Override
	public void printArray() {
		System.out.print("[");
		for (int i=0; i<Elemento.length; i++) {
			if (Elemento[i] != null){
				System.out.print(Elemento[i].numero+","+Elemento[i].prioridad+" - ");
			}
		}
		System.out.print("]");
		System.out.println();
	}

	@Override
	public void invertirCola() {
		int indexInverso = index-1;
		int i=0;
		while(i<=indexInverso) {
			elementos aux = Elemento[i];
			Elemento[i] = Elemento[indexInverso];
			Elemento[indexInverso] = aux;
			indexInverso--;
			i++;
					
		}
	}
	
}
