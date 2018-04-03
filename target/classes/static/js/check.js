$(function() {
	$('.imgList').click(function()  {
		if( $(this).hasClass('checked') ) {
			$(this).removeClass('checked');
			$(this).next(".disabled_checkbox").prop('checked', false);
		} else {
			console.log( $(this) )
			$(this).addClass('checked');
			$(this).next(".disabled_checkbox").prop('checked', true);
		}
	});
	
	$('.disabled_checkbox').click(function() {
	    return false;
	  });
});