package mx.edu.uacm.primerproyectohibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

//import com.mysql.cj.xdevapi.SessionFactory;

import mx.edu.uacm.primerproyectohibernate.dominio.usuario;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	try {
	    	//codigo legado
	    	//SessionFactory sf = new Configuration()
	    	
	    	//Creamos un registro estandar atraves de un builder
	    	StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
	    	
	    	//Creamos la sesion factory
	    	SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
	    	
	    	//abrimos la sesion
	    	Session session = sf.openSession();
	    	
	    	//Creamos la entidad
	    	usuario usr = new usuario();
	    	//usr.setId(1);
	    	usr.setNombreUsuario("paco");
	    	
	    	usuario usr2 = new usuario();
	    	//usr.setId(2);
	    	usr2.setNombreUsuario("hugo");
	    	
	    	usuario usr3 = new usuario();
	    	//usr.setId(3);
	    	usr3.setNombreUsuario("Luis");
	    	
	    	//Creamos una transaccion
	    	session.getTransaction().begin();
	    	
	    	session.persist(usr);
	    	session.persist(usr2);
	    	session.persist(usr3);
	    	
	    	session.getTransaction().commit();
	    	
	    	//se cierra la transaccion
	    	session.close();
	    	sf.close();
    
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	
    }
}
