<?php

$errormsgMonthlyDetail = "";
$countMonthlyInsertedDetail = 0;
echo "Starting movements summarize.php at " . date("Y-m-d H:i:s") . "\n";

    $host = getenv('MYSQL_HOST');
    $user = getenv('MYSQL_USER');
    $password = getenv('MYSQL_PASSWORD');
    $database = getenv('MYSQL_DATABASE');
    $beginmonth = getenv('MONTH_BEGIN');
    $endmonth = getenv('MONTH_END');
    

while ( true) {
    $connection = mysqli_connect($host, $user, "$password", $database);

    if (mysqli_connect_errno()) {
        $errormsgMonthlyDetail = "Failed to connect to MySQL: " . mysqli_connect_error();
        break;
    }

    $sql_extract_01_monthlyDetail = "SELECT EXTRACT(YEAR_MONTH FROM m.DATE) as MONTHMVT, m.ID_COMPANY,
           m.id_asso, o.societe,o.BirbCode,m.ID_ARTICLE,a.ID_CAT_ARTICLE,a.NOM_FR as article_name_fr, a.NOM_NL as article_name_nl,
           ca.NOM_FR as article_category_name_fr, ca.NOM_NL as article_category_name_nl, 
           m.ID_FOURNISSEUR, f.nom as name_supplier,m.ID_MOUV, SUM(m.QUANTITE ) as QTE        
    FROM mouvements m 
        LEFT join organisations o on (o.id_dis=m.id_asso) 
        LEFT JOIN articles a ON (a.ID_ARTICLE =m.ID_ARTICLE)
        LEFT JOIN cat_article ca on (a.ID_CAT_ARTICLE= ca.ID_CAT_ARTICLE)
        LEFT JOIN fournisseurs f ON (f.ID_FOURNISSEUR =m.ID_FOURNISSEUR)
       WHERE EXTRACT(YEAR_MONTH FROM m.DATE) >= " . $beginmonth . " 
       AND EXTRACT(YEAR_MONTH FROM m.DATE) <= " . $endmonth . "
        group by MONTHMVT,m.ID_COMPANY,m.id_asso,m.ID_ARTICLE,m.ID_FOURNISSEUR, m.id_mouv
        order by MONTHMVT,m.ID_COMPANY,m.id_asso,m.ID_ARTICLE,m.ID_FOURNISSEUR, m.id_mouv";

    $res_sql_extract_01_monthlyDetail = mysqli_query($connection, $sql_extract_01_monthlyDetail);
    if (!$res_sql_extract_01_monthlyDetail) {
        $errormsgMonthlyDetail = "Failed to execute Detail BY Org AND Article monthly  query: " . $sql_extract_01_monthlyDetail . " ;" . $connection->error;
        break;
    }


    while ($data_sql_extract_01_monthlyDetail = mysqli_fetch_object($res_sql_extract_01_monthlyDetail)) {

        $countMonthlyInsertedDetail++;
        $societeEscaped = mysqli_real_escape_string($connection, $data_sql_extract_01_monthlyDetail->societe);
        $supplierNameEscaped = mysqli_real_escape_string($connection, $data_sql_extract_01_monthlyDetail->name_supplier);
        $articleNameFrEscaped = mysqli_real_escape_string($connection, $data_sql_extract_01_monthlyDetail->article_name_fr);
        $articleNameNlEscaped = mysqli_real_escape_string($connection, $data_sql_extract_01_monthlyDetail->article_name_nl);
        $articleCategoryNameFrEscaped = mysqli_real_escape_string($connection, $data_sql_extract_01_monthlyDetail->article_category_name_fr);
        $articleCategoryNameNlEscaped = mysqli_real_escape_string($connection, $data_sql_extract_01_monthlyDetail->article_category_name_nl);
        $birbCodeEscaped = mysqli_real_escape_string($connection, $data_sql_extract_01_monthlyDetail->BirbCode);
        if (!$data_sql_extract_01_monthlyDetail->id_asso) {
            $data_sql_extract_01_monthlyDetail->id_asso = 0;
        }
        $insertQuery_monthly_detail =
            "INSERT INTO `movements_monthly_detail` (month,bank_short_name,id_org,orgname,esfcode,id_article,id_cat_article,article_name_fr,article_name_nl,
               article_category_name_fr,article_category_name_nl,id_supplier,name_supplier,id_mouv,quantity) 
            VALUES ('" . $data_sql_extract_01_monthlyDetail->MONTHMVT . "', '" . $data_sql_extract_01_monthlyDetail->ID_COMPANY . "', '" . $data_sql_extract_01_monthlyDetail->id_asso . "', '" . $societeEscaped . "', '"
            . $birbCodeEscaped . "','" . $data_sql_extract_01_monthlyDetail->ID_ARTICLE . "','" . $data_sql_extract_01_monthlyDetail->ID_CAT_ARTICLE . "','" .
            $articleNameFrEscaped . "','" . $articleNameNlEscaped . "','"  . $articleCategoryNameFrEscaped . "','" . $articleCategoryNameNlEscaped . "','"
            . $data_sql_extract_01_monthlyDetail->ID_FOURNISSEUR . "', '" . $supplierNameEscaped .
            "', '" . $data_sql_extract_01_monthlyDetail->ID_MOUV .  "', '" . $data_sql_extract_01_monthlyDetail->QTE ."');";
        $sql = $connection->query($insertQuery_monthly_detail);

        if (!$sql) {
            $errormsgMonthlyDetail = "Failed to execute insert monthly  query Detail: " . $connection->error;
            break;
        }
    }
    break;
}

    if ($errormsgMonthlyDetail == "")
    {
        $messageMonthlyDetail = "From $beginmonth  until $endmonth Inserted $countMonthlyInsertedDetail records";
    }
    else
    {
        $messageMonthlyDetail = "Error: $errormsgMonthlyDetail";
    }
    echo "Ending movements monthly Detail upload summarize.php at " . date("Y-m-d H:i:s") . " with message:" . $messageMonthlyDetail . "\n";



$connection->close();
echo "movements upload summarize.php ended at " . date("Y-m-d H:i:s") . "\n";
?>
