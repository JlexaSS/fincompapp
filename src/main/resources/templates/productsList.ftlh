<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/admin/product" class="form-inline">
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Искать по имени">
                    <button type="submit" class="btn btn-outline-dark ml-2">Поиск</button>
                </form>
            </div>
        </div>

        <a class="btn btn-dark" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Добавить новый товар
        </a>
        <div class="collapse <#if message??>show</#if>" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}" name="model" placeholder="Модель" />
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
                    <div class="form-group dropdown">
                        <select class="form-control" name="category">
                        <#list categories as category>
                            <option value="${category.id}">${category.name}</option>
                            <#else>
                            <option disabled>Нет категорий</option>
                        </#list>
                        </select>
                        <#if tagError??>
                            <div class="invalid-feedback">
                                ${tagError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group dropdown">
                        <select class="form-control" name="producer">
                            <#list producers as producer>
                                <option value="${producer.id}">${producer.name}</option>
                                <#else>
                                <option disabled>Нет производителей</option>
                            </#list>
                        </select>
                        <#if tagError??>
                            <div class="invalid-feedback">
                                ${tagError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" name="price" placeholder="Цена">
                        <#if tagError??>
                            <div class="invalid-feedback">
                                ${tagError}
                            </div>
                        </#if>
                    </div>
                     <div class="form-group">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile">
                            <label class="custom-file-label" for="customFile">Выберите файл</label>
                        </div>
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
                    <th scope="col">Изображение</th>
                    <th scope="col">Модель</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Категория</th>
                    <th scope="col">Производитель</th>
                    <th scope="col">Цена</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <#list products as product>
                    <tr>
                        <th scope="row">${product.id}</th>
                        <td><img src="/img/${product.filename}" class="card-img-top"></td>
                        <td>${product.model}</td>
                        <td>${product.description}</td>
                        <td>${product.getCategory()}</td>
                        <td>${product.getProducer()}</td>
                        <td>${product.price}</td>
                        <td><a href="/admin/product/edit/${product.id}">Изменить</a></td>
                        <td><a href="/admin/product/delete/${product.id}">Удалить</a></td>
                    </tr>
                <#else>
                    Нет товаров
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>