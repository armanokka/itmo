package example.db;

import example.entity.ResultEntity;

import java.util.Collection;

/**
 * Interface for data access operations related to ResultEntity.
 * Provides methods for creating, reading, updating, and deleting result records in the database.
 */
public interface ResultDAO {

    /**
     * Adds a new result entity to the database.
     *
     * @param result The ResultEntity object to be added.
     */
    void addNewResult(ResultEntity result);

    /**
     * Updates an existing result entity in the database.
     *
     * @param result_id The ID of the result to be updated.
     * @param result The updated ResultEntity object.
     */
    void updateResult(Long result_id, ResultEntity result);

    /**
     * Retrieves a result entity by its ID.
     *
     * @param result_id The ID of the result to retrieve.
     * @return The ResultEntity object corresponding to the specified ID.
     */
    ResultEntity getResultById(Long result_id);

    /**
     * Retrieves all result entities from the database.
     *
     * @return A collection of all ResultEntity objects.
     */
    Collection<ResultEntity> getAllResults();

    /**
     * Deletes a specific result entity from the database.
     *
     * @param result The ResultEntity object to be deleted.
     */
    void deleteResult(ResultEntity result);

    /**
     * Clears all result entities from the database.
     */
    void clearResults();
}
