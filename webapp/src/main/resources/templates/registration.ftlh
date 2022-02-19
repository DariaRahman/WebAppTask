<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <H1 align="center">Add new user<H1>
    ${message! ''}
            <H4 align="center"> <@l.login "/registration" /></a></H4>
</@c.page>