<?php
include "Admin-panel.php";
?>

<body>
            <div class="col-lg-12 grid-margin stretch-card"style=" position:relative; top:-26.5rem; left:50rem;">
              <div class="card">
                <div class="card-body">
                  <h1 class="card-title" style="font-size:1.6rem; font-family:cooper black;">Users List</h1>
                  <p class="card-description">
                  </p>
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead style="font-family:cooper;">
                        <tr>
                          <th style="font-size:1.3rem; color:#c20feb">
                            Users
                          </th>
                          <th style="font-size:1.3rem; color:#c20feb">
                          &nbsp;
                              Name
                          </th>
                          <th style="font-size:1.3rem; color:#c20feb">
                            Bio
                          </th>
                          <th style="font-size:1.3rem; color:#c20feb">
                            Remove Users</th>
                        </tr>
                      </thead>
                      <?php
                       $data = $db->retrieve("users");
                       $data = json_decode($data, 1);
                       
                       if(is_array($data)){
                          foreach($data as $id => $users){
                             echo"<tr style='font-family:cooper;'>
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