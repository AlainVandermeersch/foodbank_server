<?php

$errormsgMonthly = "";
$errormsgMonthlyDetail = "";
$errormsgDaily = "";
echo "Starting movements summarize.php at " . date("Y-m-d H:i:s") . "\n";
while ( true) {
    $host= getenv('MYSQL_HOST');
    $user =getenv('MYSQL_USER');
    $password =getenv('MYSQL_PASSWORD');
    $database =getenv('MYSQL_DATABASE');

    $connection= mysqli_connect($host,$user,"$password",$database);
    
    if (mysqli_connect_errno())
    {
        $errormsgMonthly=  "Failed to connect to MySQL: " . mysqli_connect_error();
        break;
    }
    $arrayMovements_monthly = array();
    $lastMonth = 202001;
    $lastMonthDetail = 202001;
    $countMonthlyDeleted = 0;
    $countMonthlyInsertedDetailNONAGREED = 0;
    $countMonthlyInsertedNOFEADNONAGREED = 0;
    $countMonthlyInsertedDetailAGREED = 0;
    $countMonthlyInsertedDetail = 0;
    $countDailyInsertedFEADNONAGREED = 0;
    $countDailyInsertedNOFEADNONAGREED = 0;
    $countDailyInsertedFEADAGREED = 0;
    $sql_monthly_max = "SELECT MAX(month) as lastMonth FROM `movements_monthly` ";
    $res_sql_monthly_max = mysqli_query($connection, $sql_monthly_max);
    if (!$res_sql_monthly_max)
    {
        $errormsgMonthly=  "Error: " . $sql_monthly_max . "<br>" . mysqli_error($connection);
        break;
    }
    if($row = mysqli_fetch_array($res_sql_monthly_max))
    {
        if ($row['lastMonth'] != null)
        {
            $lastMonth = $row['lastMonth'];
        }

    }
    $sql_monthly_max_detail = "SELECT MAX(month) as lastMonth FROM `movements_monthly_detail` ";
    $res_sql_monthly_max_detail = mysqli_query($connection, $sql_monthly_max_detail);
    if (!$res_sql_monthly_max_detail)
    {
        $errormsgMonthlyDetail=  "Error: " . $sql_monthly_max_detail . "<br>" . mysqli_error($connection);
        break;
    }
    if($row = mysqli_fetch_array($res_sql_monthly_max_detail))
    {
        if ($row['lastMonth'] != null)
        {
            $lastMonthDetail = $row['lastMonth'];
        }

    }

    $sql_monthly_del = "DELETE FROM `movements_monthly` where month = '" . $lastMonth . "'";
    $res_sql_monthly_del = mysqli_query($connection, $sql_monthly_del);
    if (!$res_sql_monthly_del)
    {
        $errormsgMonthly=  "Error: " . $sql_monthly_del . "<br>" . mysqli_error($connection);
        break;
    }
    $countMonthlyDeleted = mysqli_affected_rows($connection);
    $sql_extract_01_monthly = "SELECT EXTRACT(YEAR_MONTH FROM m.DATE) as MONTHMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
       WHERE m.date < CURDATE() AND m.date >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR) 
        And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and ID_FOURNISSEUR = 'FEAD' and depy_n = 0
        AND fead_n = 1 AND daten = 1 AND Birbcode <> 0
        group by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode 
        order by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";
    
    $res_sql_extract_01_monthly =mysqli_query($connection,$sql_extract_01_monthly);
    if (!$res_sql_extract_01_monthly)
    {
        $errormsgMonthly=  "Failed to execute FEADNONAGREED monthly  query: " . $sql_extract_01_monthly . " ". $connection->error;
        break;
    }
    while ($data_sql_extract_01_monthly=mysqli_fetch_object($res_sql_extract_01_monthly)) {
        if ($data_sql_extract_01_monthly->MONTHMVT < $lastMonth) continue;
        // détermination du volume FEAD livré aux CPAS ou autres organisations non affiliées
        // fead_n = 1 AND daten = 1 AND Birbcode <> 0
        $data_sql_extract_01_monthly->Category = "FEADNONAGREED";
        $data_sql_extract_01_monthly->Volume = - $data_sql_extract_01_monthly->QTE; // negative value

        // remove fead_n, daten and Birbcode from the object
        unset($data_sql_extract_01_monthly->fead_n);
        unset($data_sql_extract_01_monthly->daten);
        unset($data_sql_extract_01_monthly->Birbcode);
        unset($data_sql_extract_01_monthly->QTE);
        $countMonthlyInsertedDetailNONAGREED++;
        $arrayMovements_monthly[] = $data_sql_extract_01_monthly;
    }
    echo "movements summarize.php ended extracting movements monthly FEADNONAGREED " . date("Y-m-d H:i:s") . "\n";
    // détermination des vivres aux associations non agréées SANS FEAD
    // fead_n = 0 AND daten = 1
    $sql_extract_02_monthly = "SELECT EXTRACT(YEAR_MONTH FROM m.DATE) as MONTHMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
      WHERE m.date < CURDATE() AND m.date >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR) 
            And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and ID_FOURNISSEUR <> 'FEAD' and depy_n = 0
       AND fead_n = 0 AND daten = 1
        group by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode 
        order by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";

    $res_sql_extract_02_monthly =mysqli_query($connection,$sql_extract_02_monthly);
    if (!$res_sql_extract_02_monthly)
    {
        $errormsgMonthly=  "Failed to execute NOFEADNONAGREED monthly  query: " . $sql_extract_02_monthly . " " . $connection->error;
        break;
    }
    while ($data_sql_extract_02_monthly=mysqli_fetch_object($res_sql_extract_02_monthly)) {
        if ($data_sql_extract_02_monthly->MONTHMVT < $lastMonth) continue;
            // détermination du volume de vivres livrés aux CPAS ou autres organisations non affiliées
            // fead_n = 0 AND daten = 1

        $data_sql_extract_02_monthly->Category = "NOFEADNONAGREED";
        $data_sql_extract_02_monthly->Volume = - $data_sql_extract_02_monthly->QTE; // negative value

        // remove fead_n, daten and Birbcode from the object
        unset($data_sql_extract_02_monthly->fead_n);
        unset($data_sql_extract_02_monthly->daten);
        unset($data_sql_extract_02_monthly->Birbcode);
        unset($data_sql_extract_02_monthly->QTE);
        $countMonthlyInsertedNOFEADNONAGREED++;
        $arrayMovements_monthly[] = $data_sql_extract_02_monthly;
}
    echo "movements summarize.php ended extracting movements monthly NOFEADNONAGREED " . date("Y-m-d H:i:s") . "\n";
// détermination des vivres livrés aux associations agréées y compris fead et ramasse
        $sql_extract_03_monthly = "SELECT EXTRACT(YEAR_MONTH FROM m.DATE) as MONTHMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
       WHERE m.date < CURDATE() AND m.date >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR) 
        And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and depy_n = 0
		AND daten = 0 
        group by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode
        order by MONTHMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";

        $res_sql_extract_03_monthly =mysqli_query($connection,$sql_extract_03_monthly);
        if (!$res_sql_extract_03_monthly)
        {
            $errormsgMonthly=  "Failed to execute neg fead monthly  query: " . $sql_extract_03_monthly . " ".  $connection->error;
            break;
        }
        while ($data_sql_extract_03_monthly=mysqli_fetch_object($res_sql_extract_03_monthly)) {
            if ($data_sql_extract_03_monthly->MONTHMVT < $lastMonth) continue;
            // détermination des vivres livrés aux associations agréées y compris fead et ramasse
            $data_sql_extract_03_monthly->Category = "AGREEDFEADCOLLECT";
            $data_sql_extract_03_monthly->Volume = - $data_sql_extract_03_monthly->QTE; // negative value

            // remove fead_n, daten and Birbcode from the object
            unset($data_sql_extract_03_monthly->fead_n);
            unset($data_sql_extract_03_monthly->daten);
            unset($data_sql_extract_03_monthly->Birbcode);
            unset($data_sql_extract_03_monthly->QTE);
            $countMonthlyInsertedDetailAGREED++;
            $arrayMovements_monthly[] = $data_sql_extract_03_monthly;
        }
    echo "movements summarize.php ended extracting movements monthly FEADAGREED " . date("Y-m-d H:i:s") . "\n";
        foreach ($arrayMovements_monthly as $key => $row) {
            $societeEscaped= mysqli_real_escape_string($connection,$row->societe);
            $insertQuery_monthly = "INSERT INTO `movements_monthly` (month,bank_short_name,id_org,orgname,category,quantity) 
            VALUES ('".$row->MONTHMVT."', '".$row->bank_short_name."', '".$row->id_asso."', '".$societeEscaped."', '".$row->Category."', '".$row->Volume."')";
            $sql = $connection->query($insertQuery_monthly);

            if (!$sql)
            {
                $errormsgMonthly=  "Failed to execute insert monthly  query: " . $connection->error;
                break;
            }
        }

    if ($errormsgMonthly == "")
    {
    $countMonthlyInserted= $countMonthlyInsertedDetailAGREED + $countMonthlyInsertedDetailNONAGREED + $countMonthlyInsertedNOFEADNONAGREED;;
    $messageMonthly = "From $lastMonth Deleted $countMonthlyDeleted & Inserted $countMonthlyInserted records";
    }
    else
    {
        $messageMonthly = "Error: $errormsgMonthly";
    }
    echo "Ending movements monthly summarize.php at " . date("Y-m-d H:i:s") . " with message:" . $messageMonthly . "\n";

    $insertQuery_audit_monthly = "INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) 
        VALUES ('avdmadmin',10,0,'movements_monthly','" . substr($messageMonthly,0,50) . "','Update')";
    $sql = $connection->query($insertQuery_audit_monthly);
    if (!$sql)
    {
        $errormsg=  "Failed to execute insert monthly audit  query: " . $connection->error;
        echo "movements summarize.php failed to insert monthly statistics in auditchanges table:" . $errormsg . "\n";
    }
    // end of monthly movements

    echo "Ending monthly movements by organisation summarize.php at " . date("Y-m-d H:i:s") . "\n";
    // Adding extract for details by Org and articles for FEAD

    $countMonthlyInsertedDetail = 0;
    $sql_monthly_detail_del = "DELETE FROM `movements_monthly_detail` where month = '" . $lastMonthDetail . "'";
    $res_sql_monthly_detail_del = mysqli_query($connection, $sql_monthly_detail_del);
    if (!$res_sql_monthly_detail_del)
    {
        $errormsgMonthly=  "Error: " . $sql_monthly_detail_del . "<br>" . mysqli_error($connection);
        break;
    }
    $countMonthlyDeletedDetail = mysqli_affected_rows($connection);
    $sql_extract_01_monthlyDetail = "SELECT EXTRACT(YEAR_MONTH FROM m.DATE) as MONTHMVT, b.bank_short_name,
           m.id_asso, o.societe,o.BirbCode,m.ID_ARTICLE,a.ID_CAT_ARTICLE,a.NOM_FR as article_name_fr, a.NOM_NL as article_name_nl,
           m.ID_FOURNISSEUR, f.nom as name_supplier,SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        LEFT JOIN banques b ON (b.bank_id = o.lien_banque )
        LEFT JOIN articles a ON (a.ID_ARTICLE =m.ID_ARTICLE)
        LEFT JOIN fournisseurs f ON (f.ID_FOURNISSEUR =m.ID_FOURNISSEUR)
        WHERE YEAR(m.date) = 2022 
        AND   m.id_mouv IN('EXP','EXPCONG')  
        AND m.ID_COMPANY = b.bank_short_name
        AND depy_n = 0
        group by MONTHMVT,b.bank_id,m.id_asso,m.ID_ARTICLE,m.ID_FOURNISSEUR 
        order by MONTHMVT,b.bank_id,m.id_asso,m.ID_ARTICLE,m.ID_FOURNISSEUR";

    $res_sql_extract_01_monthlyDetail =mysqli_query($connection,$sql_extract_01_monthlyDetail);
    if (!$res_sql_extract_01_monthlyDetail)
    {
        $errormsgMonthly=  "Failed to execute FEAD BY Org AND Article monthly  query: " . $sql_extract_01_monthlyDetail . " ;". $connection->error;
        break;
    }
    while ($data_sql_extract_01_monthlyDetail=mysqli_fetch_object($res_sql_extract_01_monthlyDetail)) {
       // if ($data_sql_extract_01_monthlyDetail->MONTHMVT < $lastMonthDetail) continue;
        $data_sql_extract_01_monthlyDetail->Volume = - $data_sql_extract_01_monthlyDetail->QTE; // negative value

        unset($data_sql_extract_01_monthlyDetail->QTE);
        $countMonthlyInsertedDetail++;

        $societeEscaped= mysqli_real_escape_string($connection,$data_sql_extract_01_monthlyDetail->societe);
        $supplierNameEscaped= mysqli_real_escape_string($connection,$data_sql_extract_01_monthlyDetail->name_supplier);
        $articleNameFrEscaped= mysqli_real_escape_string($connection,$data_sql_extract_01_monthlyDetail->article_name_fr);
        $articleNameNlEscaped= mysqli_real_escape_string($connection,$data_sql_extract_01_monthlyDetail->article_name_nl);
        $birbCodeEscaped = mysqli_real_escape_string($connection,$data_sql_extract_01_monthlyDetail->BirbCode);
        $insertQuery_monthly_detail =
            "INSERT INTO `movements_monthly_detail` (month,bank_short_name,id_org,orgname,esfcode,id_article,id_cat_article,article_name_fr,article_name_nl,id_supplier,name_supplier,quantity) 
            VALUES ('".$data_sql_extract_01_monthlyDetail->MONTHMVT."', '".$data_sql_extract_01_monthlyDetail->bank_short_name."', '".$data_sql_extract_01_monthlyDetail->id_asso."', '".$societeEscaped."', '"
            .$birbCodeEscaped."','" .$data_sql_extract_01_monthlyDetail->ID_ARTICLE."','".$data_sql_extract_01_monthlyDetail->ID_CAT_ARTICLE."','" .$articleNameFrEscaped."','".$articleNameNlEscaped."','"
            .$data_sql_extract_01_monthlyDetail->ID_FOURNISSEUR."', '".$supplierNameEscaped."', '".$data_sql_extract_01_monthlyDetail->Volume."');";
        $sql = $connection->query($insertQuery_monthly_detail);

        if (!$sql)
        {
            $errormsgMonthlyDetail=  "Failed to execute insert monthly  query Detail: " . $connection->error;
            break;
        }
    }

    if ($errormsgMonthlyDetail == "")
    {
        $messageMonthlyDetail = "From $lastMonthDetail Deleted $countMonthlyDeletedDetail & Inserted $countMonthlyInsertedDetail records";
    }
    else
    {
        $messageMonthlyDetail = "Error: $errormsgMonthlyDetail";
    }
    echo "Ending movements monthly Detail summarize.php at " . date("Y-m-d H:i:s") . " with message:" . $messageMonthlyDetail . "\n";

    $insertQuery_audit_monthly = "INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) 
        VALUES ('avdmadmin',10,0,'movements_detail','" . substr($messageMonthlyDetail,0,50) . "','Update')";
    $sql = $connection->query($insertQuery_audit_monthly);
    if (!$sql)
    {
        $errormsg=  "Failed to execute insert monthly audit  query: " . $connection->error;
        echo "movements summarize.php failed to insert monthly Detail statistics in auditchanges table:" . $errormsg . "\n";
    }
    // end of monthly movements


    $arrayMovements_daily = array();

//------------------------------------------------------
// daily movements
// delete all records from movements_daily
    $lastDayObj = new DateTime();
    $lastDayObj->modify('-3 month');
    $lastDay= $lastDayObj->format('Y-m-d');
    echo "movements summarize.php last day 3 months ago is " . $lastDay . "\n";
    $countDailyDeleted = 0;
    $countDailyCleanedup = 0;
    // delete all records from movements_daily older than 3 months
$sql_daily_del = "DELETE FROM movements_daily where day < DATE_SUB(CURDATE(), INTERVAL 3 MONTH)";
$res_sql_daily_del = mysqli_query($connection, $sql_daily_del);
if (!$res_sql_daily_del)
{
    $errormsgDaily=  "Error: " . $sql_daily_del . "<br>" . mysqli_error($connection);
    break;
}
$countDailyCleanedup = mysqli_affected_rows($connection);
// find the last day in movements_daily
    $sql_daily_max = "SELECT MAX(day) as lastDay FROM `movements_daily` ";
    $res_sql_daily_max = mysqli_query($connection, $sql_daily_max);
    if (!$res_sql_daily_max)
    {
        $errormsgDaily=  "Error: " . $sql_daily_max . "<br>" . mysqli_error($connection);
        break;
    }
    if($row = mysqli_fetch_array($res_sql_daily_max))
    {
        if ($row['lastDay'] != null)
        {
            $lastDay = $row['lastDay'];
            echo "movements summarize.php last day in movements_daily is " . $lastDay . "\n";
        }

    }
    // delete all records from movements_daily equal or younger than the last day in movements_daily
    $sql_daily_del = "DELETE FROM movements_daily where day >= '$lastDay'";
    $res_sql_daily_del = mysqli_query($connection, $sql_daily_del);
    if (!$res_sql_daily_del)
    {
        $errormsgDaily=  "Error: " . $sql_daily_del . "<br>" . mysqli_error($connection);
        break;
    }
    $countDailyDeleted = mysqli_affected_rows($connection);
    // extract all records from movements_daily equal or younger than the last day in movements_daily
$sql_extract_01_daily = "SELECT DATE(m.DATE) as DAYMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
       WHERE m.date < CURDATE() AND m.date >=  '$lastDay'
        And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and ID_FOURNISSEUR = 'FEAD' and depy_n = 0
        AND fead_n = 1 AND daten = 1 AND Birbcode <> 0
        group by DAYMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode 
        order by DAYMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";

$res_sql_extract_01_daily =mysqli_query($connection,$sql_extract_01_daily);
if (!$res_sql_extract_01_daily)
{
    $errormsgDaily=  "Failed to execute FEADNONAGREED daily  query: " . $sql_extract_01_daily . " ". $connection->error;
    break;
}
while ($data_sql_extract_01_daily=mysqli_fetch_object($res_sql_extract_01_daily)) {
   
    // détermination du volume FEAD livré aux CPAS ou autres organisations non affiliées
    // fead_n = 1 AND daten = 1 AND Birbcode <> 0
    $data_sql_extract_01_daily->Category = "FEADNONAGREED";
    $data_sql_extract_01_daily->Volume = - $data_sql_extract_01_daily->QTE; // negative value

    // remove fead_n, daten and Birbcode from the object
    unset($data_sql_extract_01_daily->fead_n);
    unset($data_sql_extract_01_daily->daten);
    unset($data_sql_extract_01_daily->Birbcode);
    unset($data_sql_extract_01_daily->QTE);
    $countDailyInsertedFEADNONAGREED++;
    $arrayMovements_daily[] = $data_sql_extract_01_daily;
}
    echo "movements summarize.php ended extracting movements daily FEADNONAGREED " . date("Y-m-d H:i:s") . "\n";
// détermination des vivres aux associations non agréées SANS FEAD
// fead_n = 0 AND daten = 1
$sql_extract_02_daily = "SELECT DATE(m.DATE) as DAYMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
      WHERE m.date < CURDATE() AND m.date >= '$lastDay'
            And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and ID_FOURNISSEUR <> 'FEAD' and depy_n = 0
       AND fead_n = 0 AND daten = 1
        group by DAYMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode 
        order by DAYMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";

$res_sql_extract_02_daily =mysqli_query($connection,$sql_extract_02_daily);
if (!$res_sql_extract_02_daily)
{
    $errormsgDaily=  "Failed to execute NOFEADNONAGREED daily  query: " . $sql_extract_02_daily . " " . $connection->error;
    break;
}
while ($data_sql_extract_02_daily=mysqli_fetch_object($res_sql_extract_02_daily)) {
    // détermination du volume de vivres livrés aux CPAS ou autres organisations non affiliées
    // fead_n = 0 AND daten = 1

    $data_sql_extract_02_daily->Category = "NOFEADNONAGREED";
    $data_sql_extract_02_daily->Volume = - $data_sql_extract_02_daily->QTE; // negative value

    // remove fead_n, daten and Birbcode from the object
    unset($data_sql_extract_02_daily->fead_n);
    unset($data_sql_extract_02_daily->daten);
    unset($data_sql_extract_02_daily->Birbcode);
    unset($data_sql_extract_02_daily->QTE);
    $countDailyInsertedNOFEADNONAGREED++;
    $arrayMovements_daily[] = $data_sql_extract_02_daily;
}
    echo "movements summarize.php ended extracting movements daily NOFEADNONAGREED " . date("Y-m-d H:i:s") . "\n";
// détermination des vivres livrés aux associations agréées y compris fead et ramasse
$sql_extract_03_daily = "SELECT DATE(m.DATE) as DAYMVT, b.bank_short_name,
           m.id_asso, o.societe,o.fead_n,o.daten,o.Birbcode, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m join organisations o on (o.id_dis=m.id_asso) 
        JOIN banques b ON (b.bank_id = o.lien_banque )
       WHERE m.date < CURDATE() AND m.date >= '$lastDay'
        And   id_mouv IN('EXP','EXPCONG')  
        AND ID_COMPANY = b.bank_short_name
        and depy_n = 0
		AND daten = 0 
        group by DAYMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode 
        order by DAYMVT,b.bank_id,m.id_asso,o.societe,o.fead_n,o.daten,o.Birbcode";

$res_sql_extract_03_daily =mysqli_query($connection,$sql_extract_03_daily);
if (!$res_sql_extract_03_daily)
{
    $errormsgDaily=  "Failed to execute neg fead daily  query: " . $sql_extract_03_daily . " ".  $connection->error;
    break;
}
while ($data_sql_extract_03_daily=mysqli_fetch_object($res_sql_extract_03_daily)) {

    // détermination des vivres livrés aux associations agréées y compris fead et ramasse
    $data_sql_extract_03_daily->Category = "AGREEDFEADCOLLECT";
    $data_sql_extract_03_daily->Volume = - $data_sql_extract_03_daily->QTE; // negative value

    // remove fead_n, daten and Birbcode from the object
    unset($data_sql_extract_03_daily->fead_n);
    unset($data_sql_extract_03_daily->daten);
    unset($data_sql_extract_03_daily->Birbcode);
    unset($data_sql_extract_03_daily->QTE);
    $countDailyInsertedFEADAGREED++;
    $arrayMovements_daily[] = $data_sql_extract_03_daily;
}
    echo "movements summarize.php ended extracting movements daily FEADAGREED " . date("Y-m-d H:i:s") . "\n";
foreach ($arrayMovements_daily as $key => $row) {
    $societeEscaped= mysqli_real_escape_string($connection,$row->societe);
    $insertQuery_daily = "INSERT INTO `movements_daily` (day,bank_short_name,id_org,orgname,category,quantity) 
            VALUES ('".$row->DAYMVT."', '".$row->bank_short_name."', '".$row->id_asso."', '".$societeEscaped."', '".$row->Category."', '".$row->Volume."')";
    $sql = $connection->query($insertQuery_daily);

    if (!$sql)
    {
        $errormsgDaily=  "Failed to execute insert daily  query: " . $connection->error;
        break;
    }
}
    echo "movements summarize.php ended loading daily movements " . date("Y-m-d H:i:s") . "\n";


break;
}


if ($errormsgDaily == "")
{
    $countDailyInserted= $countDailyInsertedFEADAGREED + $countDailyInsertedFEADNONAGREED + $countDailyInsertedNOFEADNONAGREED;;
    $messageDaily = "Cleaned up $countDailyCleanedup, Deleted $countDailyDeleted & Inserted $countDailyInserted records";
}
else {
    $messageDaily= "Error: $errormsgDaily";
}
echo "Ending movements daily summarize.php at " . date("Y-m-d H:i:s") . " with message:" . $messageDaily . "\n";

$insertQuery_audit_daily = "INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) 
 VALUES ('avdmadmin',10,0,'movements_daily','" . substr($messageDaily,0,50) . "','Update')";
$sql = $connection->query($insertQuery_audit_daily);
if (!$sql)
{
    $errormsg=  "Failed to execute insert daily audit  query: " . $connection->error;
    echo "movements summarize.php failed to insert daily statistics in auditchanges table:" . $errormsg . "\n";
}


$connection->close();
echo "movements summarize.php ended at " . date("Y-m-d H:i:s") . "\n";
?>

