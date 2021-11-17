import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class EmployeeDao implements  EmployeeDaoInterface{

    private Session currentSession;

    private Transaction currentTransaction;

    public EmployeeDao(){

    }

    public Session openCurrentSession(){
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession(){
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory(){
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        SessionFactory sessionFactory = cfg.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession(){
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction(){
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public void login() {

    }

    @Override
    public void submitRequest() {

    }

    @Override
    public void viewPendingRequests() {

    }

    @Override
    public void viewPastRequests() {

    }
}
