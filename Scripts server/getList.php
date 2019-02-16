<?php
require "conn.php";
$fecha = $_POST["fechaAct"];
$profesor_ID =  $_POST["profesor_ID"];
$mysql_qry = "select clase_ID, horaIni, horaFin from clases where fecha like '$fecha' and profesor_ID like '$profesor_ID'";
$result = mysqli_query($conn, $mysql_qry);

if(mysqli_num_rows($result) > 0) {
while($row = mysqli_fetch_assoc($result))
{
	$clase_ID = $row["clase_ID"];
	$horaIni = $row["horaIni"];
	$horaFin = $row["horaFin"];
	echo $clase_ID."       ";
	echo " De: ";
	echo $horaIni.":00";
	echo " a: ";
	echo $horaFin.":00.";

}
}else{
	echo "0";
}



mysqli_close($conn);

?>