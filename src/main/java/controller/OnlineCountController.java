package controller;

import model.UserList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class OnlineCountController {

@RequestMapping(value ="/ExitServlet")
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.getSession().removeAttribute("account");//从session中移除对象
    resp.sendRedirect("user/login.jsp");
    }

@RequestMapping(value ="/VisitorServlet")
    public void addVisitor(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    UserList.addVisitor();
System.out.println("visitor: "+ UserList.getVisitor());
    req.getSession().setAttribute("visitor",UserList.getVisitor());
    req.getRequestDispatcher("visitorPage.jsp").forward(req, resp);
    }


}
