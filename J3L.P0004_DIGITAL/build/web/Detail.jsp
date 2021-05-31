<%-- 
    Document   : Detail
    Created on : May 14, 2021, 3:48:02 PM
    Author     : chutr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/detail.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                    <!--
                    <c:if test="${detail == null}">
                        <div class="tittle">
                            ${notify}
                        </div>
                    </c:if>
                        -->
                    <c:if test="${detail != null}">
                        <div class="tittle">
                            ${detail.title}
                        </div>
                        <div class="image">
                            <img src="${detail.image}"/>
                        </div>
                        <div class="text">
                            ${detail.description}
                        </div>
                        <div class="signature">
                            <div class="icon1"></div>
                            <div class="icon2"></div>
                            By ${detail.author} | ${detail.dateConvert}
                        </div>
                    </c:if>

                </div>
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
