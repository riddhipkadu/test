<!DOCTYPE html>
<html>
<head>
<meta  content = "0.5" />
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery-ui-1.10.4.min.js"></script>
</head>
<body>
<a href="./userLogin">LexCare Compliance Tool Login Page</a>

<form id="peopleSoftForm" action="http://localhost:8080/LCMT_Mankind_uat/authenticateUserPeopleSoft" method="post">
<input name="empno" id="empno" value="78" type="hidden"/>
<a href="#" onclick="document.getElementById('peopleSoftForm').submit();">Submit</a>
</form>
</body>
</html>
