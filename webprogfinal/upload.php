<?php
session_start();

// Check if a user is logged in
if (!isset($_SESSION["username"])) {
    die("User not logged in");
}

// Get the current username from the session
$currentUsername = $_SESSION["username"];

// Check if an image file was uploaded
if (isset($_FILES['image']) && $_FILES['image']['error'] == 0) {
    $name = $_FILES['image']['name'];
    $type = $_FILES['image']['type'];
    $data = file_get_contents($_FILES['image']['tmp_name']);

    // Connect to the database
    $pdo = new PDO('mysql:host=localhost;dbname=login', 'root', '');

    // Insert the image data into the database along with the username
    $stmt = $pdo->prepare("INSERT INTO images (username, name, data) VALUES (?, ?, ?)");
    $stmt->bindParam(1, $currentUsername);
    $stmt->bindParam(2, $name);
    $stmt->bindParam(3, $data);

    if ($stmt->execute()) {
        echo "Image uploaded successfully!";
    } else {
        echo "Error uploading image: " . $stmt->errorInfo()[2];
    }
}
?>
