package example.db;

import example.entity.ResultEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.Root;

import java.util.Collection;

/**
 * Implementation of the ResultDAO interface using JPA (Java Persistence API).
 * Handles database operations for ResultEntity objects.
 */
public class ResultDAOImpl implements ResultDAO {
    private final EntityManager entityManager = JPAUtils.getFactory().createEntityManager();

    @Override
    public void addNewResult(ResultEntity result) {
        entityManager.getTransaction().begin();
        entityManager.persist(result);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateResult(Long result_id, ResultEntity result) {
        entityManager.getTransaction().begin();
        entityManager.merge(result);
        entityManager.getTransaction().commit();
    }

    @Override
    public ResultEntity getResultById(Long result_id) {
        return entityManager.getReference(ResultEntity.class, result_id);
    }

    @Override
    public Collection<ResultEntity> getAllResults() {
        var cm = entityManager.getCriteriaBuilder().createQuery(ResultEntity.class);
        Root<ResultEntity> root = cm.from(ResultEntity.class);
        return entityManager.createQuery(cm.select(root)).getResultList();
    }

    @Override
    public void deleteResult(ResultEntity result) {
        entityManager.getTransaction().begin();
        entityManager.remove(result);
        entityManager.getTransaction().commit();
    }

    /**
     * This method also handles transaction rollback in case of an error.
     */
    @Override
    public void clearResults() {
        entityManager.getTransaction().begin();
        try {
            Query query = entityManager.createQuery("DELETE FROM ResultEntity r");
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Or handle the exception as needed
        } finally {
            entityManager.clear();
        }
    }
}
