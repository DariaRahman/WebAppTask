<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="container my-3">
        <H1 align="center">List Of Users</H1>
    </div>

    <div insert="~{header :: header}"></div>
    <form id="delUpdateBlock" action="/user/upd" method="post">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div>
                <input type="submit" value="unlock" name="buttonType" class="btn btn-outline-success">

                <input type="submit" value="lock" name="buttonType" class="btn btn-outline-warning">

                <input type="submit" value="delete" name="buttonType" class="btn btn-outline-danger">
                <input type="hidden" name="_csrf" value="${_csrf.token}" >
            </div>
        </nav>
    </form>

    <table class="table table-striped table-bordered ">
    <tr>
        <th><input type="checkbox" onClick="toggle(this)"/>Select All<br/></th>
<#--        <th><input form="delUpdateBlock" type="checkbox" onClick="toggle(this)" value="0"/>Select All<br/></th>-->
<#--        <th><input form="delUpdateBlock" type="text"/><input type="checkbox" onClick="toggle(this)"/>Select All<br/></th>-->
<#--        <th><input form="delUpdateBlock" type="button" onClick="toggle(this)" value="0"/>Select All<br/></th>-->
        <th>Id</th>
        <th>Name</th>
        <th>Mail</th>
        <th>Date Of Registration</th>
        <th>Last Activity</th>
        <th>Status</th>
    </tr>

    <#list users as user>
        <tr each="user : ${user}">
<#--            <td class="align-middle"><input type="checkbox" name="checkboxName" value="${user.id}"><br/></td>-->
            <td class="align-middle"><input form="delUpdateBlock" name="${user.id}" type="checkbox" class="checkboxName" value="${user.id}"><br/></td>
<#--            <td class="align-middle"><input form="delUpdateBlock" name="${user.id}" type="checkbox" name="checkboxName" value="${user.id}"><br/></td>-->

<#--            <td class="align-middle">-->
<#--                <input type="checkbox" name="checkboxName" value="${user.id}">-->
<#--                <input form="delUpdateBlock" name="${user.id}" type="hidden" value="${user.id}">-->
<#--            </td>-->
            <td class="align-middle">${user.id}</td>
            <td class="align-middle">${user.username}</td>
            <td class="align-middle">${user.mail}</td>
            <td class="align-middle">${user.lastActivity}</td>
            <td class="align-middle">${user.registered}</td>
            <td class="align-middle">
                <#if user.isActive() == true>
                true
                    <#else>
                false
                </#if>
            </td>
<#--            <td class="align-middle">${user.enabled}</td>-->
        </tr>

    </#list>
    </table>

    <script language="JavaScript">
        var signal = true;
        function toggle(source) {
            console.log(1);
            checkboxes = document.getElementsByClassName('checkboxName');
            for (var i = 0, n = checkboxes.length; i < n; i++) {
                checkboxes[i].checked = source.checked;
                //checkboxes[i].click();
            }
        }
    </script>
</@c.page>


