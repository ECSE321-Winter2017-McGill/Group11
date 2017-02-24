<?php

#Position type text field
#Skills required text field
#Experiecen required text field
#Job description text field
#Publish job posting button

#Department page button

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<ul class="nav nav-tabs">
				<li>
					<a href="#">Home</a>
				</li>
				<li>
					<a href="#">Profile</a>
				</li>
				<li>
					<a href="#">Course List</a>
				</li>
				<li class="disabled">
					<a href="#">Messages</a>
				</li>
				<li class="dropdown pull-right">
					 <a href="#" data-toggle="dropdown" class="dropdown-toggle">TA & Grader Related<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li class="active">
							<a href="#">Create Job Posting</a>
						</li>
						<li>
							<a href="#">Job Posting List</a>
						</li>
						<li>
							<a href="#">Modify Allocation</a>
						</li>
						<li class="divider">
						</li>
						<li>
							<a href="#">Write Review</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<form role="form">
				<div class="form-group">
					 
					<label for="exampleInputEmail1">
						Position type
					</label>
					<input type="email" class="form-control" id="exampleInputEmail1" />
				</div>
				<div class="form-group">
					 
					<label for="exampleInputPassword1">
						Skills required
					</label>
					<input type="password" class="form-control" id="exampleInputPassword1" />
				</div>
				<div class="form-group">
					 
					<label for="exampleInputPassword1">
						Experience Required
					</label>
					<input type="password" class="form-control" id="exampleInputPassword1" />
				</div>
				<button type="submit" class="btn btn-default">
					Publish job posting
				</button>
			</form>
		</div>
	</div>
</div>

php>
