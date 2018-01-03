<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>allBooksManger</title>
    <link href="${pageContext.request.contextPath}/css/base.css"
          type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/tab.css"
          type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/item.css"
          type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/item_do.css"
          type="text/css" rel="stylesheet">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/uploadify.css"
          type="text/css">
</head>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.uploadify.v2.0.3.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/swfobject.js"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/common.js"></script>

</head>
<body style="margin:1px;" id="ff">
<table id="dg" title="订单信息管理" class="easyui-datagrid" pagination="true"
       rownumbers="true" fit="true" data-options="pageSize:10"
       url="${pageContext.request.contextPath}/book/listAll.do" toolbar="#tb">
    <thead data-options="frozen:true">
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="10" align="center" hidden="true">编号</th>
        <th field="subtitle" width="10" align="center" hidden="true">副标题</th>
        <th field="title" width="110" align="center">客户</th>
        <th field="author" width="70" align="center">瓶气种类</th>
        <th field="isbn" width="120" align="center">瓶气数量</th>
        <th field="path" width="100" align="center" formatter="formatProPic">已回收瓶气数量</th>
        <th field="marketPrice" width="70" align="center">应收款项</th>
        <th field="pages" width="50" align="center">已付款项</th>
        <th field="publisher" width="120" align="center">销售人员</th>
        <th field="binding" width="10" align="center" hidden="true">执行人员</th>
        <th field="hot" width="10" align="center" hidden="true">发票状态</th>
        <th field="status" width="55" align="center"
            formatter="formatStatus">订单状态
        </th>
        <th field="supply" width="50" align="center">备注</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        &nbsp;客户名称：&nbsp;<input type="text" id="biaoti" size="20"
                              onkeydown="if(event.keyCode==13) searchBook()"/>&nbsp;&nbsp;欠款：&nbsp;<input
            type="text" id="zuozhe" size="20"
            onkeydown="if(event.keyCode==13) searchBook()"/>&nbsp;
        &nbsp;未回收气瓶：&nbsp;<input type="text" id="bianma" size="20"
                                 onkeydown="if(event.keyCode==13) searchBook()"/>&nbsp; <a
            href="javascript:searchBook()" class="easyui-linkbutton"
            iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg_c" class="easyui-dialog"
     style="width: 300px;height:450px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" buttons="#dlg_c-buttons">
    <form id="fm_c" method="post">
        <table cellspacing="8px" id="tab">
        </table>
    </form>
</div>

</body>
<script type="text/javascript">
    var url;

    function searchBook() {
        $("#dg").datagrid('load', {
            "title": $("#biaoti").val(),
            "author": $("#zuozhe").val(),
            "isbn": $("#bianma").val(),
        });
    }


    function openbookInfoPage(id) {
        window.parent.openTab('书籍摆放信息', 'storeInfo.jsp?id=' + id,
                'icon-shujias');
    }

    function formatProPic(val, row) {
        return "<img width=80 height=110 src='../" + val + "'>";
    }
    function formatStatus(val, row) {
        if (row.status == 1)
            return "<div style='color:red;'>已上架</div>";
        else
            return "<div style='color:gray;'>未上架</div>";
    }

</script>
</html>