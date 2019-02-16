<?php
require "conn.php";
$dni_profesor = $_POST["username"];
$password = $_POST["password"];
$mysql_qry = "select profesor_ID from usuarios where usuario = '$dni_profesor' and aes_decrypt(contrasena,'altocampoo') = '$password'";
$result = mysqli_query($conn, $mysql_qry);

$row = mysqli_fetch_assoc($result);
$profesor_ID = $row["profesor_ID"];
if(mysqli_num_rows($result) > 0) {
	echo "1"."-";
	echo $profesor_ID;
}
else {
	echo "0";
}
?>