$(function(){


var clock = [
	'<ul id="clock">',
	'<li id="date"></li>',
	'<li id="seconds"></li>',
	'<li id="hours"></li>',
	'<li id="minutes"></li>',
	'</ul>'].join('');			
		
$(clock).fadeIn().appendTo('#clockDiv');

(function Clock(){
	var date = new Date().getDate(),
         hours = new Date().getHours(),
         minutes = new Date().getMinutes();
		 seconds = new Date().getSeconds(),
              
	$("#date").html(date);

             
	var hrotate = hours * 30 + (minutes / 2);
    $("#hours").css({
		'transform'	:  'rotate('+ hrotate +'deg)',
		'-moz-transform'	:'rotate('+ hrotate +'deg)',
		'-webkit-transform'	:'rotate('+ hrotate +'deg)'
	});
				  
	var mrotate = minutes * 6;
                        
    $("#minutes").css({
		'transform'	:  'rotate('+ mrotate +'deg)',
		'-moz-transform'	:'rotate('+ mrotate +'deg)',
		'-webkit-transform'	:'rotate('+ mrotate +'deg)'
	});  
				  
    var srotate = seconds * 6;
              
    $("#seconds").css({
	    'transform'	:  'rotate('+ srotate +'deg)',
		'-moz-transform'	:'rotate('+ srotate +'deg)',
		'-webkit-transform'	:'rotate('+ srotate +'deg)'
	});
             
setTimeout(Clock,1000);
})();



});