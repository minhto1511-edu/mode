<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login</title>
        <!---Custom CSS File--->
        <link rel="stylesheet" href="css/login.css">
    <img src="images/hero-bg.jpg" alt="" class="background-image">
</head>
<body>
    <div class="container">
        <input type="checkbox" id="check">
        <div class="login form">
            <header>Đăng nhập</header>
            <h3 style="color: red">${error}</h3>
            <form action="login" method="post">
                <input type="text"  name="user" placeholder="Nhập Username">
                <input type="password" name="pass" placeholder="Nhập Password">
                <input type="submit" class="button" value="Đăng nhập">
            </form>
        </div>
    </div>
</body>
</html>

