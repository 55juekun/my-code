<?php
include 'include.php';
if(!$_POST){
	$display_block=<<<END_OF_BLOCK
	<form method="POST" action="$_SERVER[PHP_SELF]">
	
	<p><label for="subject">提交:</label><br/>
	<input type="text" id="subject" name="subject" size="40" /></p>
	
	<p><label for="message">正文:</label><br/>
	<textarea id="message" name="message" cols="50" rows="10"></textarea></p>
	
	<button type="submit" name="submit" value="submit">提交</button>
	</form>
END_OF_BLOCK;
}else if ($_POST){
	if(($_POST['subject']=="")||($_POST['message']=="")){
		header("Location: send.php");
		exit();
	}
	
	doDB();
	
	if(mysqli_connect_error()){
		printf("连接失败：%s\n",mysqli_connect_error());
		exit();
	}else {
		$sql="select email from subsceibers";
		$result=mysqli_query($mysqli,$sql) or die (mysqli_error($mysqli));
		$mailheaders="from: your mailing list!<you@youradmin.com>";
		while($row=mysqli_fetch_array($result)){
			set_time_limit(0);
			$email=$row['email'];
			mail("$email",stripcslashes($_POST['subject']),stripcslashes($_POST['message']),$mailheaders);
			$display_block .="newletters send to: ".$email."<br/>";
		}
		mysqli_free_result($result);
		mysqli_close($mysqli);
	}
}
?>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
<h1>Sending a Newsletter</h1>
<?php echo "$display_block"; ?>
</body>
</html>
