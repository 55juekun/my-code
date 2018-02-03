<?php
$mysqli=new mysqli("localhost","root","278948","normal");
if(mysqli_connect_errno()){
	printf("连接失败：%s\n",mysqli_connect_errno());
	exit();
}else{
	printf("主要信息： %s\n",mysqli_get_host_info($mysqli));
	$sql="create table nihao(id int not null primary key auto_increment,testField varchar(75))";
	$res=mysqli_query($mysqli,$sql);
	if($res==true){
		echo "成功创建一个表";
	}else{
		printf ("不能创建一个表，原因是：%s\n",mysqli_errno($mysqli));
	}
	$insert="insert into nihao (testField) values ('some value')";
	$reinsert=mysqli_query($mysqli,$insert);
	if($reinsert===true){
		echo "您已经插入一条记录";
	}else{
		printf("不能插入一条记录，原因是：%s\n",mysqli_errno($mysqli));
	}
	mysqli_close($mysqli);
}
?>