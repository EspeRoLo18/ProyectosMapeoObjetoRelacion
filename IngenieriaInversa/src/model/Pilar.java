package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pilar database table.
 * 
 */
@Entity
@NamedQuery(name="Pilar.findAll", query="SELECT p FROM Pilar p")
public class Pilar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String direccion;

	private String nombre;

	private String telefono;

	//bi-directional many-to-many association to Tallerista
	@ManyToMany
	@JoinTable(
		name="pilar_talleristas"
		, joinColumns={
			@JoinColumn(name="pilar_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="talleristas_id")
			}
		)
	private List<Tallerista> talleristas;

	public Pilar() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public List<Tallerista> getTalleristas() {
		return this.talleristas;
	}

	public void setTalleristas(List<Tallerista> talleristas) {
		this.talleristas = talleristas;
	}

}