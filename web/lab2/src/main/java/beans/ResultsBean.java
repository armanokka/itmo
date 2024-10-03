package beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultsBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Result> results;

    public ResultsBean() {
        this.results = new ArrayList<>();
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }

    @Data
    @AllArgsConstructor
    public static class Result implements Serializable {
        private static final long serialVersionUID = 2L;
        private String x;
        private String y;
        private String r;
        private boolean isHit;
    }
}

