<?php
include("includes/dbconfig.php");
include("includes/firebaseRDB.php");
$db = new firebaseRDB($databaseURL);


$email = $_POST['email'];
$password = $_POST['password'];

if($email == ""){
    echo "Email is Required";
}else if($password == ""){
    echo "Password is Required";

}else{
    $rdb = new firebaseRDB($databaseURL);
    $retrieve = $rdb->retrieve("/user", "email", "EQUAL", $email);
    $data = json_decode($retrieve, 1);

    // if(count($data) == 0){
    //     echo "Email Not Found";
    // }else{
    //     $id = array_keys($data)[0];
    //     if($data[$id]['password'] == $password){
    //         $_SESSION['user'] = $data[$id];
    //         header("location: Admin-panel.php");
    //     }else{
    //         echo "Login Failed";
    //     }
    // }

    if(!isset($data['email'])){
        echo "Email not found";
    }else if($data['password'] != $password){
        echo"Invalid Password";
    }else{
        echo"Login scuccess";
    }
}


?>