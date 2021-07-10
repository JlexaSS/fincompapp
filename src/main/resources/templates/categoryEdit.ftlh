<#import "parts/common.ftlh" as s>
<@s.page>
    <div>
        <h3>Редактор категорий</h3>
        <form action="/admin/category/edit" method="post">
            <input type="text" name="name" value="${categories.name}">
            <input type="text" name="description" value="${categories.description}">
            <input type="hidden" value="${categories.id}" name="categoryId">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Изменить</button>
        </form>
    </div>
</@s.page>
