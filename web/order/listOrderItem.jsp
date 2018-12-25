
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8"  import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>list order</title>
</head>
<body>
<h1 align="center">购物车</h1>
<h>${account}</h>
<table border="1" align="center" cellspacing="0">
    <tr>
        <td>商品名称</td>
        <td>数量</td>
        <td>单价</td>
        <td>小计</td>
        <td>缺货</td>
    </tr>

    <c:forEach items="${orderItemList}" var="item" varStatus="st">
        <tr>
            <td>${item.productName}</td>
            <td>${item.quantity}</td>
            <td>${item.price}</td>
            <td>
                <fmt:formatNumber type="number" value="${item.totalPrice}" maxFractionDigits="2"/>
            </td>
            <td>${item.oos}</td>
            <td align="center" valign="center">
            </td>
        </tr>
    </c:forEach>
    <c:if test="${!empty orderItemList}">
        <tr>

            <td colspan="5" align="center">
                <a style="text-decoration: none" href="${pageContext.request.contextPath}/createOrderServlet">生成订单</a>
            </td>
        </tr>
    </c:if>
</table>
</body>
</html>
