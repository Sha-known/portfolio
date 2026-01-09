<?php
// delete.php
session_start();

$servername = 'localhost';
$username = 'root';
$password = '';
$dbname = 'login';

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    // Handle deletion of the entry from the database
    $id = $_GET["id"];

    // Check if the entry belongs to the currently logged-in user
    $username = $_SESSION["username"]; // Adjust based on your session variable name

    $stmt = $conn->prepare("DELETE FROM entry WHERE id=? AND username=?");
    $stmt->bind_param("is", $id, $username);

    if ($stmt->execute()) {
        echo "Entry deleted successfully";
        header("Location: entries2.php");
        exit();
    } else {
        echo "Error deleting entry: " . $stmt->error;
    }

    $stmt->close();
}

$conn->close();
?>
