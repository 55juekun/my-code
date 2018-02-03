<?php
include '20include.php';
doDB();

if(!$_POST){
	if(!isset($_GET['post_id'])){
		header("Location: topiclist.php");
		exit;
	}
	
	$safe_post_id=mysqli_real_escape_string($mysqli,$_GET['post_id']);
	
	$verify_sql="select ft.topic_id, ft.topic_title
	from forum_topics as ft left join forum_posts as fp on fp.topic_id =
	ft.topic_id
	where fp.post_id ='".$safe_post_id."'";
	$verify_res=mysqli_query($mysqli,$verify_sql) or die ("aa".mysqli_error($mysqli));
	
	if(mysqli_num_rows($verify_res)<1){
		
		header("Location: topiclist.php");
		exit;
	}else{
		
		while($topic_info=mysqli_fetch_array($verify_res)){
			$topic_id=$topic_info['topic_id'];

			$topic_title=stripcslashes($topic_info['topic_title']);
		}
	}
	mysqli_free_result($verify_res);
	
	mysqli_close($mysqli);
	
}else if($_POST){
	if(((@!$_POST['topic_id'])||(@!$_POST['post_text']))||(@!$_POST['post_owner'])){
		header("Location: topiclist.php");
		exit;
	}
	
	@$safe_topic_id=mysqli_real_escape_string($mysqli,$_POST['topic_id']);
	@$safe_post_text=mysqli_real_escape_string($mysqli,$_POST['post_text']);
	@$safe_post_owner=mysqli_real_escape_string($mysqli,$_POST['post_owner']);
	echo "asd ".$safe_topic_id." oo ";
	
	$add_post_sql="insert into forum_posts (topic_id,post_text,post_create_time,post_owner) 
	values ('".$safe_topic_id."', '".$safe_post_text."',now(), '".$safe_post_owner."')";
	$add_post_res=mysqli_query($mysqli,$add_post_sql) or die ("bb".mysqli_error($mysqli));
	
	mysqli_close($mysqli);
	
	header("Location: showtopic.php?topic_id=".$_POST['topic_id']);
	exit;
}
?>
<!DOCTYPE html>
<head>
<title>Post your Reply in <?php echo $topic_title; ?></title>
</head>

<body>
<h1>Post your Reply in <?php echo $topic_title; ?></h1>
<form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">

<p><label for="post_owner">Your Email Address:</label><br/>
<input type="email" id="post_owner" name="post_owner" size="40" maxlength="150" required="required" /> </p>

<p><label for="post_text" >Post Text:</label><br/>
<textarea id="post_text" name="post_text" rows="8" cols="40" required="required" ></textarea></p>

<input type="hidden" name="topic_id" value="<?php echo $topic_id; ?>" >

<button type="submit" name="submit" value="submit" >Add Post</button>
</form>
</body>
</html>