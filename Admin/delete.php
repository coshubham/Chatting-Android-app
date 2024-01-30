<?php
include("includes/dbconfig.php");
include("includes/firebaseRDB.php");
include("includes/dbconfig2.php");



$db = new firebaseRDB($databaseURL);
$id = $_GET['id'];
if($id != ""){
   $delete = $db->delete("users", $id);
   header('location:user_list_table.php');
}
?>