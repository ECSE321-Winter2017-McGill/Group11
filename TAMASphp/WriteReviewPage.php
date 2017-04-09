<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Write Review</title>
	
	<style>
			.error {color: #FF0000;}
			.success {color: #36DE1C;}
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
				<ul class="nav nav-tabs">
					<li>
						<a href="InstructorHomePage.php">Home</a>
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
							<li class="disabled">
								<a href="#">Modify Allocation</a>
							</li>
							<li class="divider">
							</li>
							<li class="active">
								<a href="#">Write Review</a>
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
						TAMAS <small>Write Review</small>
					</h1>
				</div>
				<form action="WriteReview.php" method="post">
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
				
					<p>Author: <select name = "author" id = "author">
					<option value='-1'>Choose an option...</option>
						<option value="<?php echo $_SESSION['user'] -> getInstructorID() ?>"><?php echo $_SESSION['user'] -> getName() ?></option>
				    </select>

				    <span class="error">
					<?php
					if (isset($_SESSION['errorAuthor']) && !empty($_SESSION['errorAuthor'])){
						echo " * " . $_SESSION["errorAuthor"];
					}
					?>
					</span></p>
					
					<?php 
					$courses = $_SESSION['user'] -> getCourses();
					?>
					
					<p>Job reviewed: <select name = "aRevJobID" id = "aRevJobID">
					<option value='-1'>Choose an option...</option>
					<?php
					foreach ($courses as $course){
						$jobs = $course -> getJobs();
						foreach($jobs as $job) { ?>
						<option value="<?php echo $job -> getJobID() ?>">
						<?php echo $job -> getCorrespondingCourse() -> getCode() . " " . $job -> getPosType() ?></option>
					<?php
						}
					}?>
				    </select>

				    <span class="error">
					<?php
					if (isset($_SESSION['errorRevJobID']) && !empty($_SESSION['errorRevJobID'])){
						echo " * " . $_SESSION["errorRevJobID"];
					}
					if (isset($_SESSION['errorJobState']) && !empty($_SESSION['errorJobState'])){
						echo " * " . $_SESSION["errorJobState"];
					}
					?>
					</span></p>
					
					<?php 
					$employees = array();
					foreach ($courses as $course){
						$jobs = $course -> getJobs();
						foreach($jobs as $job) {
							$someone = $job -> getEmployee();
							if($someone != NULL){
								$employees[] = $someone;
							}
						}
					}
					?>
					
					<p>Employee reviewed: <select name = "anEmployeeID" id = "anEmployeeID">
					<option value='-1'>Choose an option...</option>
					<?php
					foreach($employees as $employee) { ?>
						<option value="<?php echo $employee -> getStudentID() ?>"><?php echo $employee -> getName() ?></option>
					<?php
						}?>
				    </select>

				    <span class="error">
					<?php
					if (isset($_SESSION['errorEmployee']) && !empty($_SESSION['errorEmployee'])){
						echo " * " . $_SESSION["errorEmployee"];
					}
					?>
					</span></p>
					
					<p>Review: <input type="text" style="height:100px; width:200px;" name="revDescription" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorRevDesc']) && !empty($_SESSION['errorRevDesc'])){
						echo " * " . $_SESSION["errorRevDesc"];
					}
					?>
					</span></p>
					
					<p><span class="success">
					<?php 
					if (isset($_SESSION['revPostingSuccess']) && !empty($_SESSION['revPostingSuccess'])){
						echo " * " . $_SESSION["revPostingSuccess"];
					}
					?>
					</span></p>
					<p><input type="submit" value="Create Review"/></p>
				</form>
				
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>