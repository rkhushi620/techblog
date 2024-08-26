$(document).ready(function() {
	
    console.log("loaded........")

    $('#reg-form').on('submit', function(event) {
		
//		this line prevent you to go ouside from this form
        event.preventDefault();
		
		//take all form data into form variable
		let form = new FormData(this);

        $("#sumbimt-btn").hide();
        $("#loader").show();
        
        //send register servlet:
        $.ajax({
            url: "RegisterServlet",
            type: 'POST',
            data: form,
            success: function(data, textStatus, jqXHR) {
               	console.log("data come from server "+data);
               	console.log("status = "+textStatus);
               	console.log("third = "+jqXHR);
                $("#sumbimt-btn").show();
                $("#loader").hide();

                if (data.trim() === 'done') {

                    swal("Registered successfully..We are going to redirect to login page")
                        .then(() => {
                            window.location = "login_page.jsp"
                        });
                } else {

                    swal(data);
                }

            },
            error: function(jqXHR, textStatus, errorThrown) {
				
                $("#sumbimt-btn").show();
                $("#loader").hide();
                swal("something went wrong..try again");

            },
            processData: false,
            contentType: false

        });



    });


});