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
							<li class="active">
								<a href="#">Create Job Posting</a>
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
						TAMAS <small>Create Job Posting</small>
					</h1>
				</div>
				<form action="CreateJobPosting.php" method="post">
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
					$_SESSION['allJobs'] = $dpt -> getAllJobs();
					
					$allJobs = $_SESSION['allJobs'];
					$_SESSION['jobNamesArray'] = array();
					foreach($allJobs as $job){
						$optionValue = $job -> getJobID();
					
						$jobCode = $job -> getCorrespondingCourse() -> getCode();
						$jobType = $job -> getPosType();
					
						$optionSeenName = $jobCode . " " . $jobType;
						
						$assocInstructor = $job -> getCorrespondingCourse() -> getInstructors();
						foreach($assocInstructor as $theInstructor){
							if(strcmp($theInstructor -> getEmail(), $_SESSION['user'] -> getEmail()) == 0){
							$_SESSION['jobNamesArray'][$optionValue] = $optionSeenName;
							}
						}
					}?>
				
					<p>Job offering: <select name = "aJobID" id = "aJobID">
					<option value='-1'>Choose an option...</option>
					<?php
					foreach($_SESSION['jobNamesArray'] as $key => $value) { ?>
						<option value="<?php echo $key ?>"><?php echo $value ?></option>
					<?php
						} ?>
				    </select>

				    <span class="error">
					<?php
					if (isset($_SESSION['errorJobID']) && !empty($_SESSION['errorJobID'])){
						echo " * " . $_SESSION["errorJobID"];
					}
					?>
					</span></p>
					
					<p>Job Description: <input type="text" style="height:100px; width:200px;" name="jobDescription" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorJobDesc']) && !empty($_SESSION['errorJobDesc'])){
						echo " * " . $_SESSION["errorJobDesc"];
					}
					?>
					</span></p>
					
					<p>Skills Required: <input type="text" style="height:100px; width:200px;" name="skillsRequired" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorSkillsReq']) && !empty($_SESSION['errorSkillsReq'])){
						echo " * " . $_SESSION["errorSkillsReq"];
					}
					?>
					</span></p>
					
					<p>Experience Required: <input type="text" style="height:100px; width:200px;" name="experienceRequired" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorExpReq']) && !empty($_SESSION['errorExpReq'])){
						echo " * " . $_SESSION["errorExpReq"];
					}
					?>
					</span></p>
					
					<p>Offer deadline: <input type="date" name="offerDeadlineDate" value="<?php echo date('Y-m-d'); ?>" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorOfferDateFormat']) && !empty($_SESSION['errorOfferDateFormat'])){
						echo " * " . $_SESSION["errorOfferDateFormat"];
					}
					if (isset($_SESSION['errorOfferDate']) && !empty($_SESSION['errorOfferDate'])){
						echo " * " . $_SESSION["errorOfferDate"];
					}
					?>
					</span></p>
					
					<p><span class="success">
					<?php 
					if (isset($_SESSION['jobPostingSuccess']) && !empty($_SESSION['jobPostingSuccess'])){
						echo " * " . $_SESSION['jobPostingSuccess'];
					}
					?>
					</span></p>
					
					<p><input type="submit" value="Create Job Posting"/></p>
				</form>
				
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>