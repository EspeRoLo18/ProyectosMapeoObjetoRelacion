package mx.edu.uacm.primerproyectohibernate.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class usuario {

	@Id //La notacion id va asociada, es decir, arriba del atributo que sera el primary key en la base de datos. (estaba asociada al nombre del usuario, la cambie al id)
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Con esta notación se genera un id incremental ;)
	private long id;
	
	private String nombreUsuario;
	private String mensajeUsuario;

	public usuario() {

	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getMensajeUsuario() {
		return mensajeUsuario;
	}

	public void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario = mensajeUsuario;
	}

}
