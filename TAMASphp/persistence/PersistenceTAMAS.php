<?php
require_once __DIR__.'\..\controller\InvalidInputException.php';
require_once __DIR__.'\..\persistence\PersistenceTAMAS.php';
require_once __DIR__.'\..\model\Course.php';
require_once __DIR__.'\..\model\Department.php';
require_once __DIR__.'\..\model\Instructor.php';
require_once __DIR__.'\..\model\Job.php';
require_once __DIR__.'\..\model\Review.php';
require_once __DIR__.'\..\model\Student.php';

class PersistenceTamas {
	private $filename;
	function __construct($filename = 'TAMASdata.txt') {
		$this->filename = $filename;
	}
	function loadDataFromStore() {
		if (file_exists($this->filename)) {
			//if a file already exists
			$str = file_get_contents($this->filename);
			$dpt = unserialize($str);
		} else {
			$dpt = new Department();
		}
		return $dpt;
	}
	function writeDataToStore($dpt) {
		$str = serialize($dpt);
		file_put_contents($this->filename, $str);
	}
}
?>