<?php
session_start();
include 'db.php';

$errors = [];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  $username = trim(filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING));
  $email = trim(filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL));
  $contact_no = trim(filter_input(INPUT_POST, 'contact_no', FILTER_SANITIZE_STRING));
  $password = trim(filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING));

  if (empty($username)) {
    $errors['username'] = "<script type='text/javascript'>
                        window.onload = function () { alert('Username is required.'); window.location='login_signup.php'; }
                      </script>";
} elseif (strlen($username) < 3) {
    $errors['username'] = "<script type='text/javascript'>
                        window.onload = function () { alert('Username must be at least three characters long.'); window.location='login_signup.php'; }
                      </script>";
}


  if (empty($email)) {
    $errors['email'] = "Email is required.";
  } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    $errors['email'] = "Invalid email format.";
  }

  if (empty($contact_no)) {
    $errors['contact_no'] = "Contact number is required.";
  } elseif (!preg_match("/^[0-9]{10,15}$/", $contact_no)) {
    $errors['contact_no'] = "Contact number must be 10-15 digits.";
  }

  if (empty($password)) {
    $errors['password'] = "Password is required.";
  } elseif (strlen($password) < 6) {
    $errors['password'] = "Password must be at least 6 characters long.";
  }

  if (empty($errors)) {
    $username = strtolower($username);
    $email = strtolower($email);

    $hashed_password = password_hash($password, PASSWORD_BCRYPT);
    $stmt = $conn->prepare("INSERT INTO users (username, email, contact_no, password) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("ssss", $username, $email, $contact_no, $hashed_password);

    if ($stmt->execute()) {
      $_SESSION['active_form'] = 'login';
      header("Location: login_signup.php");
      exit();
    } else {
      $errors['general'] = "Error creating account. Please try again.";
    }
  }

  $_SESSION['signup_errors'] = $errors;
  $_SESSION['signup_old'] = $_POST;
  $_SESSION['active_form'] = 'register';

  header("Location: login_signup.php");
  exit();
}
