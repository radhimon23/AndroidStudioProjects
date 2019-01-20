<?php
include "Function_1.php";
if(isset($_REQUEST["action"]))
{
	$obj = new Functions(); 
	//http://192.168.43.240/IOT/service_1.php?action=getRooms	
	if($_REQUEST["action"]=="getRooms")
	{
		$query = "select * from rooms";
		selectJson($query);		
	}
	else if($_REQUEST["action"]=="getDevices")
	{
		$query = "select * from devices where room = '".$_REQUEST["r_id"]."'";
		selectJson($query);		
	}
	
	else if($_REQUEST["action"]=="deleteRooms")
	{
		$roomsId = $_REQUEST["roomsId"];
		$query = "delete from rooms where id = '".$roomsId."'" ;
		executeJson($query);		
	}	
	else if($_REQUEST["action"]=="deleteDevices")
	{
		$devicesId = $_REQUEST["devicesId"];
		$query = "delete from devices where id = '".$devicesId."'" ;
		executeJson($query);		
	}
	
	
	else if($_REQUEST["action"]=="updateradhika")
	{
		
		$roomsId = $_REQUEST["roomsId"];
		$name = $_REQUEST["name"];
               // $image = $_REQUEST["image"];
		
		
		$query = "update rooms set image='".$image."',name='".$name."' where id='".$roomsId."'";
		executeJson($query);
	}	
	
	
	else if($_REQUEST["action"]=="updateDevice")
	{
		$Id = $_REQUEST["id"];
		$status = $_REQUEST["staus"];
				
		$query = "update devices set staus='".$status."' where id='".$Id."'";
		executeJson($query);
	}	
	
	
}
function insertJson($query)
{
	$response = Array();
	$subResponse = Array();
	$response["data"] = Array();
	$response1["success"] = 0;

	$result = Functions::insertQuery($query);
	if($result)
	{
		$subResponse["status"]="valid";
		$subResponse["id"]=$result;
	}
	else
	{
		$subResponse["status"]="not valid";
	}
	array_push($response["data"],$subResponse);
	 $response1["success"] = 1;
	echo json_encode($response);
}

function executeJson($query)
{
	$response = Array();
	$subResponse = Array();
	$response["data"] = Array();
	$response1["success"] = 0;

	$result = Functions::executeQuery($query);
	if($result)
	{
		$subResponse["staus"]="valid";
		$subResponse["id"]=$result;
	}
	else
	{
		$subResponse["staus"]="not valid";
	}
	array_push($response["data"],$subResponse);
	 $response1["success"] = 1;
   echo	json_encode($response);
}

function selectJson($query)
{			
	$response = Array();
	$subResponse = Array();
	$response["data"] = Array();
	$response1["success"] = 0;
    $result = Functions::selectQuery($query);
	$ColumnCount = mysqli_num_fields($result);
	
	while($row = mysqli_fetch_array($result))
	{
		for($i=0;$i<$ColumnCount;$i++)
		{
			
				$subResponse[mysqli_fetch_field_direct($result,$i)->name]=$row[$i];
			
		}			
		array_push($response["data"],$subResponse);
	}
	 $response1["success"] = 1;		
	echo json_encode($response);
}
?>
