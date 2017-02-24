<?php

class PersistenceTamas {
	private $filename;
	function __construct($filename = 'data.txt') {
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