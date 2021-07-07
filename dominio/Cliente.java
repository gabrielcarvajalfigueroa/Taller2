package dominio;
import logica.*;

public class Cliente {
	private String rut;
	private String nombre;
	private String contrasena;
	private double saldo;
	private ListaVehiculos lista;
	
	public Cliente(String rut,String nombre,String contrasena,double saldo,int cant) {
		this.rut = rut;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.saldo = saldo;
		lista = new ListaVehiculos(cant);
	}
	public ListaVehiculos getListaVehiculos() {
		return lista;
	}
	public void setListaVehiculos(ListaVehiculos lista) {
		this.lista = lista;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public void setLista(ListaVehiculos lista) {
		this.lista = lista;
	}
	@Override
	public String toString() {
		return "rut:" + rut + ", nombre:" + nombre + ", saldo:" + saldo;
	}
	
}
