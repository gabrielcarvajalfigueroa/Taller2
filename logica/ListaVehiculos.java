package logica;
import dominio.*;

public class ListaVehiculos {
	private Vehiculo[] lista;
	private int cant;
	private int max;
	
	public ListaVehiculos(int max) {
		lista = new Vehiculo[max];
		cant = 0;
		this.max = max;
	}
	public boolean ingresar(Vehiculo vehiculo) {
		if(cant < max) {
			lista[cant] = vehiculo;
			cant++;
			return true;
		}
		return false;
	}
	public Vehiculo buscarPorI(int i) {
		if(i >= 0 && i < cant) {
			return lista[i];
		}
		return null;
	}
	public Vehiculo buscar(String patente) {
		int i;
		for(i = 0; i < cant; i++) {
			if(lista[i].getPatente().equals(patente)) {
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
	public boolean eliminar(String patente) {
		int i;
		for(i = 0; i<cant;i++){
			if(lista[i].getPatente().equals(patente)){
				break;
			}
		}
		if(i==cant){
			return false;
		}
		else{
			for(int j=i; j<cant-1;j++){
				lista[j] = lista[j+1];
			}
			cant--;
			return true;
		}
	}
	public Vehiculo[] getLista() {
		return lista;
	}
	public void setLista(Vehiculo[] lista) {
		this.lista = lista;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
}
