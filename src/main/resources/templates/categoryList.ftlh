<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/admin/category" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Искать по имени">
                <button type="submit" class="btn btn-outline-dark ml-2">Поиск</button>
            </form>
        </div>
    </div>

    <a class="btn btn-dark" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить новую категорию
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}" name="name" placeholder="Введите название категории" />
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="description" placeholder="Описание">
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-outline-dark">Добавить</button>
                </div>
            </form>
        </div>
    </div>

        <div>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Название</th>
                        <th scope="col">Описание</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
            <#list categories as category>
                <tr>
                    <th scope="row">${category.id}</th>
                    <td>${category.name}</td>
                    <td>${category.description}</td>
                    <td><a href="/admin/category/edit/${category.id}">Изменить</a></td>
                    <td><a href="/admin/category/delete/${category.id}">Удалить</a></td>
                </tr>
            <#else>
                Нет категорий
            </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>