package example.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.validator.ValidatorException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Managed bean for handling Y coordinate value in JSF application.
 * Offers methods to get and set Y value, and a validator to ensure the Y value falls within a specified range.
 */
@Data
@NoArgsConstructor
@ManagedBean(name = "yCoordinateBean")
@SessionScoped
public class YCoordinateBean implements Serializable {
    private Double y = 0.0;
}