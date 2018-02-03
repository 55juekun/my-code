<?php
session_start();

if(isset($_GET['id'])){
	$mysqli=mysqli_connect("localhost","root","278948","normal");
	
	$safe_id=mysqli_real_escape_string($mysqli,$_GET['id']);
	
	$del_item_sql="delete 
	from store_shoppertrack where id='".$safe_id."' and session_id='".$_COOKIE['PHPSESSID']."'";
	$del_item_res=mysqli_query($mysqli,$del_item_sql) or die (mysqli_error($mysqli));
	
	mysqli_close($mysqli);
	
	header("Location: showcart.php");
	exit;
}else{
	header("Location: seestore.php");
	exit;
}
?>
