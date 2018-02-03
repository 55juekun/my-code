<?php 
$mysqli=mysqli_connect("localhost","root","278948","normal");
$insert_sql="insert into store_item_color
 values ('null')";
$insert_res=mysqli_query($mysqli,$insert_sql) or die (mysqli_error($mysqli));
	echo "done!"

?>