<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST["confirm"]) && $_POST["confirm"] == "yes") {
        $_SESSION = array();
        session_destroy();
        header("Location: login2.php");
        exit;
    } else {
        header("Location: home.php");
        exit;
    }
}
?>
