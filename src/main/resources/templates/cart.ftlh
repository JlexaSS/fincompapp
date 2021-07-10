<#import "parts/common.ftlh" as c>
<@c.page>
    <div>
        <div class="modal fade" id="buyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">К покупке!</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <#assign x = 0>
                        <#list carts as cart>
                            <p>${cart.getModel()} (${cart.getCount()} шт.) = ${cart.getCount() * cart.getPrice()}₽</p>
                            <#assign x =  x +(cart.getPrice()*cart.getCount())>
                        </#list>
                        <h4>Итого: ${x} ₽</h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Закрыть</button>
                        <button type="button" class="btn btn-dark">Перейти к оформлению</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-10">
                <h2>Корзина:</h2>
            </div>
            <div class="col-2">
                <h2>${x} ₽</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-10">
            </div>
            <div class="col-2">
                <button type="button" <#if x==0>disabled</#if> class="btn btn-dark" data-toggle="modal" data-target="<#if x==0>#disabled<#else>#buyModal</#if>">
                    К покупке
                </button>
            </div>
        </div>
        <#list carts as cart>
            <div class="card flex-row flex-wrap mt-3" >
                <div class="card-header border-0">
                    <img style="max-width: 200px; box-sizing: border-box" src="/img/${cart.getFilename()}" alt="">
                </div>
                <div class="card-block px-2">
                    <h4 class="card-title">${cart.getModel()}</h4>
                    <p class="card-text">${cart.getPrice()} ₽</p>
                    <p class="card-text">Количество:
                    <form action="/cart" method="post">
                        <div class="input-group mb-3">
                            <input class="form-control col-4"type="number" name="count" aria-describedby="button-addon2" value="${cart.count}">
                            <input type="hidden" name="cart" value="${cart.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" id="button-addon2" class="btn btn-outline-dark">Изменить</button>
                        </div>
                    </form>
                    </p>
                    <a href="/cart/${cart.id}" class="btn btn-outline-dark ">Удалить</a>
                </div>
                <div class="w-100"></div>
            </div>
        <#else>
            <div><h3>Нет товаров</h3></div>
        </#list>
    </div>
</@c.page>