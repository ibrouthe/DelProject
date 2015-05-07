<%-- 
        Document   : List Projects
        Created on : 08-04-2015, 02:15:23
        Author     : Gruppe 2 Silas, Thomas, Christian, Martin, Ib
--%>

<%@page import="domain.Employee"%>
<%@page import="domain.Partner"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Project"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "UI.PageControl"%>

<!doctype html>
<html lang="en">
    <%String role = (String) session.getAttribute("role");%>

    <head>

        <meta charset="utf-8"/>

        <title>Dell Partner Programme</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>

        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
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
                <h1 class="site_title"><a href="Dashboard.jsp">Dell Partner Management</a></h1>
                <h2 class="section_title">Dashboard</h2><div class="btn_view_site"><a href="PageControl?command=logout">Logout</a></div>
            </hgroup>
        </header> <!-- end of header bar -->

        <section id="secondary_bar">
            <div class="user">
                <p><%
                    String username = (String) session.getAttribute("name");

                    out.println(username);

                    %> (<a href="#">3 Messages</a>)</p>
                <!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
            </div>
            <div class="breadcrumbs_container">
                <article class="breadcrumbs"><a href="index.jsp">Home</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
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

            <div id="projectsDiv">
                <h3>Projects</h3>

                <ul class="toggle">

                    <li class="icn_new_article"><a href="newProjektForm.jsp">Add New Project</a></li>


                    <%  String roleProjectTab = (String) session.getAttribute("role");
                        if (roleProjectTab.equalsIgnoreCase("partner")) {
                    %>
                    <li class="icn_folder"><a href="PageControl?command=listParProjects">View Projects</a><input type="hidden" name="command" value="listParProjects"></li>
                        <%
                        } else if (roleProjectTab.equalsIgnoreCase("employee")) {%>
                    <li class="icn_folder"><a href="PageControl?command=listProjects">View Projects</a><input type="hidden" name="command" value="listProjects"></li>
                        <% }%>

                </ul></div>

            <div id="partnerTab" <%

                if (role.equalsIgnoreCase("partner")) {
                    out.println("style=\"display:none\"");
                }


                 %>>
                <h3>Partners</h3>
                <ul class="toggle">
                    <li class="icn_add_user"><a href="newPartnerForm.jsp">Add New Partner</a></li>
                    <li class="icn_profile"><a href="PageControl?command=listPartners">View Partners</a><input type="hidden" name="command" value="listPartners"></li>

                </ul>
            </div>

            <h3>StatisticS</h3>
            <ul class="toggle">

            </ul>
            <div id="adminTab" <% if (role.equalsIgnoreCase("partner")) {
                    out.println("style=\"display:none\"");
                    }  %>>
                <h3>Admin</h3>
                <ul class="toggle">
                    <li class="icn_add_user"><a href="newEmployeeForm.jsp">Add New Employee</a></li>
                    <li class="icn_profile"><a href="PageControl?command=listEmployees">View Employees</a><input type="hidden" name="command" value="listPartners"></li>

                </ul></div>

            <footer>
                <hr />
                <p><strong>Datamatiker 2. semesteropgave Gruppe 2</strong></p>

            </footer>
        </aside><!-- end of sidebar -->

        <section id="main" class="column">

            <h4 class="alert_info">7 projects have been edited since your last login.</h4>

            <article class="module width_full">
                <header><h3>Search Results:</h3></header>
                
                <div class="module_content" <% if (role.equalsIgnoreCase("employee")) {
                    out.println("style=\"display:none\"");
                    }  %>>

                    <%
                        ArrayList<Project> searchParProjects = (ArrayList<Project>) session.getAttribute("searchParProjects");
                        
                    if (searchParProjects != null && searchParProjects.size() > 0) {

                    %><table class="table-condensed"  border="1" width="850"> <thead>
                            <tr>
                                <th>Select</th>
                                <th>ID    </th> 
                                <th>Name      </th> 
                                <th>Start Date</th> 
                                <th>End Date  </th>
                                <th>Step  </th>
                                <th>Status</th>
                            </tr> 
                        </thead> <tbody>

                            <%                                for (Project proTemp : searchParProjects) {
                            %>
                            <tr>
                                <td><a href="PageControl?command=selectedProject&param1=<%out.print(proTemp.getProID());%>">View Project</a></td> 
                                <%//System.out.println(temp.getProID());%>
                                <td><%out.print(proTemp.getProID());%></td> 
                                <td><%out.print(proTemp.getProName());%></td> 
                                <td><%out.print(proTemp.getProStartDate());%></td> 
                                <td><%out.print(proTemp.getProEndDate());%></td>
                                <td><%out.print(proTemp.getProSteps());%></td>
                                <td><%out.print(proTemp.getProStatus());%></td>
                            </tr>  

                            <%}} %>
                        </tbody> </table>

                </div>

                
                <div class="module_content" <% if (role.equalsIgnoreCase("partner")) {
                    out.println("style=\"display:none\"");
                    }  %>>

                    <%
                        ArrayList<Project> searchProjects = (ArrayList<Project>) session.getAttribute("searchProjects");
                        
                    if (searchProjects != null && searchProjects.size() > 0) {

                    %><table class="table-condensed"  border="1" width="850"> <thead>
                            <tr>
                                <th>Select</th>
                                <th>ID    </th> 
                                <th>Name      </th> 
                                <th>Start Date</th> 
                                <th>End Date  </th>
                                <th>Step  </th>
                                <th>Status</th>
                            </tr> 
                        </thead> <tbody>

                            <%                                for (Project proTemp : searchProjects) {
                            %>
                            <tr>
                                <td><a href="PageControl?command=selectedProject&param1=<%out.print(proTemp.getProID());%>">View Project</a></td> 
                                <%//System.out.println(temp.getProID());%>
                                <td><%out.print(proTemp.getProID());%></td> 
                                <td><%out.print(proTemp.getProName());%></td> 
                                <td><%out.print(proTemp.getProStartDate());%></td> 
                                <td><%out.print(proTemp.getProEndDate());%></td>
                                <td><%out.print(proTemp.getProSteps());%></td>
                                <td><%out.print(proTemp.getProStatus());%></td>
                            </tr>  

                            <%}} %>
                        </tbody> </table>

                </div>






                <div class="module_content" <% if (role.equalsIgnoreCase("partner")) {
                    out.println("style=\"display:none\"");
                    }  %>>

                    <%

                        ArrayList<Partner> searchPartners = (ArrayList<Partner>) session.getAttribute("searchPartners");

                        if (searchPartners != null && searchPartners.size() > 0) {

                    %><table class="table-condensed" border="1" width="850"> <thead>
                            <tr>
                                <th>Select</th>
                                <th>ID    </th> 
                                <th>Name      </th>
                                <th>Contact Name      </th>
                                <th>Phone  </th> 
                                <th>CVR  </th>
                                <th>email  </th>

                            </tr> 
                        </thead> <tbody>

                            <%                                for (Partner parTemp : searchPartners) {
                            %>
                            <tr>

                                <td><a href="PageControl?command=selectedPartner&param2=<%out.print(parTemp.getParID());%>">View Partner</a></td>
                                <td><%out.print(parTemp.getParID());%></td> 
                                <td><%out.print(parTemp.getParName());%></td>
                                <td><%out.print(parTemp.getContactName());%></td>
                                <td><%out.print(parTemp.getParPhone());%></td> 
                                <td><%out.print(parTemp.getCVR());%></td>
                                <td><%out.print(parTemp.geteMail());%></td>

                            </tr>  

                            <%}
                                }%>
                        </tbody> </table>
                </div>




                <div class="module_content" <% if (role.equalsIgnoreCase("partner")) {
                    out.println("style=\"display:none\"");
                    }  %>>

                    <%

                        ArrayList<Employee> searchEmployees = (ArrayList<Employee>) session.getAttribute("searchEmployees");
                        if (searchEmployees != null && searchEmployees.size() > 0) {
                    %><table class="table-condensed" border="1" width="850"> <thead>
                            <tr>
                                <th>Select</th>
                                <th>ID    </th> 
                                <th>Name      </th> 
                                <th>email  </th>
                                <th>Status  </th>


                            </tr> 
                        </thead> <tbody>

                            <%
                                for (Employee empTemp : searchEmployees) {
                            %>
                            <tr>

                                <td><a href="PageControl?command=selectedEmployee&param3=<%out.print(empTemp.getEmpID());%>">View Employee</a></td>
                                <td><%out.print(empTemp.getEmpID());%></td> 
                                <td><%out.print(empTemp.getEmpName());%></td> 
                                <td><%out.print(empTemp.getEmpMail());%></td>
                                <td><%out.print(empTemp.getEmpStatus());%></td>
                            </tr>  


                            <%}
                                }%>
                        </tbody> </table>


                </div>
            </article><!-- end of styles article -->
            <div class="spacer"></div>

            <article class="module width_full">
                <header><h3>Stats</h3></header>
                <div class="module_content">
                    <article class="stats_graph">
                        <img src="http://chart.apis.google.com/chart?chxr=0,0,3000&chxt=y&chs=520x140&cht=lc&chco=76A4FB,80C65A&chd=s:Tdjpsvyvttmiihgmnrst,OTbdcfhhggcTUTTUadfk&chls=2|2&chma=40,20,20,30" width="520" height="140" alt="" />
                    </article>

                    <article class="stats_overview">
                        <div class="overview_today">
                            <p class="overview_day">Today</p>
                            <p class="overview_count">1,876</p>
                            <p class="overview_type">Hits</p>
                            <p class="overview_count">2,103</p>
                            <p class="overview_type">Views</p>
                        </div>
                        <div class="overview_previous">
                            <p class="overview_day">Yesterday</p>
                            <p class="overview_count">1,646</p>
                            <p class="overview_type">Hits</p>
                            <p class="overview_count">2,054</p>
                            <p class="overview_type">Views</p>
                        </div>
                    </article>
                    <div class="clear"></div>
                </div>
            </article><!-- end of stats article -->

            <div class="clear"></div>

        </section>

    </body>

</html>