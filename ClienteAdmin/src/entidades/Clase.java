package entidades;

import java.util.Date;

public class Clase {
	private int profesor_ID;
    private String nombre_alumno;
    private String tlfno_alumno;
	private int horaIni;
	private int horaFin;
	private String tipo_clase;
	private int modalidad_ID;
	private String modalidad;
	private String zona_clase;
	private int numero_alumnos;
	private int clase_ID;
	private String fecha;
	private String estado;
	private String nombre_profesor;
	

	


	public Clase(int profesor_ID, String nombre_alumno, String tlfno_alumno, int horaIni, int horaFin, String tipo_clase, int modalidad_ID, String zona_clase,
			int numero_alumnos,String fecha, String estado) {
		this.profesor_ID = profesor_ID;
		this.nombre_alumno = nombre_alumno;
		this.tlfno_alumno = tlfno_alumno;
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.tipo_clase = tipo_clase;
		this.modalidad_ID = modalidad_ID;
		this.zona_clase = zona_clase;
		this.numero_alumnos = numero_alumnos;
		this.fecha = fecha;
		this.estado = estado;
	}
	

	public Clase(int clase_ID, String nombre_profesor, String nombre_alumno, String tlfno_alumno, int horaIni, int horaFin, String tipo_clase, int modalidad_ID, String zona_clase,
			int numero_alumnos, String fecha, String estado) {
		this.nombre_profesor = nombre_profesor;
		this.nombre_alumno = nombre_alumno;
		this.tlfno_alumno = tlfno_alumno;
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.tipo_clase = tipo_clase;
		this.modalidad_ID = modalidad_ID;
		this.zona_clase = zona_clase;
		this.numero_alumnos = numero_alumnos;
		this.clase_ID = clase_ID;
		this.fecha = fecha;
		this.estado = estado;
	}
	
	public Clase(int clase_ID, String nombre_profesor, String nombre_alumno, String tlfno_alumno, int horaIni, int horaFin, String tipo_clase, String modalidad, String zona_clase,
			int numero_alumnos, String fecha, String estado) {
		this.nombre_profesor = nombre_profesor;
		this.nombre_alumno = nombre_alumno;
		this.tlfno_alumno = tlfno_alumno;
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.tipo_clase = tipo_clase;
		this.modalidad = modalidad;
		this.zona_clase = zona_clase;
		this.numero_alumnos = numero_alumnos;
		this.clase_ID = clase_ID;
		this.fecha = fecha;
		this.estado = estado;
	}
	




	public int getProfesor_ID() {
		return profesor_ID;
	}

	public void setProfesor_ID(int profesor_ID) {
		this.profesor_ID = profesor_ID;
	}
	public String getNombre_alumno() {
		return nombre_alumno;
	}
	public void setNombre_alumno(String nombre_alumno) {
		this.nombre_alumno = nombre_alumno;
	}
	public String getTlfno_alumno() {
		return tlfno_alumno;
	}
	public void setTlfno_alumno(String tlfno_alumno) {
		this.tlfno_alumno = tlfno_alumno;
	}
	public int getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(int horaIni) {
		this.horaIni = horaIni;
	}
	
	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	public String getTipo_clase() {
		return tipo_clase;
	}

	public void setTipo_clase(String tipo_clase) {
		this.tipo_clase = tipo_clase;
	}
	
	public int getModalidad_ID() {
		return modalidad_ID;
	}

	public void setModalidad_ID(int modalidad_ID) {
		this.modalidad_ID = modalidad_ID;
	}

	public String getModalidad() {
		return modalidad;
	}


	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}


	public String getZona_clase() {
		return zona_clase;
	}

	public void setZona_clase(String zona_clase) {
		this.zona_clase = zona_clase;
	}

	public int getNumero_alumnos() {
		return numero_alumnos;
	}
	public void setNumero_alumnos(int numero_alumnos) {
		this.numero_alumnos = numero_alumnos;
	}

	public int getClase_ID() {
		return clase_ID;
	}

	public void setClase_ID(int clase_ID) {
		this.clase_ID = clase_ID;
	}

	public String getNombre_profesor() {
		return nombre_profesor;
	}

	public void setNombre_profesor(String nombre_profesor) {
		this.nombre_profesor = nombre_profesor;
	}

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Clase [clase_ID="+ clase_ID+"profesor_ID=" + profesor_ID + ", nombre_alumno=" + nombre_alumno + ", tlfno_alumno="
				+ tlfno_alumno + ", horaIni=" + horaIni + ", tipo_clase=" + tipo_clase + ", modalidad=" + modalidad
				+ ", zona_clase=" + zona_clase + ", numero_alumnos=" + numero_alumnos + ", clase_ID=" + clase_ID
				+ ", fecha=" + fecha + ", estado=" + estado + ", nombre_profesor=" + nombre_profesor + "]";
	}
	
	

}
