<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
</head>
<body>
	<h1>欢迎</h1>
	<a href="emp?iid=1&name=啥啥啥">点击1</a><br>
	<a href="emp/2">点击2</a><br>
	<a href="#" onclick="getjson()">aa</a>
	
	<hr>
	<h3>文件上传</h3>
    <form action="upload" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>请选择文件:</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
    
    <hr>
    <a href="download">下载文件</a>
    
</body>
<script  type="text/javascript">
	function getjson(){
		//alert("hh");
		$.ajax({
			url:'getjson',
			type:'post',
			data:{"name":"哈哈哈"},
			success:function(data){
				//alert(data);
				alert(data["id"]+","+data["name"]);
			},
			dataType:'json'
		});
	}
</script>

</html>