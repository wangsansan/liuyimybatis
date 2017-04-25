<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用戶列表</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap-paginator.js"></script>
<script>
  $(function () {
    var carId = 1;
    $.ajax({
      url: "/OA/Setting/GetDate",
      datatype: 'json',
      type: "Post",
      data: "id=" + carId,
      success: function (data) {
        if (data != null) {
          $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
            $("#list").append('<table id="data_table" class="table table-striped">');
            $("#list").append('<thead>');
            $("#list").append('<tr>');
            $("#list").append('<th>Id</th>');
            $("#list").append('<th>部门名称</th>');
            $("#list").append('<th>备注</th>');
            $("#list").append('<th> </th>');
            $("#list").append('</tr>');
            $("#list").append('</thead>');
            $("#list").append('<tbody>');
            $("#list").append('<tr>');
            $("#list").append('<td>' + item.Id + '</td>');
            $("#list").append('<td>' + item.Name + '</td>');
            $("#list").append('<td>备注</td>');
            $("#list").append('<td>');
            $("#list").append('<button class="btn btn-warning" onclick="Edit(' + item.Id + ' );">修改</button>');
            $("#list").append('<button class="btn btn-warning" onclick="Edit(' + item.Id + ' );">删除</button>');
            $("#list").append('</td>');
            $("#list").append('</tr>');
            $("#list").append('</tbody>');

            $("#list").append('<tr>');
            $("#list").append('<td>内容</td>');
            $("#list").append('<td>' + item.Message + '</td>');
            $("#list").append('</tr>');
            $("#list").append('</table>');
          });
          var pageCount = eval("(" + data + ")").pageCount; //取到pageCount的值(把返回数据转成object类型)
          var currentPage = eval("(" + data + ")").CurrentPage; //得到urrentPage
          var options = {
            bootstrapMajorVersion: 2, //版本
            currentPage: currentPage, //当前页数
            totalPages: pageCount, //总页数
            itemTexts: function (type, page, current) {
              switch (type) {
                case "first":
                  return "首页";
                case "prev":
                  return "上一页";
                case "next":
                  return "下一页";
                case "last":
                  return "末页";
                case "page":
                  return page;
              }
            },//点击事件，用于通过Ajax来刷新整个list列表
            onPageClicked: function (event, originalEvent, type, page) {
              $.ajax({
                url: "/OA/Setting/GetDate?id=" + page,
                type: "Post",
                data: "page=" + page,
                success: function (data1) {
                  if (data1 != null) {
                    $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
                      $("#list").append('<table id="data_table" class="table table-striped">');
                      $("#list").append('<thead>');
                      $("#list").append('<tr>');
                      $("#list").append('<th>Id</th>');
                      $("#list").append('<th>部门名称</th>');
                      $("#list").append('<th>备注</th>');
                      $("#list").append('<th> </th>');
                      $("#list").append('</tr>');
                      $("#list").append('</thead>');
                      $("#list").append('<tbody>');
                      $("#list").append('<tr>');
                      $("#list").append('<td>' + item.Id + '</td>');
                      $("#list").append('<td>' + item.Name + '</td>');
                      $("#list").append('<td>备注</td>');
                      $("#list").append('<td>');
                      $("#list").append('<button class="btn btn-warning" onclick="Edit(' + item.Id + ' );">修改</button>');
                      $("#list").append('<button class="btn btn-warning" onclick="Edit(' + item.Id + ' );">删除</button>');
                      $("#list").append('</td>');
                      $("#list").append('</tr>');
                      $("#list").append('</tbody>');

                      $("#list").append('<tr>');
                      $("#list").append('<td>内容</td>');
                      $("#list").append('<td>' + item.Message + '</td>');
                      $("#list").append('</tr>');
                      $("#list").append('</table>');
                    });
                  }
                }
              });
            }
          };
          $('#example').bootstrapPaginator(options);
        }
      }
    });
  })
</script>
</head>
<body>
	<div class="span9">
        <label>用戶列表</label>
        <hr />
        <div id="list"></div>

        <div id="example"></div>
    </div>
</body>
</html>