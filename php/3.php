<?php
$num=42;
$num_tries = (isset($_POST['num_tries'])) ? $_POST['num_tries']+1 : 1;//后面用的是$_POST['num_tries']+1而不是$num_tries +1
if(!isset($_POST['guess'])){//欢迎界面 默认
	$message="welcome!!";
}elseif(!is_numeric($_POST['guess'])){//不是数字
	$message="can't understand!!";
}elseif(($_POST['guess'])==$num){
	header("Location: index.html");
	exit;
}elseif(($_POST['guess'])>$num){
	$message=$_POST['guess']." is to big!!";
}elseif(($_POST['guess'])<$num){
	$message=$_POST['guess']." is to small!!";
}else{
	$message="confused!!";
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>An HTML form that calls itself</title>
</head>
<body>
<h1><?php echo $message; ?></h1>
<p><strong>Guess number:</strong> <?php echo $num_tries; ?></p>
<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="POST">
<p><label for="guess">Type your guess here:</lable> <br/>
<input type="text" id="guess" name="guess" /> </p>
<input type="hidden" name="num_tries" value="<?php echo $num_tries; ?>" /> 
<button type="submit" name="submit" value="submit">Submit</button>
</form>
</body>
</html>