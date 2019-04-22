<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>China</title>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main1" style="width: 600px;height:400px;"></div>


<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main1'));

    $.ajax({

        url: '${pageContext.request.contextPath}/user/echarts',
        dataType: 'JSON',
        success: function (data) {
            // console.log(data)
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts',
                    subtext: '注册时间详情'
                },
                tooltip: {},
                legend: {
                    data: ['注册时间详情']
                },
                xAxis: {
                    data: ['第一周', '第二周', '第三周']
                },
                yAxis: {},
                series: [{
                    name: '注册时间详情',
                    type: 'bar',
                    data: data
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    })

</script>
</body>
</html>