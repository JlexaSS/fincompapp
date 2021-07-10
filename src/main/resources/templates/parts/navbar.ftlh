<#include "security.ftlh">
<#import "login.ftlh" as log>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">FinComp</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/catalog">Каталог</a>
            </li>
        </ul>


        <div class="navbar-text mr-3"><a href="/cart">Корзина</a></div>
        <#if name !="Гость">

            <#if isAdmin>
                <div class="mr-3"><a class="btn btn-outline-dark" href="/admin">Админ-панель</a></div>
            </#if>
            <div class="mr-3"><a class="btn btn-outline-dark" href="/admin/profile">${name}</a></div>

            <@log.logout />
        <#else>
            <div class="navbar-text mr-3">${name}</div>
            <a class="btn btn-dark" href="/login">Войти</a>
        </#if>
    </div>
</nav>