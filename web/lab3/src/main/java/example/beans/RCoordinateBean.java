package example.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Managed bean for handling X coordinate value in JSF application.
 * Provides functionality to get and set X value, and to validate it.
 */
@Data
@NoArgsConstructor
@ManagedBean(name = "rCoordinateBean")
@SessionScoped
public class RCoordinateBean implements Serializable {
    private Integer r = 1;
}
