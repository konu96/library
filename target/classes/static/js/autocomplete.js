$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	  });
	$('#search').autocomplete({
		source: function( req, res ) {
			$.ajax({
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				url: '/books/autocomplete',
				type: 'POST',
				dataType: 'json',
				data: JSON.stringify({
					bookName: (req.term),
				}),
				success: function( data ) {
					console.log(data);
					res(data);
				},
				error: function( data ) {
					console.log("error");
					console.log(data);
				}
			});
		},
		autoFocus: true,
		delay: 300,
		minLength: 1
	});
})