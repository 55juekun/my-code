<?php
include '20include.php';//代码的缩进有错误，但是懒得一行行重新改了
doDB();

$display_block ="<h1>My Categories</h1>
<p>Select a category to see its items.</p>";

$get_cats_sql="select id,cat_title,cat_desc 
	from store_categories order by cat_title";
$get_cats_res=mysqli_query($mysqli,$get_cats_sql) or die (mysqli_error($mysqli));

if(mysqli_num_rows($get_cats_res)<1){
		
	$display_block ="<p><em>Sorry,no categories to browse. </em></p>";
}else{		
	while($cats=mysqli_fetch_array($get_cats_res)){
		$cat_id=$cats['id'];
		$cat_title=strtoupper(stripcslashes($cats['cat_title']));
		$cat_desc=stripcslashes($cats['cat_desc']);
		
		$display_block .="<p><strong><a href=\"".$_SERVER['PHP_SELF'].
		"?cat_id=".$cat_id."\">".$cat_title."</a></strong><br/>"
		.$cat_desc."</p>";
		
		if(isset($_GET['cat_id'])&&($_GET['cat_id']==$cat_id)){
			@$safe_cat_id=mysqli_real_escape_string($mysqli,$_GET['cat_id']);
			
			$get_items_sql="select id,item_title,item_price
			from store_items where cat_id='".$cat_id."' 
			order by item_title ";
			$get_items_res=mysqli_query($mysqli,$get_items_sql) or die (mysqli_error($mysqli));
			
			if(mysqli_num_rows($get_items_res)<1){		
			$display_block ="<p><em>Sorry,no items in this category. </em></p>";
			}else{
				$display_block .="<ul>";
				while($items=mysqli_fetch_array($get_items_res)){
					$item_id=$items['id'];
					$item_title=stripcslashes($items['item_title']);
					$item_price=$items['item_price'];
		
					$display_block .="<li><a href=\"showitem.php?item_id="
					.$item_id."\">".$item_title."</a>(\$".$item_price.")</li>";
				}
				
				$display_block .="</ul>";
			}
			mysqli_free_result($get_items_res);
		}
	}
}

mysqli_free_result($get_cats_res);
	
mysqli_close($mysqli);
?>

<!DOCTYPE html>
<html>
<head>
<title>My categories</title>
</head>

<body>
<?php echo "$display_block"; ?>
</body>
</html>