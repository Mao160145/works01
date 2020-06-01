$(function(){
    layui.use(['form','laypage','util','layer'], function() {
        var form = layui.form;
        form.render();
        var laypage=layui.laypage;
        var util=layui.util;
        var layer=layui.layer;
        var total;//总条数


        $(".search").click(function () {
            var likeName=document.getElementById("name").value;
            var likeName1=document.getElementById("likeName1").value;
            console.log(likeName);
            find_syscompany_list(1,10,likeName,likeName1);
            laypage.render({
                elem:"table_page",//绑定页面元素节点的id(只能是id)
                count:total,//数据总数，从服务器中读取中
                //分页的样式
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                jump:function(obj,first){//当前分页执行成功后的回调函数
                    //obj包含了当前分页的所有参数
                    //obj.curr,obj.limit，当前页面所容纳的最大条数
                    if(!first){
                        find_syscompany_list(obj.curr,obj.limit,likeName,likeName1);
                    }
                }
            });
            return false;
        })


        function find_syscompany_list(page,rows,likeName,likeName1){
            $.ajax({
                type:"post",
                url:"/Company/selectCompanyAllByPage",
                async:false,
                data:{
                    "page":page,
                    "maxSize":rows,
                    "likeName":likeName,
                    "likeName1":likeName1

                },
                "success":function(result){
                    if(result.resultCode==0){
                        build_syscompany_list(result);//输出方法
                        total=result.data.total;//输出分页
                    }else{
                        layer.msg("请求失败，联系管理员",{
                            icon:2,
                            time:1000
                        });

                    }
                }
            });
        }

        function build_syscompany_list(result){
            var companyList=result.data.list;
            $(".table_tbody").empty();
            $.each(companyList, function(index,item) {
                //存放id的目的是为了删除，或者修改单个记录的时候找到那条数据
                var tr=$("<tr></tr>");
                var td1=$("<td></td>").addClass("childrenBox").append("<input type='checkbox' company_id='"+item.company_id+"' lay-skin='primary'>");
                var td2=$("<td></td>").append(item.company_name);
                var td3=$("<td></td>").append(item.company_alias);
                var td4=$("<td></td>").append(item.company_address);
                var td5=$("<td></td>").append(item.companyType_id);
                var td6=$("<td></td>").append(item.industry);
                var td7=$("<td></td>").append(item.company_scale);
                var td8=$("<td></td>").append(item.company_balance);
                //状态默认为1是开启状态
                var td9 = "";
                if(item.is_deleted == 1) {
                    td9 = $("<td></td>").append("<input type='checkbox' company_id='" + item.company_id + "' lay-text='ON|OFF' lay-skin='switch' checked>");
                }else{
                    td9 = $("<td></td>").append("<input type='checkbox' company_id='" + item.company_id + "' lay-text='ON|OFF' lay-skin='switch'>");
                }
                var td10=$("<td></td>").append("<button class='layui-btn layui-btn-sm updatecompany' company_id='"+item.company_id+"'>编辑</button>")
                    .append("<button class='layui-btn layui-btn-sm layui-btn-danger companydelete' company_id='"+item.company_id+"'>删除</button>");
                tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9).append(td10).appendTo(".table_tbody")
            });
            form.render();
        }

        //调用获取时获取公司列表的方法
        find_syscompany_list(1,10);

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
                    find_syscompany_list(obj.curr,obj.limit);
                }
            }
        });

        //删除
        $(document).on("click",".companydelete",function(){
            var companyId=$(this).attr("company_id");
            layer.confirm("您确定删除吗?",function(){
                delete_companyId(companyId);
            });
        });

        function delete_companyId(companyId){
            $.ajax({
                url:"/Company/deleteCompany",
                async:false,
                data:{"ids":companyId},

                "success":function(result){
                    if(result.resultCode==0){
                        layer.msg(result.message,{
                            icon:1,
                            time:1000
                        });
                        window.location.reload();
                    }else{
                        layer.msg(result.message,{
                            icon:1,
                            time:1000
                        });
                    }
                }
            });
        }

        //全选
        form.on('checkbox(parentBox)',function(data){
            if(data.elem.checked){
                $(".childrenBox input[type='checkbox']").prop("checked",true);

            }else{
                $(".childrenBox input[type='checkbox']").prop("checked",false);
            }
            form.render();
        });

        //反选
        form.on('checkbox',function(data){
            var length=0;	//checkbox总个数
            var checkLength=0;//被选中的总个数
            $(".childrenBox input[type='checkbox']").each(function(){
                length++;

                if($(this).is(":checked")){
                    checkLength++;
                }
            });
            if(checkLength==length){
                $(".parentBox").prop("checked",true);
            }else{
                $(".parentBox").prop("checked",false);
            }
            form.render();
        });

        //批量删除
        $(".batchDeleteCompany").click(function(){
            var ids="";//要删除的用户id
            var check=0;//记录删除的条数
            $(".childrenBox input[type='checkbox']").each(function(){
                //如果当前的checkbox被选中，则将companyId的属性值存放到ids的变量中
                if($(this).is(":checked")){
                    ids+=$(this).attr("company_id")+",";
                    check++;

                }
            });
            //若选中次数不超过一次，给出错误提示
            if(check>0){
                layer.confirm("你确定要删除"+check+"条记录吗？",function(){
                    //将最后一位的逗号给删除
                    delete_companyId(ids.substring(0,ids.length-1));
                });
            }else{
                layer.msg("不能少于0条记录",{
                    icon:2,
                    time:1000
                })
            }
        });

        //修改公司
        $(document).on("click",".updatecompany",function(){
            var companyId=$(this).attr("company_id");
            //点击修改用户全屏
            var index = layer.open({
                type: 2,
                title: false,
                shadeClose: true,
                shade:0.8,
                content: 'updatecompany.html?companyId='+companyId,
            });
            layer.full(index);
        });
    });
});