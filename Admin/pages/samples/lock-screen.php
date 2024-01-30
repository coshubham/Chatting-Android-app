<?php
include "../../database/connection.php";
?>


<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Admin Lockscreen</title>
  <link rel="shortcut icon" href="../../chatting.png" />
  <!-- plugins:css -->
  <link rel="stylesheet" href="../../vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="../../vendors/base/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="../../css/style.css">
  <!-- endinject -->
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth lock-full-bg">
        <div class="row w-100">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-transparent text-left p-5 text-center">
              <img src="admin3.png" class="lock-profile-img" alt="img">
              <form class="pt-5" method="POST">
                <div class="form-group">
                  <label for="examplePassword1" style="color:#fff; font-weight:700; font-size:1.2rem;">Password to unlock</label>
                  <input type="password" class="form-control text-center"style="color:#fff;" id="examplePassword1"name="password" placeholder="Password">
                </div>
                <div class="mt-5">
                  <button class="btn btn-block btn-success btn-lg font-weight-medium" name="login">Unlock</button>
                </div>
                <div class="mt-3 text-center">
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>

  
  <?php
      if(isset($_POST['login']))
      {
        $query="SELECT * FROM `admin` WHERE `password`='$_POST[password]'";
        $result=mysqli_query($conn,$query);
        if(mysqli_num_rows($result)==1)
        {
             header("location: ../../Admin-panel.php");
        }else{
        
          echo '<script>alert("Incorrect Password")</script>';
          
        }
      }
      ?>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="../../vendors/base/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- inject:js -->
  <script src="../../js/off-canvas.js"></script>
  <script src="../../js/hoverable-collapse.js"></script>
  <script src="../../js/template.js"></script>
  <script src="../../js/todolist.js"></script>
  <!-- endinject -->
</body>

</html>
