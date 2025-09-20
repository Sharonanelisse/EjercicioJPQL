package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Elimina un libro buscándolo por título exacto con JPQL.
Restricciones:
- Usa getSingleResult() o maneja lista vacía.
- Transacción obligatoria.
*/
public class Activity03_EliminarLibroPorTitulo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: SELECT l FROM Libro l WHERE l.titulo = :titulo -> remove

            Libro libro = entityManager.createQuery(
                    "SELECT l FROM Libro l WHERE l.titulo = :titulo",
                    Libro.class)
                    .setParameter("titulo","El Principito")
                    .getSingleResult();
            entityManager.remove(libro);

            entityManager.getTransaction().commit();
            System.out.println("Libro eliminado " + libro);
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}