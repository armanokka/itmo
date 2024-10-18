package example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a result entity for storing point data.
 * This entity is mapped to a database table 'lab3_x_test_table' within the schema 's409703'.
 * IT SHOULD INCLUDE (in theory) information about the point coordinates (x, y), radius (r) and whether the point is within a certain area (result).
 */
@Entity
@Table(name = "point_model", schema = "s409703")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "point_model_id_seq", allocationSize = 1)
    private long id;

    private double x;
    private double y;
    private int r;
    private boolean result;
}

