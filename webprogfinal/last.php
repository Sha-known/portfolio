<?php
session_start();

if (!isset($_SESSION['username'])) {
    header('location: logout.php');
    exit();
}

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['change_password'])) {
    $new_password = $_POST['new_password'];
    $confirm_password = $_POST['confirm_password'];
    $current_password = $_POST['current_password'];

    if ($new_password != $confirm_password) {
        echo "Passwords do not match!";
        exit();
    }

    $host = 'localhost';
    $user = 'root';
    $password = '';
    $database = 'login';

    $conn = new mysqli($host, $user, $password, $database);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $username = $_SESSION['username'];
    $query = "SELECT password FROM users WHERE username = '$username'";
    $result = $conn->query($query);

    if ($result && $result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $stored_password_hash = $row['password'];

        // Verify the current password
        if (password_verify($current_password, $stored_password_hash)) {
            // Hash the new password
            $new_password_hashed = password_hash($new_password, PASSWORD_DEFAULT);

            $updateQuery = "UPDATE users SET password = ? WHERE username = ?";
            $stmt = $conn->prepare($updateQuery);
            $stmt->bind_param("ss", $new_password_hashed, $username);
            $stmt->execute();

            if ($stmt->affected_rows > 0) {
                echo '<script type="text/javascript">
				window.onload = function () {alert("Password changed successfully!"); window.location="logout.php"}
				</script>';
            } else {
                echo '<script type="text/javascript">
				window.onload = function () {alert("Error updating password."); window.location="logout.php"}
				</script>';
            }

            $stmt->close();
        } else {
            echo '<script type="text/javascript">
				window.onload = function () {alert("Incorrect current password."); window.location="logout.php"}
				</script>';
        }
    } else {
        echo "Error retrieving current password: " . $conn->error;
    }

    $conn->close();
    exit();
}
?>
