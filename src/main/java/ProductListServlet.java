import data.ProductServiceImpl;
import model.Product;
import model.ProductItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet{

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = new ProductServiceImpl().getAllProductInfo();
System.out.println("有商品:"+products.size());
System.out.println(req.getParameter("account")+"???");
        req.setAttribute("products", products);
        req.setAttribute("orderItemList",new ArrayList<ProductItem>());

        req.getRequestDispatcher("/WEB-INF/jsp/listProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
