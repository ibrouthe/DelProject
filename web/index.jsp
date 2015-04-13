<%-- 
    Document   : index
    Created on : 08-04-2015, 02:15:23
    Author     : Gruppe 2 Silas, Thomas, Christian, Martin, Ib
--%>

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
                <li class="icn_new_article"><a href="#">New Project</a></li>
                <li class="icn_edit_article"><a href="#">Edit Project</a></li>
            </ul>
            <h3>Partners</h3>
            <ul class="toggle">
                <li class="icn_add_user"><a href="#">Add New Partner</a></li>
                <li class="icn_view_users"><a href="#">View Partners</a></li>
                <li class="icn_folder"><a href="#">Search Partner</a></li>
            </ul>


            <h3>StatisticS</h3>
            <ul class="toggle">
                <li class="icn_categories"><a href="#">Overview</a></li>

                <li class="icn_folder"><a href="\PageControl">Projects</a><input type="hidden" name="command" value="listProjects">
                </li>

                <li class="icn_view_users"><a href="#">Partners</a></li>
                <li class="icn_folder"><a href="#">Funds</a></li>
            </ul>








            <h3>Admin</h3>
            <ul class="toggle">
                <li class="icn_settings"><a href="#">Options</a></li>
                <li class="icn_security"><a href="#">Security</a></li>
                <li class="icn_jump_back"><a href="#">Logout</a></li>
            </ul>

            <footer>
                <hr />
                <p><strong>Datamatiker 2. semesteropgave Gruppe 2</strong></p>

            </footer>
        </aside><!-- end of sidebar -->

        <section id="main" class="column">

            <h4 class="alert_info">7 projects have been edited since your last login.</h4>






            <article class="module width_full">
                <header><h3>Active projects</h3></header>
                <div class="module_content">
                    <p>Nothing listed yet<p>

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