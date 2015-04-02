var options = {
	// Boolean - Whether we should show a stroke on each segment
	segmentShowStroke : true,

	// String - The colour of each segment stroke
	segmentStrokeColor : "#fff",

	// Number - The width of each segment stroke
	segmentStrokeWidth : 2,

	// Number - The percentage of the chart that we cut out of the middle
	percentageInnerCutout : 0, // This is 0 for Pie charts

	// Number - Amount of animation steps
	animationSteps : 40,

	// String - Animation easing effect
	animationEasing : "easeOutBounce",

	// Boolean - Whether we animate the rotation of the Doughnut
	animateRotate : true,

	// Boolean - Whether we animate scaling the Doughnut from the centre
	animateScale : false,

	// String - A legend template
	legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"

};

//var myPieChart;
//
//initPieChart = function(canvasId) {
//	// Get the context of the canvas element we want to select
//	var ctx = document.getElementById(canvasId).getContext("2d");
//	// For a pie chart
//	myPieChart = new Chart(ctx).Pie(data, options);
//}

