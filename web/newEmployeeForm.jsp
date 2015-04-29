<%-- 
    Document   : newEmployeeForm
    Created on : Apr 28, 2015, 12:18:32 PM
    Author     : Gruppe 2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "UI.PageControl"%>

<!doctype html>
<html lang="da">

    <head>
        <meta charset="utf-8"/>
        <title>Create new employee!</title>

        <link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />
        <link rel="stylesheet" type="text/css" href="/code_examples/tutorial.css">
        <script type="text/javascript" src="/code_examples/passtest.js"></script>
        <!--[if lt IE 9]>
        <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="js/jquery-1.5.2.min.js" type="text/javascript"></script>
        <script src="js/hideshow.js" type="text/javascript"></script>
        <script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/jquery.equalHeight.js"></script>
        <script type="text/javascript">
            $(document).ready(function ()
            {
                $(".tablesorter").tablesorter();
            }
            );
            $(document).ready(function () {

                //When page loads...
                $(".tab_content").hide(); //Hide all content
                $("ul.tabs li:first").addClass("active").show(); //Activate first tab
                $(".tab_content:first").show(); //Show first tab content

                //On Click Event
                $("ul.tabs li").click(function () {

                    $("ul.tabs li").removeClass("active"); //Remove any "active" class
                    $(this).addClass("active"); //Add "active" class to selected tab
                    $(".tab_content").hide(); //Hide all tab content

                    var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
                    $(activeTab).fadeIn(); //Fade in the active ID content
                    return false;
                });

            });
        </script>
        <script type="text/javascript">
            $(function () {
                $('.column').equalHeight();
            });
        </script>

    </head>


    <body>

        <header id="header">
            <hgroup>
                <h1 class="site_title"><a href="index.jsp">Dell Partner Management</a></h1>
                <h2 class="section_title">Dashboard</h2>
            </hgroup>
        </header> <!-- end of header bar -->
        

        <div class="center">
            <h1>Create new employee:</h1>
            <form name="employeeForm" action="PageControl" id="form">
                <p>Name:</p><input type="text" name="empName" value="" size="50" />
               <p>Email:</p><input type="text" name="empMail" value="" size="50" />
                <p>Password:</p><input type="password" name="empPass" value="" required id="password1" size="50" />
                <p>Retype Password:</p><input type="password" name="empPass1" value="" required id="password2" size="50" />
                
                <script type="text/javascript">
                    var password1 = document.getElementById('password1');
                    var password2 = document.getElementById('password2');

                    var checkPasswordValidity = function() {
                    if (password1.value != password2.value) {
                        password1.setCustomValidity('Passwords must match.');
                    } else {
                        password1.setCustomValidity('');
                    }        
                };

                password1.addEventListener('change', checkPasswordValidity, false);
                password2.addEventListener('change', checkPasswordValidity, false);
                </script>
                
                
                <input type="hidden" name="command" value="employeeForm" />
                <br><br>
                <input type="submit" value="Submit" name="employeesubmit"/>
            </form>
        </div>
    </body>
</html>
