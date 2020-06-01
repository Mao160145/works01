$(function(){
    layui.use(['form','laypage','util','layer'], function() {
        var form = layui.form;
        form.render();
        var laypage = layui.laypage;
        var util = layui.util;
        var layer = layui.layer;
        var total;//总条数

        $(".search").click(function () {
            var likeName1=document.getElementById("likeName1").value;

            find_cmsSta_list(1,10,likeName1);
            laypage.render({
                elem:"table_page",//绑定页面元素节点的id(只能是id)
                count:total,//数据总数，从服务器中读取中
                //分页的样式
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                jump:function(obj,first){//当前分页执行成功后的回调函数
                    //obj包含了当前分页的所有参数
                    //obj.curr,obj.limit，当前页面所容纳的最大条数
                    if(!first){
                        find_cmsSta_list(obj.curr,obj.limit,likeName1);
                    }
                }
            });
            return false;
        })
        //定义获取当前统计信息的函数
        function find_cmsSta_list(page,rows,likeName1){
            var index;
            $.ajax({
                type:"get",
                url:"/Statistics/selectStatisticsAllByPage",
                async: false,
                data:{
                    "page": page,
                    "maxSize": rows,
                    "likeName1":likeName1,
                },
                "beforeSend": function() {
                    index = layer.load(3, {
                        shade: [0.5, "black"]
                    })
                },
                "success": function(result) {
                    if(result.resultCode == 0) {
                        layer.close(index);
                        build_Sta_list(result);
                        total = result.data.total;
                    } else {
                        layer.msg("服务器错误，请联系管理员", {
                            icon: 2,
                            time: 1000
                        });
                    }
                },
                "error": function() {
                    layer.msg("服务器错误，请联系管理员", {
                        icon: 2,
                        time: 1000
                    });
                }
            });
        }
        //定义渲染统计信息的函数
        function build_Sta_list(result) {
            var StaList = result.data.list;
            $(".table_tbody").empty();

        // <tr>
        //     <td><input type="checkbox" lay-skin="primary"></td>
        //         <td>1</td>
        //         <td>2313123</td>
        //         <td>128128683</td>
        //         <td>管理</td>
        //         <td>2018/2/22</td>
        //         <td><input type="checkbox" name="" lay-skin="switch" lay-text="ON|OFF"></td>
        //         <td>
        //         <div class="layui-btn-group">
        //
        //         <button class="layui-btn layui-btn-sm layui-btn-danger">删除</button>
        //         </div>
        //         </td>
        //         </tr>
            $.each(StaList,function (index,item) {
                //存放id的目的是为了删除，或者修改单个记录的时候找那条记录
                var tr = $("<tr></tr>");
                var td1 = $("<td></td>")
                    .addClass("childrenBox").append("<input type='checkbox' statistical_id='"+item.statistics_id+"' lay-skin='primary'>")
                var td2 = $("<td></td>").append(item.statistics_id);
                var td3 = $("<td></td>").append(item.company_name);
                var td4 = $("<td></td>").append(item.statistics_amount);
                var td5 = $("<td></td>").append(item.statistics_turnover);
                var td6 = $("<td></td>").append(item.statistics_recruit);
                tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).appendTo(".table_tbody");

            });
            form.render();
        }
        //调用获取时获取统计信息列表的方法
        find_cmsSta_list(1,10);


        //分页
        laypage.render({
            elem:"table_page",//绑定页面元素节点的id(只能是id)
            count:total,//数据总数，从服务器中读取中
            //分页的样式
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump:function(obj,first){//当前分页执行成功后的回调函数
                //obj包含了当前分页的所有参数
                //obj.curr,obj.limit，当前页面所容纳的最大条数
                if(!first){
                    find_cmsSta_list(obj.curr,obj.limit);
                }
            }
        });
        //删除(未写接口方法)
        $(document).on("click",".delete",function(){
            var statisticsId=$(this).attr("Statistics_id");
            layer.confirm("您确定删除吗?",function(){
                delete_userByUserId(statisticsId);
            });
        });

    });

});