package sistemaCajaBancaria;
import java.util.Arrays;

public class ConjuntoTDA implements ConjuntoInterface{
    int[] array;
    int cant;

    
    @Override
    public String getArray() {
    	String array_n = Arrays.toString(array);
    	return array_n;
    }
    
    @Override
    public void inciarConjunto() {
        array = new int[15];
        cant = 0;
    }
    
    @Override
    public void agregar(int x) {
        if (!this.pertenece(x)) {
            array[cant] = x;
            cant++;
        }
    }
    
    @Override
    public void sacar(int x) {
        int i = 0;
        while (i < cant && array[i] != x) {
            i++;
        }
        if (i < cant) {
            array[i] = array[cant - 1];
            cant--;
        }
    }
    
    @Override
    public int elegir() {
        return array[cant - 1];
    }
    
    @Override
    public boolean pertenece(int x) {
        int i = 0;
        while (i < cant && array[i] != x) {
            i++;
        }
        return (i < cant);
    }

    @Override
    public boolean estaVacio() {
        return (cant == 0);
    }

}
