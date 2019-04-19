<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {

        var tb = [{
            iconCls: 'icon-add',
            text: '添加',
            handler: function () {
                //alert('编辑按钮')
                $('#dd_banner').dialog('open');

            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '修改',
            handler: function () {
                $('#dg_banner').edatagrid('saveRow');
            }
        }, '-', {
            iconCls: 'icon-delete',
            text: '删除',
            handler: function () {

                $('#dg_banner').edatagrid('destroyRow');
            }
        }, '-', {
            iconCls: 'icon-save',
            text: '保存',
            handler: function () {
                //alert('帮助按钮')
                $('#dg_banner').edatagrid('saveRow');
            }
        }];

        $('#dg_banner').edatagrid({
            method: 'get',
            url: '${pageContext.request.contextPath}/banner/select',
            saveUrl: '${pageContext.request.contextPath}/banner/update',
            updateUrl: '${pageContext.request.contextPath}/banner/update',
            destroyUrl: '${pageContext.request.contextPath}/banner/delete',
            fit: true,
            fitColumns: true,
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 25, 30, 35],
            columns: [[
                {
                    field: 'title', title: '标题', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {field: 'createDate', title: '添加日期', width: 100}
            ]],
            toolbar: tb,

            view: detailview,
            //rowIndex:行的索引
            //rowData ：行数据
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/jsp/images/shouye/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.title + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });
    })

    function addBanner() {
        // call 'submit' method of form plugin to submit the form
        $('#ff').form('submit', {
            url: '${pageContext.request.contextPath}/banner/insert',
            onSubmit: function () {


            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.flag) {
                    $('#dd_banner').dialog('close');
                    $('#dg_banner').edatagrid("load")
                } else {
                    alert("添加失败")
                }
            }
        });

    }

    function closeff() {
        $('#dd_banner').dialog('close');
    }

</script>
<table id="dg_banner"></table>
<div id="dd_banner" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    addBanner();
				}
			},{
				text:'关闭',
				handler:function(){
				   closeff();
				}
			}]">

    <form id="ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">标题:</label>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <%--<input type="hidden" name="createDate" value='<c:out>${currentTime}</c:out>'/>--%>
        <%--<div>
            <label for="email">Email:</label>
            <input id="email" class="easyui-validatebox" type="text" name="email" data-options="validType:'email'"/>
        </div>--%>
        <input class="easyui-filebox" name="image" style="width:200px">
    </form>
</div>
