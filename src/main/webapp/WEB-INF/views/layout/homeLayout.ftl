<#macro mainLayout>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ru" lang="ru">
    
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Shared Stories</title>            
			<#include "headerstyles.ftl"/>                                
        </head>  
        
        <body >        
			
			<#nested/>
			
			<#include "baseScripts.ftl"/>
			<#include "storyElements.ftl"/>
            <#include "clientscripts.ftl"/>
            
        </body>
    </html>
</#macro>