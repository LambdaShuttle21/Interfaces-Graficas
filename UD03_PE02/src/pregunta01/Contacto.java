package pregunta01;

public class Contacto {

	private static int contador = 0;

	private int id;
	private String nombre;
	private String telefono;

	public Contacto(String nombre, String telefono) {
		contador++;
		this.id = contador;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String mostrarInfo() {
		return "ID: " + id + " | Nombre: " + nombre + " | Telefono: " + telefono;
	}
}
