package mx.edu.uacm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mx.edu.uacm.dominio.Usuario;

public class Main {
	
	public static void main(String args[]) {
		 EntityManagerFactory factory = Persistence.createEntityManagerFactory("FProyectoJPA");	
		 EntityManager em = factory.createEntityManager();
	
		 //crear una transaccion
		 em.getTransaction().begin();
		 
		 //Construir un objeto
		 Usuario usr1 = new Usuario();
		 //usr1.setId(1L);
		 usr1.setNombreUsuario("Hugo");
		 usr1.setMensajeUsuario("Hola Baby");
		 
		 em.persist(usr1);
		 
		 //Commit en la tranzsaccion(esto no se hace normalmente)
		 em.getTransaction().commit();
		 
		 //Cerrar el entitymanager
		 em.close();
		 }
}
