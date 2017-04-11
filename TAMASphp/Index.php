<?php
// Destroy the old session and start a new one after logout/before login

session_start();
foreach($_SESSION as $key => $value){
	$key = NULL;
	$value = NULL;
}
session_destroy();

// Redirect to dummy creation
header("Location: Dummies.php");
exit;
?>