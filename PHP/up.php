<?php

 chmod(“/Server/a”, 0777);
$ruta = “fotos/” . basename( $_FILES['fotoUp']['name']);
if(move_uploaded_file($_FILES['fotoUp']['tmp_name'], $ruta)){
chmod (basename( $_FILES['fotoUp']['name']), 0777);
echo "si";
}
else{
echo "no se ha subido";
}
?>
