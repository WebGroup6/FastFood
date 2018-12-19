<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!doctype html>
<html lang="en">
  <head>
    <title>Login</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="./css/login.css" media="screen" >
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
  </head>
  <body>
      

    <!-- <div class="dn">

    </div> -->

    <div class=" dn">
        <div class="row">
          <div class="col-6 mx-auto">
            <h1 class="display-4 text-center ">Login</h1>
      
            <div class="login">
       
                <div class="input-group mb-3" style="width:500px;margin-left:80px;">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa fa-user"></i></span>
                    </div>
                    <input type="text" class="form-control" placeholder="Tên đăng nhập" id="tenDN" name="username" required >
                    
                    
                </div>
                <div class="input-group mb-3" style="width:500px;margin-left:80px;">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa fa-lock"></i></span>
                    </div>
                    <input type="text" class="form-control" placeholder="Mật khẩu" id="mk" name="password" required>
                 
                </div>
              <div class="form-group text-center">
                <!-- <a href="#" data-toggle="modal" data-target="#myModalQuenMK" data-dismiss="modal" id="quenMK"  style="margin-right:310px;">Quên mật khẩu</a>                       -->
                <button type="button" class="btn btn-secondary" onclick="validateFormDNhap()"><i class="fa fa-arrow-right"></i></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script src="js/jquery.min.js"></script>  
    <script type="text/javascript" src="JS/dki-dnhap.js"></script>
    <script type="text/javascript" src="JS/main.js"></script>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  </body>
</html>