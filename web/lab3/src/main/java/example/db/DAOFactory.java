package example.db;

public class DAOFactory {
    private static ResultDAO resultDAO;

    private static DAOFactory instance;

    public static DAOFactory getInstance() {
        if (instance == null)
            instance = new DAOFactory();
        return instance;
    }

    public ResultDAO getResultDAO() {
        if (resultDAO == null)
            resultDAO = new ResultDAOImpl();
        return resultDAO;
    }
}
