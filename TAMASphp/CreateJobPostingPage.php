<?php
require_once 'persistence/PersistenceTAMAS.php';
session_start();
$persis= new PersistenceTamas();
$dpt = $persis -> loadDataFromStore();?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Create Job Posting</title>
	
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
							<li class="disabled">
								<a href="#">Modify Allocation</a>
							</li>
							<li class="divider">
							</li>
							<li class="disabled">
								<a href="#">Write Review</a>
							</li>
						</ul>
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
					<p>Job ID? <select name = "aJobID" > 
					<?php
					foreach($dpt->getAllJobs() as $job){ ?>
						<option value="<?php $job->getJobID();?>">
						<?php $job->getJobID();?>
						</option><?php } ?>
						<option name="aJobID"> </option>
				    </select>
				    
				    <span class="error">
					<?php
					if (isset($_SESSION['errorJobID']) && !empty($_SESSION['errorJobID'])){
						echo " * " . $_SESSION["errorJobID"];
					}
					?>
					</span></p>
					
					<p>Job Description? <input type="text" name="jobDescription" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorJobDesc']) && !empty($_SESSION['errorJobDesc'])){
						echo " * " . $_SESSION["errorJobDesc"];
					}
					?>
					</span></p>
					
					<p>Skills Required? <input type="text" name="skillsRequired" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorSkillsReq']) && !empty($_SESSION['errorSkillsReq'])){
						echo " * " . $_SESSION["errorSkillsReq"];
					}
					?>
					</span></p>
					
					<p>Experience Required? <input type="text" name="experienceRequired" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorExpReq']) && !empty($_SESSION['errorExpReq'])){
						echo " * " . $_SESSION["errorExpReq"];
					}
					?>
					</span></p>
					
					<p>Offer Date? <input type="date" name="offerDeadlineDate" value="<?php echo date('Y-m-d'); ?>" />
					
					<span class="error">
					<?php
					if (isset($_SESSION['errorOfferDate']) && !empty($_SESSION['errorOfferDate'])){
						echo " * " . $_SESSION["errorOfferDate"];
					}
					?>
					</span></p>
					
					<p><span class="sucess">
					<?php 
					if(empty($_SESSION['errorOfferDate']) && !empty($_SESSION['errorExpReq']) &&
							empty($_SESSION['errorSkillsReq']) && empty($_SESSION['errorJobDesc']) &&
							empty($_SESSION['errorJobID'])){
						echo "Job published successfully.";
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