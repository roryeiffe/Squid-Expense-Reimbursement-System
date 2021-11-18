import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserDao loginDao;

    public void init(){
        loginDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        try{
            authenticate(req, resp);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String email = req.getParameter("email");
        String password = req.getParameter("password");

//        Configuration cfg = new Configuration();
//        cfg.configure("hibernate.cfg.xml");
//        SessionFactory factory = cfg.buildSessionFactory();
//        Session session = factory.openSession();
//
//        String hql = "FROM User u WHERE u.email LIKE :useremail";
//        Query qry = session.createQuery(hql);
//        Query qry2 = qry.setParameter("useremail", email);
//        User user = (User) qry2.getSingleResult();
//        System.out.println(user);
        if(loginDao.validate(email, password)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("loginsuccess.html");
            dispatcher.forward(req,resp);
            System.out.println("Welcome!");
        } else{
            throw new Exception("Login not successful...");
        }
    }
}
