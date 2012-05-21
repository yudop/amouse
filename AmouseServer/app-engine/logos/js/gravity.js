var Gravity = {
		
	init: function() {
		
		$("#air .block_container").css({
			height: $("#air").outerHeight() + "px"
		});
		
		$(".falling").mouseenter(function(i) {
					
			var block = $(this); //.parent().next();
			var yposBlock = $(block).position()['top'] - $("#air").position()['top'];
			var fallDist = ($("#air").outerHeight() - yposBlock) - $(block).outerHeight();
							
			$(this).animate({
				//height:"+=20",
				//width:"+=20",  
				marginTop:"-=100px",
				//marginLeft:"-=10px",
			},1000,'easeOutCubic');
			
			/*
			//let the block fall
			$(block).animate({
				marginTop: fallDist+"px"
			}, {
				duration: 1000,
				easing: "easeOutBounce"
			});		*/;	
		});
		
		$(".falling").mouseleave(function(i) {
			
			var block = $(this); //.parent().next();
			var yposBlock = $(block).position()['top'] - $("#air").position()['top'];
			var fallDist = ($("#air").outerHeight() - yposBlock) - $(block).outerHeight();
			
			
			
			
			
			//let the block fall
			$(block).stop().animate({
				marginTop: fallDist+"px"
			}, {
				duration: 1000,
				easing: "easeOutBounce"
			});		
			
			/*
			$(this).animate({
				//height:"-=20",
				//width:"-=20",  
				marginTop:"+=100px",
				//marginLeft:"+=10px",
			},1000,'easeOutElastic');*/
		});
	
	},
	
	
	fall: function(i,delay) {
			//alert("tjo"+i);		
			var block = $(i); //.parent().next();
			var yposBlock = $(block).position()['top'] - $("#air").position()['top'];
			
			var fallDist = ($("#air").outerHeight() - yposBlock) - 128;
					
			//let the block fall
			$(block).animate({
				marginTop: fallDist+"px"
			}, {
				duration: 2000,
				//complete: Gravity.fall("#home",2),
				easing: "easeOutBounce"
			});	
				
		
	},
	
	
	reset: function() {
		$(".handle").stop().animate({
			height: "50px"
		},{
			duration: 1000,
			easing: "easeInElastic"
		});
		
		$(".block").stop().animate({
			marginTop: "0px"
		},{
			duration: 1000,
			easing: "easeInBounce"
		});
	}

}

$(document).ready(function(){
	Gravity.init();
	Gravity.fall("#blog",100);
	setTimeout('Gravity.fall("#html5",4)',100);
	setTimeout('Gravity.fall("#downloads",4)',200);
	setTimeout('Gravity.fall("#home",4)', 300);
	setTimeout('Gravity.fall("#android",4)', 400);
	setTimeout('Gravity.fall("#app_engine",4)', 500);
	
	$( "#blog" ).draggable();
	$( "#html5" ).draggable();
	$( "#android" ).draggable();
	$( "#home" ).draggable();
	$( "#app_engine" ).draggable();
	$( "#downloads" ).draggable();
});