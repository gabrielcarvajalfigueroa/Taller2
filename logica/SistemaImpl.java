package logica;
import dominio.*;

public class SistemaImpl implements Sistema{
	public ListaClientes lc;
	public ListaVehiculos lv;
	public EnVenta enventa;
	
	public SistemaImpl() {
		this.lc = new ListaClientes(1000);
		this.lv = new ListaVehiculos(1000);
		this.enventa = new EnVenta(1000);
	}
	
	public boolean ingresarCliente(String rut,String nombre,String contrasena,int saldo) {
		Cliente c = new Cliente(rut,nombre,contrasena,saldo,100);
		
		boolean ingreso = lc.ingresar(c);
		
		return ingreso;
	}
	
	public boolean ingresarAuto(String modelo,String patente,int precio,int ano,double rendimiento) {
		Vehiculo a = new Auto(modelo,patente,precio,ano,rendimiento);
		
		boolean ingreso = lv.ingresar(a);
		
		return ingreso;	
	}
	
	public void asociarVehiculoCliente(String rut,String patente) {
		Vehiculo v = lv.buscar(patente);
		Cliente c = lc.buscar(rut);
		
		if( v != null && c != null) {
			c.getListaVehiculos().ingresar(v);
		}
		else {
			throw new NullPointerException("No existe cliente y/o dueño");
		}
	}
	
	public void asociarVehiculoEnVenta(String patente) {
		Vehiculo v = lv.buscar(patente);
		
		if(v != null) {
			enventa.getListaVehiculos().ingresar(v);
		}
		else {
			throw new NullPointerException("No existe el vehiculo");
		}
	}
	
	public String obtenerDatosCliente(String rut) {
		Cliente c = lc.buscar(rut);
		
		String r = "rut: " + c.getRut() + ", nombre: " + c.getNombre() + ", saldo: " + c.getSaldo() + "\n";
		
		String vehiculos = "";
		
		for(int i = 0; i < c.getListaVehiculos().getCant(); i++) {
			Vehiculo v = c.getListaVehiculos().buscarPorI(i);
			if(v instanceof Auto) {
				vehiculos = vehiculos + "placa: " + v.getPatente() + ", tipo: " + "Auto." + "\n";
			}
			else {
				vehiculos = vehiculos + "placa: " + v.getPatente() + ", tipo: " + "Motocicleta." + "\n";
			}
		}
		
		return r + vehiculos; 
		
	}
	
	public void agregarSaldo(String rut,int monto) {
		Cliente c = lc.buscar(rut);
		
		c.setSaldo(c.getSaldo() + monto);
	}
	
	public boolean buscarCliente(String rut,String contrasena) {
		Cliente c = lc.buscar(rut);
		
		if(c.getContrasena().equals(contrasena)) {
			return true;
		}
		return false;
	}
	
	public void cambiarContrasena(String rut,String contrasenaNueva) {
		Cliente c = lc.buscar(rut);
		
		c.setContrasena(contrasenaNueva);
	}
	
	public String obtenerVehiculosCliente(String rut) {
		String salida = "\nVehiculos del cliente " + rut + "\n";
		Cliente c = lc.buscar(rut);
		int cont = 1;
		if(c != null) {
			for(int i = 0; i < c.getListaVehiculos().getCant(); i++) {
				Vehiculo v = c.getListaVehiculos().buscarPorI(i);
				salida = salida + "Datos vehiculo "  + cont + " " + v.toString() + "\n";
				cont++;		
			}
		}
		else {
			throw new NullPointerException("No existe el cliente");
		}
		return salida;
	}
	
	public double obtenerPrecioVenta(String rut, String patente) {
		Cliente c = lc.buscar(rut);
		Vehiculo v = c.getListaVehiculos().buscar(patente);
		double pv = 0;
		
		if(c != null && v != null) {
			if(v instanceof Auto) {
				Auto a = (Auto) v;
				pv = (a.getPrecio()) / ((Auto.getCostovariable() + a.getRendimiento()) / 100 );
			}
			else if(v instanceof Motocicleta) {
				Motocicleta m = (Motocicleta) v;
				pv = (m.getPrecio() * 0.87) - Motocicleta.getRevisiontecnica();
			}
		}
		else {
			throw new NullPointerException("No existe el cliente y/o rut");
		}
		return pv;
	}
	
	public boolean venderVehiculo(String rut, String patente) {
		Cliente c = lc.buscar(rut);
		Vehiculo v = c.getListaVehiculos().buscar(patente);
		
		if(c != null && v != null) {
			enventa.getListaVehiculos().ingresar(v);
			double pv = obtenerPrecioVenta(rut,patente);
			c.getListaVehiculos().eliminar(patente);
			c.setSaldo(c.getSaldo() + pv);
			
			return true;
		}
		else {
			throw new NullPointerException("Cliente y/o vehiculo no existe");
		}
	}
	
	public String obtenerVehiculosEnVenta() {
		String salida = "\nVehiculos En Venta \n";
		int cont = 1;
		
		for(int i = 0; i< enventa.getListaVehiculos().getCant();i++) {
			Vehiculo v = enventa.getListaVehiculos().buscarPorI(i);
			salida = salida + "Datos vehiculo "  + cont + ": " + v.toString() + "\n";
			cont++;
		}
		return salida;
	}
	
	public boolean comprarVehiculo(String rut,String patente) {
		Cliente c = lc.buscar(rut);
		Vehiculo v = enventa.getListaVehiculos().buscar(patente);
		double pc = 0;
		
		if(c == null || v == null) {
			throw new NullPointerException("Cliente y/o vehiculo no existen");
		}
		
		if(v instanceof Auto) {
			Auto a = (Auto) v;
			pc = ( (a.getPrecio()) / (Auto.getCostovariable() / 100)) + Auto.getRevisiontecnica();
		}
		else if(v instanceof Motocicleta) {
			Motocicleta m = (Motocicleta) v;
			pc = (m.getPrecio()) / (Motocicleta.getCostovariable() / 100);
		}
		
		if(c.getSaldo() >= pc) {
			enventa.getListaVehiculos().eliminar(patente);
			c.getListaVehiculos().ingresar(v);
			c.setSaldo(c.getSaldo() + pc);
			return true;
		}
		else {
			throw new NullPointerException("Saldo insuficiente");
		}
	}
	
	public String informacionCliente(String rut) {
		Cliente c = lc.buscar(rut);
		if(c!=null) {
			String salida = "\n Vehiculos del cliente " + rut + "\n";
			int cont = 1;
			
			for(int i = 0; i < c.getListaVehiculos().getCant(); i++) {
				Vehiculo v = c.getListaVehiculos().buscarPorI(i);
				double usd = ((double)v.getPrecio()) / 730;
				salida = salida + "Datos del vehiculo n°" + cont + ". Modelo: " + v.getModelo() + ", precio(USD): " + usd;
				cont++;
			}
			return salida;
		}
		else {
			throw new NullPointerException("El cliente no existe");
		}
	}
	
	public String posibleCompra() {
		int mayor = -1;
		String rut = "";
		
		for(int i = 0; i < lc.getCant(); i++) {
			if(lc.buscarPorI(i).getListaVehiculos().getCant() > mayor) {
				mayor = lc.buscarPorI(i).getListaVehiculos().getCant();
				rut = lc.buscarPorI(i).getRut();
			}
		}
		Cliente c = lc.buscar(rut);
		String salida = "\n Datos de Posible Compra \n";
		for(int j = 0; j < lc.getCant(); j++) {
			if(lc.buscarPorI(j).getListaVehiculos().getCant() == mayor) {
				salida = salida + c.toString() + "\n";
				for(int z = 0; z < c.getListaVehiculos().getCant(); z++) {
					salida = salida + c.getListaVehiculos().buscarPorI(z).toString() + "\n";
					String patente = c.getListaVehiculos().buscarPorI(z).getPatente();
					salida = salida + "Precio Venta " + obtenerPrecioVenta(rut,patente);
				}
			}
		}
		return salida;
	}
	
	public String vehiculosEnVenta() {
		String salida = "\n Vehiculos En Venta \n";
		double pv = 0;
		double pc = 0;
		for(int i = 0; i < enventa.getListaVehiculos().getCant(); i++) {
			Vehiculo v = enventa.getListaVehiculos().buscarPorI(i);
			salida = salida + v.toString() + "\n";
			if(v instanceof Auto) {
				Auto a = (Auto) v;
				pv = (a.getPrecio()) / ((Auto.getCostovariable() + a.getRendimiento()) / 100 );
				pc = ( (a.getPrecio()) / (Auto.getCostovariable() / 100)) + Auto.getRevisiontecnica();
			}
			else if(v instanceof Motocicleta) {
				Motocicleta m = (Motocicleta) v;
				pv = (m.getPrecio() * 0.87) - Motocicleta.getRevisiontecnica();
				pc = (m.getPrecio()) / (Motocicleta.getCostovariable() / 100);
			}
			salida = salida + "Precio Venta: " + pv + ", Precio Compra: " + pc + "\n";
		}
		return salida;
	}
	
	public String futurasGanancias() {
		String salida = "\n Futuras Ganancias \n";
		double pv = 0;
		double pc = 0;
		for(int i = 0; i < enventa.getListaVehiculos().getCant(); i++) {
			Vehiculo v = enventa.getListaVehiculos().buscarPorI(i);
			salida = salida + v.toString() + "\n";
			if(v instanceof Auto) {
				Auto a = (Auto) v;
				pv = (a.getPrecio()) / ((Auto.getCostovariable() + a.getRendimiento()) / 100 );
				pc = ( (a.getPrecio()) / (Auto.getCostovariable() / 100)) + Auto.getRevisiontecnica();
			}
			else if(v instanceof Motocicleta) {
				Motocicleta m = (Motocicleta) v;
				pv = (m.getPrecio() * 0.87) - Motocicleta.getRevisiontecnica();
				pc = (m.getPrecio()) / (Motocicleta.getCostovariable() / 100);
			}
			salida = salida + "Ganancia esperada: " +  (pc - pv) + "\n";
		}
		return salida;
	}
	
	public String obtenerDatosDeVehiculos() {
		String salida = "";
		for(int i = 0; i < lc.getCant(); i++) {
			Cliente c = lc.buscarPorI(i);
			String rut = c.getRut();
			for(int j = 0; j < c.getListaVehiculos().getCant(); j++) {
				Vehiculo v = c.getListaVehiculos().buscarPorI(j);
				String modelo = v.getModelo();
				String patente = v.getPatente();
				int precio = v.getPrecio();
				int ano = v.getAno();
				if(v instanceof Auto) {
					Auto a = (Auto) v;
					String tipo = "Auto";
					double rendimiento = a.getRendimiento();
					salida = salida + rut+ "," + modelo + "," + patente + "," + precio + "," + ano + "," + tipo + "," + rendimiento + "\n";
				}
				else {
					salida = salida + rut+ "," + modelo + "," + patente + "," + precio + "," + ano + ",Motocicleta\n"; 
				}
			}
		}
		for(int b = 0; b < enventa.getListaVehiculos().getCant(); b++) {
			Vehiculo v = enventa.getListaVehiculos().buscarPorI(b);
			String rut = "En venta";
			String modelo = v.getModelo();
			String patente = v.getPatente();
			int precio = v.getPrecio();
			int ano = v.getAno();
			if(v instanceof Auto) {
				Auto a = (Auto) v;
				String tipo = "Auto";
				double rendimiento = a.getRendimiento();
				salida = salida + rut+ "," + modelo + "," + patente + "," + precio + "," + ano + "," + tipo + "," + rendimiento + "\n";
			}
			else {
				salida = salida + rut+ "," + modelo + "," + patente + "," + precio + "," + ano + ",Motocicleta\n"; 
			}
		}
		return salida;
	}
	
	public String obtenerDatosDeClientes() {
		String salida = "";
		
		for(int i = 0; i < lc.getCant(); i++) {
			Cliente c = lc.buscarPorI(i);
			
			String rut = c.getRut();
			String nombre = c.getNombre();
			String contrasena = c.getContrasena();
			int cantVehiculos = c.getListaVehiculos().getCant();
			double saldo = c.getSaldo();
			
			salida = salida + rut + "," + nombre + "," + contrasena + "," + cantVehiculos + "," + saldo + "\n";
		}
		return salida;
	}
}
