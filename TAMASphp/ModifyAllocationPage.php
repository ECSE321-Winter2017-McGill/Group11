<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Create Job Posting</title>
	
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
							<li class="active">
								<a href="ModifyAllocation.php">Modify Allocation</a>
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
						TAMAS <small>Create Job Posting</small>
					</h1>
				</div>
				<form action="ModifyAllocation.php" method="post">
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
					$_SESSION['allocAllJobs'] = $dpt -> getAllJobs();
					
					$allJobs = $_SESSION['allocAllJobs'];
					$_SESSION['allocJobNamesArray'] = array();
					foreach($allJobs as $job){
						$assocInstructor = $job -> getCorrespondingCourse() -> getInstructors();
						if($job -> getState() == "Allocated"){
							foreach($assocInstructor as $theInstructor){
								if(strcmp($theInstructor -> getEmail(), $_SESSION['user'] -> getEmail()) == 0){
									$_SESSION['allocJobNamesArray'][$job -> getJobID()] = $job -> getCorrespondingCourse() -> getCode() . " " . $job -> getPosType();
									foreach($job -> getAllocatedStudent() as $allocStud){
										$allocAllocStudents[] = $allocStud;
									}
									foreach($job -> getApplicant() as $appliStud){
										$allocAppliStudents[] = $appliStud;
									}
								}
							}
						}
					}?>
				
					<p>Job offering: <select name = "aJobID" id = "aJobID">
					<option value='-1'>Choose an option...</option>
					<?php
					foreach($_SESSION['allocJobNamesArray'] as $key => $value) { ?>
						<option value="<?php echo $key ?>"><?php echo $value ?></option>
					<?php
						} ?>
				    </select>

				    <span class="error">
					<?php
					if (isset($_SESSION['errorAllocID']) && !empty($_SESSION['errorAllocID'])){
						echo " * " . $_SESSION["errorAllocID"];
					}
					?>
					</span>
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorAllocJobState']) && !empty($_SESSION['errorAllocJobState'])){
						echo " * " . $_SESSION["errorAllocJobState"];
					}
					?>
					</span></p>
					
					<p>Allocated student: <select name = "allocatedStudentID" id = "allocatedStudentID">
					<option value='-1'>Choose an option...</option>
					<?php
					foreach($allocAllocStudents as $allocStudent) { ?>
						<option value="<?php echo $allocStudent -> getStudentID() ?>"><?php echo $allocStudent -> getName() ?></option>
					<?php
						} ?>
				    </select>

				    <span class="error">
					<?php
					if (isset($_SESSION['errorAllocStudent']) && !empty($_SESSION['errorAllocStudent'])){
						echo " * " . $_SESSION["errorAllocStudent"];
					}
					?>
					</span></p>
					
					<p>Student to be allocated: <select name = "appliedStudentID" id = "appliedStudentID">
					<option value='-1'>Choose an option...</option>
					<?php
					foreach($allocAppliStudents as $applicants) { ?>
						<option value="<?php echo $applicants -> getStudentID() ?>"><?php echo $applicants -> getName() ?></option>
					<?php
						} ?>
				    </select>

				    <span class="error">
					<?php
					if (isset($_SESSION['errorAllocAppliedStudent']) && !empty($_SESSION['errorAllocAppliedStudent'])){
						echo " * " . $_SESSION["errorAllocAppliedStudent"];
					}
					?>
					</span></p>
					
					<p><span class="success">
					<?php 
					if (isset($_SESSION['modAllocationSuccess']) && !empty($_SESSION['modAllocationSuccess'])){
						echo " * " . $_SESSION['modAllocationSuccess'];
					}
					?>
					</span></p>
					
					<p><input type="submit" value="Modify Allocation"/></p>
				</form>
				
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>