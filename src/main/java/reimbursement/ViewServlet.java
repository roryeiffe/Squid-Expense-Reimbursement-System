package reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // set up print writer:
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        // get status from submitted form:
        String status = request.getParameter("status");
        // TODO get employee id from httpsession
        // TODO Also make sure user is logged in
        int id = -1;

        // create a dao:
        ReimbursementDao dao = new ReimbursementDao();
        dao.openCurrentSessionWithTransaction();
        // get list of reimbursements:
        List<Reimbursement> reimbursementList = dao.getReimbursements(id,status);
        dao.commitAndClose();

        // forward this request to jsp file:
        request.setAttribute("posts", reimbursementList);
        request.setAttribute("status", status);
        if(id == -1) request.setAttribute("type", "manager");
        else request.setAttribute("type","employee");
        request.getRequestDispatcher("displayRequests.jsp").forward(request, response);

//        // employee view, just show the information, don't allow any changes::
//        if(id > -1) {
//            // construct table
//            out.print("<table border = '1'> <tr> <th>Title</th> <th>Description</th> <th>Amount</th> <th>Status</th> </tr>");
//            for(reimbursement.Reimbursement reimbursement: reimbursementList) {
//                // create a row for each reimbursement:
//                out.print("<tr>");
//                out.print("<td>" + reimbursement.getTitle() + "</td>");
//                out.print("<td>" + reimbursement.getDescription() + "</td>");
//                out.print("<td>" + reimbursement.getAmount() + "</td>");
//                out.print("<td>" + reimbursement.getStatus() + "</td>");
//                out.print("</tr>");
//            }
//            out.print("</table");
//        }
//        // manager view:
//        else{
//            // create a form, this will let the manager update reimbursements:
//            out.print("<form action = '/updateReimbursement' method = 'post'>");
//            // construct table
//            out.print("<table border = '1'> <tr> <th>Employee</th> <th>Title</th> <th>Description</th> <th>Amount</th> <th>Status</th>");
//            if(status.equals("pending")) out.print("<th>Approve</th> <th>Reject</th>");
//            out.print("</tr>");
//            for(reimbursement.Reimbursement reimbursement: reimbursementList) {
//                // create a row for each reimbursement:
//                out.print("<tr>");
//                out.print("<td>" + reimbursement.getEmpId() + "</td>");
//                out.print("<td>" + reimbursement.getTitle() + "</td>");
//                out.print("<td>" + reimbursement.getDescription() + "</td>");
//                out.print("<td>" + reimbursement.getAmount() + "</td>");
//                out.print("<td>" + reimbursement.getStatus() + "</td>");
//                // if this is a pending reimbursemet, print out the options to approve/reject
//                if(status.equals("pending")) {
//                    out.print("<td><input type = 'radio' name = '" +reimbursement.getId() +"' value = 'approve'/></td>");
//                    out.print("<td><input type = 'radio' name = '" +reimbursement.getId() + "' value = 'reject'/></td>");
//                }
//                out.print("</tr>");
//            }
//            // close out the table:
//            out.print("</table");
//            // close out the form:
//            if(status.equals("pending")) out.print("<td><input type = 'submit' value = 'Update Reimbursements'/></td>");
//            out.print("</form>");
//        }





    }

}
