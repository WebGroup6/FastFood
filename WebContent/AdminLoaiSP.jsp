<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<title>GinChicken's Admin</title>
<link rel="shortcut icon" type="image/png" href="./img/logo1.png" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<!-- Custom styles for this template -->

<c:if test="${loaiSanPham == null }">
	<link rel="stylesheet" href="./css/CSS.css">
</c:if>
<c:if test="${loaiSanPham != null }">
	<link rel="stylesheet" href="../css/CSS.css">
</c:if>
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div id="Admin">
				<a href="Admin_Profile.html" class="btn btn-default btn-sm"> <span
					class="glyphicon glyphicon-user"></span> Thông tin cá nhân
				</a>
				<button data-toggle="modal" data-target="#myModalExit"
					class="btn btn-default btn-sm">
					<span class="glyphicon glyphicon-log-out"></span> Log out
				</button>

			</div>
			<div id="img">
				<c:if test="${loaiSanPham == null }">
					<img src="img/logo1.png" class="d-inline-block align-top"
						width="100" height="85">
				</c:if>
				<c:if test="${loaiSanPham != null }">
					<img src="../img/logo1.png" class="d-inline-block align-top"
						width="100" height="85">
				</c:if>
			</div>
			<div id="header">
				<h3>WELCOME ADMIN</h3>
				<h4>GIN'S CHICKEN</h4>
			</div>
		</div>
	</div>

	<!--Thoat Admin-->
	<div class="container">
		<div id="myModalExit" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog-exit" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Thông báo</h4>
					</div>
					<div class="modal-body">
						<p>Bạn có muốn thoát không?</p>
					</div>
					<div class="modal-footer">
						<a href="TrangChu.html" class="btn btn-primary"> Có</a>
						<button type=" button" class="btn btn-default"
							data-dismiss="modal">Không</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<!--left bar-->
				<ul class="nav nav-sidebar">
					<li><a href="">Thống Kê</a></li>
					<li><a href="#">Khách hàng</a></li>
					<li><a href="NhanVienServlet">Nhân viên</a></li>
					<li><a href="LoaiSPServlet">Loại sản phẩm</a></li>
					<li><a href="SanPhamServlet">Sản phẩm</a></li>
					<li><a href="HoaDonServlet">Đơn hàng</a></li>
					<li><a href="TinKMServlet">Tin khuyến mãi</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="QLSP">
					<h1>Quản lý loại sản phẩm</h1>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered">

						<tr>
							<th>Mã loại</th>
							<th>Tên loại sản phẩm</th>
							<th>Lựa chọn</th>
						</tr>

						<c:forEach var="loaisp" items="${listLoaiSP}">
							<tbody>
								<tr>
									<td><c:out value="${loaisp.getMaLoai()}" /></td>
									<td><c:out value="${loaisp.getTenLoaiSP()}" /></td>
									<td width="75px"><a
										href="/FastFood/LoaiSPServlet/edit?maLoai=<c:out value="${loaisp.getMaLoai()}" />"
										class="edit"> <span class="glyphicon glyphicon-edit"></span></a>
										<a
										href="/FastFood/LoaiSPServlet/delete?maLoai=<c:out value="${loaisp.getMaLoai()}" />"
										class="delete"> <span
											class="glyphicon glyphicon-remove-sign"></span>
									</a></td>

									<%-- <td width="200px">
										<a data-toggle="modal"
										data-target="#modalSua" href="LoaiSPServlet/edit"> 
											<span class="glyphicon glyphicon-edit"></span>
										</a>
										<a
										href="LoaiSPServlet/delete?maLoai=<c:out value='${loaisp.maLoai}' />">
											<span class="glyphicon glyphicon-remove-sign"></span>
										</a>
									
									</td> --%>

								</tr>

							</tbody>
						</c:forEach>




					</table>
				</div>

				<div class="btnThem">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#myModal">Thêm mới</button>
				</div>

				<c:if test="${loaiSanPham == null }">
					<form method="post" action="/FastFood/LoaiSPServlet/insert">
				</c:if>
				<c:if test="${loaiSanPham != null }">
					<form method="get" action="/FastFood/LoaiSPServlet/update">
				</c:if>

				<!-- The Modal THEM-->
				<div class="modal fade" id="myModal">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">

							<!-- Modal Header -->
							<c:if test="${loaiSanPham == null }">
								<div class="modal-header">
									<h3 class="modal-title">Thêm sản phẩm</h3>
								</div>
							</c:if>
							<c:if test="${loaiSanPham != null }">
								<div class="modal-header">
									<h3 class="modal-title">Sửa sản phẩm</h3>
								</div>
							</c:if>
							<!-- Modal body -->

							<div class="modal-body">
								<div class="">
									<div class="form_row">
										<c:if test="${loaiSanPham != null }">
											<label>Mã loại:</label>
											<input type="text" class="form_input" name="txtMaLoai"
												required="required" readonly="readonly"
												value="${loaiSanPham.getMaLoai() }" />
										</c:if>
										<c:if test="${loaiSanPham == null }">
											<label>Mã loại:</label>
											<input type="text" class="form_input" name="txtMaLoai"
												required="required" readonly="readonly" value="${maLoaiMoi}" />
										</c:if>
									</div>
									<div class="form_row">
										<label>Tên sản phẩm :</label> <input type="text"
											class="form_input" name="txtTenLoaiSP" required="required"
											value="${loaiSanPham.getTenLoaiSP() }" />
									</div>

								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<div class="pull-right">
									<c:if test="${loaiSanPham == null }">
										<input type="submit" class="btn btn-success" value="Thêm Mới"></input>
									</c:if>
									<c:if test="${loaiSanPham != null }">
										<input type="submit" class="btn btn-success"
											value="Sửa loại sản phẩm"></input>
									</c:if>

								</div>

								<button type="button" class="btn btn-secondary pull-left"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<c:if test="${loaiSanPham != null }">
		<script type="text/javascript">
			jQuery.noConflict();
			$('#myModal').modal('show');
		</script>
	</c:if>


	<!--Sua -->
	<!-- <form action="LoaiSPServlet/edit" method="get">
					<div class="modal fade" id="modalSua">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								Modal Header
								<div class="modal-header">
									<h3 class="modal-title">Thêm mới loại món</h3>
								</div>
								Modal body
								<div class="modal-body">


									<div class="form_row">
										<label>Mã loại món :</label> <input type="text"
											class="form_input" name="maLoai" />
									</div>

									<div class="form_row">
										<label>Tên loại món :</label> <input type="text"
											class="form_input" name="tenLoaiSP" />
									</div>


								</div>

								Modal footer
								<div class="modal-footer">
									<button type="submit" class="btn btn-success">Luu</button>
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Hủy</button>
								</div>

							</div>
						</div>
					</div>
				</form>

	


				<div class="container">
					<div id="myModalXoaHD" class="modal fade" tabindex="-1"
						role="dialog">
						<div class="modal-dialog-delete" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Thông báo</h4>
								</div>
								<div class="modal-body">
									<p>Bạn có muốn xóa sản phẩm này không?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" href="">Có</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Không</button>
								</div>
							</div>
						</div>
					</div>
				</div>



				<form action="LoaiSPServlet/insert" method="get">
					<div class="modal fade" id="myModal">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">

								<div class="modal-header">
									<h3 class="modal-title">Thêm Loại món mới</h3>
								</div>

								<div class="modal-body">


									<div class="form_row">
										<label>Mã loại món :</label> <input type="text"
											class="form_input" name="maLoai" />
									</div>

									<div class="form_row">
										<label>Tên loại món :</label> <input type="text"
											class="form_input" name="tenLoaiSP" />
									</div>


									<div class="modal-footer">
										<button type="submit" class="btn btn-success">Thêm</button>
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Hủy</button>
									</div>

								</div>
							</div>
						</div>
					</div>
				</form>


			</div>
		</div>


		<script>
			type = "text/javascript" > $.validator.setDefaults({
				submitHandler : function() {
					alert("Thêm mới thành công !");
				}
			});

			$(document).ready(function() {
				$("#form_Them").validate({
					rules : {
						maloai : {
							required : true,
						},
						tenloai : {
							required : true,
						},

						agree : "required"
					},
					messages : {
						maloai : {
							required : "This field is required",
						},
						tenloai : {
							required : "This field is required",
						},

						agree : "Please accept our policy"
					},
					errorElement : "em",
					errorPlacement : function(error, element) {
						// Add the 'help-block' class to the error element
						error.addClass("help-block");

						if (element.prop("type") === "checkbox") {
							error.insertAfter(element.parent("label"));
						} else {
							error.insertAfter(element);
						}
					},

				});
			});
		</script>
		validate sua
		<script>
			type = "text/javascript" > $.validator.setDefaults({
				submitHandler : function() {
					alert("Thêm mới thành công !");
				}
			});

			$(document).ready(function() {
				$("#form_Sua").validate({
					rules : {
						maloai : {
							required : true,
						},
						tenloai : {
							required : true,
						},

						agree : "required"
					},
					messages : {
						maloai : {
							required : "This field is required",
						},
						tenloai : {
							required : "This field is required",
						},

						agree : "Please accept our policy"
					},
					errorElement : "em",
					errorPlacement : function(error, element) {
						// Add the 'help-block' class to the error element
						error.addClass("help-block");

						if (element.prop("type") === "checkbox") {
							error.insertAfter(element.parent("label"));
						} else {
							error.insertAfter(element);
						}
					},

				});
			});
		</script>
		-->

</body>

</html>