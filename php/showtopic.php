<?php
include '20include.php';//代码的缩进有错误，但是懒得一行行重新改了
doDB();

	if(!isset($_GET['topic_id'])){
		header("Location: topiclist.php");
		exit;
	}
	
	$safe_topic_id=mysqli_real_escape_string($mysqli,$_GET['topic_id']);
	
	$verify_topic_sql="select topic_title
	from forum_topics where topic_id='".$safe_topic_id."'";
	$verify_topic_res=mysqli_query($mysqli,$verify_topic_sql) or die (mysqli_error($mysqli));
	//16-20行的代码只是为了后面的if(mysqli_num_rows($verify_post_res)<1)，没有其他作用
	@$safe_post_id=mysqli_real_escape_string($mysqli,$_GET['post_id']);
	
	$verify_post_sql="select post_id
	from forum_posts where topic_id='".$safe_topic_id."'";
	$verify_post_res=mysqli_query($mysqli,$verify_post_sql) or die (mysqli_error($mysqli));
		
	if(mysqli_num_rows($verify_post_res)<1){
		
		$display_block ="<p><em>You have selected an invalid topic. <br/>
		Please<a href=\"topiclist.php\">try again</a>.</em></p>";
	}else{
		
		while($topic_info=mysqli_fetch_array($verify_topic_res)){
			$topic_title=stripcslashes($topic_info['topic_title']);
		}
		
		$get_post_sql="select post_id,post_text,
		date_format(post_create_time,  '%b %e %Y<br/>%r')
		AS fmt_post_create_time ,post_owner
		from forum_posts where topic_id='".$safe_topic_id."'
		order by post_create_time ASC";
		
		$get_post_res=mysqli_query($mysqli,$get_post_sql) or die (mysqli_error($mysqli));
	
		$display_block =<<<END_OF_TEXT
		<p>Showing posts for the<strong>$topic_title</strong> topic:</p>
		<table>
		<tr>
		<th>AUTHOR</th>
		<th>POST</th>
		</tr>
END_OF_TEXT;

		while($post_info=mysqli_fetch_array($get_post_res)){
			$post_id=$post_info['post_id'];
			$post_text=nl2br(stripcslashes($post_info['post_text']));
			@$post_create_time=$post_info['fmt_post_create_time'];
			$post_owner=stripcslashes($post_info['post_owner']);
			
			$display_block .=<<<END_OF_TEXT
			<tr>
			<td>$post_owner<br/><br/>
			created on: <br/>$post_create_time</td>
			<td>$post_text<br/><br/>
			<a href="replytopost.php?post_id=$post_id ">
			<strong>REPLY TO POST</strong></a></td>
			</tr>
END_OF_TEXT;
		}
		mysqli_free_result($get_post_res);
		mysqli_free_result($verify_topic_res);
	
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
		vertical-align: top;
	}
	.num_post_col{text-align:center}
</style>
<!DOCTYPE html>
<html>
<head>
<title>Posts in Topic</title>
</head>

<body>
<h1>Posts in Topic</h1>
<?php echo "$display_block"; ?>
</body>
</html>