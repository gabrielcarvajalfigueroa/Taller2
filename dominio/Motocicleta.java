package dominio;

public class Motocicleta extends Vehiculo{
	private static int costovariable = 81;
	private static int revisiontecnica = 8250;
	
	public Motocicleta(String modelo,String patente,int precio,int ano) {
		super(modelo,patente,precio,ano);
	}

	public static int getCostovariable() {
		return costovariable;
	}

	public static void setCostovariable(int costovariable) {
		Motocicleta.costovariable = costovariable;
	}

	public static int getRevisiontecnica() {
		return revisiontecnica;
	}

	public static void setRevisiontecnica(int revisiontecnica) {
		Motocicleta.revisiontecnica = revisiontecnica;
	}
	
}
