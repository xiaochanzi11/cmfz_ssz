<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {

        var tb = [{
            iconCls: 'icon-add',
            text: '注册',
            handler: function () {
                //alert('编辑按钮')
                $('#dd_user').dialog('open');

            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '更改选中账户状态',
            handler: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/upStatus",
                    success: function () {
                        $('#dg_user').edatagrid("load")
                    }
                })
            }
        }, '-', {
            iconCls: 'icon-delete',
            text: '删除',
            handler: function () {
                $('#dg_user').edatagrid('destroyRow');
            }
        }, '-', {
            iconCls: 'icon-save',
            text: '保存',
            handler: function () {
                //alert('帮助按钮')
                $('#dg_user').edatagrid('saveRow');
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '数据导出',
            handler: function () {
                exportXls()
            }
        }];

        $('#dg_user').edatagrid({
            method: 'get',
            url: '${pageContext.request.contextPath}/user/selectAll',
            saveUrl: '${pageContext.request.contextPath}/user/update',
            updateUrl: '${pageContext.request.contextPath}/user/update',
            destroyUrl: '${pageContext.request.contextPath}/user/delete',
            fit: true,
            fitColumns: true,
            pagination: true,
            autoSave: true,
            pageSize: 5,
            pageList: [5, 10, 25, 30, 35],
            columns: [[
                {
                    field: 'name', title: '姓名', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {
                    field: 'dharms', title: '法名', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {
                    field: 'sex', title: '性别', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {
                    field: 'phone', title: '电话', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {
                    field: 'password', title: '密码', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {field: 'createDate', title: '注册日期', width: 100}
            ]],
            toolbar: tb,

            view: detailview,
            //rowIndex:行的索引
            //rowData ：行数据
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/jsp/images/user/' + rowData.headImg + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>姓名: ' + rowData.name + '</p>' +
                    '<p>账户状态: ' + rowData.status + '</p>' +
                    '<p>个人签名: ' + rowData.sign + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });
    })

    function regist() {

        $('#ff').form('submit', {
            url: '${pageContext.request.contextPath}/user/insert',
            onSubmit: function () {

            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.flag) {
                    $('#dd_user').dialog('close');
                    $('#dg_user').edatagrid("load")
                } else {
                    alert("注册失败")
                }
            }
        });

    }

    function closeff() {
        $('#dd_user').dialog('close');
    }

    function exportXls() {
        window.location.href = ("${pageContext.request.contextPath}/user/exportXls")
    }

    function upStatus() {

    }

</script>
<table id="dg_user"></table>

//注册用户
<div id="dd_user" class="easyui-dialog" title="注册账号" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    regist();
				}
			},{
				text:'关闭',
				handler:function(){
				   closeff();
				}
			}]">

    <form id="ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">姓名:</label>
            <input id="name" class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
        </div>
        <div>
            <label for="dharms">法名:</label>
            <input id="dharms" class="easyui-validatebox" type="text" name="dharms" data-options="required:true"/>
        </div>
        <div>
            <label>性别:</label>
            <input type="radio" style="outline:none;" name="sex" value=1 checked="checked"><span>男</span>
            <input type="radio" style="outline:none;" name="sex" value=0><span>女</span>

        </div>
        <%--<div>
            <label for="name">省份:</label>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <label for="city">城市:</label>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>--%>
        <div>
            <label for="phone">电话:</label>
            <input id="phone" class="easyui-validatebox" type="text" name="phone" data-options="required:true"/>
        </div>
        <div>
            <label for="password">密码:</label>
            <input id="password" class="easyui-validatebox" type="text" name="password" data-options="required:true"/>
        </div>

        <div>
            <label for="head_img">头像:</label>
            <input id="head_img" class="easyui-filebox" name="image" style="width:200px"/>
        </div>

    </form>
</div>
