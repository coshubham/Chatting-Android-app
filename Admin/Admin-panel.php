<?php
include("includes/dbconfig.php");
include("includes/firebaseRDB.php");
include("includes/dbconfig2.php");
$db = new firebaseRDB($databaseURL);
?>
<?php
 session_start();
 if(!isset($_SESSION['admin_name'])){
    header("location: index.php");
 }
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="vendors/base/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="chatting.png" />
</head>
<body>

    
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row" >
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center" style="	background-image: linear-gradient(45deg, #e0feff, #fff, #e0feff);">
        <a class="navbar-brand brand-logo " href="index.php"><img src="chatting.png" alt="logo" style="height:6rem; width:5.6rem;"><p style="font-size:1.4rem;display:inline; font-family:cooper black; color:#924936;">Admin Panel</p></a>
        <a class="navbar-brand brand-logo-mini" href="index.php"><img src="chatting.png" alt="logo" style="height:4rem; width:5rem;"></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end" style="	background-image: linear-gradient(45deg, #e0feff, #fff, #e0feff);">
        
        </button>  
             
            </div>
          </li>
        </ul>
       
      </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar" style="	background-image: linear-gradient(180deg, #e0feff, #fff);">
      <form method="POST">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="Admin-panel.php">
              <i class="ti-shield menu-icon"></i>
              <span class="menu-title">Dashboard</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="user_list_table.php">
            <img src="user1.gif" alt="" style="height:25px; width:25px; border-radius: 150px;"></i>
              <span class="menu-title"style="margin-left:20px;">Users</span>
            </a>
          </li>

          <!-- <li class="nav-item">
            <a class="nav-link" href="Contact Us.php">
              <img src="contact.gif" alt="" style="height:25px; width:25px; border-radius: 200px;"></i>
              <span class="menu-title"style="margin-left:20px;">Contact Us</span>
            </a>
          </li> -->
         
          <li class="nav-item">
            <a class="nav-link"  href="pages/samples/lock-screen.php">
            <img src="lock.gif" alt="" style="height:25px; width:25px; border-radius: 200px;"></i>
              <span class="menu-title" style="margin-left:20px;">Lockscreen</span>
            </a>
             
            <li class="nav-item">
            <a class="nav-link">
            <img src="logout.gif" alt="" style="height:25px; width:25px; border-radius: 200px;"></i>
              <button class="menu-title" style="margin-left:20px; border:none; background:none;" name="logout">Logout</button>
            </a>
            
            <div class="collapse" id="auth">
              <ul class="nav flex-column sub-menu">

              </ul>
            </div>
          </li>
          
        </ul>
        </form>
      </nav>

      <?php
      if(isset($_POST['logout']))
      {
        session_destroy();
        header("location:index.php");
      }

      ?>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper" style="background-image: linear-gradient(196deg, #fff, #fff);" >
        <!-- style="	background-image: linear-gradient(196deg, #f2c5ff, #6cd0ff);" -->
          <div class="row">
            <div class="col-md-12 grid-margin">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <h4 class="font-weight-bold mb-0" style="font-size:2rem; font-family:cooper black; color:#8f07ff;">Chat Dashboard</h4>
                </div>
                <div>
                   
                   </div>
              </div>
            </div>
          </div>
          <div class="row">
           <div class="col-md-3 grid-margin stretch-card" style="position:relative; left:1.6rem">
            <div class="card" style="background:#ff9c32; border-radius: 150px 150px 150px 5px;">
                <div class="card-body">
                  <p class="card-title text-md-center text-xl-left" style="font-family:cooper black; font-size:1.6rem; margin-right:2rem; color:whitesmoke;">Users</p>
                  <?php
                  $ref_table = 'users';
                  $total_count = $database->getReference($ref_table)->getSnapshot()->numChildren();
                  if($total_count)
                  {
                     echo '<h3 class="mb-0 mb-md-2 mb-xl-0 order-md-1 order-xl-0" style="font-family:cooper; color:whitesmoke; margin-left:4rem; position:relative; top:3.5rem;">'.$total_count.'</h3>';
                  }else{
                    echo '<h3 class="mb-0 mb-md-2 mb-xl-0 order-md-1 order-xl-0"style="font-family:cooper; color:whitesmoke; margin-left:4.4rem; position:relative; top:3.5rem;">0</h3>';
                  }

                  ?>
                  <div class="d-flex flex-wrap justify-content-between justify-content-md-center justify-content-xl-between align-items-center">
                    
                   <a href="user_list_table.php"><img src="user.gif" style="color:#fff; margin-left:2.6rem; position:relative; top:-2rem; font-size:3.7rem; height:55px; width:66px; text-decoration:none; border-radius:100px;"></i></a>
                  </div>
              
                  
                </div>
              </div>
            </div>
            
                  <!-- <p class="mb-0 mt-2 text-danger">0.47% <span class="text-black ms-1"><small>(30 days)</small></span></p> -->
               
           </div>
         </div>
         </div>
         <div class="row">
            <div class="col-md-6 grid-margin stretch-card">
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="vendors/base/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page-->
  <script src="vendors/chart.js/Chart.min.js"></script>
  <script src="js/jquery.cookie.js" type="text/javascript"></script>
  <!-- End plugin js for this page-->
  <!-- inject:js -->
  <script src="js/off-canvas.js"></script>
  <script src="js/hoverable-collapse.js"></script>
  <script src="js/template.js"></script>
  <script src="js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="js/dashboard.js"></script>
  <!-- End custom js for this page-->
</body>

</html>

