<%-- 
    Document   : selectedPartner
    Created on : Apr 28, 2015, 10:21:17 AM
    Author     : TOcvfan
--%>

<%@page import="domain.Partner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "UI.PageControl"%>

<!DOCTYPE html>
<html lang="da">
    <head>
        <meta charset="utf-8"/>
        <title>Dell Partner Programme</title>

        <link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />
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
                <h2 class="section_title">Dashboard</h2><div class="btn_view_site"><a href="PageControl?command=logout">Logout</a></div>
            </hgroup>
        </header> <!-- end of header bar -->

        <section id="secondary_bar">
            <div class="user">
                <p> <%
                    String username = (String) session.getAttribute("name");

                    out.println(username);

                    
                    
                    
                    %> (<a href="#">3 Messages</a>)</p>
                <!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
            </div>
            <div class="breadcrumbs_container">
                <article class="breadcrumbs"><a href="index.html">Home</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
            </div>
        </section><!-- end of secondary bar -->

        <aside id="sidebar" class="column">
             <form class="quick_search" action="PageControl" id="form">
                <input type="text" name="searchField" value="Quick Search" onfocus="if (!this._haschanged) {
                            this.value = ''
                        }
                        ;
                        this._haschanged = true;">
                <input type="hidden" name="command" value="search" />
                
                
                
            </form>
            <hr/>
            <h3>Projects</h3>
            <ul class="toggle">
                <li class="icn_new_article"><a href="newProjektForm.jsp">Add New Project</a></li>
                <li class="icn_folder"><a href="PageControl?command=listProjects">View Projects</a><input type="hidden" name="command" value="listProjects"></li>
            </ul>
            <h3>Partners</h3>
            <ul class="toggle">
                <li class="icn_add_user"><a href="newPartnerForm.jsp">Add New Partner</a></li>
                <li class="icn_profile"><a href="PageControl?command=listPartners">View Partners</a><input type="hidden" name="command" value="listPartners"></li>

            </ul>

            <h3>StatisticS</h3>
            <ul class="toggle">

            </ul>

            <h3>Admin</h3>
            <ul class="toggle">
                <li class="icn_add_user"><a href="newEmployeeForm.jsp">Add New Employee</a></li>
                <li class="icn_profile"><a href="PageControl?command=listEmployees">View Employees</a><input type="hidden" name="command" value="listPartners"></li>


            </ul>

            <footer>
                <hr />
                <p><strong>Datamatiker 2. semesteropgave Gruppe 2</strong></p>

            </footer>
        </aside><!-- end of sidebar -->
        <%
            Partner selPar = new Partner();
            selPar = (Partner) session.getAttribute("clickedPartner");
            int parID = selPar.getParID();
            String parName = selPar.getParName();
            String parAdress = selPar.getParAdress();
            String parPhone = selPar.getParPhone();
            String eMail = selPar.geteMail();
            String CVR = selPar.getCVR();
            String parPass = selPar.getParPass();
            int parFunds = selPar.getParFunds();
            String contactName = selPar.getContactName();
            
            Partner p = new Partner(
                    parID, parName, parAdress, parPhone, eMail,
                    CVR, parPass, parFunds, contactName);
            request.getSession().removeAttribute("clickedPartner");

        %>

        <section id="main" class="column">
            <article class="module width_full">
                <header><h3><%out.println(p.getParName());%></h3></header>
                <div class="module_content">

                    <div id="list">

                        <h5>Contact Name: <%out.println(p.getContactName());%></h5>
                        <h5>Address: <%out.println(p.getParAdress());%></h5>
                        <h5>Phone: <%out.println(p.getParPhone());%></h5>
                        <h5>eMail: <%out.println(p.geteMail());%></h5>
                        <h5>Funds: <%out.println(p.getParFunds());%></h5>
                        <h5>CVR: <%out.println(p.getCVR());%></h5>
                        
                    </div>
                    
                </div>
            
    </article><!-- end of stats article -->

    <div class="clear"></div>

</section>
    </body>
</html>

