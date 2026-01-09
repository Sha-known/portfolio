<?php
session_start();

// Check if a user is logged in
if (!isset($_SESSION["username"])) {
    die("User not logged in");
}

// Get the current username from the session
$currentUsername = $_SESSION["username"];

// Get the ID of the image from the URL
$id = $_GET['id'];

// Connect to the database
$pdo = new PDO('mysql:host=localhost;dbname=login', 'root', '');

// Retrieve the image data from the database, including the 'username' filter
$stmt = $pdo->prepare("SELECT name, data FROM images WHERE id=? AND username=?");
$stmt->bindParam(1, $id);
$stmt->bindParam(2, $currentUsername);
$stmt->execute();

// Fetch the file extension from the image name
$row = $stmt->fetch(PDO::FETCH_ASSOC);
$fileExtension = pathinfo($row['name'], PATHINFO_EXTENSION);

// Determine the content type based on the file extension
switch ($fileExtension) {
    case "gif": $contentType = "image/gif"; break;
    case "png": $contentType = "image/png"; break;
    case "jpeg":
    case "jpg": $contentType = "image/jpeg"; break;
    case "svg": $contentType = "image/svg+xml"; break;
    default: $contentType = "application/octet-stream"; break; // Default to binary data
}

// Set the content type header
header("Content-Type: $contentType");

// Output the image data
echo $row['data'];
?>
