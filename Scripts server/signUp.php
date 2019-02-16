<?php
require "conn.php";
$dni_profesor = $_POST["username"];
$password = $_POST["password"];
$mysql_qry1 = "select profesor_ID from profesores where dni = '$dni_profesor'";
$result1 = mysqli_query($conn, $mysql_qry1);

$row = mysqli_fetch_assoc($result1);
$profesor_ID = $row["profesor_ID"];
if(mysqli_num_rows($result1) > 0) {
	//Existe el profesor
	$mysql_qry2 = "select profesor_ID from usuarios where usuario = '$dni_profesor'";
	$result2 = mysqli_query($conn, $mysql_qry2);
	if(mysqli_num_rows($result2) > 0) {
		//Ya esta registrado
		echo "El usuario ya esta registrado";
	}
	else{
		$mysql_qry3 = "insert into usuarios(usuario,contrasena,profesor_ID) values ('$dni_profesor', aes_encrypt('$password','altocampoo'), '$profesor_ID')";
		if(mysqli_query($conn, $mysql_qry3)){
			//Registrado
			echo "Registro completado";
		}else{
			//Error al resgistrar
			echo "Error al resgistrar";
		}
	}
}
else {
	//No existe el profesor
	echo "No existe el profesor";
}
?>