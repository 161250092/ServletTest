import data.ProductServiceImpl;
import model.Product;
import model.ProductItem;
import model.User;
import model.UserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/VisitorServlet")
public class VisitorServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserList.addVisitor();
System.out.println("visitor: "+ UserList.getVisitor());
        req.getSession().setAttribute("visitor",UserList.getVisitor());

        req.getRequestDispatcher("visitorPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
