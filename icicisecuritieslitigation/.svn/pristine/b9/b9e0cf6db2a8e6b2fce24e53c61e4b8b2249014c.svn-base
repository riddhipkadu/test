
	var count = 1;

	var numOfItems = $(".slider .item").length;
	
	$(".slider .item:nth-child("+count+") .carousel_bg").fadeIn("slow");
	$(".slider .item:nth-child("+count+") .carousel_image").delay(500).animate({left: 250,opacity:"show"},1000,"easeOutExpo");
	$(".slider .item:nth-child("+count+") .carousel_caption").delay(500).animate({right: 630,opacity:"show"},1000,"easeOutExpo");

	function imageRotator(){

		$(".slider .item:nth-child("+count+") .carousel_bg").fadeOut("slow");
		$(".slider .item:nth-child("+count+") .carousel_image").animate({left: -690,opacity:"hide"},1000,"easeOutExpo");
		$(".slider .item:nth-child("+count+") .carousel_caption").animate({right: -300,opacity:"hide"},1000,"easeOutExpo");

		count++;

		if(count > numOfItems)
		{
			count = 1;
		}

		$(".slider .item:nth-child("+count+") .carousel_bg").fadeIn("slow");
		$(".slider .item:nth-child("+count+") .carousel_image").delay(500).animate({left: 250,opacity:"show"},1000,"easeOutExpo");
		$(".slider .item:nth-child("+count+") .carousel_caption").delay(500).animate({right: 630,opacity:"show"},1000,"easeOutExpo");

	}

	var intervalid = setInterval(function(){

		imageRotator()

	},3000);

	var display = true;

	$("carousel").hover(function(){

		$(".btns").fadeIn();

	},
	function(){

		$(".btns").fadeOut();

	});

	$(".pause").click(function(e){

		e.preventDefault();

		display = !display;

		if(display)
		{
			$(this).html("Pause");
			imageRotator()
				intervalid = setInterval(function(){
						imageRotator()
				},3000);
		}
		else{

			$(this).html("Play");
			clearInterval(intervalid);	
		}		

	});