<#import "parts/common.ftlh" as s>
<@s.page>
    <div>
        <h3>Редактор категорий</h3>
        <form action="/admin/producer/edit" method="post">
            <input type="text" name="name" value="${producers.name}">
            <input type="text" name="country" value="${producers.country}">
            <input type="hidden" value="${producers.id}" name="producerId">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Изменить</button>
        </form>
    </div>
</@s.page>
