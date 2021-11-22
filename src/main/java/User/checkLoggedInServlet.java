package User;

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
 Finally, it will redirect to SubmitRequest.html, where you will
 have to manually check the value of the attribute and display the appropriate response
 **/

public class checkLoggedInServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // get login status from httpsession:
        HttpSession session = request.getSession(true);
        String userType = (String)session.getAttribute("userType");
        boolean loggedIn = (userType != null);

        String loggedInStatus;

        // set attribute of log in status:
        if(loggedIn) {
            loggedInStatus = userType;
        }
        else{
            loggedInStatus = "none";
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println(loggedInStatus);

        // check where the user is trying to access:
        String path = request.getParameter("path");
        // to submit request, must be logged in as employee:
        if(path.equals("SubmitRequest")){
            if(loggedInStatus.equals("employee")){
                request.getRequestDispatcher("SubmitRequest.html").forward(request, response);
            }
            else{
                request.getRequestDispatcher("index.html").include(request, response);
                // add danger alert:
                out.print("<script>alert('Must be logged in as employee to submit request!','danger')</script>");
            }
        }
        // must be logged in to view requests:
        else if (path.equals("View")) {
            if(!loggedInStatus.equals("none")) {
                request.getRequestDispatcher("View.html").forward(request, response);
            }
            else {
                request.getRequestDispatcher("index.html").include(request, response);
                // add danger alert:
                out.print("<script>alert('Must be logged in to view requests!','danger')</script>");
            }
        }
        
    }

}
