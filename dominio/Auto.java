package dominio;

public class Auto extends Vehiculo{
	private double rendimiento;
	private static int costovariable = 77;
	private static int revisiontecnica = 7950;
	
	public Auto(String modelo,String patente,int precio,int ano,double rendimiento) {
		super(modelo,patente,precio,ano);
		this.rendimiento = rendimiento;
	}

	public double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

	public static int getCostovariable() {
		return costovariable;
	}

	public static void setCostovariable(int costovariable) {
		Auto.costovariable = costovariable;
	}

	public static int getRevisiontecnica() {
		return revisiontecnica;
	}

	public static void setRevisiontecnica(int revisiontecnica) {
		Auto.revisiontecnica = revisiontecnica;
	}
	
}
