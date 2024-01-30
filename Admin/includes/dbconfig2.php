<?php


 require __DIR__.'/vendor/autoload.php';

 use Kreait\Firebase\Factory;

 $factory = (new Factory)
    //  ->withServiceAccount('chat-app-b690f-firebase-adminsdk-z7mfb-f9727a957a.json')
     ->withDatabaseUri('https://chat-app-b690f-default-rtdb.firebaseio.com');
    

 $database = $factory->createDatabase(); 
?>