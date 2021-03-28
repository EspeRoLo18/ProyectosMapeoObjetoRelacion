import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
public class test {

	@Column
	private String name;

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

}