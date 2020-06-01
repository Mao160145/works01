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
            find_sysUser_list(1,10,likeName,likeName1);
            laypage.render({
                elem:"table_page",//绑定页面元素节点的id(只能是id)
                count:total,//数据总数，从服务器中读取中
                //分页的样式
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                jump:function(obj,first){//当前分页执行成功后的回调函数
                    //obj包含了当前分页的所有参数
                    //obj.curr,obj.limit，当前页面所容纳的最大条数
                    if(!first){
                        console.log(11);
                        find_sysUser_list(obj.curr,obj.limit,likeName,likeName1);
                    }
                }
            });
            return false;
        })

        function find_sysUser_list(page,rows,likeName,likeName1){
            $.ajax({
                type:"post",
                url:"/index/selectUserAllByPage",
                async:false,
                data:{
                    "page":page,
                    "maxSize":rows,
                    "likeName":likeName,
                    "likeName1":likeName1

                },
                "success":function(result){
                    if(result.resultCode==0){
                        build_sysUser_list(result);//输出方法
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

        function build_sysUser_list(result){
            var userList=result.data.list;
            $(".table_tbody").empty();
            $.each(userList, function(index,item) {
                //存放id的目的是为了删除，或者修改单个记录的时候找到那条数据
                var tr=$("<tr></tr>");
                var td1=$("<td></td>").addClass("childrenBox").append("<input type='checkbox' user_id='"+item.user_id+"' lay-skin='primary'>");
                var td2=$("<td></td>").append(item.user_name);
                var td3=$("<td></td>").append(item.user_account);
                //判断phone
                var phone="";
                if(item.user_phone!=null){
                    phone=item.user_phone
                }
                var td4=$("<td></td>").append(phone);
                //判断style
                var style=["普通用户","公司负责人"];
                var td5=$("<td></td>").text(style[item.style]);
                //判断user_sex
                var user_sex=["保密","男","女"];
                var td6=$("<td></td>").text(user_sex[item.user_sex]);
                var td7=$("<td></td>").append(item.user_age);
                //判断user_regtime
                var user_regtime="";
                if(item.user_regtime!=null){
                    user_regtime=item.user_regtime
                }
                var td8=$("<td></td>").append(util.toDateString(user_regtime,"yyyy-MM-dd"));
                ///判断user_updetatime
                var user_updetatime="";
                if(item.user_updetatime!=null){
                    user_updetatime=item.user_updetatime
                }
                var td9=$("<td></td>").append(util.toDateString(user_updetatime,"yyyy-MM-dd"));

                var td10=$("<td></td>").append("<button class='layui-btn layui-btn-sm updateUser' user_id='"+item.user_id+"'>编辑</button>")
                    .append("<button class='layui-btn layui-btn-sm layui-btn-danger delete' user_id='"+item.user_id+"'>删除</button>");
                tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9).append(td10).appendTo(".table_tbody")
            });
            form.render();
        }



        //调用获取时获取用户列表的方法
        find_sysUser_list(1,10);

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
                    console.log(11);
                    find_sysUser_list(obj.curr,obj.limit);
                }
            }
        });

        //删除
        $(document).on("click",".delete",function(){
            var userId=$(this).attr("user_id");
            layer.confirm("您确定删除吗?",function(){
                delete_userByUserId(userId);
            });
        });

        function delete_userByUserId(userId){
            $.ajax({
                url:"/index/deleteUsers",
                async:false,
                data:{"ids":userId},

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
        $(".batchDelete").click(function(){
            var ids="";//要删除的用户id
            var check=0;//记录删除的条数
            $(".childrenBox input[type='checkbox']").each(function(){
                //如果当前的checkbox被选中，则将userId的属性值存放到ids的变量中
                if($(this).is(":checked")){
                    ids+=$(this).attr("user_id")+",";
                    check++;
                }
            });
            //若选中次数不超过一次，给出错误提示
            if(check>0){
                layer.confirm("你确定要删除"+check+"条记录吗？",function(){
                    //将最后一位的逗号给删除
                    delete_userByUserId(ids.substring(0,ids.length-1));
                });
            }else{
                layer.msg("不能少于0条记录",{
                    icon:2,
                    time:1000
                })
            }
        });

        //修改用户
        $(document).on("click",".updateUser",function(){
            var userId=$(this).attr("user_id");
            //点击修改用户全屏
            var index = layer.open({
                type: 2,
                title: false,
                shadeClose:true,
                shade:0.8,
                content: 'updateuser.html?userId='+userId,
            });
            layer.full(index);
        });

    });
});	
