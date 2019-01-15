package controller;

import factory.ServiceFactory;
import model.Product;
import model.ProductItem;
import model.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.LoginManageService;
import service.ProductManageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private LoginManageService loginManageService;

    @Autowired
    private ProductManageService productManageService;

    @RequestMapping(value="/Login")
    public void login(@RequestParam("user") String user,
                      @RequestParam("pw") String pw,
                      HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        int state;
        state = loginManageService.Login_in(user,pw);
        if (state == 0)
            showError(response);

        else if (state == 1) {
            List<Product> products = productManageService.getAllProductInfo();
System.out.println(request.getSession().getAttribute("account"));
System.out.println("有商品:" + products.size());
System.out.println("在线用户:" + UserList.getUserCount());

            request.getSession().setAttribute("account", request.getParameter("user"));
            request.getSession().setAttribute("online", UserList.getUserCount());
            request.getSession().setAttribute("visitor", UserList.getVisitor());
            request.getSession().setAttribute("all", UserList.getAllNum());
            request.getSession().setAttribute("products", products);
            request.getSession().setAttribute("orderItemList", new ArrayList<ProductItem>());

            request.getRequestDispatcher("order/listProduct.jsp").forward(request, response);

        }
    }

    public void showError(HttpServletResponse response) throws ServletException, IOException{

        PrintWriter out = response.getWriter();
        String title = "username or password error";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "</body></html>");
    }


}
