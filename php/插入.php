<?php
$mysqli=new mysqli("localhost","root","278948","normal");
if(mysqli_connect_errno()){
	printf("连接失败：%s\n",mysqli_connect_errno());
	exit();
}else{
	$clean_text= mysqli_real_escape_string($mysqli,$_POST['testfield']);
	$sql="insert into nihao (testField) values ('".$clean_text."')";
	$res=mysqli_query($mysqli,$sql);
	if($res===true){
		echo "您已经插入一条记录";
	}else{
		printf("不能插入一条记录，原因是：%s\n",mysqli_errno($mysqli));
	}
	mysqli_close($mysqli);
}
?>