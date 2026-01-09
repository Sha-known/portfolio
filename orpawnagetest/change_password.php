<?php

require_once 'db.php';

session_start();

$errors = [];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $old_password = trim(filter_input(INPUT_POST, 'old_password', FILTER_SANITIZE_STRING));
  $new_password = trim(filter_input(INPUT_POST, 'new_password', FILTER_SANITIZE_STRING));
  $confirm_new_password = trim(filter_input(INPUT_POST, 'confirm_new_password', FILTER_SANITIZE_STRING));

  // Input validation
  if (empty($old_password)) {
    $errors[] = "Old password is required.";
  }

  if (empty($new_password)) {
    $errors[] = "New password is required.";
  }

  if (empty($confirm_new_password)) {
    $errors[] = "Confirm new password is required.";
  }

  if ($new_password !== $confirm_new_password) {
    $errors[] = "New passwords do not match.";
  }

  if (strlen($new_password) < 6 || strlen($confirm_new_password) < 6) {
    $errors[] = "Password must be at least 6 characters long.";
  }

  // If no errors, proceed with password change
  if (empty($errors)) {
    $user_id = $_SESSION['user_id'];

    // Fetch the current password from the database
    $stmt = $conn->prepare("SELECT password FROM users WHERE id = ?");
    $stmt->bind_param("i", $user_id);
    $stmt->execute();
    $stmt->bind_result($hashed_password);
    $stmt->fetch();
    $stmt->close();

    // Verify the old password
    if (password_verify($old_password, $hashed_password)) {
      // Hash the new password
      $new_hashed_password = password_hash($new_password, PASSWORD_DEFAULT);

      // Update the password in the database
      $stmt = $conn->prepare("UPDATE users SET password = ? WHERE id = ?");
      $stmt->bind_param("si", $new_hashed_password, $user_id);

      if ($stmt->execute()) {
        $_SESSION['success_message'] = "Password changed successfully.";
        header("Location: index.php");
        exit();
      } else {
        $errors[] = "Failed to update password.";
      }
      $stmt->close();
    } else {
      $errors[] = "Old password is incorrect.";
    }
  }

  $_SESSION['errors'] = $errors;
  header("Location: index.php");
  exit();
}
