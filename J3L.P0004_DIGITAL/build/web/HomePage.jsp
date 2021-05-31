<%-- 
    Document   : HomePage
    Created on : May 10, 2021, 3:20:54 PM
    Author     : chutr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">

            <jsp:include page="Header.jsp"/>

            <div class="content">
                <div class="main">
                    <div class="tittle">
                        
                             ${top1.title}
                   
                    </div>
                    <div class="image">
                        <img src="${top1.image}">
                    </div>
                    <div class="text">${top1.description}</div>
                    <div class="signature">
                        <div class="icon1"></div>
                        <div class="icon2"></div>
                        By ${top1.author} | ${top1.dateConvert}
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>

            <jsp:include page="Footer.jsp"/> 
        </div>
    </body>
</html>
