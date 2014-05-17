<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="tr">

<head>
<title>Kullanıcı Yönetimi Uygulaması</title>
<meta charset="utf-8">

<link rel="stylesheet"
	href="images/css/humanity/jquery-ui-1.10.4.custom.min.css" />
<link rel="stylesheet" type="text/css"
	href="images/css/pnotify.custom.min.css">

<link rel="stylesheet" href="images/css/styles.css" />

<script src="images/js/jquery-1.10.2.js"></script>
<script src="images/js/jquery-ui-1.10.4.custom.min.js"></script>

<script src="images/js/app.js"></script>

</head>

<body>
	<div class="body-container">

		<div id="create-user-dialog-form" title="Create new user">
			<p class="validateTips">Yıldızlı alanları doldurmak zorunludur.</p>

			<form id="formCreateUser">
				<fieldset id="fieldsetCreateUser">
					<label for="firstname">İsim *</label> <input type="text"
						name="firstname" id="firstname"
						class="text ui-widget-content ui-corner-all"> <label
						for="lastname">Soyisim *</label> <input type="text" name="lastname"
						id="lastname" value="" class="text ui-widget-content ui-corner-all">
					<label for="phone">Telefon</label> <input type="text"
						name="phone" id="phone" value=""
						class="text ui-widget-content ui-corner-all">
					<label for="captcha"><img src="captcha.jpg" id="imgCaptcha" /></label> <input type="text"
						name="captcha" id="captcha" value=""
						class="text ui-widget-content ui-corner-all">
				</fieldset>
			</form>
		</div>
		
		<div id="update-user-dialog-form" title="Update user">
			<p class="validateTips">Başında yıldız olan alanları doldurmak zorunludur.</p>

			<form id="formUpdateUser">
			<input type="hidden" name="updateuserid" id="updateuserid" value="" />
				<fieldset id="fieldsetCreateUser">
					<label for="firstname2">İsim *</label> <input type="text"
						name="firstname2" id="firstname2"
						class="text ui-widget-content ui-corner-all"> <label
						for="lastname2">Soyisim *</label> <input type="text" name="lastname2"
						id="lastname2" value="" class="text ui-widget-content ui-corner-all">
					<label for="phone2">Telefon</label> <input type="text"
						name="phone2" id="phone2" value=""
						class="text ui-widget-content ui-corner-all">
				</fieldset>
			</form>
		</div>

		<div id="users-contain" class="ui-widget">
			<h1>Kullanıcılar:</h1>
			<table id="users" class="ui-widget ui-widget-content">
				<thead>
					<tr class="ui-widget-header ">
						<th>İsim</th>
						<th>Soyisim</th>
						<th>Telefon</th>
						<th>İşlem</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<button id="create-user">Yeni Kullanıcı Oluştur</button>

	</div>
	<div id="divModalMessage"></div>
	<div class="modal"></div>
</body>
</html>

