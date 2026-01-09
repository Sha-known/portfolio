<?php include('security.php') ?>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="Account.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="profile.css">
  <title>Account</title>
<style>
@media only screen and (min-width: 360px) and (max-width: 768px) {
      * {
        font-size: 14px; /* Adjust font size for smaller screens */
		
      }

	main {
        grid-template-columns: 20%, 80%; /* Full width for small screens */
      }

	section {
        width: 17em !important;
		height: 98% !important;
        margin: 1px; 
      }
	 img.pic {
        width: 10em !important;
        height: 10em !important;
        margin-right: 0;
        float: none;
    }
	.logo {
		display: block;
		width: 65px;
		margin: 5px auto;
	}
	.input-group input {
    width: 100%;
	font-size: .5rem;
	height: 1em;
	}
	.input-group input[type="file"] {
        font-family: 'Space Mono', monospace;
        color: rgb(224, 130, 204);
        font-weight: bold;
		width: 100% !important;
		height: 2em !important;
    }
	label{
		font-size: 1rem;
	}
	
	.button, .button1 {
    width: 100% !important;
	text-align: center;
	font-size: .5rem;
	height: 1em;
}

.entry-text-box {
  width: 100%;
  height: 10em;
}
.nav-icon {
  width: 40px;
  height: 20px;
  font-size: 20px;
  text-align: center;
  margin-left: -.2em;
}
    }
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
  width: 100%;
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
 <main style="border: 1px solid rgb(224, 130, 204); grid-template-columns: 16% 84%;">
        <nav class="main-menu">
            <h1>Persona</h1>
            <img class="logo" src="lg4.png" alt="">
            <ul>
         
          <li class="nav-item">
            <b></b>
            <b></b>
            <a href="main.php">
              <i class="fa fa-plus nav-icon" style="font-size:2em;"></i>
              <span class="nav-text">Add</span>
            </a>
          </li>

          <li class="nav-item">
            <b></b>
            <b></b>
            <a href="entries2.php">
              <i class="	fa fa-book nav-icon" style="font-size:2em;"></i>
              <span class="nav-text">Entries</span>
            </a>
          </li>
		  
		  <li class="nav-item">
            <b></b>
            <b></b>
            <a href="gallery.php">
              <i class="fa fa-image nav-icon" style="font-size:2em;"></i>
              <span class="nav-text">Gallery</span>
            </a>
          </li>

          <li class="nav-item">
            <b></b>
            <b></b>
            <a href="aboutus.php">
              <i class="fa fa-info-circle nav-icon" style="font-size:2em;"></i>
              <span class="nav-text">About Us</span>
            </a>
          </li>
		 

          <li class="nav-item active">
            <b></b>
            <b></b>
            <a href="logout.php">
              <i class="fa fa-power-off nav-icon" style="font-size:2em;"></i>
              <span class="nav-text">Logout</span>
            </a>
          </li>
        </ul>
      </nav>
      <section style="background: aliceblue;
				border: 3px solid #66b5ff;
                padding: 50px;
                line-height: 1.7em;
				margin: 30px;
				width: 94.5%;
				height: 92%;
				border-radius: 20px;">
				<center><br><br>
  <div class="account">
    <form action="last.php" method="post">
      <label for="current_password">Current Password</label>
      <input type="password" name="current_password" id="current_password" required>

      <label for="new_password">New Password</label>
      <input type="password" name="new_password" id="new_password" required>

      <label for="confirm_password">Confirm Password</label>
      <input type="password" name="confirm_password" id="confirm_password" required>

      <button class="button button1" type="submit" name="change_password">Change Password</button>
    </form>
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
