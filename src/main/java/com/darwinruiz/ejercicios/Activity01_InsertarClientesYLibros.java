package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
ENUNCIADO:
1) Inserta al menos 3 clientes y 3 libros con datos realistas.
2) Varía fechas (fechaRegistro y fechaPublicacion), genera 1 libro con descripcion = NULL y 1 con stock = 0.
3) Imprime los IDs generados.
Restricciones:
- Usa tipos explícitos (sin var).
- Maneja transacción (begin/commit/rollback).
*/
public class Activity01_InsertarClientesYLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: crear clientes y libros, settear campos y persistir
            // Cliente cliente = new Cliente(); cliente.setNombre("..."); ...

            Cliente clienteAlex = new Cliente();
            clienteAlex.setNombre("Alex");
            clienteAlex.setEmail("alex@gmail.com");
            clienteAlex.setCiudad("Nueva York");
            clienteAlex.setEdad(36);
            clienteAlex.setActivo(true);
            clienteAlex.setFechaRegistro(LocalDate.now().minusDays(21));
            entityManager.persist(clienteAlex);

            Cliente clienteSharon = new Cliente();
            clienteSharon.setNombre("Sharon");
            clienteSharon.setEmail("sharon@gmail.com");
            clienteSharon.setCiudad("Londres");
            clienteSharon.setEdad(27);
            clienteSharon.setActivo(true);
            clienteSharon.setFechaRegistro(LocalDate.now().minusDays(14));
            entityManager.persist(clienteSharon);

            Cliente clienteJacky = new Cliente();
            clienteJacky.setNombre("Jacky");
            clienteJacky.setEmail("jacky@gmail.com");
            clienteJacky.setCiudad("Roma");
            clienteJacky.setEdad(31);
            clienteJacky.setActivo(true);
            clienteJacky.setFechaRegistro(LocalDate.now().minusDays(4));
            entityManager.persist(clienteJacky);


            Libro libroElPrincipito = new Libro();
            libroElPrincipito.setTitulo("El Principito");
            libroElPrincipito.setAutorNombre("Antoine de Saint-Exupéry");
            libroElPrincipito.setGenero("Novela");
            libroElPrincipito.setPrecio(new BigDecimal("45.95"));
            libroElPrincipito.setStock(10);
            libroElPrincipito.setActivo(true);
            libroElPrincipito.setFechaPublicacion(LocalDate.now().minusDays(1945));
            libroElPrincipito.setDescripcion("El Principito es la historia de un piloto que, perdido en el desierto del Sahara, conoce a un niño misterioso que ha viajado desde un pequeño planeta");
            entityManager.persist(libroElPrincipito);

            Libro libroOdisea = new Libro();
            libroOdisea.setTitulo("Odisea");
            libroOdisea.setAutorNombre("Homero");
            libroOdisea.setGenero("Novela");
            libroOdisea.setPrecio(new BigDecimal("35.99"));
            libroOdisea.setStock(0);
            libroOdisea.setActivo(true);
            libroOdisea.setFechaPublicacion(LocalDate.now().minusDays(2004));
            libroOdisea.setDescripcion("Narra el viaje de regreso del héroe Odiseo (Ulises) a su reino de Ítaca tras la Guerra de Troya");
            entityManager.persist(libroOdisea);

            Libro libroElcodigoDaVinci = new Libro();
            libroElcodigoDaVinci.setTitulo("El codigo de Vinci");
            libroElcodigoDaVinci.setAutorNombre("Dan Brown");
            libroElcodigoDaVinci.setGenero("Novela");
            libroElcodigoDaVinci.setPrecio(new BigDecimal("55.60"));
            libroElcodigoDaVinci.setStock(30);
            libroElcodigoDaVinci.setActivo(true);
            libroElcodigoDaVinci.setFechaPublicacion(LocalDate.now().minusDays(1545));
            entityManager.persist(libroElcodigoDaVinci);

            entityManager.getTransaction().commit();

            // TODO: imprimir IDs creados

            System.out.println("Clientes creados: " + clienteAlex.getId() + ", " + clienteSharon.getId() + ", " + clienteJacky.getId());
            System.out.println("Libros creados: " + libroElPrincipito.getId() + ", " + libroOdisea.getId() + ", " + libroElcodigoDaVinci.getId());
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}