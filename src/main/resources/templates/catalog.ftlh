<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <div class="form-row">
            <div class="form-group col-md-12 mb-3">
                <form method="get" action="/catalog" class="form-inline">
                    <div class="col col-md-auto">
                        <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Искать по наименованию">
                    </div>
                    <div class="col col-md-auto">
                    <select class="form-control form-padding" name="category_filter">
                            <option value="">Искать по категории</option>
                        <#list categories as category>
                            <option <#if category_filter?hasContent && category.id == category_filter.id>selected</#if> value="${category.id}">${category.name}</option>
                        <#else>
                            <option disabled>Нет категорий</option>
                        </#list>
                    </select>
                    </div>
                    <div class="col col-md-auto" >
                    <select class="form-control form-padding" name="producer_filter">
                        <option value="">Искать по производителю</option>
                        <#list producers as producer>
                            <option <#if producer_filter?hasContent && producer.id == producer_filter.id>selected</#if> value="${producer.id}">${producer.name}</option>
                        <#else>
                            <option disabled>Нет производителей</option>
                        </#list>
                    </select>
                    </div>
                    <div class="col col-md-auto">
                    <button type="submit form-padding" class="btn btn-outline-dark ml-2">Поиск</button>
                    </div>
                </form>
            </div>
        </div>
        <#if cart?hasContent>
            <div class="alert alert-success  alert-dismissible fade show" role="alert">
                <img style="max-width: 50px; box-sizing: border-box" src="/img/true.png" alt="">
                Товар успешно добавлен в корзину!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <#list products as product>
            <div class="card flex-row flex-wrap mt-3" >
                <div class="card-header border-0">
                    <img style="max-width: 200px; box-sizing: border-box" src="/img/${product.filename}" alt="">
                </div>
                <div class="card-block px-2">
                    <h4 class="card-title">${product.model}</h4>
                    <p class="card-text">${product.description}</p>
                    <h3 class="card-text" style="padding-left: 650px;: ">${product.price} ₽</h3>
                    <form method="post" action="/catalog">
                        <p style="padding-left: 650px;: "><input class="form-control"type="number" name="count" value="1"></p>
                        <input type="hidden" name="product" value="${product.id}" />
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <p style="padding-left: 650px;: "><button type="submit" class="btn btn-outline-dark ">Добавить в корзину</button></p>
                    </form>
                </div>
                <div class="w-100"></div>
                <div class="card-footer w-100 text-muted">
                    ${product.getCategory()} -> ${product.getProducer()}
                </div>
            </div>
        <#else>
            <div><h3>Нет товаров</h3></div>
        </#list>
    </div>
</@c.page>