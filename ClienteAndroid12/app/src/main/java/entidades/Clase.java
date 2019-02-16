package entidades;

public class Clase {
    private String nombre_alumno;
    private String tlfno_alumno;
    private int horaIni;
    private int horaFin;
    private int numero_alumnos;
    private String zona_clase;
    private String estado;





    public Clase(String nombre_alumno, String tlfno_alumno, int horaIni, int horaFin, int numero_alumnos, String zona_clase,
                 String estado) {
        this.nombre_alumno = nombre_alumno;
        this.tlfno_alumno = tlfno_alumno;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.numero_alumnos = numero_alumnos;
        this.zona_clase = zona_clase;
        this.estado = estado;
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

    public int getHoraFin(){ return horaFin;}
    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getNumero_alumnos(){ return numero_alumnos; }
    public void setNumero_alumnos(int numero_alumnos){ this.numero_alumnos = numero_alumnos; }

    public String getZona_clase() {
        return zona_clase;
    }
    public void setZona_clase(String zona_clase) {
        this.zona_clase = zona_clase;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Clase [nombre_alumno=" + nombre_alumno + ", tlfno_alumno="
                + tlfno_alumno + ", horaIni=" + horaIni + ", horaFin=" + horaFin + ", numero_alumnos=" + numero_alumnos + ", zona_clase=" + zona_clase + ", estado=" + estado + "]";
    }



}
