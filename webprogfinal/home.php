<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="homestyle.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>Home</title>
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
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
.center-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh; /* Adjust this value based on your layout */
  }
.content {
  position: relative;
  max-width: 1000px;
  height: 500px;
  margin: 2px;
  bottom: 0;
  background: rgb(0, 0, 0); /* Fallback color */
  background: rgba(0, 0, 0, 0.5); /* Black background with 0.5 opacity */
  color: #f1f1f1;
  width: 100%;
  padding: 30px;
}
</style>
</head>
<body style="background-color: aliceblue;">
 <main style="border: 1px solid rgb(224, 130, 204);">
      <nav class="main-menu">
        <h1>Persona</h1>
        <img class="logo" src="logo3.png" alt="">
        <ul>
          <li class="nav-item active">
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
	  <section style="position: relative;
				background-image: url('diary.jpg');
				background-repeat: no-repeat;
				background-size: cover;
				opacity: 0.6px;
				border: 3px solid #66b5ff;
                padding: 50px;
                line-height: 1.7em;
				margin: 30px;
				width: 94.5%;
				height: 92%;
				border-radius: 20px; display: flex; align-items: center; justify-content: center; text-align: center;">
    <div class="container">
        <div class="content">
            <BR><BR>
            <h1 style="font-family: 'Space Mono', monospace; color: white; font-size: 20px; font-weight: bold;">Welcome to</h1>
            <BR><BR><BR><BR>
            <h1 style="font-family: 'Space Mono', monospace; color: white; font-size: 230px; font-weight: bold;">PERSONA</h1>
            <div style="position: absolute; bottom: 15%; left: 50%; transform: translateX(-50%);">
                <a href="main.php" class="button button1" role="button">Get Started</a>
            </div>
        </div>
    </div>
</section>
    </main>

  <script>

  function uploadImage() {
    var formData = new FormData($('#uploadForm')[0]);

    $.ajax({
        url: 'upload.php',  // Replace with the actual PHP file handling the upload
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        beforeSend: function () {
            $('#uploadStatus').html('Uploading...');
        },
        success: function (response) {
            $('#uploadStatus').html('Succesfully uploaded', response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('Error:', errorThrown);
        }
    });
}
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
