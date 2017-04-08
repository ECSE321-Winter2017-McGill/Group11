<?php
abstract class JobStatus{
	const Ready = 'Ready';
	const Posted = 'Posted';
	const AppliedTo = 'AppliedTo';
	const Allocated = 'Allocated';
	const AllocationFinalized = 'AllocationFinalized';
	const Offered = 'Offered';
	const JobFull = 'JobFull';
}
?>