<?php
$host= getenv('MYSQL_HOST');
$user =getenv('MYSQL_USER');
$password =getenv('MYSQL_PASSWORD');
$database =getenv('MYSQL_DATABASE');

$countDonateurs = 0;
$countDons = 0;
$countDons2022 = 0;
// Create connection

$conn = new mysqli($host, $user, $password, $database);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

echo "Connected successfully\n";
$don_colums=array();
$columns = $conn->query("SHOW COLUMNS FROM donation_bat LIKE 'don_2%'");;
while ($column = $columns->fetch_assoc()) {
    $don_colums[] = $column['Field'];
}

$sql = "SELECT * FROM donation_bat";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // prepare and bind
    $sqlInsertDonateur = $conn->prepare("INSERT INTO donateurs(lien_banque, nom, prenom, adresse, cp, city, pays, titre)  VALUES (?,?,?,?,?,?,?,?)");

    while ($row = $result->fetch_assoc()) {
        $old_donateur_id = $row['don_id'];
        $sqlInsertDonateur->bind_param("ssssssss", $row['lien_banque'], $row['nom'], $row['prenom'], $row['adresse'], $row['cp'], $row['city'], $row['pays'], $row['titre']);
        if ($sqlInsertDonateur->execute()) {
            $countDonateurs++;
        } else {
            die("insert donateur failed: " . $conn->errno . ' ' . $conn->error . '\n' . $sqlInsertDonateur);
        }
        $donateur_id = mysqli_insert_id($conn);
        foreach($don_colums as $donyear)
        {
            $amount = $row[$donyear];
            $donDate = substr($donyear, 4) . "-12-31";

            if ($amount != 0) {

                $sqlInsertDon = "INSERT INTO dons(amount, lien_banque, donateur_id,  appended, checked, date) 
                VALUES($amount,{$row['lien_banque']}, $donateur_id,false,true,'$donDate') ";
                if ($conn->query($sqlInsertDon)) {
                    $countDons++;
                } else {
                    die("insert don failed: " . $conn->errno . ' ' . $conn->error . '\n' . $sqlInsertDonateur);
                }
            }
        }
        $sql1 = "SELECT * FROM don_bat where donateur_id = $old_donateur_id";
        $result1 = $conn->query($sql1);

        if ($result1->num_rows > 0) {
            while ($row1 = $result1->fetch_assoc()) {
                $sqlInsertDon1 = "INSERT INTO dons(amount, lien_banque, donateur_id,  appended, checked, date) 
                VALUES({$row1['amount']},{$row1['lien_banque']}, $donateur_id,false,true,'{$row1['date']}') ";
                if ($conn->query($sqlInsertDon1)) {
                    $countDons2022++;
                } else {
                    die("insert don 2022 failed: " . $conn->errno . ' ' . $conn->error . '\n' . $sqlInsertDonateur);
                }
            }
    }
}

}
$conn->close();

echo "$countDonateurs Donateurs added. $countDons Dons converted from previous years. $countDons2022 Dons converted from 2022\n";


