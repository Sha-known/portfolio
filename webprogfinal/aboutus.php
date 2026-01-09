<?php include('security.php') ?>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  <title>About Us</title>
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
 

  
  .fa:hover {
      opacity: 0.7;
  }
  
  .fa-facebook {
    background: #3B5998;
    color: white;
  }
  
  .fa-twitter {
    background: #55ACEE;
    color: white;
  }
  .fa-youtube {
    background: #bb0000;
    color: white;
  }
  
  .fa-instagram {
    background: #125688;
    color: white;
  }
  .logoss{
    padding:.2px;
    float: right;
    width: 200px;
    top: -240px;
    right: -950px;
	flex-wrap: wrap;
	text-align: center;

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

          <li class="nav-item active">
            <b></b>
            <b></b>
            <a href="aboutus.php">
              <i class="fa fa-info-circle nav-icon" style="font-size:2em;"></i>
              <span class="nav-text">About Us</span>
            </a>
          </li>
		  

          <li class="nav-item">
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
				height: 95%;
				border-radius: 20px;">
	  <div style="font-family: 'Space Mono', monospace; text-align: justify; line-height: 1.10rem;">
	  <p style="font-family: 'Space Mono', monospace;"><img class="pic" src="logotoh.png" alt="Persona" style="width:280px; height:280px; margin-right:15px; float:left;">
	  <h1 style="font-family: 'Space Mono', monospace; color: black;">ABOUT US</h1><br>
	  Step into the inviting realm of Persona Diary, a digital haven meticulously designed for introspection and mindfulness. 
	  Our platform is a secure sanctuary where you're encouraged to explore the depths of 
	  your thoughts and emotions. 
	  In the whirlwind of modern life, we acknowledge the therapeutic value of journaling, providing a judgment-free zone 
	  where every entry is a step towards understanding yourself. At Persona Diary, we are not just a platform but a supportive 
	  companion on your digital self-expression journey. Your narratives, be they triumphs or tribulations, matter here, 
	  as we believe in the transformative power of storytelling for personal growth and resilience. Welcome to a space where 
	  your unique story unfolds and the therapeutic magic of journaling is embraced.
	  </p>
	  </div>
	  <br>
	  <hr>
	  <br>
	  <div style="font-family: 'Space Mono', monospace; text-align: justify; line-height: 1.10rem;">
		<img class="pic" src="grp.jpg" alt="Our Company" style="width: 280px; height: 280px; margin-left: 15px; float: right;">
		<h1 style="font-family: 'Space Mono', monospace; color: black; text-align: right;">MEET OUR TEAM</h1><br>
		<p>
			 
			Meet our dedicated and talented team who work collaboratively to bring innovation and 
			excellence to every project.  From creative minds that bring ideas to life, 
			to meticulous planners ensuring smooth operations, each member plays a crucial role in our success. 
			Together, we share a passion for problem-solving, creativity, and a commitment to exceeding expectations. 
			Get to know the faces behind our success and discover the unique skills and personalities that make our 
			team thrive. 
			We take pride in our unity, diversity, and the collective pursuit of excellence that defines us as a
			team.<br><br>
		<!-- start of the icon -->
		<div style="border-radius: 50%;">
			<a href="https://www.facebook.com/" class="fa fa-facebook"></a>
			<a href="https://twitter.com/?lang=en" class="fa fa-twitter"></a>
			<a href="https://www.youtube.com/" class="fa fa-youtube"></a>
			<a href="https://www.instagram.com/" class="fa fa-instagram"></a>
		</p>
	  </div>
	  <br>
	  <br>
	  <br>
	  <hr>
	  <br>
	  <!-- start of the feedback form -->
<br>
<div>
  <h2 style="font-family: 'Space Mono', monospace; line-height: .5rem;">How can we help you?</h2><br><br>
  <form action="feedback.php" method="POST">
    <label for="feedback"></label>
    <textarea class="entry-text-box" name="feedback" style="color: black; font-weight: bold; font-size: 1rem; background: #f2f2f2;" placeholder="Write something..."></textarea>
    <input class="button button1" name="submit" type="submit" style="font-family: 'Space Mono', monospace; float: right; width:38%;" value="Submit">
  </form>
</div>
	  </section>
    </main>

  <script>
    // Your existing script for adding the "active" class to the clicked navigation item
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