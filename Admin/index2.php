<?php
include("includes/dbconfig.php");
include("includes/firebaseRDB.php");
include("includes/dbconfig2.php");
$db = new firebaseRDB($databaseURL);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Document</title>
</head>
<body>


    
<div class="col-md-12">

<div class="col-md-8">
    <div class="row">
        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                    <h4>Total Number of record:</h4>
                    <?php
                    $ref_table = 'users';
                    $total_count = $database->getReference($ref_table)->getSnapshot()->numChildren();
                    echo $total_count;
                    ?>
                </div>
            </div>
        </div>
    </div>
</div>
    <div class="card">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-border">
                    <thead>
                        <tr>
                        
                            <th>name</th>
                            <th>bio</th>
                            <th>Image</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    <?php
   $data = $db->retrieve("users");
   $data = json_decode($data, 1);
   
   if(is_array($data)){
      foreach($data as $id => $users){
         echo "<tr>
         
         <td>{$users['name']}</td>
         <td>{$users['bio']}</td>
         <td><img src='{$users['imageUrl']}'></td>
         
         <td><a href='edit.php?id=$id'>EDIT</a></td>
         <td><a href='delete.php?id=$id'>DELETE</a></td>
      </tr>";
      }
   }
   ?>

                       
                    </tbody>

                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>