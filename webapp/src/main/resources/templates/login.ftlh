<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <H1 align="center">Login page<H1>
    <H4 align="center"><@l.login "/login" />
        <a href="/registration">Add new user</a></H4>
</@c.page>

