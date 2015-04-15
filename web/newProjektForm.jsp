<%-- 
    Document   : newProjektForm
    Created on : Apr 14, 2015, 11:03:48 AM
    Author     : TOcvfan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new project!</title>
    </head>
    <body>
        <h1>Create new project:</h1>
        <form name="projectForm" action="PageControl">
            <p>Project Name:</p><input type="text" name="proName" value="" size="50" />
            <p>Dell Employee ID:</p><input type="text" name="proEmpID" value="" size="50" /><label> if unknown type 0</label>
            
            <p>Partner Id:</p><input type="text" name="ProParID" value="" size="50" />
            <p>Project start date:</p><input type="text" name="proStartDate" value="" size="50" />
            <p>Project end date:</p><input type="text" name="proEndDate" value="" size="50" />
            <p>Funds request</p><input type="text" name="eMail" value="" size="50" />
            <input type="hidden" name="origin" value="projectForm" />
            <br><br>
            <input type="submit" value="Submit" name="projectsubmit" />
            <li class="icn_folder"><a href="http://localhost:8080/Dell/PageControl?command=projectForm">Send Lortet</a><input type="hidden" name="command" value="projectForm"></li>
        </form>
        
        
        
    </body>
</html>
