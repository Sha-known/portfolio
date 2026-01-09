<?php
session_start();
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

// Process form submission
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["submit"])) {
    // Use the username from the session
    $userid = $_SESSION["id"];

    $date = $_POST["date"];
    $title = $_POST["title"];
    $entry = $_POST["entry"];
	
	// Set the username and email in the session
	$_SESSION["id"] = $userid; // Assuming you have the username from the signup process
	$_SESSION["username"] = $username;

    // Insert data into the database using a prepared statement
    $stmt = $conn->prepare("INSERT INTO entry (username, date, title, entry) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("ssss", $username, $date, $title, $entry);

    if ($stmt->execute()) {
        // Redirect to entries.php after successful submission
        header("Location: entries2.php");
        exit();
    } else {
        echo "Error: " . $stmt->error;
    }

    $stmt->close(); // Close the prepared statement
}

// Close connection
$conn->close();
?>