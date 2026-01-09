<?php include('security.php') ?>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Gallery</title>
<style>
div.gallery {
  border: 1px solid #ccc;
}

div.gallery:hover {
  border: 1px solid #777;
}

div.gallery img {
  width: 100%;
  height: auto;
}

div.desc {
  padding: 15px;
  text-align: center;
}

* {
  box-sizing: border-box;
}

.responsive {
  padding: 0 6px;
  float: left;
  width: 24.99999%;
}

@media only screen and (max-width: 700px) {
  .responsive {
    width: 49.99999%;
    margin: 6px 0;
  }
}

@media only screen and (max-width: 500px) {
  .responsive {
    width: 100%;
  }
}

.clearfix:after {
  content: "";
  display: table;
  clear: both;
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
		  
		  <li class="nav-item active">
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
	  <section id="entriesSection" style="background: aliceblue;
	  border: 3px solid #66b5ff;
                padding: 50px;
                line-height: 1.7em;
				margin: 30px;
				width: 93%;
				height: 89%;
				border-radius: 20px;">
	<h1 style="font-family: 'Space Mono', monospace; color: black; text-align: center;">GALLERY</h1>
	<br>
<?php

// Database connection parameters
$servername = 'localhost';
$username = 'root';
$password = '';
$dbname = 'login';

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Get the username from the session
if (isset($_SESSION["username"])) {
    $currentUsername = $_SESSION["username"];
    // Get the search text from the user input
    $searchText = isset($_GET['searchText']) ? $_GET['searchText'] : '';

    // Fetch data from the database for the specific user with search filtering
    $result = $conn->query("SELECT id, date FROM entry WHERE username = '$currentUsername' AND (title LIKE '%$searchText%' OR entry LIKE '%$searchText%') ORDER BY date DESC");

    if ($result->num_rows > 0) {
        // Output data of each row
        while ($row = $result->fetch_assoc()) {
            // Display Image for the current entry within the provided HTML structure
            echo "<div class='responsive'>";
            echo "<div class='gallery'>";
            echo "<a target='_blank' href='display.php?id=" . $row["id"] . "' style='text-decoration: none;'>";
            echo "<img src='display.php?id=" . $row["id"] . "' alt='Entry Image' style='width: 100%; height: 50%; border-radius: 5px;' />";
             echo "<p style='font-family: \"Space Mono\", monospace; color: white; color: #000; text-align: center;'>" . $row["date"] . "</p>";
			echo "</a>";
            echo "</div>";
            echo "</div>";
        }
    } else {
        echo "<div style='text-align: center;'><br><br><img src='lg1.png' alt='No entries found' style='display: inline-block; width: 27%; opacity: 0.5;'></div>";
    }
} else {
    echo "<p>User not logged in</p>";
}

// Close connection
$conn->close();
?>
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
	function editEntry(id) {
        // Redirect to the edit page with the entry ID
        window.location.href = "edit.php?id=" + id;
    }
	
	function deleteEntry(id) {
        // Use JavaScript to confirm deletion
        var confirmDelete = confirm("Are you sure you want to delete this entry?");
        if (confirmDelete) {
            // Redirect to the delete page with the entry ID
            window.location.href = "delete.php?id=" + id;
        }
    }
	
</script>
</body>
</html>