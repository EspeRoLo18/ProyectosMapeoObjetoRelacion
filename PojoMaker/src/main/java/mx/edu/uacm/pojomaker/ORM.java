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
import org.springframework.beans.factory.annotation.Autowired;

public class ORM {
	
	private static final Logger log = 
			LogManager.getLogger(ORM.class);
	
	@Autowired
	private DataSource dataSource;
	
	DatabaseMetaData md;
	
	
	public ORM() {
		
	}
	
	/**
	 * Regresa falso en caso de no crear la carpeta,
	 * verdadero en caso contrario.
	 * @return
	 */
	public boolean generaCarpeta() {
		File carpeta = new File("SalidaMapeo/");
		if (carpeta.mkdirs()) {
			log.debug("Carpeta creada");
			return true;
		} else {
			log.error("Error al crear carpeta");
			return false;
		}
	}
	
	/**
	 * Regresa DatabaseMetaData de la conexion
	 * @return
	 * @throws SQLException
	 */
	public DatabaseMetaData generaMetadataDB() throws SQLException {
		md = dataSource.getConnection().getMetaData();
		return md;
	}
	/**
	 * Regresa falso en caso de error y verdadero en caso contrario.
	 * @param tablaNombre
	 * @param cabecera
	 * @param atributos
	 * @param getters_and_setters
	 * @param fin_archivo
	 * @return
	 */
	public boolean guardarDatosEnArchivo(
			String tablaNombre, 
			String cabecera, 
			String atributos, 
			String getters_and_setters, 
			String fin_archivo
	) {
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
			return true;
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	    	log.error(e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Regresa verdadero en caso de exito, en caso de tener 
	 * alguna falla regresa falso.
	 * @return
	 * @throws SQLException
	 */
	public boolean generaClases() throws SQLException {
		//Obteniendo tablas
		String[] tipos = {
				"TABLE"	
				};	
		ResultSet tablas = md.getTables("hibernate",  null, "%", tipos);//(null, null, "%", null);
		
		String cabecera = "import javax.persistence.Entity;\n"
				+ "import javax.persistence.Column;\n"
				+ "import javax.persistence.Id;\n\n"
				+ "@Entity\n";
		//Recorriendo tabla por tabla
		while(tablas.next()) {
			String tablaNombre = tablas.getString(3);
			cabecera += "public class " + tablaNombre + " {\n\n";
			String atributos = "";
			String getters_and_setters = "";
			//Obteniendo columnas de la tabla en curso
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
				}
			}
			String fin_archivo = "}";
			//log.debug("archivo de la tabla: " + tablaNombre + "\n\n" + cabecera + atributos + getters_and_setters + fin_archivo);
			//Guardando clase en archivo
			boolean correcto = guardarDatosEnArchivo(tablaNombre, cabecera, atributos, getters_and_setters, fin_archivo);
			if (!correcto) {
				return false;
			}
			cabecera = "import javax.persistence.Entity;\n"
					+ "import javax.persistence.Column;\n"
					+ "import javax.persistence.Id;\n\n"
					+ "@Entity\n";
		}
		return true;
	}
	
}
