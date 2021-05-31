<%-- 
    Document   : error
    Created on : May 10, 2021, 3:37:07 PM
    Author     : chutr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/error.css" rel="stylesheet" type="text/css"/>

        <title>JSP Page</title>
    </head>
    <body>
        <div class="contents">
            <jsp:include page="Header.jsp"/>
            <div class="mainerror">
                <div class="wrong">
                    <h1>${errormess}</h1>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>

            <jsp:include page="Footer.jsp" />
        </div>
    </body>
</html>
