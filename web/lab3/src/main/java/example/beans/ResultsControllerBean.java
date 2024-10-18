package example.beans;

import example.db.DAOFactory;
import example.entity.ResultEntity;
import example.helpers.AreaChecker;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Managed bean for handling results in JSF application.
 * This bean is responsible for managing operations related to result entities.
 */
@Data
@Slf4j
@ManagedBean(name = "resultsControllerBean")
@ApplicationScoped
public class ResultsControllerBean implements Serializable {
    private ArrayList<ResultEntity> results = new ArrayList<>();

    @PostConstruct
    public void init() {
        var resultsEntities = DAOFactory.getInstance().getResultDAO().getAllResults();
        results = new ArrayList<>(resultsEntities);
        log.info("Results initialized with {} entries.", results.size());
    }

    public static double truncate(double value, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.floor(value * factor) / factor;
    }

    public void addResult(Double x, Double y, Integer r) {
        x = truncate(x, 3);
        y = truncate(y, 3);

        ResultEntity entity = ResultEntity.builder().x(x).y(y).r(r).result(AreaChecker.isInArea(x, y, r)).build();
        results.add(entity);

        // add to db
        DAOFactory.getInstance().getResultDAO().addNewResult(entity);
        log.info("Added new result to the db: X={}. Y={}, R={}", x, y, r);
    }
}
