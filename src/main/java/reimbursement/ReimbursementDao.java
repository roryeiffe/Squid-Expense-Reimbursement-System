package reimbursement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

// https://examples.javacodegeeks.com/enterprise-java/hibernate/hibernate-jpa-dao-example/
// used for inspiration
// This class is responsible for maintaining sessions and transactions while
// querying and updating the database:
public class ReimbursementDao {
    private Session currentSession;
    private Transaction currentTransaction;

    public ReimbursementDao() {
    }

    // open a session
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    // open a session and a new transaction:
    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    // close the current session:
    public void closeCurrentSession() {
        currentSession.close();
    }

    // close the current session and commit the current transaction:
    public void commitAndClose() {
        currentTransaction.commit();
        currentSession.close();
    }

    // return a session factory:
    private static SessionFactory getSessionFactory() {
        // create a configuration object:
        Configuration cfg = new Configuration();

        // read the configuration and load in the object:
        cfg.configure("hibernate.cfg.xml");

        // create a factory:
        SessionFactory factory = cfg.buildSessionFactory();

        return factory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    // insert a reimbursement into the database:
    public void insert(Reimbursement reimbursement) {
        getCurrentSession().save(reimbursement);
    }

    // update a current reimbursement
    public void update(Reimbursement reimbursement) {
        getCurrentSession().update(reimbursement);
    }

    // return a reimbursement by a certain id:
    public Reimbursement getById(int id) {
        Reimbursement reimbursement = getCurrentSession().get(Reimbursement.class, id);
        return reimbursement;
    }

    // return a list of reimbursements based on a certain condition:
    // status can be a string like "pending" or "approved" to only get reimbursements
    // which have those statuses, or % to retrieve all statues
    public List<Reimbursement> getReimbursements(int empId, String status){
        // construct query string from arguments
        String hql = "FROM reimbursement.Reimbursement R ";
        // default to show all statuses:
        hql += "WHERE R.status LIKE '%' ";
        // get approved/rejected reimbursements:
        if (status.equals("past")) hql += "AND (R.status = 'approved' OR R.status = 'rejected') ";
        else if (status.equals("pending")) hql += "AND R.status = 'pending'";
        // unless we are a manager, only show reimbursements for the given employee:
        if(empId != -1) {
            hql += "AND empId = " + empId;
        }

        // execute query and get list:
        Query qry = currentSession.createQuery(hql);
        List l= qry.list();
        return l;
    }


}
