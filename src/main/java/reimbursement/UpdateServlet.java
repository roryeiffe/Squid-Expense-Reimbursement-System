package reimbursement;

import Factory.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class UpdateServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // create a dao:
        ReimbursementDao dao = DaoFactory.getReimbursementDao();
        dao.openCurrentSessionWithTransaction();

        // get map of parameters:
        Map<String, String[]> parameters = request.getParameterMap();
        for (Map.Entry<String,String[]> entry : parameters.entrySet()){
            // get reimbursement id from key:
            int reimbursementId = Integer.parseInt(entry.getKey());
            Reimbursement reimbursement = dao.getById(reimbursementId);
            // update status, based on action chosen:
            String action = entry.getValue()[0];
            if (action.equals("approve")) reimbursement.setStatus("approved");
            else reimbursement.setStatus("rejected");
            System.out.println(reimbursementId + " " + action);
            // update the db:
            dao.update(reimbursement);
        }

        // commit transaction:
        dao.commitAndClose();

        // let the user know that the update was successful:
        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.include(request, response);
        // add success alert:
        out.print("<script>alert('Reimbursements updated successfully!','success')</script>");
    }
}
