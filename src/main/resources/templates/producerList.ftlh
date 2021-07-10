<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/admin/producer" class="form-inline">
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Искать по имени">
                    <button type="submit" class="btn btn-outline-dark ml-2">Поиск</button>
                </form>
            </div>
        </div>

        <a class="btn btn-dark" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Добавить нового производителя
        </a>
        <div class="collapse <#if message??>show</#if>" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}" name="name" placeholder="Введите название производителя" />
                        <#if textError??>
                            <div class="invalid-feedback">
                                ${textError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="country" placeholder="Страна">
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
                    <th scope="col">Страна</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <#list producers as producer>
                    <tr>
                        <th scope="row">${producer.id}</th>
                        <td>${producer.name}</td>
                        <td>${producer.country}</td>
                        <td><a href="/admin/producer/edit/${producer.id}">Изменить</a></td>
                        <td><a href="/admin/producer/delete/${producer.id}">Удалить</a></td>
                    </tr>
                <#else>
                    Нет производителей
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>