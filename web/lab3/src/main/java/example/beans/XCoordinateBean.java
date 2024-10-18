package example.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.validator.ValidatorException;
import jakarta.ws.rs.BeanParam;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Managed bean for handling X coordinate value in JSF application.
 * Provides functionality to get and set X value, and to validate it.
 */
@Data
@NoArgsConstructor
@ManagedBean(name = "xCoordinateBean")
@SessionScoped
public class XCoordinateBean implements Serializable {
    private Double x = 0.0;
}