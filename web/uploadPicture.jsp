<%-- 
    Document   : uploadPicture
    Created on : 04-05-2015, 13:10:18
    Author     : KirstenPiil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "UI.PageControl"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Proof Of Execution</h1>
        <br>
                
        <form name="nana" action="PageControl" method="post">
            <input type="file" name="photo" value="" width="50" />
            <button type="submit" value="uploadPOE" name="command">Change Status</button>
            
        </form>
        <br>
   

    </body>
</html>
