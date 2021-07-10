<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div><H3>Регистрация</H3><div>
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>