<%--
    Document   : SelectedProject
    Created on : Apr 21, 2015, 10:12:58 AM
    Author     : TOcvfan
--%>
 
<%@page import="java.io.InputStream"%>
<%@page import="domain.Project"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "UI.PageControl"%>
 
<!doctype html>
<html lang="en">
 
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
                <h2 class="section_title">Dashboard</h2><div class="btn_view_site"><a href="http://www.medialoot.com">View Site</a></div>
            </hgroup>
        </header> <!-- end of header bar -->
 
        <section id="secondary_bar">
            <div class="user">
                <p>John Doe (<a href="#">3 Messages</a>)</p>
                <!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
            </div>
            <div class="breadcrumbs_container">
                <article class="breadcrumbs"><a href="index.html">Home</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
            </div>
        </section><!-- end of secondary bar -->
 
        <aside id="sidebar" class="column">
            <form class="quick_search">
                <input type="text" value="Quick Search" onfocus="if (!this._haschanged) {
                           this.value = ''
                       }
                       ;
                       this._haschanged = true;">
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
            Project selPro = new Project();
            selPro = (Project) session.getAttribute("clickedProject");
            int proID = selPro.getProID();
            int proEmpID = selPro.getProEmpID();
            int proParID = selPro.getProParID();
            String proName = selPro.getProName();
            String proStartDate = selPro.getProStartDate();
            String proEndDate = selPro.getProEndDate();
            InputStream proPeo = selPro.getProPeo();
            int proStatus = selPro.getProStatus();
            int proSteps = selPro.getProSteps();
            int proFunds = selPro.getProFunds();
            int proReqFunds = selPro.getProReqFunds();
            Project pj = new Project(
                    proID, proEmpID, proParID, proName, proStartDate, proEndDate, proPeo,
                    proStatus, proSteps, proReqFunds, proFunds);
        %>
 
        <section id="main" class="column">
            <article class="module width_full">
                <header><h3><%out.println(pj.getProName());%></h3></header>
                <div class="module_content">
 
                            <table border="0" width="950">
                        <thead>
                            <tr>
                                <th <%if (proSteps == 1) {%>
                                    id="active1"<%} else {%>class="step"<%}%>>Step 1 </th>
                                <th <%if (proSteps == 2) {%>
                                    id="active2"<%} else {%>class="step"<%}%>>Step 2 </th>
                                <th <%if (proSteps == 3) {%>
                                    id="active3"<%} else {%>class="step"<%}%>>Step 3 </th>
                                <th <%if (proSteps == 4) {%>
                                    id="active4"<%} else {%>class="step"<%}%>>Step 4 </th>
                                <th <%if (proSteps == 5) {%>
                                    id="active5"<%} else {%>class="step"<%}%>>Step 5 </th>
                                <th <%if (proSteps == 6) {%>
                                    id="active6"<%} else {%>class="step"<%}%>>Step 6 </th>
                                <th <%if (proSteps == 7) {%>
                                    id="active7"<%} else {%>class="step"<%}%>>Step 7 </th>
                            </tr>
                        </thead>
                    </table>
                             
                    <div id="list">
 
                        <h5>Status: <% if (proStatus == 0) {
                                out.println("Needs Approval");
                            } else if (proStatus == 1) {
                                out.println("Active");
                            } else if (proStatus == 2) {
                                out.println("Inactive/Dismissed");
                            } else if (proStatus == 3) {
                                out.println("Completed");
                            }%></h5>
                        <h5>Start: <%out.println(pj.getProStartDate());%></h5>
                        <h5>End: <%out.println(pj.getProEndDate());%></h5>
                        <h5>Requested funds: <%out.println(pj.getProReqFunds());%></h5>
                        <h5>Funds: <%out.println(pj.getProFunds());%></h5>
                        <h5>Project Id: <%out.println(pj.getProID());%></h5>
                        <h5>Employee Id: <%out.println(pj.getProEmpID());%></h5>
                        <h5>Partner Id: <%out.println(pj.getProParID());%></h5>
                        <h5>POE: <% if(pj.getProPeo() != null) {out.println("--- A POE has been uploaded! ---");}
                        else{out.println(" --- No POE uploaded yet! ---");};  %></h5>
                    </div>
                    <%
                        if (proStatus == 0) {%>
                    <form name="Dismissed" action="PageControl" id="approval">                          
                        <button type="submit" name="command" value="approve"> Approve </button>
                        <input type="hidden" value="active" name="changeStatus">        
                    </form>
                    <form name="Approved" action="PageControl" id="approval">
                        <button type="submit" name="command" value="approve"> Dismiss </button>
                        <input type="hidden" value="inactive" name="changeStatus">                   
                    </form>
                    <%}%>
                    <% if (proStatus != 0) {%> <p>Change Status:</p> <form action="PageControl"><select name="changeStatus">
                            <option value="active">Active</option>
                            <option value="inactive">Inactive</option>                    
                            <option value="completed">Completed</option>
                            <option value="needsApproval">Needs approval</option>
                        </select><button type="submit" value="approve" name="command">Change Status</button>                    
                    </form>
                    <%};%>
                    <br>
                    
                    <form name="uploadForm" action="PageControl" method="post" enctype="multipart/form-data">
                    <input type="file" name="photo" value="" width="50" />
                    <button type="submit" value="uploadPOE" name="command">Upload_POE</button>
                    <input type="hidden" name="clickedID" value="<%out.println(pj.getProID());%>" />
                    </form>
 
 
                </div>
            </article><!-- end of styles article -->
            <div class="spacer"></div>
 
            <div class="clear"></div>
        </div>
    </article><!-- end of stats article -->
 
    <div class="clear"></div>
 
</section>
 
</body>
 
</html>