<#import "parts/common.ftlh" as s>
<@s.page>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Имя</th>
                <th scope="col">Роль</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td scope="row">${user.id}</td>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#sep></#list></td>
                    <td><a href="/admin/user/edit/${user.id}">Изменить</a></td>
                    <td><a href="/admin/user/delete/${user.id}">Удалить</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@s.page>
