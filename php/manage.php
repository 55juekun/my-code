<?php
include 'include.php';
//取决于是否需要看到表格
if(!$_POST){
	//创建表格
	$display_block=<<<END_OF_BLOCK
	<form method="POST" action="$_SERVER[PHP_SELF]">
	
	<p><label for="email">Your E-Mail Address: </label><br/>
	<input type="email" id="email" name="email" size="40" maxlength="150" /></p>
	
	<fieldset>
	<legend>Action: </legend><br/>
	<input type="radio" id="action_sub" name="action" value="sub" checked />
	<label for="action_sub">注册</label><br/>
	<input type="radio" id="action_unsub" name="action" value="unsub" />
	<label for="action_unsub">注销</label><br/>
	</fieldset>
	<button type="submit" name="submit" value="submit">Submit</button>
	</form>
END_OF_BLOCK;
}else if (($_POST)&&($_POST['action']=="sub")){
	//尝试注册，并确定邮件地址
	if($_POST['email']==""){
		header("Location: manage.php");
		exit;
	}else {
		//连接数据库
		doDB();
		//检查提交的邮件地址是否在表中
		emailChecker($_POST['email']);
		//数据库中是否有邮件地址
		if(mysqli_num_rows($check_res)<1){
			//格式化此表格
			mysqli_free_result($check_res);
			//添加新的邮件地址
			$add_sql="insert into subsceibers(email) value('".$safe_email."')";
			$add_res=mysqli_query($mysqli,$add_sql) or die (mysqli_error($mysqli));
			$display_block="<p>Thanks for signing up!</p>";
			//关闭数据库
			mysqli_close($mysqli);
		}else{
			//失败
			$display_block="<p>You're already subsceibed!</p>";
		}
	}
}else if (($_POST)&&($_POST['action']=='unsub')){
	//注销相应的邮件地址
	if($_POST['email']==""){
		header("Location: manage.php");
		exit;
	}else {
		//连接数据库
		doDB();
		//检查提交的邮件地址是否在表中
		emailChecker($_POST['email']);
		//数据库中是否有邮件地址
		if(mysqli_num_rows($check_res)<1){
			//格式化此表格
			mysqli_free_result($check_res);
			//失败，数据库已经是空的了
			$display_block="<p>Couldn't find your address!</p>
			<p>No action was taken.</p>";
		}else{
			//获取到邮件地址相应的id
			while($row=mysqli_fetch_array($check_res)){
				$id=$row['id'];
			}
			//注销
			$del_sql="delete from subsceibers where id=".$id;
			$del_res=mysqli_query($mysqli,$del_sql) or die (mysqli_error($mysqli));
			$display_block="<p>You're unsubsceibed!</p>";
		}
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
<h1>注册或者注销一个邮件</h1>
<?php echo "$display_block"; ?>
</body>
</html>
