<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
</head>
<body>
<form action="/uploadFile" enctype="multipart/form-data" method="POST">
    <h2>基本form表单上传文件</h2>
    文件一：<input type="file" name="file1" /><br />
    文件二：<input type="file" name="file2" /><br />
    <input type="submit" value="提交" />
</form>
</body>
</html>