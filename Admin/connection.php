<?php
require __DIR__.'/vendor/autoload.php';

use Kreait\Firebase\Factory;

$factory = (new Factory)
     ->withServiceAccount('chat-app-b690f-firebase-adminsdk-z7mfb-a57e1a1bc4.json')
     ->withDatabaseUri('https://chat-app-b690f-default-rtdb.firebaseio.com/');

     $database = $factory->createDatabase();   

// $conn= mysqli_connect("localhost", "root", "" ,"chat");
// if(!$conn){
//     die("Sorry your connection is Failed:".mysqli_connect_error());
// }
// else{
//     echo "";
// }
?>