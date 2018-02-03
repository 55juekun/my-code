<?php
function doDB(){
	global $mysqli;
	$mysqli=mysqli_connect("localhost","root","278948","normal");
	if(mysqli_connect_errno()){
		printf("连接失败：%s\n",mysqli_connect_errno());
		exit();
	}
}
?>