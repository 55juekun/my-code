<?php
$to = "1061229681@qq.com";  //改成自己的邮箱进行测试
$subject = "Test mail";
$message = "Hello! This is a simple email message.";
$from = "shf@qq.com";  //随意设置
$headers = "From: $from";
mail($to,$subject,$message,$headers);
echo "Mail Sent.";
?>