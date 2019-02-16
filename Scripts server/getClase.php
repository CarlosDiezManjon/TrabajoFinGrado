<?php
require "conn.php";
$clase_ID = $_POST["clase_ID"];
$mysql_qry = "select  nombre_alumno, tlfno_alumno, horaIni, horaFin, numero_alumnos, zona_clase, estado from clases where clase_ID = '$clase_ID'";
$result = mysqli_query($conn, $mysql_qry);

$row = mysqli_fetch_assoc($result);

	$nombre_alumno = $row["nombre_alumno"];
	$tlfno_alumno = $row["tlfno_alumno"];
	$horaIni = $row["horaIni"];
	$horaFin = $row["horaFin"];
	$numero_alumnos = $row["numero_alumnos"];
	$zona_clase = $row["zona_clase"];
	$estado = $row["estado"];
	
	echo $nombre_alumno."-";
	echo $tlfno_alumno."-";
	echo $horaIni."-";
	echo $horaFin."-";
	echo $numero_alumnos."-";
	echo $zona_clase."-";
	echo $estado;
?>