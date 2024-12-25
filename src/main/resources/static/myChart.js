var chartDataStr = decodeHtml(chartData);
var chartJSONArray = JSON.parse(chartDataStr);

var arrayLength = chartJSONArray.length;
var numericData = [];
var labelData = [];

for (var i = 0; i < arrayLength; i++){
    numericData[i] = chartJSONArray[i].count;
    labelData[i] = chartJSONArray[i].label;
}
//Pie Chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    data: {
          labels: labelData,
          datasets: [{
            label: 'Project DataSet',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData
          }]
          },
    options: {
        title: {
            display: true,
            text: 'Project Statuses'
        }
    }
});

// [{"value": 1, "label": "COMPLETED}, {"value": 2, "label": "NOTSTARTED}, {"value": 1, "label": "INPROGRESS"}]
function decodeHtml(html){
    var text = document.createElement("textarea");
    text.innerHTML = html;
    return text.value;
}