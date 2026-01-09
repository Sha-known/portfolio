<?php
// Start the session
session_start();

// Database connection parameters
$host = "localhost";
$username = "root";
$password = "";
$database = "login";

// Create a database connection
$conn = new mysqli($host, $username, $password, $database);

// Check the connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Process form submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Check if "feedback" key is set in $_POST
    if (isset($_POST["feedback"])) {
        $feedback = $_POST["feedback"];

        // Get username from the session
        $username = $_SESSION["username"] ?? ''; // Assuming you've set the username in the session during login/signup

        // Check if $feedback is not null or empty
        if (!empty($feedback)) {

            // Use prepared statement to prevent SQL injection
            $stmt = $conn->prepare("INSERT INTO feedback (username, feedback) VALUES (?, ?)");
            $stmt->bind_param("ss", $username, $feedback);

            // Execute the statement
            if ($stmt->execute()) {
                echo '<script type="text/javascript">
                    window.onload = function () {alert("Feedback submitted successfully!"); window.location="aboutus.php"}
                    </script>';
                exit;
            } else {
                echo "Error: " . $stmt->error;
            }

            // Close the statement
            $stmt->close();
        } else {
            echo "Error: Feedback is empty or null.";
        }
    } else {
        echo "Error: Feedback key is not set in the form.";
    }
}

// Close the database connection
$conn->close();
?>
