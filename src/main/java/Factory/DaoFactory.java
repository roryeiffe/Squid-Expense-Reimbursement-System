package Factory;

import User.UserDao;
import reimbursement.ReimbursementDao;

public class DaoFactory {
    private static ReimbursementDao reimbursementDao = null;
    private static UserDao userDao = null;

    private DaoFactory() {}

    public static ReimbursementDao getReimbursementDao() {
        if (reimbursementDao == null) {
            reimbursementDao = new ReimbursementDao();
        }
        return reimbursementDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }
}
