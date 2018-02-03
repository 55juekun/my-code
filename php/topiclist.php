<?php
include '20include.php';
doDB();

	$get_topics_sql="select topic_id,topic_title,
	date_format(topic_create_time,  '%b %e %Y at %r')
	AS fmt_topic_create_time ,topic_owner
	from forum_topics order by topic_create_time DESC";
	$get_topic_res=mysqli_query($mysqli,$get_topics_sql) or die (mysqli_error($mysqli));

if(mysqli_num_rows($get_topic_res)<1){
	$display_block .="<p><em>No topics exist!</em></p>";
}else{
	$display_block = <<<END_OF_TEXT
	<table>
	<tr>
	<th>TOPIC TITLE</th>
	<th># of POSTS</th>
	</tr>
END_OF_TEXT;

	while($topic_info=mysqli_fetch_array($get_topic_res)){
		$topic_id=$topic_info['topic_id'];
		$topic_title=stripcslashes($topic_info['topic_title']);
		$topic_create_time=$topic_info['fmt_topic_create_time'];
		$topic_owner=stripcslashes($topic_info['topic_owner']);
		
		
	$get_num_post_sql="select count(post_id) as post_count
	from forum_posts where topic_id='".$topic_id."'";
	$get_num_post_res=mysqli_query($mysqli,$get_num_post_sql) or die (mysqli_error($mysqli));
	
	while($posts_info=mysqli_fetch_array($get_num_post_res)){
		$num_posts=$posts_info['post_count'];
	}
	
	$display_block .=<<<END_OF_TEXT
	<tr>
	<td><a href="showtopic.php?topic_id=$topic_id">
	<strong>$topic_title</strong></a><br/>
	Create on $topic_create_time by $topic_owner</td>
	<td class="num_posts_col">$num_posts</td>
	</tr>
END_OF_TEXT;
	}
	mysqli_free_result($get_topic_res);
	mysqli_free_result($get_num_post_res);
	
	mysqli_close($mysqli);

	$display_block .="</table>";
}
?>
<style type="text/css">
	table{
		border:1px solid black;
		border-collapse:collapse;
	}
	th{
		border:1px solid black;
		padding:5px;
		font-weight:bold;
		background:#ccc;
	}
	td{
		border:1px solid black;
		padding:6px;
	}
	.num_post_col{text-align:center}
</style>
<!DOCTYPE html>
<html>
<head>
<title>Topic in my Forum</title>
</head>

<body>
<h1>Topic in my Forum</h1>
<?php echo "$display_block"; ?>
<p>Would you like to <a href="addtopic.html">add a topic</a>?</p>
</body>
</html>