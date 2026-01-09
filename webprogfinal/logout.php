<?php include('security.php') ?>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Logout</title>
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
                        <i class="fa fa-book nav-icon" style="font-size:2em;"></i>
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
            <a href="#" onclick="confirmLogout()">
              <i class="fa fa-power-off nav-icon" style="font-size:2em;"></i>
              <span class="nav-text">Logout</span>
              
              <!-- Logout form -->
        <form id="logoutForm" action="logout_connect.php" method="post">
          <!-- Hidden input  -->
          <input type="hidden" name="confirm" value="yes">
      </form>

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
	  <h1 style="font-family: 'Space Mono', monospace; color: black; text-align: center;">PERSONA</h1>
	  <br>
	  <p style="font-family: 'Space Mono', monospace;"><center>Logging out is not just closing a chapter; it's an opportunity to start a new one. 
	  As you bid farewell to 'Persona' for now, remember: Your stories are your own, and this digital 
	  canvas awaits your next masterpiece. Until our next rendezvous, embrace the beauty of your narrative.
	  Keep creating, keep reflecting, and keep making moments that matter. See you soon, storyteller!</center></p>
		<br><br>
	<center>
	<section style="background: aliceblues;
				border: 3px solid gray;
				width: 100%;
				border-radius: 20px;">
	<br>
    <p>Username: <?php echo $username; ?></p>
    <p>Email: <?php echo $email; ?></p>
    <form action="Account.php">
      <button>@Manage Password</button>
    </form>
	<br>
    </section>
	</center>
	<br><br>
	  <footer style="background: #333; color: white; text-align: center; padding: 10px;">
        &copy; 2023 Persona Diary. All rights reserved.
    </footer>
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


            function confirmLogout() {
            var result = confirm("Do you want to logout?");
            
            if (result) {
                document.getElementById("logoutForm").submit();
            }
        }

</script>
</body>
</html>