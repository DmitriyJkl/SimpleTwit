<#import "parts/common.ftl" as temp>
<#import "parts/login.ftl" as login>

<@temp.page>
Login page
<@login.login "/login" />
<a href="/registration">Add new user</a>
</@temp.page>