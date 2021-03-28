package mx.edu.uacm.pojomaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PojoMakerApplicationTests {
	
	private static final Logger log = 
			LogManager.getLogger(PojoMakerApplicationTests.class);
	
	@Autowired
	DataSource dataSource;
	
	@Test
	void contextLoads() throws SQLException {
		//Creando carpeta
		File carpeta = new File("SalidaMapeo/");
		if (carpeta.mkdirs()) {
			log.debug("Carpeta creada");
		} else {
			log.error("Error al crear carpeta");
		}
		//Generando conexion
		DatabaseMetaData md = 
				dataSource.getConnection().getMetaData();
		//Obteniendo tablas
		String[] tipos = {
			"TABLE"	
		};
		ResultSet rs = md.getTables("hibernate",  null, "%", tipos);//(null, null, "%", null);
		//cabecera de la clase
		String cabecera = "import javax.persistence.Entity;\n"
						+ "import javax.persistence.Column;\n"
						+ "import javax.persistence.Id;\n\n"
						+ "import java.util.Date;\n\n"
						+ "@Entity\n";
		//Recorriendo tabla por tabla
		while(rs.next()) {
			String tablaNombre = rs.getString(3);
			cabecera += "public class " + tablaNombre + " {\n\n";
			String atributos = "";
			String getters_and_setters = "";
			ResultSet columnas = md.getColumns(null, null, tablaNombre, null);
			//Recorriendo columna de la tabla en curso
			while(columnas.next()) {
				String columnaNombre = columnas.getString(4);
				String columnaTipo = columnas.getString(6);
				switch (columnaTipo) {
					case "serial":
						atributos += "	@Id\n";
						atributos += "	@Column\n";
						atributos += "	private int " + columnaNombre + ";\n\n";
						getters_and_setters += "	public int get" + columnaNombre + "() {\n"
											 + "		return " + columnaNombre + ";\n"
											 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(int " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "varchar":
						atributos += "	@Column\n";
						atributos += "	private String " + columnaNombre + ";\n\n";
						getters_and_setters += "	public String get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(String " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "int4":
						atributos += "	@Column\n";
						atributos += "	private int " + columnaNombre + ";\n\n";
						getters_and_setters += "	public int get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(int " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "float8":
						atributos += "	@Column\n";
						atributos += "	private double " + columnaNombre + ";\n\n";
						getters_and_setters += "	public double get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(double " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "float4":
						atributos += "	@Column\n";
						atributos += "	private double " + columnaNombre + ";\n\n";
						getters_and_setters += "	public double get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(double " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "numeric":
						atributos += "	@Column\n";
						atributos += "	private double " + columnaNombre + ";\n\n";
						getters_and_setters += "	public double get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(double " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "bool":
						atributos += "	@Column\n";
						atributos += "	private boolean " + columnaNombre + ";\n\n";
						getters_and_setters += "	public boolean get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(boolean " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "char":
						atributos += "	@Column\n";
						atributos += "	private String " + columnaNombre + ";\n\n";
						getters_and_setters += "	public String get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(String " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "text":
						atributos += "	@Column\n";
						atributos += "	private String " + columnaNombre + ";\n\n";
						getters_and_setters += "	public String get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(String " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "int2":
						atributos += "	@Column\n";
						atributos += "	private int " + columnaNombre + ";\n\n";
						getters_and_setters += "	public int get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(int " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "money":
						atributos += "	@Column\n";
						atributos += "	private double " + columnaNombre + ";\n\n";
						getters_and_setters += "	public double get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(double " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
					case "date":
						atributos += "	@Column\n";
						atributos += "	private Date " + columnaNombre + ";\n\n";
						getters_and_setters += "	public Date get" + columnaNombre + "() {\n"
								 + "		return " + columnaNombre + ";\n"
								 + "	}\n\n";
						getters_and_setters += "	public void set" + columnaNombre + "(Date " + columnaNombre + ") {\n"
											 + "		this." + columnaNombre + " = " + columnaNombre + ";\n"
											 + "	}\n\n";
						break;
				}
			}
			String fin_archivo = "}";
			log.debug("archivo de la tabla: " + tablaNombre + "\n\n" + cabecera + atributos + getters_and_setters + fin_archivo);
			
			//Guardando clase en archivo
			try {
				File file = new File("SalidaMapeo/" + tablaNombre + ".java");
				//Creando archivo
				if (file.createNewFile()) {
					log.debug("File created: " + file.getName());
				} else {
					log.debug("File already exists.");
				}
				//Guardando data en archivo
				FileWriter myWriter = new FileWriter("SalidaMapeo/" + tablaNombre + ".java");
				myWriter.write(cabecera + atributos + getters_and_setters + fin_archivo);
				myWriter.close();
				log.debug("Escritura exitosa");
		    } catch (IOException e) {
		    	System.out.println("An error occurred.");
		    	log.error(e.getMessage());
		    }
			cabecera = "import javax.persistence.Entity;\n"
					+ "import javax.persistence.Column;\n"
					+ "import javax.persistence.Id;\n"
					+ "import java.util.Date;\n\n"
					+ "@Entity\n";
		}
	}

}
