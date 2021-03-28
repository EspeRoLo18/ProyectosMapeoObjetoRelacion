import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class dulces {

	@Id
	@Column
	private int id;

	@Column
	private int cantidad;

	@Column
	private String descripcion;

	@Column
	private double descuento;

	@Column
	private String imagen;

	@Column
	private String marca;

	@Column
	private String nombre;

	@Column
	private double precio;

	@Column
	private int categoria_id;

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public int getcantidad() {
		return cantidad;	}

	public void setcantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getdescripcion() {
		return descripcion;
	}

	public void setdescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getdescuento() {
		return descuento;
	}

	public void setdescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getimagen() {
		return imagen;
	}

	public void setimagen(String imagen) {
		this.imagen = imagen;
	}

	public String getmarca() {
		return marca;
	}

	public void setmarca(String marca) {
		this.marca = marca;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public double getprecio() {
		return precio;
	}

	public void setprecio(double precio) {
		this.precio = precio;
	}

	public int getcategoria_id() {
		return categoria_id;	}

	public void setcategoria_id(int categoria_id) {
		this.categoria_id = categoria_id;
	}

}