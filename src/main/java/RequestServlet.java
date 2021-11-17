import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // get data from submitted form:
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int amount = Integer.parseInt(request.getParameter("amount"));
        // default to pending
        String status = "pending";
        // TODO: Get employee id from session/cookie/wherever it is stored:
        int empId = 1;

        Reimbursement reimbursement = new Reimbursement(empId, title, description, amount, status);
        // create a dao:
        ReimbursementDao dao = new ReimbursementDao();
        // first, create a session:
        dao.openCurrentSessionWithTransaction();
        // insert reimbursement:
        dao.insert(reimbursement);
        // commit transaction:
        dao.commitAndClose();

        // TODO check for success/failure
        out.println("You request was submitted successfully!");
        // display message on same page:
        RequestDispatcher rd = request.getRequestDispatcher("/reimbursementRequestForm.html");
        rd.include(request,response);




    }
}
