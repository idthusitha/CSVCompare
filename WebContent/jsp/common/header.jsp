
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" type="text/css" href="<spring:url value='/HibernateTest/css/style.css' />" /> 
        <link rel="stylesheet" href="/HibernateTest/css/jquery-ui.css" />
        <link rel="stylesheet" href="/HibernateTest/css/date-style.css" />
        <link rel="stylesheet" type="text/css" href="/HibernateTest/css/menu.css" >
        <link rel="stylesheet" type="text/css" href="/HibernateTest/css/style.css" >
        <link rel="stylesheet" href="/HibernateTest/css/validationEngwine.jquery.css" type="text/css"/>
        <script src="/HibernateTest/js/jquery-1.8.3.js"></script>
        <script src="/HibernateTest/js/jquery-ui.js"></script>
        <script src="/HibernateTest/js/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>       
        <script src="/HibernateTest/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

        <script>
            $(function() {
                $("#accordion").accordion();
            });
           
            function clear(formId) {
                $(':input', formId)
                        .not(':radio, :button, :submit, :reset')
                        .val('')
                        .removeAttr('checked')
                        .removeAttr('selected');
            }
        </script> 
        <title>Server Configuration System</title>
        <style>
            .messageblock {
                color: #00ff00;
                background-color: #ffEEEE;
                border: 3px solid #00ff00;
                padding: 8px;
                margin: 8px;
                text-align:center; 	
            }
            .errorblock {
                color: #ff0000;
                background-color: #ffEEEE;
                border: 3px solid #ff0000;
                padding: 8px;
                margin: 8px;
                text-align:center; 	
            }
        </style>
    </head>
    <body>
    
        <div id="header">
            <div id="headerContent">
                <div style="float: right ">
                	<form action="#">
                   
                    </form>
                </div>
                <br /><br />
                <div style="float: left;height: 50px">
                    <h1 style="margin-bottom: 0;">Report Label Mapping Setup</h1>
                    <h3 style="margin-bottom: 0;">English ..etc</h3>
                </div>              
                <div style="float: right; width: 300px;">
                    <b>Welcome :</b><font color="red"> <%=session.getAttribute("userName")!= null ? (String)session.getAttribute("userName"): "-"%> </font>
                </div>
            </div>  
            <div id="mainMenu">
            	             
            </div> 
        </div>

        <div>
            <div id="container">
                <div id="bodyContainer">
                    <div>&nbsp;</div>
                    

