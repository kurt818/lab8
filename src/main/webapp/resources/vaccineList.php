<?php
header('Content-Type: text/xml');
echo '<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>';

echo '<response>';
$vaccine = $_GET['vaccine'];
$vaccineArray = array('Adenovirus', 'Anthrax', 'Hepatitis', 'Measles', 'Rubella', 'Varicella', 'TB');
if(in_array($vaccine, $vaccineArray))
	echo 'The'.$vaccine.'is valid name';
elseif($vaccine=='')
	echo 'Please enter a vaccine name';
else
	echo 'The vaccine'.$vaccine.'is not a valid name';
echo '</response>';
?>