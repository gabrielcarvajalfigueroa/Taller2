package dominio;

public class Vehiculo {
		private String modelo;
		private String patente;
		private int precio;
		private int ano;
		
		protected Vehiculo(String modelo, String patente,int precio, int ano) {
			this.modelo = modelo;
			this.patente = patente;
			this.precio = precio;
			this.ano = ano;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getPatente() {
			return patente;
		}

		public void setPatente(String patente) {
			this.patente = patente;
		}

		public int getPrecio() {
			return precio;
		}

		public void setPrecio(int precio) {
			this.precio = precio;
		}

		public int getAno() {
			return ano;
		}

		public void setAno(int ano) {
			this.ano = ano;
		}

		@Override
		public String toString() {
			return "modelo:" + modelo + ", patente:" + patente + ", precio:" + precio + ", año:" + ano + "]";
		}
		
		
		

}
