//    < !--loading post using ajax-- >


//temp means object of clicking element
function getPosts(catId) {
	$("#loader").show();
	$("#post-container").hide()
	// remove the class active from all link
//	$(".c-link").removeClass('active')


	$.ajax({
		url: "load_posts.jsp",
		//send data parameter cid and data catId
		data: { cid: catId },
		success: function(data, textStatus, jqXHR) {
			console.log(data);
			$("#loader").hide();
			$("#post-container").show();
			$('#post-container').html(data)
			//add class active for temp element
//			$(temp).addClass('active')

		}
	})

}



$(document).ready(function(e) {


//	let first = $('.c-link')[0]
	getPosts(0)


})
