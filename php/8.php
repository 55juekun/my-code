<?php
$mysqli=new mysqli("localhost","root","278948","normal");
if(mysqli_connect_errno()){
	printf("连接失败：%s\n",mysqli_connect_errno());
	exit();
}else{
	$sql="select * from nihao ";
	$res=mysqli_query($mysqli,$sql);
	if($res==true){
		while($newArray=mysqli_fetch_array($res,MYSQLI_ASSOC)){
			$id=$newArray['id'];
			$testField=$newArray['testField'];
			echo "The ID is ".$id." and the Text is: ".$testField."<br/>";
		}
	}else{
		printf("不能读出数据，原因是：%s\n",mysqli_errno($mysqli));
	}
	mysqli_free_result($res);
	mysqli_close($mysqli);
}
?>