package logica;

public interface Sistema {
	
	public boolean ingresarCliente(String rut,String nombre,String contrasena,int saldo);
	
	public boolean ingresarAuto(String modelo,String patente,int precio,int ano,double rendimiento);
	
	public void asociarVehiculoCliente(String rut,String patente);
	
	public void asociarVehiculoEnVenta(String patente);
	
	public String obtenerDatosCliente(String rut);
	
	public void agregarSaldo(String rut,int monto);
	
	public boolean buscarCliente(String rut, String contrasena);
	
	public void cambiarContrasena(String rut,String contrasenaNueva);
	
	public String obtenerVehiculosCliente(String rut);
	
	public double obtenerPrecioVenta(String rut, String patente);
	
	public boolean venderVehiculo(String rut, String patente);
	
	public String obtenerVehiculosEnVenta();
	
	public boolean comprarVehiculo(String rut, String patente);
	
	public String informacionCliente(String rut);
	
	public String vehiculosEnVenta();
	
	public String futurasGanancias();
	
	public String obtenerDatosDeVehiculos();
	
	public String obtenerDatosDeClientes();
}
