function doLike(user_id,post_id,cl){
	
	
	const d = {
		userId:user_id,
		postId:post_id,
		operation:'like'
	}
	
	$.ajax({
		url: "LikeServlet",
		data: d,
		success: function(data,textStatus,jqXHR){
			if(data.trim() == 'true'){
				let c = $("#"+cl).text();
				console.log("value of c = "+c);
				c++;
				$("#"+cl).html(" "+c);
				swal("Liked Successfully.");
				
			}else{
				swal(data);
			}
		},
		error: function(jqXHR,textStatus,errorThrown){
			swal("Error in server");
		}
	})
}

function disLike(user_id,post_id){
	const d = {
		userId:user_id,
		postId:post_id,
		operation:'dislike'
	}
	
	$.ajax({
		url: "LikeServlet",
		data: d,
		success: function(data,textStatus,jqXHR){
			if(data.trim() == 'true'){
				let c = $(".like-counter").html();
				c--;
				$(".like-counter").html(" "+c);
				$(".dislike-class").hide();
				swal("Disliked Successfully.");
				
			}else{
				swal(data);
			}
		},
		error: function(jqXHR,textStatus,errorThrown){
			swal("Error in server");
		}
	})
}


