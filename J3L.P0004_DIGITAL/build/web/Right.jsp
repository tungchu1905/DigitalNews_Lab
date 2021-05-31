<%-- 
    Document   : Right
    Created on : May 12, 2021, 2:56:59 PM
    Author     : chutr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/right.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        
    </head>
    <body>
        <div class="right">
            <div class="newst">
                <div class="tittle"> 
                    Digital News
                </div>
                <div class="contentNews" >
                    ${top1.shortDes}
                </div>
            </div>
            <div class="newst">
                <div class="tittle">
                    Search
                </div>
                <div class="search" >
                    <form action="SearchController" method="post" >
                        <input   id="txtSearch" class="searchBox" type="text" name="txtSearch" size="15" value="${txt}"
                                 required maxlength="20" pattern="(?=.*[a-z])[a-zA-Z0-9\s]+" title="You have input full space, pls input something">
                        <input  class="searchButton" type="submit" name="btnGo" value="Go">
                    </form>
                </div>
            </div>

            <div class="newst">
                <div class="tittle">Last Articles</div>
                <c:forEach items="${top5}" var="o">
                    <div class="lastArticles">
                        <a href="DetailController?id=${o.id}" >${o.title}</a> <br>
                    </div>
                </c:forEach>


            </div>
        </div>
        
    </body>
</html>
