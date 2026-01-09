<?php
// Include database connection
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "login";

// Start the session
session_start();

// Create database connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check the connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Check if 'username' is set in the session
if (isset($_SESSION['username'])) {
    $username = $_SESSION['username'];

    if (isset($_POST['submit'])) {
		$file = $_FILES['file'];

		$fileName = $file['name'];
		$fileTmpName = $file['tmp_name'];
		$fileSize = $file['size'];
		$fileError = $file['error'];
		$fileType = $file['type'];

		$fileExt = explode('.', $fileName);
		$fileActExt = strtolower(end($fileExt));

		$allowed = array('jpg', 'jpeg');

		if (in_array($fileActExt, $allowed)) {
			if ($fileError === 0) {
				if ($fileSize < 1000000) {
					$fileNewName = "profile" . $username . '.' . $fileActExt;
					$finalDestination = 'uploads/' . $fileNewName;
					move_uploaded_file($fileTmpName, $finalDestination);

					$sql = "UPDATE profileimg SET status = 0 WHERE username='$username';";
                
                // Perform mysqli_query without closing the connection explicitly
					$result = mysqli_query($conn, $sql);

					if ($result) {
						header("Location: profile.php?upload_success");
						exit();
					} else {
						echo "Error updating database: " . mysqli_error($conn);
					}
				} else {
					echo "Your file is too big!";
				}
			} else {
				echo "There was an error uploading your file!";
			}
		} else {
			echo "You cannot upload files of this type!";
		}
}
}
// Close the connection after all operations
$conn->close();
?>
