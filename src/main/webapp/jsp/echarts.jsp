<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>China</title>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="echartsmain" style="width: 600px;height:400px;"></div>


<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echartsmain'));


    $.ajax({
        url: '${pageContext.request.contextPath}/user/echarts',
        dataType: 'json',
        success: function (data) {
            var option = {
                title: {
                    text: 'ECharts',
                    subtext: '注册时间详情'
                },
                tooltip: {},
                legend: {
                    data: ['数量', '趋势']
                },
                xAxis: {
                    data: ['前一周', '前二周', '前三周']
                },
                yAxis: {},
                series: [{
                    name: '数量',
                    type: 'bar',
                    data: data
                }, {
                    name: '趋势',
                    type: 'line',
                    data: data
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

    })

    var goEasy = new GoEasy({
        appkey: 'BC-815b87e03de84e63ac875abcc90d8a8b'
    });

    goEasy.subscribe({
        channel: 'user_charts',
        onMessage: function (message) {
            var data2 = message.content
            data2 = JSON.parse(data2);
            console.log(data2 + "11111111")
            var option = {
                title: {
                    text: 'ECharts',
                    subtext: '注册时间详情'
                },
                tooltip: {},
                legend: {
                    data: ['数量', '趋势']
                },
                xAxis: {
                    data: ['前一周', '前二周', '前三周']
                },
                yAxis: {},
                series: [{
                    name: '数量',
                    type: 'bar',
                    data: data2
                }, {
                    name: '趋势',
                    type: 'line',
                    data: data2
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    })


</script>
</body>
</html>