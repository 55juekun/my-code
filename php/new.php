<?php 
$mysqli=mysqli_connect("localhost","root","278948","normal");
$add_sql="create table store_order_time(
	id int not null primary key auto_increment,
	order_id int,
	sel_item_id int,
	sel_item_qty smallint,
	sel_item_size varchar(25),
	sel_item_color varchar(25),
	sel_item_price float(6,2)
	)";
$add_res=mysqli_query($mysqli,$add_sql) or die (mysqli_error($mysqli));
	echo "done!"

?>