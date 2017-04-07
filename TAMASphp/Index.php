<?php
session_start();
foreach($_SESSION as $key => $value){
	$key = NULL;
	$value = NULL;
}
session_destroy();
header("Location: Dummies.php");
exit;
?>