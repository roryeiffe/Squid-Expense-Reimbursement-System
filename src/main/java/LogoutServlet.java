import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("index.html").include(req, resp);

        HttpSession session = req.getSession();

        session.invalidate();

        //out.print("You are successfully logged out!");
        RequestDispatcher dispatcher = req.getRequestDispatcher("logoutsuccessful.html");
        dispatcher.forward(req,resp);
        out.close();
    }
}
