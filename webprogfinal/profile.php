<?php
session_start();
if(!isset($_SESSION['username'])){
  header('location: login2.php');
  exit();
}

$host = 'localhost';
$user = 'root';
$password = '';
$database = 'login';

$conn = new mysqli($host, $user, $password, $database);
$username = $_SESSION['username'];
$query = "SELECT username, email, password from users where username = '$username'";
$result  = $conn-> query($query);

if($result){
   if($result ->num_rows > 0){
    $row = $result -> fetch_assoc();
    $username = $row ['username'];
    $email = $row ['email'];
    $password = $row ['password'];
   } else {
    $username = "N/A";
    $email = "N/A";
   }
} else{
    $username = "N/A";
    $email = "N/A";
}
$conn ->close();
?>


<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="mainstyle.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="profile.css">
  <title>Profile</title>
<style>
.button {
  background-color: rgb(224, 130, 204):
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
  border-radius: 5px;
}
.button1 {
  background-color: #d48cb0; 
  color: white; 
  border: 2px solid rgb(224, 130, 204);
  font-family: 'Space Mono', monospace;
}

.button1:hover {
  background-color: #bf4d86;
  color: white;
}
input[type="file"] {
        font-family: 'Space Mono', monospace;
        color: rgb(224, 130, 204);
        font-weight: bold;
    }

input[type="file"]::file-selector-button {
		font-family: 'Space Mono', monospace;
        color: rgb(224, 130, 204);
		font-weight: bold;
    }
 
</style>
</head>
<body style="background-color: aliceblue;">
 <main style="border: 1px solid rgb(224, 130, 204);">
        <nav class="main-menu">
            <h1>Persona</h1>
            <img class="logo" src="logo3.png" alt="">
            <ul>
                <li class="nav-item">
            <b></b>
            <b></b>
            <a href="home.php">
              <i class="fa fa-home nav-icon" style="font-size:26px;"></i>
              <span class="nav-text">Home</span>
            </a>
          </li>
          <li class="nav-item">
            <b></b>
            <b></b>
            <a href="main.php">
              <i class="fa fa-plus nav-icon" style="font-size:26px;"></i>
              <span class="nav-text">Add</span>
            </a>
          </li>

          <li class="nav-item">
            <b></b>
            <b></b>
            <a href="entries2.php">
              <i class="	fa fa-book nav-icon" style="font-size:26px;"></i>
              <span class="nav-text">Entries</span>
            </a>
          </li>
		  
		  <li class="nav-item">
            <b></b>
            <b></b>
            <a href="gallery.php">
              <i class="fa fa-image nav-icon" style="font-size:26px;"></i>
              <span class="nav-text">Gallery</span>
            </a>
          </li>

          <li class="nav-item">
            <b></b>
            <b></b>
            <a href="aboutus.php">
              <i class="fa fa-info-circle nav-icon" style="font-size:26px;"></i>
              <span class="nav-text">About Us</span>
            </a>
          </li>
		  

          <li class="nav-item active">
            <b></b>
            <b></b>
            <a href="profile.php">
              <i class="fa fa-user nav-icon" style="font-size:26px;"></i>
              <span class="nav-text">Profile</span>
            </a>
          </li>

          <li class="nav-item">
            <b></b>
            <b></b>
            <a href="logout.php">
              <i class="fa fa-power-off nav-icon" style="font-size:26px;"></i>
              <span class="nav-text">Logout</span>
            </a>
          </li>
        </ul>
      </nav>
      <section style="background: #fff;
				border: 3px solid rgb(224, 130, 204);
                padding: 50px;
                line-height: 1.7em;
				margin: 30px;
				width: 94.5%;
				height: 90%;
				border-radius: 20px;">
				<center>
  <div>
    <form action="upload1.php" method="POST" enctype="multipart/form-data">
      <?php
      $profileImagePath = "uploads/profile" . $_SESSION['username'] . ".jpg";
      if (file_exists($profileImagePath)) {
        echo '<img src="' . $profileImagePath . '" alt="Profile Image" class="img1">';
      } else {
        echo '<img src="noprofil.jpg" alt="Default Profile Image" class="img1">';
      }
      ?>
	  <div class="input-group"><br>
      <input style="font-family: 'Space Mono', monospace; color: rgb(224, 130, 204); font-weight: bold;  background: #efefef;" type="file" name="file">
      <button class="button button1" type="submit" name="submit">Upload</button>
	  </div>
    </form>
    <br>
	<section style="background: #fff;
				border: 3px solid rgb(224, 130, 204);
				width: 63%;
				border-radius: 20px;">
	<br>
    <p>Username: <?php echo $username; ?></p>
    <p>Email: <?php echo $email; ?></p>
    <form action="Account.php">
      <button>@Manage Password</button>
    </form>
	<br>
    </section>
  </div>
  </center>
  <br>
  <div class=""></div>
</section>
      
        
	  
    </main>
	<script>
	const navItems = document.querySelectorAll(".nav-item");

            navItems.forEach((navItem, i) => {
                navItem.addEventListener("click", () => {
                    navItems.forEach((item, j) => {
                        item.classList.remove("active");
                    });
                    navItem.classList.add("active");
                });
            });
</script>
  
</body>
</html>