<?php
ob_start();
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}
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
    $result = $conn->query("SELECT id, date, title, entry FROM entry WHERE username = '$currentUsername' AND (title LIKE '%$searchText%' OR entry LIKE '%$searchText%') ORDER BY date DESC");

    if ($result->num_rows > 0) {
        // Output data of each row
        while ($row = $result->fetch_assoc()) {
            echo "<div class='entry'>";
            echo "<h3 style='font-family: \"Space Mono\", monospace; color: white; color: #000; font-weight: bold;'>" . $row["title"] . "</h3>";
            echo "<p style='font-family: \"Space Mono\", monospace; color: white; color: #000;'><strong>Date:</strong> " . $row["date"] . "</p>";
            echo "<p style='font-family: \"Space Mono\", monospace; color: white; color: #000; '><strong>Entry:</strong> " . $row["entry"] . "</p>";

            // Display Image for the current entry
			
            echo "<img src='display.php?id=" . $row["id"] . "' alt='Entry Image' style='width: 100%; height: auto; border-radius: 5px;' />";

            // Add Edit and Delete buttons
            echo "<button class= \"button button1\" style='width: 49%;' onclick='editEntry(" . $row["id"] . ")'>Edit</button>";
            echo "<button class= \"button button1\" style='width: 49.9%;' onclick='deleteEntry(" . $row["id"] . ")'>Delete</button>";

            echo "</div>";
        }
    } else {
        echo "<div style='text-align: center;'><img src='lg2.png' alt='No entries found' style='display: inline-block; width: 27%; opacity: 0.5;'></div>";
    }
} else {
    echo "<p>User not logged in</p>";
}

// Close connection
$conn->close();
?>