import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class test {

	@Column
	private String name;

	@Column
	private boolean datobool;

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public boolean getdatobool() {
		return datobool;
	}

	public void setdatobool(boolean datobool) {
		this.datobool = datobool;
	}

}