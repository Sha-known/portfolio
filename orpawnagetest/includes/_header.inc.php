<?php
session_start();

if (!isset($_SESSION['user_id'])) {
  header("Location: login_signup.php");
}

$errors = $_SESSION['errors'] ?? [];
$success = $_SESSION['success_message'] ?? [];

$keepModalOpen = !empty($errors) || !empty($success);

unset($_SESSION['errors']);
unset($_SESSION['success_message']);

?>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!--=============== FAVICON ===============-->
  <link rel="shortcut icon" href="assets/images/Orpawnage.logo.png" type="image/x-icon">

  <!--=============== REMIX ICONS ===============-->
  <link
    href="https://cdn.jsdelivr.net/npm/remixicon@4.5.0/fonts/remixicon.css" rel="stylesheet" />

  <!--=============== SWIPER CSS ===============-->
  <link rel="stylesheet" href="assets/css/swiper-bundle.min.css">

  <!--=============== CSS ===============-->
  <link rel="stylesheet" href="assets/css/load.css">
  <link rel="stylesheet" href="assets/css/about.css">
  <link rel="stylesheet" href="assets/css/donation.css">
  <link rel="stylesheet" href="assets/css/index.css">

  <title>ORPAWNAGE</title>
</head>


<body>
  <!--==================== PRELOADER ====================-->
  <?php include_once 'includes/_load.inc.php'; ?>


  <!--==================== HEADER ====================-->
  <header class="header" id="header">
    <nav class="nav">
      <a href="index.php" class="nav__logo">
        <img src="assets/images/Orpawnage.logo.png" alt="logo">
        </i>
      </a>
      <div class="nav__menu" id="nav-menu">
        <ul class="nav__list">
          <li class="nav__item">
            <a href="index.php" class="nav__link active-link">Home</a>
          </li>
          <li class="nav__item">
            <a href="#services" class="nav__link">Services</a>
          </li>
          <li class="nav__item">
            <a href="#report" class="nav__link">Report</a>
          </li>
          <li class="nav__item">
            <a href="about.php" class="nav__link">About Us</a>
          </li>
          <li class="nav__item">
            <a href="donation.php" class="nav__link">Donations</a>
          </li>
          <li class="nav__item">
            <a href="#profile" id="user_settings" class="nav__link">
              <i class="ri-account-circle-line"></i>
            </a>
            <!-- Dropdown Menu -->
            <div class="dropdown-menu" id="dropdownMenu">
              <a href="#" class="dropdown-item btn--show-modal">Change Password</a>
              <a href="#" class="dropdown-item">Transaction Status</a>
              <a href="logout.php" class="dropdown-item">Logout (<?php echo $_SESSION['username']; ?>)</a>
            </div>
          </li>
        </ul>

        <div class="nav__close" id="nav-close">
          <i class="ri-close-line"></i>
        </div>
      </div>

      <!--Toggle button -->
      <dev class="nav__toggle" id="nav-toggle">
        <i class="ri-menu-line"></i>
      </dev>
    </nav>
  </header>

  <!--==================== CHANGE PASSWORD MODAL ====================-->
  <div class="modal <?php echo $keepModalOpen ? '' : 'hidden'; ?>">
    <button class="btn--close-modal">&times;</button>
    <h2 class="modal__header">
      Change Password
    </h2>
    <form class="modal__form" action="change_password.php" method="POST">
      <div class="input-box">
        <label for="oldPass">Old Password</label>
        <input name="old_password" id="oldPass" class="password--input" type="password" placeholder="Old Password" required />
      </div>
      <div class="input-box">
        <label for="newPass">New Password</label>
        <input name="new_password" id="newPass" class="password--input" type="password" placeholder="New Password" required />
      </div>
      <div class="input-box">
        <label for="confirmPass">Re-type New Password</label>
        <input name="confirm_new_password" id="confirmPass" class="password--input" type="password" placeholder="Re-type New Password" required />
      </div>
      <div class="input-box" id="toggle-password">
        <input type="checkbox" id="togglePass">
        <label for="togglePass">Show Password</label>
      </div>

      <?php if (!empty($success)): ?>
        <p class="success"><?php echo $success; ?></p>
        <?php unset($_SESSION['success_message']); ?>
      <?php endif; ?>

      <?php if (!empty($errors)): ?>
        <?php foreach ($errors as $error): ?>
          <p class="error"><?php echo $error; ?></p>
          <?php unset($_SESSION['errors']); ?>
        <?php endforeach; ?>
      <?php endif; ?>

      <button class="button change--password" name="change_password">Submit</button>
    </form>
  </div>
  <div class="overlay hidden"></div>