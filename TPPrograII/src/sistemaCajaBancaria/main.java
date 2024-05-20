package sistemaCajaBancaria;
//Importamos Scanner para leer inputs
import java.util.Scanner;

import sistemaCajaBancaria.ColaPrioridadTDA.elementos;

public class main {

	public static void main(String[] args) {
		//Inicializamos un objeto de la clase scanner para leer inputs 
		Scanner scanner = new Scanner(System.in);
		
		//Inicializamos la cola con prioridad que modela la caja de una entidad bancaria
		ColaPrioridadTDA caja = new ColaPrioridadTDA();
		caja.inicializar();
		//Inicializamos un conjunto que sirve mas adelante para verificar que no se repitan IDs, y para validar datos
		ConjuntoTDA conjuntoIDs = new ConjuntoTDA();
		conjuntoIDs.inciarConjunto();
		
		//Iniciamos el bucle para ingresar, al menos, 15 usuarios a la caja. Nos valemos de un objeto de la clase Scanner para leer los inputs por teclado
		//Se establece el ingreso de -1 en cualquiera de los 2 campos para finalizar la carga de clientes a la cola
		System.out.println();
		System.out.println("(Para finalizar la carga, ingresé -1 en cualquier campo.)");
		System.out.println();
		int flag = 0;
		int cantidad = 0;
		for(int i=1; i<=15; i++) {
			//flag sirve para terminar la carga en una situación específica (ver mas abajo)
			if(flag==1) {break;}
			System.out.println("------------------------------");
			System.out.println("Cargando cliente número "+i);
			
			//SOLICITAMOS ID
			System.out.println("Ingresá tu ID: ");
			int id = scanner.nextInt();
			
			//verificamos que no se haya ingresado -1
			if(id == -1) {
				//Si se ingresó -1 terminamos el bucle
				System.out.println("Carga finalizada.");
				break;
			}
			else {
				//chequeamos que el ID no se encuentre ya en el conjunto de IDs (que no se repita el ID)
				while(true) {
				//Si el ID esta ocupado, hacemos un bucle que se repita hasta que se ingrese uno válido.
				if(conjuntoIDs.pertenece(id) == true) {
					System.out.println("Esa ID ya se encuentra en la caja esperando, Ingrese otra.");
					System.out.println("Ingresá tu ID: ");
					id = scanner.nextInt();
					}
				//Si el ID no se encuentra ocupado, terminamos el bucle.
				else {
					conjuntoIDs.agregar(id);
					//System.out.println(conjuntoIDs.getArray());
					break;
					}
				}
			}
			
			//SOLICITAMOS PRIORIDAD
			System.out.println("Ingresá 1 si sos un cliente de tipo empresa, 2 si sos un cliente particular, o 3 si sos un particular no cliente: ");
			int prioridad = scanner.nextInt();
			//verificamos que no se haya ingresado -1
			if(prioridad == -1) {
				//Si se ingresó -1 terminamos el bucle
				System.out.println("Carga finalizada.");
				break;
			}
			else {
				//Verificamos que se haya ingresado un numero entre 1 y 3
				while(true) {
					if(prioridad == -1) {
						System.out.println("Carga finalizada.");
						//sirve para terminar todo el bucle de carga
						flag = 1;
						break;
					}
					else if(prioridad<1 || prioridad>3) {
					//Si prioridad no está en el rango permitido, pedimos que ingrese otra vez hasta que se haga bien
						System.out.println("Ese tipo de cliente no existe, solo puede ser 1, 2 o 3.");
						System.out.print("Ingrese su tipo de cliente correspondiente: ");
						prioridad = scanner.nextInt();
					}
					else {
						caja.acolarPrioridad(id, prioridad);
						System.out.print("Estado actual de la cola: ");
						caja.printArray();
						break;
					}
				}
			}
			cantidad++;
			System.out.println("------------------------------");
			System.out.println();
		}
		System.out.println("--------------");
		
		//Inicializamos conjunto para devolver los clientes atendidos según su tipo
		ConjuntoTDA ConjuntoEmpresa = new ConjuntoTDA();
		ConjuntoEmpresa.inciarConjunto();
		ConjuntoTDA ConjuntoParticularCliente = new ConjuntoTDA();
		ConjuntoParticularCliente.inciarConjunto();
		ConjuntoTDA ConjuntoParticularNoCliente = new ConjuntoTDA();
		ConjuntoParticularNoCliente.inciarConjunto();
		
		//Damos vuelta la cola
		caja.invertirCola();
		//caja.printArray();
		
		//Simulación de que atendemos la caja
		System.out.println("¿Cuantos clientes vas a atender de los "+cantidad+"?");
		int cantidadClientes = scanner.nextInt();
		cantidadClientes--;
		//Loop para atender clientes
		for(int i=0; i<=cantidadClientes; i++) {
			//obtenemos ID del primer cliente de la cola
			int clienteID = caja.primero();
			//obtenemos el tipo de cliente del primero en la cola
			int clientePrioridad = caja.prioridad();
			//Mostramos por pantalla
			System.out.println("Atendiendo a:");
			System.out.println("cliente:"+clienteID+", Prioridad:"+clientePrioridad);
			System.out.println();
			//Una vez atendido, lo sacamos de la cola
			caja.desacolar();
			
			//Vemos que tipo de cliente es para saber a que conjunto agregarlo, y lo agregamos 
			switch(clientePrioridad) {
			case 1:
				ConjuntoEmpresa.agregar(clienteID);
				break;
			case 2:
				ConjuntoParticularCliente.agregar(clienteID);
				break;
			case 3:
				ConjuntoParticularNoCliente.agregar(clienteID);
				break;
			}
		}
		
		//Mostramos los conjuntos de clientes atendidos por tipo
		System.out.println("Los clientes de tipo EMPRESA que ya fueron atendidos, son los siguientes: ");
		System.out.println(ConjuntoEmpresa.getArray());
		//Clientes particulares:
		System.out.println("Los clientes de tipo CLIENTE PARTICULAR que ya fueron atendidos, son los siguientes: ");
		System.out.println(ConjuntoParticularCliente.getArray());
		//Particulares NO clientes:
		System.out.println("Los clientes de tipo PARTICULAR NO CLIENTE que ya fueron atendidos, son los siguientes: ");
		System.out.println(ConjuntoParticularNoCliente.getArray());
		
	}

}
