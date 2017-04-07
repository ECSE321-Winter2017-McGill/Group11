<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Login Page</title>
    
    <style>
			.error {color: #FF0000;}
	</style>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

  </head>
  <body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h1>
						TAMAS <small>The Teacher Assistant Management System</small>
					</h1>
				</div>
				<p>
					Welcome to the Instructor module. This login page is not fully implemented yet.
				</p>
				<form action="Login.php" method="post">
					<p> Email: <input type="text" name="instructorEmail"/>
					<span class="error">
					<?php
					session_start();
					if (isset($_SESSION['errorEmail']) && !empty($_SESSION['errorEmail'])){
						echo " * " . $_SESSION["errorEmail"];
					}
					?>
					</span></p>
					
					<p> Instructor ID: <input type="text" name="instructorID"/>
					<span class="error">
					<?php
					if (isset($_SESSION['errorID']) && !empty($_SESSION['errorID'])){
						echo " * " . $_SESSION["errorID"];
					}
					?>
					</span></p>
					
					<p><input type="submit" value="Login to TAMAS"/></p>
					
					<h3>
						The following are premade dummy instructors.
					</h3>
					<table class="Instructor attributes table">
						<thead>
							<tr>
								<th>
									Name
								</th>
								<th>
									Email
								</th>
								<th>
									ID
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									Teacher Teaching
								</td>
								<td>
									teacher.teaching@mcgill.ca
								</td>
								<td>
									260543215
								</td>
							</tr>
							<tr>
								<td>
									Instructor Teaching
								</td>
								<td>
									instructor.teaching@mcgill.ca
								</td>
								<td>
									260102344
								</td>
							</tr>
							<tr>
								<td>
									Test Instructor
								</td>
								<td>
									test.instructor@mcgill.ca
								</td>
								<td>
									000000000
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>