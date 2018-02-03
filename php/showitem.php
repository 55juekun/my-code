<?php
include '20include.php';//代码的缩进有错误，但是懒得一行行重新改了
doDB();

$display_block ="<h1>My Store - Item Detail</h1>";

@$safe_item_id=mysqli_real_escape_string($mysqli,$_GET['item_id']);

$get_item_sql="select sc.id as cat_id,sc.cat_title,si.item_title,si.item_price,si.item_desc,si.item_image
	from store_items as si left join store_categories as sc on sc.id=si.cat_id
	where si.id='".$safe_item_id."'";
$get_item_res=mysqli_query($mysqli,$get_item_sql) or die (mysqli_error($mysqli));

if(mysqli_num_rows($get_item_res)<1){
		
		$display_block ="<p><em>Invalid item selection.</em></p>";
}else{
	
	while($item_info=mysqli_fetch_array($get_item_res)){
		
		$cat_id=$item_info['cat_id'];
		$cat_title=strtoupper(stripcslashes($item_info['cat_title']));
		$item_title=stripcslashes($item_info['item_title']);
		$item_price=$item_info['item_price'];
		$cat_title=strtoupper(stripcslashes($item_info['cat_title']));
		$item_desc=stripcslashes($item_info['item_desc']);
		$item_image='D:/apach/Apache24/htdocs/2/33/'.$item_info['item_image'];
	}
	
	$display_block .=<<<END_OF_TEXT
	<p><em>You are Viewing:</em><br/>
	<strong><a href="seestore.php?cat_id=$cat_id ">$cat_title</a> &gt;
	$item_title</strong></p>
	
	<div style="float:left;"><img src="$item_image" alt="$item_title"  height="500px" width="500px"/></div>
	<div style="float:left; padding-left:12px">
	<p><strong>Description:</strong><br/>$item_desc</p>
	<p><strong>Price:</strong>\$$item_price</p>
	<form method="post" action="addtocart.php">
END_OF_TEXT;
	mysqli_free_result($get_item_res);
	
	$get_colors_sql="select item_color
	from store_item_color
	where item_id='".$safe_item_id."' order by item_color";
	$get_colors_res=mysqli_query($mysqli,$get_colors_sql) or die (mysqli_error($mysqli));
	
	if(mysqli_num_rows($get_colors_res)>0){
		
		$display_block .="<p><label for=\"sel_item_color\">Available Colors:</label>
		<select id=\"sel_item_color\" name=\"sel_item_color\">";
		
		while($colors=mysqli_fetch_array($get_colors_res)){
			$item_color=$colors['item_color'];
			$display_block .="<option value=\"".$item_color."\">".
			$item_color."</option>";
		}
		$display_block .="</select></p>";
	}	
	
	mysqli_free_result($get_colors_res);
	
	$get_size_sql="select item_size
	from store_item_size
	where item_id='".$safe_item_id."' order by item_size";
	$get_size_res=mysqli_query($mysqli,$get_size_sql) or die (mysqli_error($mysqli));
	
	if(mysqli_num_rows($get_size_res)>0){
		
		$display_block .="<p><label for=\"sel_item_size\">Available Size:</label>
		<select id=\"sel_item_size\" name=\"sel_item_size\">";
		while($sizes=mysqli_fetch_array($get_size_res)){
			$item_size=$sizes['item_size'];
			$display_block .="<option value=\"".$item_size."\">".
			$item_size."</option>";
		}
		$display_block .="</select></p>";
	}	
	
	mysqli_free_result($get_size_res);
	
	$display_block .="<p><label for=\"sel_item_qty\">Select Quantity:</label>
	<select id=\"sel_item_qty\" name=\"sel_item_qty\">";
		
	for($i=1;$i<=11;$i++){
		$display_block .="<option value=\"".$i."\">".$i."</option>";
	}

	$display_block .=<<<END_OF_TEXT
	</select></p>
	<input type="hidden" name="sel_item_id" value="$_GET[item_id]" />
	<button type="submit" name="submit" value="submit" >Add to Cart</button>
	</form>
	</div>
END_OF_TEXT;
}	
	mysqli_close($mysqli);
?>

<!DOCTYPE html>
<html>
<head>
<title>My Store</title>
</head>
<style type="text/css">
	lable{font-weight:bold;}
</style>
<body>
<?php echo "$display_block"; ?>
</body>
</html>
