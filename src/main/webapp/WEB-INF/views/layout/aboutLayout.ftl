<#macro mainLayout>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ru" lang="ru">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>SimpleMVC</title>                    
        </head>  
        
        <body >        
            <table width="400" border="1" cellpadding="0" cellspacing="0" >        
                <tr >    
                    <td colspan="2" style="background-color: rgb(240,240,200);">                                                            
                        <#include "header.ftl"/>
                    </td>                                                                    
                </tr>
                <tr >
                    <td colspan="2" style="background-color: rgb(200,240,200);">
                        <#include "menubar.ftl"/>
                    </td>
                </tr>
                <tr>
                    <td width="50" style="background-color: rgb(240,200,200);">                            
                        <#include "aboutSidebar.ftl"/>                                                                                        
                                                                                                                                                        
                    </td>    
                    <td style="background-color: rgb(200,200,240);">
                        <center>                                            
                            <b><#nested/></b>
                        </center>                        
                    </td>                                
                </tr>
                <tr>
                    <td colspan="2" style="background-color: rgb(240,240,200);">
                        <#include "footer.ftl"/>
                    </td>
                </tr>
            </table>
        </body>
    </html>
</#macro>