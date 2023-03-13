<?php
echo "Starting population.php at " . date("Y-m-d H:i:s") . "\n";
$errormsg = "";
while ( true) {
$host= getenv('MYSQL_HOST');
$user =getenv('MYSQL_USER');
$password =getenv('MYSQL_PASSWORD');
$database =getenv('MYSQL_DATABASE');

$connection= mysqli_connect($host,$user,"$password",$database);

if (mysqli_connect_errno())
{
    $errormsg=  "Failed to connect to MySQL: " . mysqli_connect_error();
    break;
}

$reqdis ="SELECT id_dis FROM organisations WHERE 0=0 and depy_n = 1 and dep_princ = 0 and actif = 1"; // les dépôts principaux ont dep_princ = 1
//echo $reqdis;
$resdis = mysqli_query($connection,$reqdis);
while ($datadis= mysqli_fetch_object($resdis)) // while 1 = select chaque dépôt
{
    $requete ="SELECT * FROM population WHERE lien_dep =".$datadis->id_dis." and date_stat = '".$mydate."'";


//	echo $requete;
    $resultat= mysqli_query($connection,$requete);
    if (!$resultat)
    {
        # inform the user
        echo '<br>Il y a une erreur dans le programme compte_depot.php. Veuillez essayer plus tard'. mysqli_error();
    }
    $countactif=0; // familles/assoc
    $countmember=0; // personnes / assoc
    $countnourisson=0; // nour/assoc
    $countbebe=0; // bebe/assoc
    $countenf=0; // enfants /assoc
    $countad=0;//ados / association
    $countyad=0; // 18-24 ans
    $countsenior=0;
    $counteq = 0;
    while ($data=@mysqli_fetch_object($resultat)) // while 2 = compte population pour chaque organisation dépendante du dépot
    {
        $drapeau=1;
        $countactif = $countactif + $data->n_fam;
        $countmember = $countmember + $data->n_pers;
        $countnourisson=$countnourisson + $data->n_nour;
        $countbebe = $countbebe + $data->n_bebe;
        $countenf = $countenf + $data->n_enf;
        $countad = $countad + $data->n_ado;
        $countyad = $countyad + $data->n_18_24;
        $countsenior =$countsenior + $data->n_sen;
        $counteq = $counteq +$data->n_eq;
    } //end while 2
    $requete ="UPDATE organisations SET 
			n_fam='".$countactif."',
			n_pers='".$countmember."',
			n_nour='".$countnourisson."',
			n_bebe='".$countbebe."',
			n_enf='".$countenf."',
			n_ado='".$countad."',
			n_18_24='".$countyad."',
			n_sen='".$countsenior."',
			n_eq='".$counteq."' WHERE id_dis=".$datadis->id_dis."";
    echo $requete;
    $resultat=mysqli_query($connection,$requete);
    if (!$resultat)
        echo '<br>Il y a une erreur dans le programme compte_depot.php. Veuillez essayer plus tard'. mysqli_error() ;
    else
        echo '<br>Le comptage des d&eacute;p&ocirc;ts est terminé';
}
    break;
}
$message = "Update was successful";
if ($errormsg != "")
{
  $message = "Error: " . $errormsg;
}
$insertQuery_audit_daily = "INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) 
 VALUES ('avdmadmin',10,0,'populationDepot','" . substr($message,0,50) . "','Update')";
$sql = $connection->query($insertQuery_audit_daily);
if (!$sql)
{
    $errormsg=  "Failed to execute insert daily audit  query: " . $connection->error;
    echo "movements populationDepot.php failed to insert daily statistics in auditchanges table:" . $errormsg . "\n";
}


$connection->close();
echo "movements summarize.php ended at " . date("Y-m-d H:i:s") . "\n";
?>
?>
