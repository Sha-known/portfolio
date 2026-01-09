<?php include('security.php') ?>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
	width: 100%;
	
}
.button1 {
	background-color: rgb(224, 130, 204); 
	color: white; 
	border: 2px solid rgb(224, 130, 204);
}

.button1:hover {
	background-color: rgb(224, 130, 204);
	color: white;
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
<body style="background-color: #FFF;">
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
	  <section style="background: aliceblue;
				border: 3px solid #66b5ff;
                padding: 50px;
                line-height: 1.7em;
				margin: 30px;
				width: 94.5%;
				height: 92%;
				border-radius: 20px;">

<?php
// edit.php

$servername = 'localhost';
$dbusername = 'root';
$dbpassword = '';
$dbname = 'login';

$conn = new mysqli($servername, $dbusername, $dbpassword, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $id = $_POST["id"];
    $username = $_POST["username"];
    $newTitle = $_POST["newTitle"];
    $newEntry = $_POST["newEntry"];

    // Use prepared statement to avoid SQL injection
    $stmt = $conn->prepare("UPDATE entry SET title=?, entry=? WHERE id=? AND username=?");
    $stmt->bind_param("ssis", $newTitle, $newEntry, $id, $username);

    if ($stmt->execute()) {
        echo "Entry updated successfully";
        header("Location: entries2.php");
        exit();
    } else {
        echo "Error updating entry: " . $stmt->error;
    }

    $stmt->close();
} else {
    $id = $_GET["id"];
    $username = $_SESSION["username"]; // Assuming you store the username in the session

    // Use prepared statement to avoid SQL injection
    $stmt = $conn->prepare("SELECT id, date, title, entry FROM entry WHERE id=? AND username=?");
    $stmt->bind_param("is", $id, $username);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $title = htmlspecialchars($row["title"]);
        $entry = htmlspecialchars($row["entry"]);

        echo "<h1 style=\"font-family: 'Space Mono', monospace; color: black; text-align: center;\">Edit Entry</h1>";
        echo "<form method='post' action='edit.php'>";
        echo "<div class=\"input-group\">";
        echo "<input type='hidden' name='id' value='$id'>";
        echo "<input type='hidden' name='username' value='$username'>"; // Added username field
        echo "<label style=\"font-family: 'Space Mono', monospace; color: black; font-weight: bold; font-size: 1.2rem;\">Edit Entry Title:</label><br>";
        echo "<input style=\"font-family: 'Space Mono', monospace; color: #000; font-weight: bold;  background: #f2f2f2;\" type='text' name='newTitle' value='$title' placeholder='Entry Title Here...'><br><br>";
        echo "<label style=\"font-family: 'Space Mono', monospace; color: black; font-weight: bold; font-size: 1.2rem;\">Edit Entry:</label><br>";
        echo "<textarea style=\"font-family: 'Space Mono', monospace; color: #000; font-weight: bold;  background: #f2f2f2;\" name='newEntry' class='entry-text-box' placeholder='What\'s on your mind?'>$entry</textarea>";
        echo "<button class='button button1' name='submit' type='submit' style='font-family: \"Space Mono\", monospace;'>Save</button>";
        echo "</form>";
        echo "</div>";
    } else {
        echo "Entry not found";
    }

    $stmt->close();
}

$conn->close();
?>


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

