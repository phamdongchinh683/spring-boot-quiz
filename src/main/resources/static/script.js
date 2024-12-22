$(document).ready(function() {
	$("input[type='radio'][name='question']").on('change', function() {
	
	        // Get the value of the selected radio button
	        var selectedValue = $(this).val();
	        
	        // Get the label associated with the selected radio button
	        var label = $(this).next('label');
	        
	        // Apply CSS to the selected radio button's label
			$(".radio-input label").css({
			            'border-color': '', // Reset the border color
			            'color': '' // Reset the color
			        });
			        
			        label.css({
			            'border-color': 'rgb(22, 245, 22)', // Set the border color
			            'color': 'rgb(16, 184, 16)' // Set the color
			        });
	        
	        
	    });
		
		$("input[type='radio'][name='question']").on({
		    change: function(event){
		    
			 event.preventDefault(); // prevent the form from submitting
			 					  var selectedOption = $("input[name='question']:checked").val();;
			 					  var questionId = $("#questionId").val();
			 					  if (!selectedOption) {
			 					    alert("Please select an option.");
			 					    return false;
			 					  }
			 					  $.ajax({
			 					    type: "GET",
			 					    url: "/submitAnswer",
			 					    data: {
			 					    
			 					      questionId: questionId,
			 						  question: selectedOption
			 					    },
			 					    success: function(response) {	debugger
			 					      if (response === "true") {
			 							
			 							$("input[name='question']:checked").next('label').css({
			 							                    'border-color': 'rgb(22, 245, 22)',
			 							                    'color': ' rgb(16, 184, 16)'
			 												
			 							                });
			 					        //alert("Correct answer!");

			 					      } else {
			 							$("input[name='question']:checked").next('label').css({
			 							                    'border-color': 'red',
			 							                    'color': 'red'
			 							                });
			 					       // alert("Incorrect answer.");
			 					      }
			 					    }
			 					  });
		    } 
		});	
	
	
	
	$(".submit").click(function(event) {
		
	  
		
			
	  
	});

	
	
	
	
	
		
});

