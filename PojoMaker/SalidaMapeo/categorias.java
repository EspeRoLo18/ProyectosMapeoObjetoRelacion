import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
public class categorias {

	@Id
	@Column
	private int id;

	@Column
	private String nombre;

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

}