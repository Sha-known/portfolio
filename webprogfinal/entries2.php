<?php
ob_start();
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
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Read Entries</title>
<style>
.entry {
	margin-bottom: 20px;
	border: 3px solid gray;
	background-color: #fff;
	padding: 20px;
}

.entry h3 {
	margin-top: 0;
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
.search-container {
    margin-top: 20px;
    text-align: right;
}

#searchInput {
    padding: 10px;
    width: 200px;
	border-radius: 15px;
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
	.search-container {
		margin-top: 20px;
		text-align: right;
	}

	#searchInput {
		padding: 10px;
		width: 100%;
		border-radius: 15px;
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

          <li class="nav-item active">
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
	  <section id="entriesSection" 
	  style="	padding: 50px;
                line-height: 1.7em;
				margin: 30px;
				width: 93%;
				height: 89%;
				border-radius: 20px;">
	   <h1 style="font-family: 'Space Mono', monospace; color: black; text-align: center;">ENTRIES</h1>
	   <div class="search-container">
        <input type="text" id="searchInput" placeholder="Search...">
        <button class="button1" style="padding: 10px; border: 1px solid white;" onclick="searchEntries()"><i class="fa fa-search"></i></button>
		</div>
		<br>
<?php include('entries_connect.php') ?>
	  </section>
    </main>
	<script>
	  function searchEntries() {
    // Get the value from the search input
    var searchText = document.getElementById('searchInput').value;

    // Use AJAX to send the search text to the server
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // Update the entries section with the response
            document.getElementById('entriesSection').innerHTML = xhr.responseText;
        }
    };

    // Send the search text as a parameter to the same PHP file
    xhr.open('GET', 'entries_connect.php?searchText=' + searchText, true);
    xhr.send();
}
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