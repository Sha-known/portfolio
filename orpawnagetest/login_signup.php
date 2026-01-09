<?php
session_start();

$active_form = $_SESSION['active_form'] ?? 'login';
$errors = $_SESSION['errors'] ?? [];
$signup_errors = $_SESSION['signup_errors'] ?? [];
$old = $_SESSION['old'] ?? [];
$signup_old = $_SESSION['signup_old'] ?? [];

unset($_SESSION['active_form']);
unset($_SESSION['errors']);
unset($_SESSION['signup_errors']);
unset($_SESSION['old']);
?>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>ORPAWNAGE</title>
	<link rel="stylesheet" type="text/css" href="assets/css/login_signup.css">
	<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>

	<div class="container" style="background: linear-gradient(to bottom, #fcc2c2, #e7f7a3);" <?php echo $active_form === 'register' ? 'active' : ''; ?>">
		<div class="form-box login" style="background: linear-gradient(to bottom, #fcc2c2, #e7f7a3);">
			<form action="login.php" method="POST">
				<img src="assets/images/Orpawnage.logo.png" alt="logo">
				<h1>Sign In</h1>


				<div class="input-box">
					<input type="text" name="username" placeholder="Username" value="<?php echo isset($old['username']) ? htmlspecialchars($old['username']) : ''; ?>" required>
					<i class='bx bxs-user'></i>
					<?php if (!empty($errors['username'])): ?>
						<p class=" error"><?php echo $errors['username']; ?></p>
					<?php endif; ?>
				</div>

				<div class="input-box">
					<input id="logPasswordInput" type="password" name="password" placeholder="Password" required>
					<i id="logToggleIcon" style="cursor:pointer;" class='bx bxs-show'></i>
					<?php if (!empty($errors['password'])): ?>
						<p class="error"><?php echo $errors['password']; ?></p>
					<?php endif; ?>
				</div>

				<?php if (!empty($errors['general'])): ?>
					<p class="error"><?php echo $errors['general']; ?></p>
				<?php endif; ?>

				<div class="forgot-link">
					<a href="#">Forgot password?</a>
				</div>

				<button type="submit" class="btn">Sign In</button>
			</form>
		</div>
		<div class="form-box register" style="background: linear-gradient(to bottom, #fcc2c2, #e7f7a3); ">
			<form action="signup.php" method="POST">
				<img src="assets/images/Orpawnage.logo.png" alt="logo">
				<h1>Create Account</h1>
				<div class="input-box" >
					<input type="text" name="username" placeholder="Username" value="<?php echo $signup_old['username'] ?? ''; ?>" required>
					<i class='bx bxs-user'></i>
					<?php if (!empty($signup_errors['username'])): ?>
						<p class="error"><?php echo $signup_errors['username']; ?></p>
					<?php endif; ?>
				</div>

				<div class="input-box">
					<input type="email" name="email" placeholder="Email" value="<?php echo $signup_old['email'] ?? ''; ?>" required>
					<i class='bx bxs-envelope'></i>
					<?php if (!empty($signup_errors['email'])): ?>
						<p class="error"><?php echo $signup_errors['email']; ?></p>
					<?php endif; ?>
				</div>

				<div class="input-box">
					<input type="text" name="contact_no" placeholder="Contact No." value="<?php echo $signup_old['contact_no'] ?? ''; ?>" required>
					<i class='bx bxs-phone'></i>
					<?php if (!empty($signup_errors['contact_no'])): ?>
						<p class="error"><?php echo $signup_errors['contact_no']; ?></p>
					<?php endif; ?>
				</div>

				<div class="input-box">
					<input id="regPasswordInput" type="password" name="password" placeholder="Password" required>
					<i id="regToggleIcon" style="cursor: pointer;" class='bx bxs-show'></i>
					<?php if (!empty($signup_errors['password'])): ?>
						<p class="error"><?php echo $signup_errors['password']; ?></p>
					<?php endif; ?>
				</div>

				<?php if (!empty($signup_errors['general'])): ?>
					<p class="error"><?php echo $signup_errors['general']; ?></p>
				<?php endif; ?>

				<button type="submit" class="btn">Sign Up</button>
			</form>
			
		</div>

		<div class="toggle-box">
			<div class="toggle-panel toggle-left">
				<h1>Welcome back! Find love today!</h1>
				<div class="bottom">
					<p>Don't have an account? Sign up now. </p>
					<button class="btn register-btn">Sign Up</button>
				</div>
			</div>
			<div class="toggle-panel toggle-right">
				<h1>Join us! Save lives today!</h1>
				<div class="bottom">
					<p>Already have an account? Sign in now.</p>
					<button class="btn login-btn">Sign In</button>
				</div>
			</div>
		</div>
	</div>

	
	<script src="assets/js/login_signup.js"></script>

</body>

</html>