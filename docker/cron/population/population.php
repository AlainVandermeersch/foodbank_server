<?php
// ceci est un script qui est lancé par cron toutes les nuits et reprend la logique de l'ancien script php compte.php
// cette fonction calcule l'age en jours et tient compte des années bisextiles.
function AgeJours($jour, $mois, $annee) {
    $now = time();
    $your_date = strtotime($jour.'-'.$mois.'-'.$annee);
    $datediff = $now - $your_date;

    return floor($datediff / (60 * 60 * 24));
}

// Ce code ventile chaque jour les bénéficiaires /organisation / tranche d'âge et met à jour les populations des organisations;
// La seconde partie du code évalue et met à jour les suspensions d'organisation.

echo "Starting population.php at " . date("Y-m-d H:i:s") . "\n";
$errormsg = "";
$nbUpdatesCat1 = 0;
$nbUpdatesCat2 = 0;
$nbUpdatesCat3 = 0;
$nbUpdatesCat4 = 0;
$nbUpdatesSusp = 0;
while ( true) {
    $host= getenv('MYSQL_HOST');
    $user =getenv('MYSQL_USER');
    $password =getenv('MYSQL_PASSWORD');
    $database =getenv('MYSQL_DATABASE');


    $connection= mysqli_connect($host,$user,$password,$database);

    if (mysqli_connect_errno())
    {
    $errormsg=  "Failed to connect to MySQL: " . mysqli_connect_error();
    break;
    }



    $now = time();
    $mydate=date("Y-m-d",$now);

    $sql_date_last_run =  "SELECT MAX(date_stat) as lastDate FROM `population` ";
    $res_sql_date_last_run = mysqli_query($connection, $sql_date_last_run);
    if (!$res_sql_date_last_run)
    {
        $errormsg=  "Error: " . $sql_date_last_run . "<br>" . mysqli_error($connection);
        break;
    }
    if($row = mysqli_fetch_array($res_sql_date_last_run))
    {
        if ($row['lastDate'] != null)
        {
            $lastDate = $row['lastDate'];
            if ($lastDate == $mydate)
            {
                $errormsg=  "Error: Already run today\n";
                break;
            }
        }

    }

    $prog='';
    $fixinf="update dep set eq = 1 where eq = 0";
    $resfixinf = mysqli_query($connection,$fixinf);
    $reqdis ="SELECT id_dis, nbrefix,lien_depot,lien_banque,msonac,gest_ben,n_pers,n_eq,n_fam,n_nour,n_bebe,n_enf,n_ado,n_18_24,n_sen FROM organisations WHERE actif = 1 and depy_n = 0  ORDER BY societe";
    $resdis= mysqli_query($connection,$reqdis);
    while ($datadis= mysqli_fetch_object($resdis)) // while 1
    {
    set_time_limit(120);

    $m_ac=$datadis->msonac;
    $gm=$datadis->gest_ben;// = 1 si la gestion des bénéficiaires est faites sur le site
    $lb=$datadis->lien_banque;
    $ld=$datadis->lien_depot;
    $nfix=$datadis->nbrefix; // nombre fixe pour les maisons d'accueil - nbre de lits agréés
    $pers=$datadis->n_pers;
    $eq=$datadis->n_eq;
    $actif=$datadis->n_fam;
    $nourisson=$datadis->n_nour;
    $bebe=$datadis->n_bebe; // bebe/assoc
    $enf=$datadis->n_enf; // enfants /assoc
    $ad=$datadis->n_ado;//ados / association
    $youngad=$datadis->n_18_24; // jeunes adultes 18-24 ans
    $senior=$datadis->n_sen; //+ 65 ans
    if($m_ac == 0 && $gm == 0) //pas maison d'accueil et pas de gestion des bénéficiaires sur le site
        $prog = 1;
    if($m_ac == 0 && $gm == 1) //pas maison d'accueil et gestion des bénéficiaires sur le site
        $prog = 2;
    if($m_ac == 1 && $gm == 1) // maison d'accueil et  gestion des bénéficiaires supplémentaires sur le site
        $prog = 3;
    if($m_ac == 1 && $gm == 0) //maison d'accueil et pas de gestion des bénéficiaires sur le site
        $prog = 4;
    switch ($prog)
    {
        case 1: //pas maison d'accueil et pas de gestion des bénéficiaires sur le site
            $countactif=$actif;
            $countmember=$pers; // personnes
            $counteq=$pers; // equivalents
            $countnourisson=$nourisson;
            $countbebe=$bebe; // bebe/assoc
            $countenf=$enf; // enfants /assoc
            $countad=$ad;//ados / association
            $count18_24=$youngad;//jeunes adultes / assoc
            $countsenior=$senior; //+ 65 ans
            if($countnourisson == '') {
                $countnourisson = 0;
            }
            if($countmember == '') {
                $countmember = 0;
            }
            if($countactif == '') {
                $countactif = 0;
            }
            if($countbebe == '') {
                $countbebe = 0;
            }
            if($countsenior == '') {
                $countsenior = 0;
            }
            if($countad == '') {
                $countad = 0;
            }
            if($count18_24 == '') {
                $count18_24 = 0;
            }
            if($countenf == '') {
                $countenf = 0;
            }

            $requete ="UPDATE organisations SET 
            n_fam='".$countactif."',
            n_pers='".$countmember."',
            n_nour='".$countnourisson."',
            n_bebe='".$countbebe."',
            n_enf='".$countenf."',
            n_ado='".$countad."',
            n_18_24='".$count18_24."',
            n_sen='".$countsenior."',
            n_eq='".number_format($pers, 2, '.', '')."' WHERE id_dis=".$datadis->id_dis."";

            $resultat=mysqli_query($connection,$requete);
            if (!$resultat)
            {
               $errormsg = "error updating organisation(case 1 - see population.php) $datadis->id_dis with $requete . mysqli_error($connection)";
               break;
            }
            else
            {
                $requete ="INSERT INTO population (date_stat,id_dis,n_fam,n_pers,n_nour,n_bebe,n_enf,n_ado,n_18_24,n_eq,lien_dep,n_sen,lien_banque) VALUES('".$mydate."','".$datadis->id_dis."','".$countactif."','".$countmember."','".$countnourisson."','".$countbebe."','".$countenf."','".$countad."','".$count18_24."','".number_format($pers, 2, ',', ' ')."','".$ld."','".$countsenior."','".$lb."')";

                $resultat=mysqli_query($connection,$requete);
                if (!$resultat)
                {
                    $errormsg = "error inserting population record for organisation(case 1 - see population.php) $datadis->id_dis with $requete . mysqli_error($connection)";
                    break;
                }
                else
                {
                    $nbUpdatesCat1++;
                }
            }

            break;

        case 2: //pas maison d'accueil et gestion des bénéficiaires sur le site
            $countactif=0; // familles/assoc
            $countnourisson=0; // nour/assoc
            $countbebe=0; // bebe/assoc
            $countenf=0; // enfants /assoc
            $countad=0;//ados / association
            $count18_24=0;//jeunes adultes de 18 à 24 ans
            $countsenior=0; //+ 65 ans
            $countmember = 0;
            $counteq=0;
            $requete ="SELECT daten,daten_conj,id_client,coeff,nomconj FROM clients WHERE lien_dis=".$datadis->id_dis." and actif=1";
            $resultat= mysqli_query($connection,$requete);

            while ($data= mysqli_fetch_object($resultat)) // while 2
            {
                $lm = $data->id_client;
                $coef = $data->coeff;
                if($coef < 1) {
                    $coef = 1;
                }
                $countactif = $countactif +1;
                $countmember = $countmember +1;
                $counteq = $counteq +(1/$coef);

                if (!empty($data->nomconj) && !empty($data->daten_conj)) // calcul de l'âge du conjoint
                {
                    $countmember = $countmember +1;
                    $counteq = $counteq +(1/$coef);
                    $datnaissance = explode("/",$data->daten_conj);
                    $age = 9000; // default adult age
                    if (count($datnaissance) == 3) {
                        $age = AgeJours($datnaissance[0],$datnaissance[1],$datnaissance[2]);
                    }
                    if ($age>23741) {
                        $countsenior=$countsenior+1;
                    }
                    if ($age>6570 and $age<8760) {
                        $count18_24=$count18_24+1;
                    }

                }


                // calcul de l'âge du chef de famille
                $datnaissance = explode("/",$data->daten);
                $age = 9000; // default adult age
                if (count($datnaissance) == 3) {
                    $age = AgeJours($datnaissance[0],$datnaissance[1],$datnaissance[2]);
                }
                if ($age>23741) {
                    $countsenior=$countsenior+1;
                }
                if ($age>6570 and $age<8760) {
                    $count18_24=$count18_24+1;
                }
                $query = "SELECT * FROM dep WHERE lien_mast= $lm and deleted = 0"; // calcul de l'âge de chaque dépendant
                $result =  mysqli_query($connection,$query);
                while ($data2= mysqli_fetch_object($result)) // while 3
                {
                    $coef=$data2->eq;
                    if($coef < 1) {
                        $coef = 1;
                    }
                    $datnaissance = explode("/",$data2->datenais);
                    $age = 9000; // default adult age
                    if (count($datnaissance) == 3) {
                        $age = AgeJours($datnaissance[0],$datnaissance[1],$datnaissance[2]);
                    }
                    if ($age <=182)
                    {
                        $countnourisson = $countnourisson + 1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }
                    if ( $age >182 && $age<730)
                    {
                        $countbebe = $countbebe +1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }
                    if ($age >=730 && $age<4745)
                    {
                        $countenf = $countenf + 1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }

                    if ($age >=4745 && $age<6570)
                    {
                        $countad = $countad + 1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }
                    if ($age>=6570 and $age<8760)
                    {
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                        $count18_24=$count18_24+1;
                    }
                    if ($age>=8760 && $age<23741)
                    {
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                        $countad =$countad + 1;
                    }

                    if ($age>=23741)
                    {
                        $countsenior = $countsenior + 1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }
                }//end while 3
            }// end while 2


            $requete ="UPDATE organisations SET 
            n_fam='".$countactif."',
            n_pers='".$countmember."',
            n_nour='".$countnourisson."',
            n_bebe='".$countbebe."',
            n_enf='".$countenf."',
            n_ado='".$countad."',
            n_18_24='".$count18_24."',
            n_sen='".$countsenior."',
            n_eq='".number_format($counteq, 2, '.', '')."' WHERE id_dis=".$datadis->id_dis."";

          

            $resultat=mysqli_query($connection,$requete);
            if (!$resultat)
            {
                $errormsg = "error updating organisation(case 2 - see population.php) $datadis->id_dis with $requete . mysqli_error($connection)";
                break;
            }
            else
            {
                $requete ="INSERT INTO population (date_stat,id_dis,n_fam,n_pers,n_nour,n_bebe,n_enf,n_ado,n_18_24,n_eq,lien_dep,n_sen,lien_banque) VALUES('".$mydate."','".$datadis->id_dis."','".$countactif."','".$countmember."','".$countnourisson."','".$countbebe."','".$countenf."','".$countad."','".$count18_24."','".number_format($counteq, 2, ',', ' ')."','".$ld."','".$countsenior."','".$lb."')";

                $resultat=mysqli_query($connection,$requete);
                if (!$resultat)
                {
                    $errormsg = "error inserting population record for organisation(case 2 - see population.php) $datadis->id_dis with $requete . mysqli_error($connection)";
                    break;
                }
                else
                {
                  $nbUpdatesCat2++;
                }
            }

            break;
        case 3: // maison d'accueil et  gestion des bénéficiaires supplémentaires sur le site
            $countactif=$nfix;
            $countnourisson=0;
            $countbebe=0; // bebe/assoc
            $countenf=0; // enfants /assoc
            $countad=0;//ados / association
            $count18_24=0;//jeunes adulres 18-24
            $countsenior=0; //+ 65 ans
            $countmember=$nfix; // ajout du nombre fixe
            $counteq=$nfix; // ajout du nombre fixe
            $requete ="SELECT * FROM clients WHERE lien_dis=".$datadis->id_dis." and actif=1";
            $resultat= mysqli_query($connection,$requete);

            while ($data= mysqli_fetch_object($resultat)) // while 4
            {
                $lm = $data->id_client;
                $coef = $data->coeff;
                if($coef < 1) {
                    $coef = 1;
                }
                $countactif = $countactif +1;
                $countmember = $countmember +1;
                $counteq = $counteq +(1/$coef);

                if (!empty($data->nomconj)) // calcul de l'âge du conjoint
                {
                    $countmember = $countmember +1;
                    $counteq = $counteq +(1/$coef);
                    $datnaissance = explode("/",$data->daten_conj);
                    $age = 9000; // default adult age
                    if (count($datnaissance) == 3) {
                        $age = AgeJours($datnaissance[0],$datnaissance[1],$datnaissance[2]);
                    }
                }
                if ($age>6570 and $age<8760) {
                    $count18_24=$count18_24+1;
                }

                if ($age>23741) {
                    $countsenior=$countsenior+1;
                }

                // calcul de l'âge du chef de famille
                $datnaissance = explode("/",$data->daten);
                $age = AgeJours($datnaissance[0],$datnaissance[1],$datnaissance[2]);
                if ($age>23741) {
                    $countsenior=$countsenior+1;
                }

                $query = "SELECT * FROM dep WHERE lien_mast= $lm and actif = 1  and deleted = 0"; // calcul de l'âge de chaque dépendant
                $result =  mysqli_query($connection,$query);
                while ($data2= mysqli_fetch_object($result)) // while 5
                {
                    $coef=$data2->eq;
                    if($coef < 1) {
                        $coef = 1;
                    }
                    $datnaissance = explode("/",$data2->datenais);
                    $age = 9000; // default adult age
                    if (count($datnaissance) == 3) {
                        $age = AgeJours($datnaissance[0],$datnaissance[1],$datnaissance[2]);
                    }

                    if ($age <=182)
                    {
                        $countnourisson = $countnourisson + 1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }
                    if ( $age >182 && $age<730)
                    {
                        $countbebe = $countbebe +1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }
                    if ($age >=730 && $age<4745)
                    {
                        $countenf = $countenf + 1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }

                    if ($age >=4745 && $age<6570)
                    {
                        $countad = $countad + 1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }
                    if ($age>=6570 and $age<8760)
                    {
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                        $count18_24=$count18_24+1;
                    }
                    if ($age>=8760 && $age<23741)
                    {
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                        $countad =$countad + 1;
                    }

                    if ($age>=23741)
                    {
                        $countsenior = $countsenior + 1;
                        $countmember = $countmember + 1;
                        $counteq = $counteq +(1/$coef);
                    }
                }// end while 5
            }// end while 4



            $requete ="UPDATE organisations SET 
            n_fam='".$countactif."',
            n_pers='".$countmember."',
            n_nour='".$countnourisson."',
            n_bebe='".$countbebe."',
            n_enf='".$countenf."',
            n_ado='".$countad."',
            n_18_24='".$count18_24."',
            n_sen='".$countsenior."',
            n_eq='".number_format($counteq, 2, '.', '')."' WHERE id_dis=".$datadis->id_dis."";

            $resultat=mysqli_query($connection,$requete);
            if (!$resultat)
            {
                $errormsg = "error updating organisation(case 3 - see population.php) $datadis->id_dis with $requete . mysqli_error($connection)";
                break;
            }
            else
            {
                $requete ="INSERT INTO population (date_stat,id_dis,n_fam,n_pers,n_nour,n_bebe,n_enf,n_ado,n_18_24,n_eq,lien_dep,n_sen,lien_banque) VALUES('".$mydate."','".$datadis->id_dis."','".$countactif."','".$countmember."','".$countnourisson."','".$countbebe."','".$countenf."','".$countad."','".$count18_24."','".number_format($counteq, 2, ',', ' ')."','".$ld."','".$countsenior."','".$lb."')";

                $resultat=mysqli_query($connection,$requete);
                if (!$resultat)
                {
                    $errormsg = "error inserting population record for organisation(case 3 - see population.php) $datadis->id_dis with $requete . mysqli_error($connection)";
                    break;
                }
                else
                {
                    $nbUpdatesCat3++;
                }
            }

            break;
        case 4://maison d'accueil et pas de gestion des bénéficiaires sur le site
            $countactif=$nfix;
            $countmember=$nfix; // nbrefix = nombre de personnes agréées pour maison d'accueil (pas d'enregistrement individuel)
            $counteq=$nfix;
            $countnourisson=$nourisson;
            $countbebe=$bebe; // bebe/assoc
            $countenf=$enf; // enfants /assoc
            $countad=$ad;//ados / association
            $count18_24=$youngad; // jeunes adultes /assoc
            $countsenior=$senior; //+ 65 ans
            if($countnourisson == '') {
                $countnourisson = 0;
            }
            if($countmember == '') {
                $countmember = 0;
            }
            if($countactif == '') {
                $countactif = 0;
            }
            if($countbebe == '') {
                $countbebe = 0;
            }
            if($countsenior == '') {
                $countsenior = 0;
            }
            if($countad == '') {
                $countad = 0;
            }
            if($countenf == '') {
                $countenf = 0;
            }
            if($count18_24 == '') {
                $count18_24 = 0;
            }

            $requete ="UPDATE organisations SET 
            n_fam='".$countactif."',
            n_pers='".$countmember."',
            n_nour='".$countnourisson."',
            n_bebe='".$countbebe."',
            n_enf='".$countenf."',
            n_ado='".$countad."',
            n_18_24='".$count18_24."',
            n_sen='".$countsenior."',
            n_eq='".number_format($counteq, 2, '.', '')."' WHERE id_dis=".$datadis->id_dis."";
            $resultat=mysqli_query($connection,$requete);
            if (!$resultat)
            {
                $errormsg = "error updating organisation(case 4 - see population.php) $datadis->id_dis with $requete . mysqli_error($connection)";
                break;
            }
            else
            {
                $requete ="INSERT INTO population (date_stat,id_dis,n_fam,n_pers,n_nour,n_bebe,n_enf,n_ado,n_18_24,n_eq,lien_dep,n_sen,lien_banque) VALUES('".$mydate."','".$datadis->id_dis."','".$countactif."','".$countmember."','".$countnourisson."','".$countbebe."','".$countenf."','".$countad."','".$count18_24."','".number_format($counteq, 2, ',', ' ')."','".$ld."','".$countsenior."','".$lb."')";

                $resultat=mysqli_query($connection,$requete);
                if (!$resultat)
                {
                    $errormsg = "error inserting population record for organisation(case 4 - see population.php) $datadis->id_dis with $requete . mysqli_error($connection)";
                    break;
                }
                else
                {
                   $nbUpdatesCat4++;
                }
            }

            break;
        } // end switch
    } //end while 1

    ////////////////////////////////////////////////////////////////////
    // - le code suivant évalue la date de fin de suspension d'une organisation et annule la suspension 1 jour avant la date de fin.
    $req="select stop_susp,id_dis from organisations where stop_susp <>' '";
    $res=mysqli_query($connection,$req);
    while ($data=mysqli_fetch_object($res))
    {
        $today=0;
        $dn=0;
        $days_susp=0;
        $id=$data->id_dis;
        $today = mktime(0,0,0,date("m" ),date("d" ),date("Y" ));
        $stopsusp = explode("/",$data->stop_susp);
        $dn=mktime(0,0,0,$stopsusp[1].("m"),$stopsusp[0].("d") ,$stopsusp[2].("Y" ));
     
        $days_susp=($dn-$today)/100000;
     
        $daysus=round($days_susp);
        
        if ($daysus < 2) {
            $req2=mysqli_query($connection,"UPDATE organisations SET stop_susp = ' ', susp = 0 WHERE id_dis =".$id);
            if (!$req2)
            {
                $errormsg = "error stopping suspension(see population.php) for organisation $id with $req2 . mysqli_error($connection)";
                break;
            }
            else
            {
                $nbUpdatesSusp++;
                echo 'population.php: suspension stopped for organisation '.$id. "\n";;
            }

        }
    } // end while

    ///// le code suivant enlève les espaces blancs dans le champ n_eq
    $result = mysqli_query($connection,"UPDATE `population` SET `n_eq` = REPLACE(`n_eq`, ' ', '')");
    break;
} //end while true
$nbUpdates= $nbUpdatesCat1 + $nbUpdatesCat2 + $nbUpdatesCat3 + $nbUpdatesCat4 + $nbUpdatesSusp;
echo 'population.php: '.$nbUpdatesCat1.' organisations updated for case 1' . "\n";

echo 'population.php: '.$nbUpdatesCat2.' organisations updated for case 2' . "\n";

echo 'population.php: '.$nbUpdatesCat3.' organisations updated for case 3' . "\n";

echo 'population.php: '.$nbUpdatesCat4.' organisations updated for case 4' . "\n";

echo 'population.php: '.$nbUpdatesSusp.' organisations updated for suspension' . "\n";

$message = "Updated $nbUpdates orgs and stopped $nbUpdatesSusp suspensions";
if ($errormsg != "")
{
    $message = "Error: " . $errormsg;
    echo $message;
}
$insertQuery_audit_daily = "INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) 
 VALUES ('avdmadmin',10,0,'population','" . substr($message,0,50) . "','Update')";
$sql = $connection->query($insertQuery_audit_daily);
if (!$sql)
{
    $errormsg=  "Failed to execute insert daily audit  query: " . $connection->error;
    echo "population.php failed to insert daily statistics in auditchanges table:" . $errormsg . "\n";
}


$connection->close();
echo "population.php ended at " . date("Y-m-d H:i:s") . "\n";
?>
