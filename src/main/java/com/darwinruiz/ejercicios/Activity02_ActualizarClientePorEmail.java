package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para encontrar un cliente por email
y actualizar su nombre y ciudad.
Restricciones:
- Maneja transacción.
- Si no encuentra, muestra un mensaje.
*/
public class Activity02_ActualizarClientePorEmail {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: obtener cliente por NamedQuery, modificar campos y merge

            Cliente clienteAlex = entityManager
                    .createNamedQuery("Cliente.buscarPorEmail",Cliente.class)
                    .setParameter("email","alex@gmail.com")
                    .getSingleResult();

            clienteAlex.setNombre("Eddy Alexander");
            clienteAlex.setCiudad("Guatemala");

            entityManager.getTransaction().commit();
            System.out.println("Cliente actualizado " + clienteAlex);
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}