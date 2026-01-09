<?php
session_start();

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$host = 'localhost';
$user = 'root';
$password = '';
$database = 'login';

$conn = new mysqli($host, $user, $password, $database);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['signup'])) {
        $username = trim($_POST['username']);
        $email = trim($_POST['email']);
        $password = trim($_POST['password']);

        // Check if the username already exists
        $checkUsernameStmt = $conn->prepare("SELECT * FROM users WHERE username = ?");
        $checkUsernameStmt->bind_param("s", $username);
        $checkUsernameStmt->execute();
        $checkUsernameResult = $checkUsernameStmt->get_result();

        // Check if the email already exists
        $checkEmailStmt = $conn->prepare("SELECT * FROM users WHERE email = ?");
        $checkEmailStmt->bind_param("s", $email);
        $checkEmailStmt->execute();
        $checkEmailResult = $checkEmailStmt->get_result();

        if ($checkUsernameResult->num_rows > 0) {
            echo '<script type="text/javascript">
                        window.onload = function () {alert("Username already exists. Choose another."); window.location="login2.php"}
                      </script>';
        } elseif ($checkEmailResult->num_rows > 0) {
            echo '<script type="text/javascript">
                        window.onload = function () {alert("Email already exists. Choose another."); window.location="login2.php"}
                      </script>';
        } else {
            $hashedPassword = password_hash($password, PASSWORD_DEFAULT);

            $stmt = $conn->prepare("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
            $stmt->bind_param("sss", $username, $email, $hashedPassword);

            if ($stmt->execute()) {
                $_SESSION['id'] = $conn->insert_id; // Use the auto-incremented id as the session identifier
                header("Location: login2.php");
                exit();
            } else {
                echo "Error: " . $stmt->error;
            }
        }
    }

    if (isset($_POST['signin'])) {
        $username = trim($_POST['username']);
        $signInPassword = trim($_POST['password']);

        $stmt = $conn->prepare("SELECT id, password FROM users WHERE username = ?");
        $stmt->bind_param("s", $username);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            $passwordFromDB = $row['password'];

            if (password_verify($signInPassword, $passwordFromDB)) {
                $_SESSION['username'] = $username;
                header("Location: main.php");
                exit();
            } else {
                echo '<script type="text/javascript">
                        window.onload = function () {alert("Incorrect Password or Username"); window.location="login2.php"}
                      </script>';
            }
        } else {
            echo "Username not found.";
        }
    }
}

$conn->close();
?>
