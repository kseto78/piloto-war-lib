<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="framework" uri="/framework" %>

 <script type="text/javascript">
    /*<![CDATA[*/
    function IEHoverPseudo() {

        var navItems = document.getElementById("primary-nav").getElementsByTagName("li");

        for (var i=0; i<navItems.length; i++) {
            if(navItems[i].className == "menubar") {
                navItems[i].onmouseover=function() { this.className += " over"; }
                navItems[i].onmouseout=function() { this.className = "menubar"; }
            }
        }

    }
    window.onload = IEHoverPseudo;
    /*]]>*/
    </script>

    <style type="text/css">

        ul#primary-nav,
        ul#primary-nav ul {
            width: 150px;
            margin: 0;
            padding: 0;
            background: #fff; /* IE6 Bug */
            font-size: 100%;
        }

        ul#primary-nav {
            float: left;
            width: 600px;
        }

        ul#primary-nav:after {
            content: ".";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }

        ul#primary-nav li {
            position: relative;
            list-style: none;
            float: left;
            width: 150px; /* Width of Menu Items */
        }

        ul#primary-nav li a,
        ul#primary-nav li li a {
            display: block;
            text-decoration: none;
            color: #777;
            padding: 5px;
            border: 1px solid #ccc;
        }

        /* Fix IE. Hide from IE Mac \*/
        * html ul#primary-nav li { float: left; height: 1%; }
        * html ul#primary-nav li a { height: 1%; }
        /* End */

        ul#primary-nav ul {
            position: absolute;
            display: none;
        }

        ul#primary-nav ul ul {
            left: 150px;
            top: 0;
        }

        ul#primary-nav li ul li a { padding: 2px 5px; } /* Sub Menu Styles */

        ul#primary-nav li:hover ul ul,
        ul#primary-nav li:hover ul ul ul,
        ul#primary-nav li.over ul ul,
        ul#primary-nav li.over ul ul ul { display: none; } /* Hide sub-menus initially */

        ul#primary-nav li:hover ul,
        ul#primary-nav li li:hover ul,
        ul#primary-nav li li li:hover ul,
        ul#primary-nav li.over ul,
        ul#primary-nav li li.over ul,
        ul#primary-nav li li li.over ul { display: block; } /* The magic */

        ul#primary-nav li.menubar { background: transparent url(images/arrow-down.gif) right center no-repeat; }
        ul#primary-nav li li.menubar { background: transparent url(images/arrow-right.gif) right center no-repeat; }
 
        ul#primary-nav li:hover,
        ul#primary-nav li.over { background-color: #f9f9f9 !important; }

        ul#primary-nav li a:hover { color: #E2144A; }
    </style>



	<framework:useMenuDisplayer name="CSSListMenu" config="MenuDisplayerStrings"  id="primary-nav" locale="WW_TRANS_I18N_LOCALE" bundle="ApplicationResources"  permissions="rolesAdapter">
    	<framework:displayMenu name="mainMenu"/>
	</framework:useMenuDisplayer>
