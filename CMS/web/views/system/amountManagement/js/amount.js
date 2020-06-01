layui.use(['table', 'util','laypage','layer'], function() {

    var myArray1 = new Array();
    var myArray2 = new Array();
    var util = layui.util;

    function Num() {
        $.ajax({
            type: "post",
            url: "/Position/Number",
            async: false,
            "success": function (result) {
                if (result.resultCode == 0) {
                    var userList = result.data;
                    var userList2 = result.data.length;
                    for (var i = 0; i < userList2; i++) {
                        myArray1[i] = result.data[i].countNumber*10;
                        myArray2[i] = util.toDateString(result.data[i].dateTime, "yyyy-MM-dd");
                    }
                    console.log(userList)
                    console.log(userList2)
                } else {
                    layer.msg("请求失败，联系管理员", {
                        icon: 2,
                        time: 1000
                    });

                }
            }
        });
    }
    Num();

    var myChart = echarts.init(document.getElementById('main'));

    option = {
        xAxis: {
            type: 'category',
            data: myArray2
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: myArray1,
            type: 'line'
        }]
    };
    myChart.setOption(option);
});