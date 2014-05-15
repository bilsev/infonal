
function btnDuzenleClick(userID) {
	alert("Düzenle: " + userID);
}
function btnSilClick(userID) {
	alert("Sil: " + userID);
}

function fillUsers() {
	$.get("ajax/userManagement/getAllUsers.do", function(data) {
		$( "#users tbody" ).html("");
		
		if (data.status == "OK") {
			$.each(data.data, function(index, value) {
				$( "#users tbody" ).append( "<tr>" +
					"<td>" + value.firstname + "</td>" +
					"<td>" + value.lastname + "</td>" +
					"<td>" + value.phone + "</td>" +
					'<td><input type="button" onclick="btnDuzenleClick('+ value.id +')" id="btnDuzenle'+ value.id +'" value="Düzenle" />'+
					'<input type="button" onclick="btnSilClick('+ value.id +')" id="btnSil'+ value.id +'" value="Sil" /></td>' +
					"</tr>" );
			});
		}
	});
}

$(document).ready(function() {
	$body = $("body");
	$(document).on({
		ajaxStart: function() { $body.addClass("loading"); },
    	ajaxStop: function() { $body.removeClass("loading"); }
	});
	
	var firstname = $( "#firstname" ),
		lastname = $( "#lastname" ),
		phone = $( "#phone" ),
		captcha = $( "#captcha" ),
		allFields = $( [] ).add( firstname ).add( lastname ).add( phone ).add( captcha ),
		tips = $( ".validateTips" );
	
	function updateTips( t ) {
		tips
			.text( t )
			.addClass( "ui-state-highlight" );
		setTimeout(function() {
			tips.removeClass( "ui-state-highlight", 1500 );
		}, 500 );
	}
	
	function checkLength( o, n, min, max ) {
		if ( o.val().length > max || o.val().length < min ) {
			o.addClass( "ui-state-error" );
			updateTips( "Bu alan " +
				min + " ile " + max + " karakter uzunluğunda olmalı." );
			return false;
		} else {
			return true;
		}
	}
	
	function checkRegexp( o, regexp, n ) {
		if ( !( regexp.test( o.val() ) ) ) {
			o.addClass( "ui-state-error" );
			updateTips( n );
			return false;
		} else {
			return true;
		}
	}
	
	$( "#dialog-form" ).dialog({
		autoOpen: false,
		height: 500,
		width: 500,
		modal: true,
		buttons: {
			"Kaydet": function() {
				var bValid = true;
				allFields.removeClass( "ui-state-error" );
	
				bValid = bValid && checkLength( firstname, "username", 4, 32 );
				bValid = bValid && checkLength( lastname, "lastname", 4, 32);
				bValid = bValid && checkLength( phone, "phone", 4, 32 );
				bValid = bValid && checkLength( captcha, "captcha", 5, 5 );
	
				bValid = bValid && checkRegexp( firstname, /^[a-z]([0-9a-z_])+$/i,
					"İsim şu karakterlerden oluşabilir: a-z, 0-9, alt çizgi, ve alfanümerik bir karakterle başlamalı." );
				bValid = bValid && checkRegexp( lastname, /^[a-z]([0-9a-z_])+$/i,
					"Soyisim şu karakterlerden oluşabilir: a-z, 0-9, alt çizgi, ve alfanümerik bir karakterle başlamalı." );
				bValid = bValid && checkRegexp( phone, /^([\+][0-9]{2} [0-9]{3} [0-9]{3} [0-9]{2} [0-9]{2})+$/, "Telefon alanı formatı : +90 555 123 45 67" );
				bValid = bValid && checkRegexp( captcha, /^([0-9a-z])+$/i,
					"Şu karakterlerden oluşabilir: a-z, 0-9." );

				if ( bValid ) {
					// TODO 14may14 1239 Put ajax code here
					$.post("ajax/userManagement/addUser.do",
					{
						firstname: $("#firstname").val(),
						lastname: $("#lastname").val(),
						phone: $("#phone").val(),
						captcha: $("#captcha").val()
					},
					function(data) {
						alert(data);
						fillUsers();
					});
					$( this ).dialog( "close" );
				}
			},
			"İptal": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
		}
	});

	$( "#create-user" )
	.button()
	.click(function() {
		$( "#dialog-form" ).dialog("open");
	});
	
	fillUsers();
	
});
