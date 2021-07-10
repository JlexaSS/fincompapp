<#import "parts/common.ftlh" as s>
<@s.page>
    <div>
        <h3>Редактор товара</h3>
        <form action="/admin/product/edit" method="post" enctype="multipart/form-data">
            <div class="form-group row ">
                <div class="input-group mb-3">
                    <div class="col">
                        <label for="model">Модель:</label>
                        <input type="text" class="form-control" id="model" name="model" value="${products.model}">
                    </div>
                    <div class="col">
                        <label for="description">Описание:</label>
                        <input type="text" class="form-control" id="description" name="description" value="${products.description}">
                    </div>
                    <div class="col">
                        <label for="category">Категория:</label>
                        <select class="form-control" id="category" name="category" value="${products.getIdCategory()}">
                            <#list categories as category>
                                <option value="${category.id}">${category.name}</option>
                            <#else>
                                <option disabled>Нет производителей</option>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <label for="producer">Производитель:</label>
                    <select class="form-control" id="producer" name="producer" value="${products.getIdProducer()}">
                        <#list producers as producer>
                            <option value="${producer.id}">${producer.name}</option>
                        <#else>
                            <option disabled>Нет производителей</option>
                        </#list>
                    </select>
                </div>
                <div class="col">
                    <label for="price">Цена:</label>
                    <input class="form-control" type="number" id="price" name="price" value="${products.price}">
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <label for="customFile">Файл:</label>
                    <input type="file" class="form-control" name="file" id="customFile">
                    <input type="hidden" value="${products.id}" name="productId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </div>
            </div>
            <div class=" form-group row">
                <div class="col">
                    <button class="btn btn-dark" type="submit">Изменить</button>
                </div>
            </div>
        </form>
    </div>
</@s.page>
