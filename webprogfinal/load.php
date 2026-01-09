<html>
<head>
	<link rel="stylesheet" href="loadstyle.css">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" href="favicon.ico" type="image/ico">
	<title>Loading...</title>
</head>
<body>
	<div class="example-loader-page">	
		<div class="determiniate-loader">
			<svg viewBox='-200 -200 400 400'>
				<defs>
					<linearGradient id="bts-red" gradientUnits="userSpaceOnUse" x1="13.6318" y1="9.0277" x2="61.6571" y2="66.262">
						<stop  offset="0" style="stop-color:#d48cb0"/>
						<stop  offset="1" style="stop-color:#de78ac"/>
					</linearGradient>
				</defs>

				<g class="base">
				<circle r='160'/>
				</g>
				<g class="filler">
				<circle stroke-width="6" stroke-linecap="round" stroke-linejoin="round"  r='160'/>
				</g>
			</svg>

		<div class="loader-status">
			<span class="count"></span>
		</div>
	</div>
</div>
<script>
	// Simulate loading time (optional)
	setTimeout(function () {
	// Redirect to the main page
	window.location.href = "login2.php";
	}, 5000); // Adjust the time as needed (in milliseconds)
</script>
</body>
</html>