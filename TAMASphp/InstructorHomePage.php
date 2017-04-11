<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Instructor Home Page</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

	</head>
	<body>

	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#">Home</a>
					</li>
					<li class="disabled">
						<a href="#">Profile</a>
					</li>
					<li class="disabled">
						<a href="#">Course List</a>
					</li>
					<li class="dropdown pull">
						 <a href="#" data-toggle="dropdown" class="dropdown-toggle">TA and Grader Related<strong class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li>
								<a href="CreateJobPostingPage.php">Create Job Posting</a>
							</li>
							<li class="disabled">
								<a href="#">Job Posting List</a>
							</li>
							<li>
								<a href="ModifyAllocationPage.php">Modify Allocation</a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="WriteReviewPage.php">Write Review</a>
							</li>
						</ul>
					</li>
					<li class="enabled pull-right">
						<a href="LogoutPage.php">Logout</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h1>
						TAMAS <small>Instructor Home Page</small>
					</h1>
				</div>
				<p>
					<?php
					require_once 'controller\InvalidInputException.php';
					require_once 'persistence\PersistenceTAMAS.php';
					require_once 'model\Course.php';
					require_once 'model\Department.php';
					require_once 'model\Instructor.php';
					require_once 'model\Job.php';
					require_once 'model\Review.php';
					require_once 'model\Student.php';
					session_start();
					$persis = new PersistenceTamas();
					$dpt = $persis -> loadDataFromStore();
					?>
					
					Welcome <?php echo $_SESSION['user'] -> getName()?>!
				</p>
				<p>
					Please navigate via the heading thumbnails.
					The greyed-out ones are extra features that remained unimplemented.
				</p>
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>