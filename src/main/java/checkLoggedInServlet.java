import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class checkLoggedInServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String userType = (String)session.getAttribute("userType");
        boolean loggedIn = (userType != null);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(loggedIn) {
            out.write("Logged in!");
            out.write(userType);
        }
        else{
            out.write("Not logged in!");
        }
        
    }

}
