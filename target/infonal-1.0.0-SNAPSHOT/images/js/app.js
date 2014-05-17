
function btnDuzenleClick(userID, firstname, lastname, phone) {
	$("#updateuserid").val(userID);
	$("#firstname2").val(firstname);
	$("#lastname2").val(lastname);
	$("#phone2").val(phone);
	
	$( "#update-user-dialog-form" ).dialog("open");
}

function btnSilClick(userID) {
	if (!confirm("Bunu silmek istediğinize emin misiniz? Bu işlemin geri dönüşü yoktur."))
		return false;
	
	$.post("ajax/userManagement/removeUser.do",
	{
		id: userID
	},
	function(data) {
		$("#divModalMessage").html("<p><strong>"+ data.data +"</strong></p>");
		$("#divModalMessage").dialog({
			modal: true,
			height: 300,
			width: 500,
			buttons: {
				"Tamam": function() {
					$("#divModalMessage").html();
					$( this ).dialog( "close" );
				}
			}
		});
		if (data.status == "OK") {
			fillUsers();
		}
	});
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
					'<td><input type="button" onclick="btnDuzenleClick(\''+ value.id +'\', \''+ value.firstname +'\', \''+ value.lastname +'\', \''+ value.phone+'\' )" id="btnDuzenle'+ value.id +'" value="Düzenle" />'+
					'<input type="button" onclick="btnSilClick(\''+ value.id +'\')" id="btnSil'+ value.id +'" value="Sil" /></td>' +
					"</tr>" );
			});
		}
		else {
			$("#divModalMessage").html("<p><strong>"+ data.data +"</strong></p>");
			$("#divModalMessage").dialog({
				modal: true,
				height: 300,
				width: 500,
				buttons: {
					"Tamam": function() {
						$("#divModalMessage").html();
						$( this ).dialog( "close" );
					}
				}
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

	var firstname2 = $( "#firstname2" ),
		lastname2 = $( "#lastname2" ),
		phone2 = $( "#phone2" ),
		allFields2 = $( [] ).add( firstname2 ).add( lastname2 ).add( phone2 ),
		tips2 = $( ".validateTips" );
	
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
	
	$( "#create-user-dialog-form" ).dialog({
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
				bValid = bValid && checkLength( phone, "phone", 0, 32 );
				bValid = bValid && checkLength( captcha, "captcha", 5, 5 );
	
				bValid = bValid && checkRegexp( firstname, /^[a-zA-ZğüşçöıĞÜİŞÇÖ]([0-9a-zA-ZğüşçöıĞÜİŞÇÖ_ ])+$/i,
					"İsim şu karakterlerden oluşabilir: a-z, 0-9, alt çizgi, ve alfanümerik bir karakterle başlamalı." );
				bValid = bValid && checkRegexp( lastname, /^[a-zA-ZğüşçöıĞÜİŞÇÖ]([0-9a-zA-ZğüşçöıĞÜİŞÇÖ_ ])+$/i,
					"Soyisim şu karakterlerden oluşabilir: a-z, 0-9, alt çizgi, ve alfanümerik bir karakterle başlamalı." );
				//bValid = bValid && checkRegexp( phone, /^([\+][0-9]{2} [0-9]{3} [0-9]{3} [0-9]{2} [0-9]{2})+$/, "Telefon alanı formatı : +90 555 123 45 67" );
				bValid = bValid && checkRegexp( captcha, /^([0-9a-z])+$/i,
					"Şu karakterlerden oluşabilir: a-z, 0-9." );

				if ( bValid ) {
					$.post("ajax/userManagement/addUser.do",
					{
						firstname: $("#firstname").val(),
						lastname: $("#lastname").val(),
						phone: $("#phone").val(),
						captcha: $("#captcha").val()
					},
					function(data) {
						$("#divModalMessage").html("<p><strong>"+ data.data +"</strong></p>");
						$("#divModalMessage").dialog({
							modal: true,
							height: 300,
							width: 500,
							buttons: {
								"Tamam": function() {
									$("#divModalMessage").html();
									$( this ).dialog( "close" );
								}
							}
						});
						if (data.status == "OK") {
							fillUsers();
						}
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

	$( "#update-user-dialog-form" ).dialog({
		autoOpen: false,
		height: 500,
		width: 500,
		modal: true,
		buttons: {
			"Kaydet": function() {
				var bValid = true;
				allFields2.removeClass( "ui-state-error" );
	
				bValid = bValid && checkLength( firstname2, "username2", 4, 32 );
				bValid = bValid && checkLength( lastname2, "lastname2", 4, 32);
				bValid = bValid && checkLength( phone2, "phone2", 0, 32 );
	
				bValid = bValid && checkRegexp( firstname2, /^[a-zA-ZğüşçöıĞÜİŞÇÖ]([0-9a-zA-ZğüşçöıĞÜİŞÇÖ_ ])+$/i,
					"İsim şu karakterlerden oluşabilir: a-z, 0-9, alt çizgi, ve alfanümerik bir karakterle başlamalı." );
				bValid = bValid && checkRegexp( lastname2, /^[a-zA-ZğüşçöıĞÜİŞÇÖ]([0-9a-zA-ZğüşçöıĞÜİŞÇÖ_ ])+$/i,
					"Soyisim şu karakterlerden oluşabilir: a-z, 0-9, alt çizgi, ve alfanümerik bir karakterle başlamalı." );

				if ( bValid ) {
					$.post("ajax/userManagement/updateUser.do",
					{
						id: $("#updateuserid").val(),
						firstname: $("#firstname2").val(),
						lastname: $("#lastname2").val(),
						phone: $("#phone2").val()
					},
					function(data) {
						$("#divModalMessage").html("<p><strong>"+ data.data +"</strong></p>");
						$("#divModalMessage").dialog({
							modal: true,
							height: 300,
							width: 500,
							buttons: {
								"Tamam": function() {
									$("#divModalMessage").html();
									$( this ).dialog( "close" );
								}
							}
						});
						if (data.status == "OK") {
							fillUsers();
						}
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
		$("#imgCaptcha").attr("src", "captcha.jpg?" + new Date().getTime());
		$( "#create-user-dialog-form" ).dialog("open");
	});
	
	fillUsers();
	
});
