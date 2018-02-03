<?php
function doDB(){
	global $mysqli;
	$mysqli=mysqli_connect("localhost","root","278948","normal");
	if(mysqli_connect_errno()){
		printf("连接失败：%s\n",mysqli_connect_errno());
		exit();
	}
}
function emailChecker($email){
	global $mysqli, $safe_email,$check_res;
	$safe_email=mysqli_real_escape_string($mysqli,$email);
	$check_sql="select id from subsceibers where email='".$safe_email."'";
	$check_res=mysqli_query($mysqli,$check_sql) or die(mysqli_error($mysqli));
}
?>