<?php
class Functions
{
	public static $Connect;
	public static $filePath = "http://192.168.43.240/IOT/";
	function __construct()	{		
	   	self::$Connect = mysqli_connect("localhost","root","","iot");
		if (!self::$Connect) 
		{
			echo "Error: Unable to connect to MySQL." . PHP_EOL;
			echo "Debugging errno: " . mysqli_connect_errno() . PHP_EOL;
			echo "Debugging error: " . mysqli_connect_error() . PHP_EOL;
			exit;
		}		
	}
	
	public static function insertQuery($Query)
	{
		if($Result=mysqli_query(self::$Connect,$Query))
		{
			return mysqli_insert_id(self::$Connect);
		}		
		return 0;		
	}
	public static function updateQuery($Query)
	{
		if($Result=mysqli_query(self::$Connect,$Query))
		{
			return $Result;
		}		
		return 0;		
	}
	public static function executeQuery($Query)
	{
		if($Result=mysqli_query(self::$Connect,$Query))
		{
			return 1;
		}
		else
		{
			return 0;
		}		
	}
		
	public static function selectQuery($Query)
	{
		if($Result = mysqli_query(self::$Connect,$Query))
		{
			return $Result;
		}
		else
		{
			return 0;
		}
	}
	public static function TestFunctions()
	{
		return DbConfig::$DatabaseName;
	}
	
}



?>