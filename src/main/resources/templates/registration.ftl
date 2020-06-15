<#import "parts/common.ftl" as temp>
<#import "parts/login.ftl" as login>

<@temp.page>
Add new user
${message!}
<@login.login "/registration" />
</@temp.page>