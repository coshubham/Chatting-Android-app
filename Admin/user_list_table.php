<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link rel="shortcut icon" href="chatting.png" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Tables</title>
</head>
<body>
<?php
include "Admin-panel.php";
// include '../php/config.php';
?>
<body>
            <div class="col-lg-9 grid-margin stretch-card" style="position:relative; top:-15rem; left:17rem">
              <div class="card">
                <div class="card-body">
                  <h1 class="card-title" style="font-size:1.6rem; font-family:cooper black;">Users List</h1>
                  
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead style="font-family:cooper;">
                        <tr>
                          <th style="font-size:1.3rem; color:#c20feb">
                            Users
                          </th>
                          <th style="font-size:1.3rem; color:#c20feb">
                          &nbsp;&nbsp;
                              Name
                          </th>
                          <th style="font-size:1.3rem; color:#c20feb">
                          &nbsp;&nbsp;
                            Bio
                          </th>
                          <!-- <th style="font-size:1.3rem; color:#c20feb; position: relative; left:-2.2rem">
                            Password
                          </th>
                          <th style="font-size:1.3rem; color:#c20feb">
                            Status
                          </th> -->
                          <th style="font-size:1.3rem; color:#c20feb">
                            Remove Users</th>
                        </tr>
                      </thead>
                      <?php
       $data = $db->retrieve("users");
       $data = json_decode($data, 1);
       
       if(is_array($data)){
          foreach($data as $id => $users){
              echo "<tr style='font-family:cooper;'>
              <td class='py-1'>
              <img src='{$users['imageUrl']}'>
              </td>
              <td>
              {$users['name']}
              </td>
              <td>{$users['bio']}</td>
              <td>
             
              <button type='button' class='btn btn-danger' style='height:30px; width:5.7rem; margin-left:2.5rem;'><a href='delete.php?id=".$id."' class='text-light' style='text-decoration:none; font-size:1.1rem; font-family:cooper; position:relative; top:-0.6rem; left:-0.9rem '>Remove</a></button>
          
              </td>
            </tr>";
            }
          }
?>
                   
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="../../vendors/base/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page-->
  <!-- End plugin js for this page-->
  <!-- inject:js -->
  <script src="../../js/off-canvas.js"></script>
  <script src="../../js/hoverable-collapse.js"></script>
  <script src="../../js/template.js"></script>
  <script src="../../js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <!-- End custom js for this page-->
</body>
</html>