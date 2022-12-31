<?php
$errormsg = "";
while ( $errormsg == "") {
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
    $arrayMovements = array();
    $lastMonth = 202002;
    $countDeleted = 0;
    $countInsertedFEADNONAGREED = 0;
    $countInsertedNOFEADNONAGREED = 0;
    $countInsertedFEADAGREED = 0;
    $sql_00 = "SELECT MAX(month) as lastMonth FROM `movements_monthly` ";
    $res_sql_00 = mysqli_query($connection, $sql_00);
    if (!$res_sql_00)
    {
        $errormsg=  "Error: " . $sql_00 . "<br>" . mysqli_error($connection);
        break;
    }
    if($row = mysqli_fetch_array($res_sql_00))
    {
        if ($row['lastMonth'] != null)
        {
            $lastMonth = $row['lastMonth'];
        }

    }
    $strLastMonth = (string)$lastMonth;
    if(substr($strLastMonth,4) == "01") {
       $lastMonth = ((int)(substr($strLastMonth,0,4))-1) * 100 + 12;
    }
    else {
       $lastMonth--;
    }
    $sql_00 = "DELETE FROM `movements_monthly` where month >= '" . $lastMonth . "'";
    $res_sql_00 = mysqli_query($connection, $sql_00);
    if (!$res_sql_00)
    {
        $errormsg=  "Error: " . $sql_00 . "<br>" . mysqli_error($connection);
        break;
    }
    $countDeleted = mysqli_affected_rows($connection);
    $sql_01 = "SELECT EXTRACT(YEAR_MONTH FROM m.DATE) as MONTHMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
       WHERE m.date < CURDATE() AND m.date >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR) 
        And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and ID_FOURNISSEUR = 'FEAD' and depy_n = 0
        AND fead_n = 1 AND daten = 1 AND Birbcode <> 0
        group by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";
    
    $res_sql_01 =mysqli_query($connection,$sql_01);
    if (!$res_sql_01)
    {
        $errormsg=  "Failed to execute FEADNONAGREED query: " . $sql_01 . " ". $connection->error;
        break;
    }
    while ($data_sql_01=mysqli_fetch_object($res_sql_01)) {
        if ($data_sql_01->MONTHMVT <= $lastMonth) continue;
        // détermination du volume FEAD livré aux CPAS ou autres organisations non affiliées
        // fead_n = 1 AND daten = 1 AND Birbcode <> 0
        $data_sql_01->Category = "FEADNONAGREED";
        $data_sql_01->Volume = - $data_sql_01->QTE; // negative value

        // remove fead_n, daten and Birbcode from the object
        unset($data_sql_01->fead_n);
        unset($data_sql_01->daten);
        unset($data_sql_01->Birbcode);
        unset($data_sql_01->QTE);
        $countInsertedFEADNONAGREED++;
        $arrayMovements[] = $data_sql_01;
    }
    // détermination des vivres aux associations non agréées SANS FEAD
    // fead_n = 0 AND daten = 1
    $sql_02 = "SELECT EXTRACT(YEAR_MONTH FROM m.DATE) as MONTHMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
      WHERE m.date < CURDATE() AND m.date >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR) 
            And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and ID_FOURNISSEUR <> 'FEAD' and depy_n = 0
       AND fead_n = 0 AND daten = 1
        group by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";

    $res_sql_02 =mysqli_query($connection,$sql_02);
    if (!$res_sql_02)
    {
        $errormsg=  "Failed to execute NOFEADNONAGREED query: " . $sql_02 . " " . $connection->error;
        break;
    }
    while ($data_sql_02=mysqli_fetch_object($res_sql_02)) {
        if ($data_sql_02->MONTHMVT <= $lastMonth) continue;
            // détermination du volume de vivres livrés aux CPAS ou autres organisations non affiliées
            // fead_n = 0 AND daten = 1

        $data_sql_02->Category = "NOFEADNONAGREED";
        $data_sql_02->Volume = - $data_sql_02->QTE; // negative value

        // remove fead_n, daten and Birbcode from the object
        unset($data_sql_02->fead_n);
        unset($data_sql_02->daten);
        unset($data_sql_02->Birbcode);
        unset($data_sql_02->QTE);
        $countInsertedNOFEADNONAGREED++;
        $arrayMovements[] = $data_sql_02;
}

// détermination des vivres livrés aux associations agréées y compris fead et ramasse
        $sql_03 = "SELECT EXTRACT(YEAR_MONTH FROM m.DATE) as MONTHMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
       WHERE m.date < CURDATE() AND m.date >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR) 
        And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and depy_n = 0
		AND daten = 0 
        group by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";

        $res_sql_03 =mysqli_query($connection,$sql_03);
        if (!$res_sql_03)
        {
            $errormsg=  "Failed to execute neg fead query: " . $sql_03 . " ".  $connection->error;
            break;
        }
        while ($data_sql_03=mysqli_fetch_object($res_sql_03)) {
            if ($data_sql_03->MONTHMVT <= $lastMonth) continue;
            // détermination des vivres livrés aux associations agréées y compris fead et ramasse
            $data_sql_03->Category = "AGREEDFEADCOLLECT";
            $data_sql_03->Volume = - $data_sql_03->QTE; // negative value

            // remove fead_n, daten and Birbcode from the object
            unset($data_sql_03->fead_n);
            unset($data_sql_03->daten);
            unset($data_sql_03->Birbcode);
            unset($data_sql_03->QTE);
            $countInsertedFEADAGREED++;
            $arrayMovements[] = $data_sql_03;
        }
        foreach ($arrayMovements as $key => $row) {
            $societeEscaped= mysqli_real_escape_string($connection,$row->societe);
            $insertQuery = "INSERT INTO `movements_monthly` (month,bank_short_name,id_org,orgname,category,quantity) 
            VALUES ('".$row->MONTHMVT."', '".$row->bank_short_name."', '".$row->id_asso."', '".$societeEscaped."', '".$row->Category."', '".$row->Volume."')";
            $sql = $connection->query($insertQuery);

            if (!$sql)
            {
                $errormsg=  "Failed to execute insert query: " . $connection->error;
                break;
            }
        }

    break;
}

if ($errormsg == "")
{
    $countInserted= $countInsertedFEADAGREED + $countInsertedFEADNONAGREED + $countInsertedNOFEADNONAGREED;;
    $message = "From $lastMonth Deleted $countDeleted & Inserted $countInserted records";
}
else {
    $message= substr($errormsg,0,50);
}

$insertQuery = "INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) 
 VALUES ('avdmadmin',10,0,'movements_monthly','" . $message . "','Update')";
$sql = $connection->query($insertQuery);
if (!$sql)
{
    $errormsg=  "Failed to execute insert query: " . $connection->error;
    echo $errormsg;
}
$connection->close();

