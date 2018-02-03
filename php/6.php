<?php
$myImage=ImageCreate(300,300);

$black=ImageColorAllocate($myImage,0,0,0);
$white=ImageColorAllocate($myImage,255,255,255);
$red=ImageColorAllocate($myImage,255,0,0);
$green=ImageColorAllocate($myImage,0,255,0);
$blue=ImageColorAllocate($myImage,0,0,255);

ImageRectangle($myImage,15,15,95,95,$red);
ImageRectangle($myImage,95,155,175,295,$white);
ImageRectangle($myImage,175,15,255,155,$red);

header("Content-type: image/png");
Imagepng($myImage);

imagedestroy($myImage);
?>
