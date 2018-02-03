<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
<?php
$dirname="D:";
$dh=opendir($dirname) or die("不能打开文件夹");

while(!(($file=readdir($dh))===false)){
	if(is_dir("$dirname/$file")){
		echo "(D)";
	}
	echo $file."<br/>";
}
closedir($dh);
?>
</body>
</html>