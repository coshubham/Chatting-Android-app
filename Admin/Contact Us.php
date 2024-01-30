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
include "database/connection.php";

?>
<body>
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h1 class="card-title" style="font-size:1.6rem; font-family:cooper black;">CONTACT List</h1>
                  <p class="card-description">
                  </p>
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead style="font-family:cooper black; ">
                        <tr>
                          <th style="font-size:1.3rem; color:#c20feb">
                          &nbsp;&nbsp;&nbsp;&nbsp;
                            Email
                          </th>
                          <th style="font-size:1.3rem; color:#c20feb">
                            Messages
                          </th>
                          
                          <th style="font-size:1.3rem; color:#c20feb">
                          
                            Remove</th>
                        </tr>
                      </thead>
                      <?php
        $Sql = "Select * from contact";
        $result = mysqli_query($conn,$Sql);

        if($result){

         /* $row=mysqli_fetch_assoc($result);
            echo $row ['name'];*/
            while($row=mysqli_fetch_assoc($result))
            {
              
              
              $email= $row['email'];
              $message= $row['message'];

              echo '<tr style="font-family:cooper;">
              <td class="py-1">
              '.$email.'
              </td>
              <td>
              '.$message.'
              </td>
              <td>
              <button type="button" class="btn btn-danger" style="height:30px; width:5.7rem;"><a href="deletes.php?deleteemail='.$email.'" class="text-light" style="text-decoration:none; font-size:1.1rem; font-family:cooper; position:relative; top:-0.6rem; left:-0.8rem ">Remove</a></button>
              </td>
            </tr>';
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