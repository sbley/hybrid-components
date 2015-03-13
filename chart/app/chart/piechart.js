var data = [ {
	value : 0,
	color : "#F7977A", // "#F7464A",
	highlight : "#F7464A",
	label : "Todo"
}, {
	value : 0,
	color : "#FFF79A", // "#FFFC5C",
	highlight : "#FFFC5C",
	label : "In Progress"
}, {
	value : 0,
	color : "#82CA9D", // "#B0FF5C",
	highlight : "#B0FF5C",
	label : "Done"
} ]

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

var myPieChart;

initPieChart = function(canvasId) {
	// Get the context of the canvas element we want to select
	var ctx = document.getElementById(canvasId).getContext("2d");
	// For a pie chart
	myPieChart = new Chart(ctx).Pie(data, options);
}

updateValues = function(todo, inProgress, done) {
	myPieChart.segments[0].value = todo;
	myPieChart.segments[1].value = inProgress;
	myPieChart.segments[2].value = done;
	myPieChart.update();
}
