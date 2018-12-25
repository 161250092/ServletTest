
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>商品列表</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#input.addCartButton").click(function () {
                $(this).attr("disabled","disabled");
                var button=$(this);
                var pid=$(this).attr("pid");

                var number=$("input.number[pid="+pid+"]").val();
                //使用get请求方法
                //设置请求的域名
                var page="/servlets.AddOrderItemServlet";
                //请求
                $.get(
                    page,
                    //请求参数
                    {"num":number,"pid":pid}
                );
            });
        });
    </script>
</head>


<h>${account}</h>
<a style="text-decoration: none" href="ExitServlet">登出</a>
<div align="center" style="height: 20px; margin: 20px">
    <span style="color:blue" id="addCartSuccessMessage">商品列表</span>
</div>
<p>online: ${online}</p>
<p>visitor: ${visitor}</p>
<p>all: ${all}</p>
<p>${message}</p>

<table align="center" border="1" cellspacing="0">
    <tr>
        <td>商品号</td>
        <td>名称</td>
        <td>价格</td>
        <td>购买</td>
    </tr>
    <c:forEach items="${products}" var="product" varStatus="st">
        <tr>
            <td>${product.productId}</td>
            <td>${product.productName}</td>
            <td>
                <fmt:formatNumber type="number" value="${product.price}" maxFractionDigits="2"/>
            </td>
            <td>
                <form action="AddOrderItemServlet" method="post">
                    数量<input type="text" value="1" name="num">
                    <input type="hidden" name="pid" value="${product.productId}">
                    <input type="submit" value="添加">
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4" align="center"><a href="${pageContext.request.contextPath}/order/listOrderItem.jsp">查看购物车</a> </td>
    </tr>
</table>
</body>
</html>
