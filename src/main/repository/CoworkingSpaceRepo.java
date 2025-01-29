package repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.CoworkingSpace;

import java.util.List;

public class CoworkingSpaceRepo {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("coworking");

    public List<CoworkingSpace> getSpaces() {
        EntityManager em = emf.createEntityManager();
        List<CoworkingSpace> spaces = em.createQuery("SELECT s FROM CoworkingSpace s", CoworkingSpace.class).getResultList();
        em.close();
        return spaces;
    }

    public void saveSpace(CoworkingSpace space) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(space);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteSpace(int space) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Integer spaceToRemove = em.merge(space);
        em.remove(spaceToRemove);
        em.getTransaction().commit();
        em.close();
    }
}
