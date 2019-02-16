package entidades;

public class Factura {
	private int clase_ID;
	private String fecha;
	private String tipo_clase;
	private double importe_persona;
	private double importe_total;
	
	public Factura(int clase_ID, String fecha, String tipo_clase, double importe_persona,
			double importe_total) {
		this.clase_ID = clase_ID;
		this.fecha = fecha;
		this.tipo_clase = tipo_clase;
		this.importe_persona = importe_persona;
		this.importe_total = importe_total;
	}

	public int getClase_ID() {
		return clase_ID;
	}

	public void setClase_ID(int clase_ID) {
		this.clase_ID = clase_ID;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo_clase() {
		return tipo_clase;
	}

	public void setTipo_clase(String tipo_clase) {
		this.tipo_clase = tipo_clase;
	}

	public double getImporte_persona() {
		return importe_persona;
	}

	public void setImporte_persona(double importe_persona) {
		this.importe_persona = importe_persona;
	}

	public double getImporte_total() {
		return importe_total;
	}

	public void setImporte_total(double importe_total) {
		this.importe_total = importe_total;
	}
	
	
	
	
	

}
