<?php
// AL VOLO TANTO PER VEDERE COME LAVORA 


error_reporting(E_ALL);
header("Content-type: application/json; charset=utf-8");
class DB_Connection {
		
		private $connect;
		function __construct() {
			$this->connect = mysqli_connect('host', 'nameuser','pwd', 'namedb')
			or die("Could not connect to db");
			
		}
		
		public function getConnection()
		{
			return $this->connect;
		}
	}
     
     
     
     
     
     class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($email,$password)
		{
            $pwd=md5(mysqli_real_escape_string($this -> connection,$password));
            $email=mysqli_real_escape_string($this -> connection,$email);
			$query = "Select * from utenti_store where utenti_store='$email' and pwd_store = '$pwd' ";
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0){
				$json['success'] = ' Welcome '.$email;
				echo json_encode($json);
				mysqli_close($this -> connection);
                
                
                
                
			}else{
                
			//	$query = "insert into utenti_store (utenti_store, pwd_store) values ( '$email','$pwd')";
				//$inserted = mysqli_query($this -> connection, $query);
				$inserted=1;
                if($inserted == 1 ){
					$json['success'] = 'Acount created';
				}else{
					$json['error'] = 'Wrong password';
				}
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['email'],$_POST['password'])) {
		$email = $_POST['email'];
		$password = $_POST['password'];
		
		if(!empty($email) && !empty($password)){
			
			$encrypted_password = md5($password);
			$user-> does_user_exist($email,$password);
			
		}else{
			echo json_encode("you must type both inputs");
		}
		
	}

?>
