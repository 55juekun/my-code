<?php
include '20include.php';
doDB();

if(!$_POST){
	$display_block="<h1>Select an Entry to Delete</h1>";
	$get_list_sql="select id,CONCAT_WS(', ', l_name,f_name)AS display_name 
	from master_name order by l_name,f_name";
	$get_list_res=mysqli_query($mysqli,$get_list_sql) or die (mysqli_error($mysqli));
	
	if(mysqli_num_rows($get_list_res)<1){
			$display_block .="<p><em>No records to select!</em></p>";
	}else{
		$display_block .="
		<form method=\"post\" action=\"".$_SERVER['PHP_SELF']."\">
		<p><label for=\"sel_id\">Select a Record:</label><br/>
		<select id=\"sel_id\" name=\"sel_id\" required=\"required\">
		<option value=\"\">-- Select One --</option>";
		
		while($recs=mysqli_fetch_array($get_list_res)){
			$id=$recs['id'];
			$display_name=stripcslashes($recs['display_name']);
			$display_block .="<option value=\"".$id."\">".$display_name."</option>";
		}
		$display_block .="
		</select>
		<button type=\"submit\" name=\"submit\" value=\"view\">View Selected Entry\"></button>
		</form>";
	}
	mysqli_free_result($get_list_res);
	
}else if($_POST){
	
	if($_POST['sel_id']==""){
		header("Location: delentry.php");
		exit;
	}
	
	$safe_id=mysqli_real_escape_string($mysqli,$_POST['sel_id']);
	
	$del_master_sql="delete 
	from master_name where id='".$safe_id."'";
	$del_master_res=mysqli_query($mysqli,$del_master_sql) or die (mysqli_error($mysqli));
	
	$del_addresses_sql="delete 
	from address where master_id='".$safe_id."'";
	$del_addresses_res=mysqli_query($mysqli,$del_addresses_sql) or die (mysqli_error($mysqli));
	
	$del_tel_sql="delete 
	from telephone where master_id='".$safe_id."'";
	$del_tel_res=mysqli_query($mysqli,$del_tel_sql) or die (mysqli_error($mysqli));
	
	$del_fax_sql="delete
	from fax where master_id='".$safe_id."'";
	$del_fax_res=mysqli_query($mysqli,$del_fax_sql) or die (mysqli_error($mysqli));
	
	$del_email_sql="delete
	from email where master_id='".$safe_id."'";
	$del_email_res=mysqli_query($mysqli,$del_email_sql) or die (mysqli_error($mysqli));
	
	$del_notes_sql="delete
	from personal_notes where master_id='".$safe_id."'";
	$del_notes_res=mysqli_query($mysqli,$del_notes_sql) or die (mysqli_error($mysqli));
	
	mysqli_close($mysqli);

	$display_block ="<h1>Record(s) Delete</h1>
	<p>Would you like to 
	<a href=\"".$_SERVER['PHP_SELF']."\">delete another</a>?</p>";
}
?>
<!DOCTYPE html>
<html>
<head>
<title>My Records</title>
</head>
<body>
<?php echo "$display_block"; ?>
</body>
</html>