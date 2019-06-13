<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<script>
    $(function () {

        // 音乐对话框
        $("#dd_music").dialog({
            title: "播放音乐对话框",
            width: 300,
            height: 200,
            //buttons: "#addBtn",
            closed: true,
            minimizable: true,
            collapsible: true,
        })

        var tb = [{
            iconCls: 'icon-search',
            text: '专辑详情',
            handler: function () {
                var c = $('#tt_album').treegrid('getSelected');
                if (c.albumId == null) {
                    c = c.id;
                } else {
                    alert("请选择一个专辑")
                    //c = c.albumId;
                }
                selectAlbumByid(c);

            }
        }, '-', {
            iconCls: 'icon-add',
            text: '添加专辑',
            handler: function () {
                //alert('编辑按钮')
                $('#dd_album').dialog('open');

            }
        }, '-', {
            iconCls: 'icon-add',
            text: '添加章节',
            handler: function () {
                var c = $('#tt_album').treegrid('getSelected');
                if (c.albumId == null) {
                    c = c.id;
                    openinsertChapter(c);
                } else {
                    alert("请选择一个专辑")
                    //c = c.albumId;
                }

            }
        }, '-', {
            iconCls: 'icon-add',
            text: '修改数据',
            handler: function () {
                var c = $('#tt_album').treegrid('getSelected');
                if (c.albumId == null) {
                    //专辑
                    console.log(c.id)
                    openupdateAlbum(c.id)
                } else {
                    //章节
                    console.log(c.id)
                    openupdateChapter(c.id)
                }

            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '音频下载',
            handler: function () {
                var c = $('#tt_album').treegrid('getSelected');
                if (c.albumId == null) {
                    alert("请选择一个音频")
                } else {
                    c = c.id;
                    //alert(c)
                    downLoad(c);
                }


            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '音频在线播放',
            handler: function () {
                var c = $('#tt_album').treegrid('getSelected');
                if (c.albumId == null) {
                    alert("请选择一个章节")
                } else {
                    c = c.id;
                    //alert(c)
                    openMusic(c);
                }
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '数据导出',
            handler: function () {

                exportXls();
                /*$.ajax({
                    url: "${pageContext.request.contextPath}/album/exportXls",

                })*/
            }
        }];

        $('#tt_album').treegrid({
            //后台Controller查询所有专辑以及对应的章节集合
            url: '${pageContext.request.contextPath}/album/select',
            idField: 'id',//用来标识标识树节点   主干树与分支树节点  ID不能有相同  并且使用相同的字段  否则ID冲突
            treeField: 'title',//用来定义树节点   树形表格上要展示的信息   注意使用相同的字段 用来展示对应节点名称
            toolbar: tb,
            fit: true,
            fitColumns: true,
            columns: [[
                {field: 'title', title: '名字', width: 60},
                {
                    field: 'size', title: '章节大小', width: 60, formatter: function (value, rowData, rowIndex) {
                        if (value == null) {
                            return "";
                        } else {
                            var c = value
                            return c;
                        }
                    }
                },
                {
                    field: 'downloadPath', title: '下载路径', width: 60, formatter: function (value, rowData, rowIndex) {

                        if (value == null) {
                            return "";
                        } else {
                            var c = value
                            return c;
                        }
                    }
                },
                {
                    field: 'duration', title: '章节的时长', width: 80, formatter: function (value, rowData, rowIndex) {
                        if (value == null) {
                            return "";
                        } else {
                            var theTime = parseInt(value);// 秒
                            var theTime1 = 0;// 分
                            var theTime2 = 0;// 小时
                            if (theTime > 60) {
                                theTime1 = parseInt(theTime / 60);
                                theTime = parseInt(theTime % 60);
                                if (theTime1 > 60) {
                                    theTime2 = parseInt(theTime1 / 60);
                                    theTime1 = parseInt(theTime1 % 60);

                                }
                            }
                            var result = "" + parseInt(theTime) + "秒";
                            if (theTime1 > 0) {
                                result = "" + parseInt(theTime1) + "分" + result;
                            }
                            if (theTime2 > 0) {
                                result = "" + parseInt(theTime2) + "小时" + result;
                            }
                            return result;
                        }

                    }
                }
            ]]

        });

    })

    function addAlbum() {
        $('#ff_album').form('submit', {
            url: '${pageContext.request.contextPath}/album/addalbum',
            onSubmit: function () {
                return $("#ff_album").form("validate");
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.isadd) {
                    $('#dd_album').dialog('close');
                    $('#tt_album').treegrid('load');
                } else {
                    alert("添加失败, 请重新添加")
                }
            }
        });
    }


    function addChapter() {
        $('#ff_chapter').form('submit', {

            url: '${pageContext.request.contextPath}/chapter/insert',

            onSubmit: function () {

                return $("#ff_chapter").form("validate");
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.isadd) {
                    $('#dd_chapter').dialog('close');
                    $('#tt_album').treegrid('load');
                } else {
                    alert("添加失败, 请重新添加")
                }
            }
        });
    }

    //修改专辑发送请求
    function updateAlbum() {
        $('#updateff_album').form('submit', {

            url: '${pageContext.request.contextPath}/album/update',

            onSubmit: function () {

                return $("#updateff_album").form("validate");
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.isupdate) {
                    $('#update_album').dialog('close');
                    $('#tt_album').treegrid('load');
                } else {
                    alert("修改失败")
                }
            }
        });
    }

    //修改章节发送请求
    function updateChapter() {
        $('#updateff_chapter').form('submit', {

            url: '${pageContext.request.contextPath}/chapter/update',

            onSubmit: function () {

                return $("#updateff_chapter").form("validate");
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.isupdate) {
                    $('#update_chapter').dialog('close');
                    $('#tt_album').treegrid('load');
                } else {
                    alert("修改失败")
                }
            }
        });
    }

    function selectAlbumByid(c) {
        $.ajax({
            url: "${pageContext.request.contextPath}/album/selectAlbumByid",
            type: "get",
            data: "id=" + c,
            dataType: "json",
            success: function (data) {
                $("#albumTitle").html(data.title);
                $("#albumTime").html(data.publishDate);
                $("#albumScore").html(data.score);
                $("#albumAmount").html(data.amount);
                $("#albumAuthor").html(data.author);
                $("#albumBoardCast").html(data.boardcast);
                $("#albumBrief").html(data.brief);
                $("#albumImg").prop("src", "${pageContext.request.contextPath}/jsp//images/audioCollection/" + data.imgPath);

                $("#dd_album_display").dialog("open");

            }
        })
    }

    //修改专辑对话框
    function openupdateAlbum(c) {
        $.ajax({
            url: "${pageContext.request.contextPath}/album/selectOne",
            type: "get",
            data: "id=" + c,
            dataType: "json",
            success: function (data) {
                console.log(data.title)
                $("#updateid").val(data.id);
                $("#updatename").val(data.title);
                $("#updateamount").val(data.amount);
                $("#updatescore").val(data.score);
                $("#updateauthor").val(data.author);
                $("#updateboardcast").val(data.boardcast);
                $("#updatebrief").val(data.brief);
                $("#updateImgPath").html(data.imgPath);
                $("#updatealbumImg").prop("src", "${pageContext.request.contextPath}/jsp//images/audioCollection/" + data.imgPath);

                $("#update_album").dialog("open");
            }
        })
    }

    //修改章节对话框
    function openupdateChapter(c) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chapter/selectOne",
            type: "get",
            data: "id=" + c,
            dataType: "json",
            success: function (data) {
                console.log(data.downloadPath)
                $("#updateid2").val(data.id);
                $("#updatename2").val(data.title);

                $("#updatedownloadPath").html(data.downloadPath);

                $("#update_chapter").dialog("open");

            }
        })
    }

    function openinsertChapter(data) {
        console.log(data)

        if (data != null) {
            console.log("有数据")
            var s1 = "<input name=\"albumId\" value=" + data + " type=\"hidden\"/>"
        }
        $("#selectAlbum").append(s1);
        $('#dd_chapter').dialog('open');
    }

    function downLoad(c) {
        window.location.href = ("${pageContext.request.contextPath}/chapter/download.do?id=" + c)
    }

    function exportXls() {
        window.location.href = ("${pageContext.request.contextPath}/album/exportXls")
    }

    function openMusic(c) {
        //console.log(c)
        $.ajax({
            url: "${pageContext.request.contextPath}/chapter/selectOne",
            type: "get",
            data: "id=" + c,
            dataType: "json",
            success: function (data) {
                var src = '${pageContext.request.contextPath}/jsp/chapter/' + data.downloadPath
                //console.log(src)
                $('#music').attr('src', src)
                $('#dd_music').dialog('open');
            }
        })


    }


</script>
<table id="tt_album" style="width:600px;height:400px"></table>
//专辑详情对话框
<div id="dd_album_display" class="easyui-dialog" title="专辑详情" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'确定',
				handler:function(){
				    $('#dd_album_display').dialog('close');
				}
			}]">
    <p>专辑名称：&nbsp; <span id="albumTitle"></span></p>
    <p>专辑评分：&nbsp; <span id="albumScore"></span>分</p>
    <p>专辑章节：&nbsp; <span id="albumAmount"></span></p>
    <p>专辑作者：&nbsp; <span id="albumAuthor"></span></p>
    <p>专辑播音：&nbsp; <span id="albumBoardCast"></span></p>
    <p>出版时间：&nbsp; <span id="albumTime"></span></p>
    <p>描述 ：&nbsp;<span id="albumBrief"></span>
    <p>专辑封面 :</p><img id="albumImg" width="70px" height="100px"/>

</div>
//添加专辑
<div id="dd_album" class="easyui-dialog" title="添加专辑" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    addAlbum();
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#dd_album').dialog('close');
				}
			}]">
    <form id="ff_album" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">标题:</label>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <label for="amount">集数:</label>
            <input id="amount" class="easyui-validatebox" type="text" name="amount" data-options="required:true"/>
        </div>
        <div>
            <label for="score">评分:</label>
            <input id="score" class="easyui-validatebox" type="text" name="score" data-options="required:true"/>
        </div>
        <div>
            <label for="author">作者:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            <label for="boardcast">播音员:</label>
            <input id="boardcast" class="easyui-validatebox" type="text" name="boardcast" data-options="required:true"/>
        </div>
        <div>
            <label for="brief">简介:</label>
            <input id="brief" class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <input class="easyui-filebox" style="width:150px" name="file">
    </form>

</div>
//修改专辑
<div id="update_album" class="easyui-dialog" title="修改专辑信息" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    updateAlbum();
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#update_album').dialog('close');
				}
			}]">
    <form id="updateff_album" method="post" enctype="multipart/form-data">
        <input id="updateid" type="hidden" name="id" value=""/>
        <div>
            <label for="updatename">标题:</label>
            <input id="updatename" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <label for="updateamount">集数:</label>
            <input id="updateamount" class="easyui-validatebox" type="text" name="amount" data-options="required:true"/>
        </div>
        <div>
            <label for="updatescore">评分:</label>
            <input id="updatescore" class="easyui-validatebox" type="text" name="score" data-options="required:true"/>
        </div>
        <div>
            <label for="updateauthor">作者:</label>
            <input id="updateauthor" class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            <label for="updateboardcast">播音员:</label>
            <input id="updateboardcast" class="easyui-validatebox" type="text" name="boardcast"
                   data-options="required:true"/>
        </div>
        <div>
            <label for="updatebrief">简介:</label>
            <input id="updatebrief" class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <input value="" class="easyui-filebox" style="width:150px" name="file"><span id="updateImgPath"></span>
        <p>专辑封面 :</p><img id="updatealbumImg" width="70px" height="100px"/>
    </form>

</div>

//添加章节
<div id="dd_chapter" class="easyui-dialog" title="添加章节" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    addChapter();
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#dd_chapter').dialog('close');
				}
			}]">
    <form id="ff_chapter" method="post" enctype="multipart/form-data">
        <div id="selectAlbum">
            <label for="cname">标题:</label>
            <input id="cname" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>

        <label>上传文件:</label>
        <input class="easyui-filebox" style="width:150px" name="file">
    </form>

</div>

//修改章节
<div id="update_chapter" class="easyui-dialog" title="修改章节信息" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    updateChapter();
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#updatedd_chapter').dialog('close');
				}
			}]">
    <form id="updateff_chapter" method="post" enctype="multipart/form-data">
        <input id="updateid2" type="hidden" name="id" value=""/>
        <div>
            <label for="updatename2">标题:</label>
            <input id="updatename2" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>

        <label>更改音频文件:</label>
        <input value="" class="easyui-filebox" style="width:150px" name="file"><span id="updatedownloadPath"></span>

    </form>

</div>

//音频播放
<div id="dd_music" title="添加专辑" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'关闭',
				handler:function(){
				    $('#dd_music').dialog('close');
				}
			}]">
    <audio id="music" controls="controls" preload="auto" autoplay="autoplay" loop="loop">
        您浏览器不支持HTML5音频播放器
    </audio>
</div>