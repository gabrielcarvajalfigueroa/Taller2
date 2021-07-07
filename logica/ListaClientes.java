package logica;
import dominio.*;

public class ListaClientes {
	private Cliente[] lista;
	private int cant;
	private int max;
	
	public ListaClientes(int max) {
		lista = new Cliente[max];
		cant = 0;
		this.max = max;
	}
	public boolean ingresar(Cliente cliente) {
		if(cant < max) {
			lista[cant] = cliente;
			cant++;
			return true;
		}
		return false;
	}
	public Cliente buscarPorI(int i) {
		if (i >=0 && i < cant) {
			return lista[i]; 
		}
		else {
			return null;
		}
	}
	public Cliente buscar(String rut) {
		int i;
		for(i = 0; i<cant; i++) {
			if(lista[i].getRut().equals(rut)) {
				break;
			}
		}
		if(i == cant) {
			return null;
		}
		else {
			return lista[i];
		}
	}
	
	public int getCant() {
		return cant;
	}
	
	
	
	
	
	
	
	
	
}
