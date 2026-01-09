<?php
session_start();
if(!isset($_SESSION['username'])){
  header('location: login2.php');
  exit();
}

$host = 'localhost';
$user = 'root';
$password = '';
$database = 'login';

$conn = new mysqli($host, $user, $password, $database);
$username = $_SESSION['username'];
$query = "SELECT username, email, password from users where username = '$username'";
$result  = $conn-> query($query);

if($result){
   if($result ->num_rows > 0){
    $row = $result -> fetch_assoc();
    $username = $row ['username'];
    $email = $row ['email'];
    $password = $row ['password'];
   } else {
    $username = "N/A";
    $email = "N/A";
   }
} else{
    $username = "N/A";
    $email = "N/A";
}
$conn ->close();
?>