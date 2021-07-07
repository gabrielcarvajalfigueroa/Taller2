package dominio;
import logica.*;

public class EnVenta {
	private ListaVehiculos lista;
	
	public EnVenta(int cant) {
		lista = new ListaVehiculos(cant);
	}

	public ListaVehiculos getListaVehiculos() {
		return lista;
	}

	public void setLista(ListaVehiculos lista) {
		this.lista = lista;
	}
	
}
