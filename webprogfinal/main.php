<?php include('security.php') ?>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>Add</title>
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
.button1:disabled {
  background-color: #ccc;
  color: #666;
  cursor: not-allowed;
}

.button1.disabled:hover {
  background-color: #ccc;
  color: #666;
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
@media only screen and (min-width: 360px) and (max-width: 768px) {
      * {
        font-size: 14px; /* Adjust font size for smaller screens */
		
      }

	main {
        grid-template-columns: 20%, 80%; /* Full width for small screens */
      }

	section {
        width: 17em !important;
		height: 35em !important;
        margin: 1px; 
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
          <li class="nav-item active">
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
	  <section style="background:  ALICEBLUE;
				border: 3px solid #66b5ff;
                padding: 50px;
                line-height: 1.7em;
				margin: 30px;
				width: 95%;
				height: 92%;
				border-radius: 20px;">
	  <h1 style="font-family: 'Space Mono', monospace; color: black; text-align: center;">PERSONA</h1>
	  <form action="process_main.php" method="POST">
	  <div class="input-group">
			<label style="font-family: 'Space Mono', monospace; color: black; font-weight: bold; font-size: 1.2rem;">Entry Date:</label><br>
			<input type="Date" name="date" style="font-family: 'Space Mono', monospace; color: #000; font-weight: bold;  background: #f2f2f2;" placeholder="Entry Date Here..." required>
			<br>
			<label style="font-family: 'Space Mono', monospace; color: black; font-weight: bold; font-size: 1.2rem;">Entry Title:</label><br>
			<input type="text" name="title" style="font-family: 'Space Mono', monospace; color: #000; font-weight: bold;  background: #f2f2f2;" placeholder="Entry Title Here..." required>
			<br>
			<label style="font-family: 'Space Mono', monospace; color: black; font-weight: bold; font-size: 1.2rem;">Today's Entry:</label><br>
			<textarea name="entry" class="entry-text-box" style="color: black; font-weight: bold; font-size: 1rem; background: #f2f2f2;" placeholder="What's on your mind?" required></textarea>
			<button class="button button1" name="submit" type="submit" style="font-family: 'Space Mono', monospace; float: right; width:33%;">Submit</button>
	  </div>
	  </form>
	  <form id="uploadForm" enctype="multipart/form-data">
	  <div class="input-group">
	  <input accept="image/png, image/gif, image/jpeg" style="font-family: 'Space Mono', monospace; color: rgb(224, 130, 204); font-weight: bold;  background: #efefef;" type="file" name="image" id="image" required>
	  <button style="width:15%;" class="button button1" type="button" onclick="uploadImage()">Upload</button>
	  </div>
	  </form>
<div id="uploadStatus"></div>
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
        $('#uploadStatus').html(response);
        // Enable the submit button after successful image upload
        enableSubmitButton();
      },
      error: function (jqXHR, textStatus, errorThrown) {
        console.log('Error:', errorThrown);
      }
    });
  }

  function enableSubmitButton() {
    // Check if an image has been uploaded
    var uploadedImage = $('#image').prop('files')[0];

    // If an image is uploaded, enable the submit button
    if (uploadedImage) {
      $('button[name="submit"]').prop('disabled', false).removeClass('disabled');
    } else {
      // If no image is uploaded, disable the submit button
      $('button[name="submit"]').prop('disabled', true).addClass('disabled');
    }
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

  // Initial check to disable the submit button if no image is uploaded
  enableSubmitButton();
</script>

</body>
</html>
