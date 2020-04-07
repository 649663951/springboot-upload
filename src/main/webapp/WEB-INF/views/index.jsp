<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>文件上传</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/spark-md5.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/patchUpload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/upload/webuploader/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/upload/webuploader/webuploader.min.js"></script>
    <link href="<%=request.getContextPath()%>/upload/webuploader/webuploader.css" type="css/text" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/upload/admin/bootstrap/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>upload/admin/bootstrap/jquery-ui.js"></script>
    <link href="<%=request.getContextPath()%>/upload/admin/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="<%=request.getContextPath()%>/upload/admin/bootstrap/bootstrap.min.js"></script>
</head>
<body>
<div style="margin: 30px;">	
	<h2>文件上传</h2>	
	<div style="margin: 20px 20px 20px 0;">
		<div id="picker" class="form-control-focus"></div>
		<input id="file" type="file"/>
	</div>
	<div id="thelist" class="uploader-list"></div>
	<button id="upload" type="button" class="btn btn-warning">开始同步</button>

</div>
</body>
</html>