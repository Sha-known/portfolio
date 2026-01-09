<?php
session_start();
include 'db.php';

$errors = [];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  $username = trim(filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING));
  $password = trim(filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING));

  if (empty($username)) {
    $errors['username'] = "Username is required.";
  }

  if (empty($password)) {
    $errors['password'] = "Password is required.";
  }

  if (empty($errors)) {
    $username = strtolower($username);

    $stmt = $conn->prepare("SELECT id, password FROM users WHERE username = ?");
    $stmt->bind_param("s", $username);
    $stmt->execute();
    $stmt->store_result();
    $stmt->bind_result($id, $hashed_password);
    $stmt->fetch();


    if ($stmt->num_rows > 0 && password_verify($password, $hashed_password)) {
      $_SESSION['user_id'] = $id;
      $_SESSION['username'] = $username;
      header("Location: index.php");
      exit();
    } else {
      $errors['general'] = "Invalid username or password.";
    }
  }

  $_SESSION['errors'] = $errors;
  $_SESSION['old'] = $_POST; // Store old input values
  $_SESSION['active_form'] = 'login';

  header("Location: login_signup.php");
  exit();
}
