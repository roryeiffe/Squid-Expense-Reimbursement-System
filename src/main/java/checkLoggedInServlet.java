import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/** use this servlet whenever you need to check whether user is logged in
 as a certain type (or at all) before navigating to the page
 example usage:
 <a href = "checkLoggedIn?path=SubmitRequest">Request Reimbursement</a>
 This line of HTMl code will first check HTTP session if user is logged in then store
 that in an attribute "loggedIn" (possible values are "manager", "employee", and "none")
 Finally, it will redirect to SubmitRequest.jsp, where you will
 have to manually check the value of the attribute and display the appropriate response
 **/

public class checkLoggedInServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        String userType = (String)session.getAttribute("userType");
        boolean loggedIn = (userType != null);

        // set attribute of log in status:
        if(loggedIn) {
            request.setAttribute("loggedIn", userType);
        }
        else{
            request.setAttribute("loggedIn", "none");
        }

        // forward to correct page:
        request.getRequestDispatcher(request.getParameter("path") + ".jsp").forward(request, response);
        
    }

}
