package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tallerista database table.
 * 
 */
@Entity
@NamedQuery(name="Tallerista.findAll", query="SELECT t FROM Tallerista t")
public class Tallerista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String correo;

	private String descripcion;

	private String nombre;

	private String telefono;

	//bi-directional many-to-many association to Pilar
	@ManyToMany(mappedBy="talleristas")
	private List<Pilar> pilars;

	public Tallerista() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Pilar> getPilars() {
		return this.pilars;
	}

	public void setPilars(List<Pilar> pilars) {
		this.pilars = pilars;
	}

}