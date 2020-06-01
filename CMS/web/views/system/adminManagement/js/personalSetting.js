$(function() {
    layui.use(['form', 'layer', 'laydate', 'util'], function () {
        var form = layui.form;
        form.render();
        var layer = layui.layer;
        var laydate = layui.laydate;
        var util = layui.util;

        laydate.render({
            elem: "#admin.birthday",
            type: "date"
        });

        //修改头像

        function  upload1(obj) {
            $.ajax({
                type:"POST",
                url:"/admin/upload",
                data:obj,
                contentType : false,
                processData : false,
                async:false,
                mimeType : 'multipart/form-data',
                success: function(result) {
                    alert("头像上传成功");
                }
            });


        }

        //查找当前管理员信息函数
        function find_currentAdmin_info() {
            $.ajax({
                type: "get",
                url: "/admin/selectAdminAll",
                async: true,
                "success": function (result) {
                    if (result.resultCode == 0) {
                        build_currentAdmin_info(result);
                    }
                }
            });
        }
        //构造当前管理员信息函数
        function build_currentAdmin_info(result) {
            var data = result.data;
            console.log(data);
            $("input[name='admin_id']").val(data[0].admin_id);
            $("input[name='admin_name']").val(data[0].admin_name);
            $("input[name='version']").val(data[0].version);
            $("input[name='admin_account']").val(data[0].admin_account);
            $("input[name='admin_name']").val(data[0].admin_name);
            $("input[name='admin_phone']").val(data[0].admin_phone);
            $("input[name='admin_birthday']").val(util.toDateString(data.admin_birthday, "yyyy-MM-dd"));
            var sex = ["input[title='保密']", "input[title='男']", "input[title='女']"];
            $(sex[data[0].admin_sex]).prop("checked", true);

            form.render();
        }

        form.on("submit(updateAdmin)", function (data) {
            var img=document.getElementById("file").value;
            if(img!=null){
                // 创建表单数据对象
                var obj = new FormData();
                // 获取文件框中的数据
                var file= document.getElementById("file").files[0];
                // 将文件数据添加至表单数据对象中
                obj.append("file", file);
                console.log("请求参数---》》》" + obj);
                upload1(obj);
            }
            var params = data.field;
            // var use = data.field.admin_id;
            // console.log(use);
            update_admin(params);
            return false;
        });

        //修改管理员的ajax
        function update_admin(params) {
            $.ajax({
                type: "post",
                url: "/admin/updateAdmin",
                data: params,
                "success": function (result) {
                    if (result.resultCode == 0) {
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1000
                        });
                        window.location.href="personal.html";
                    } else {
                        layer.msg(result.message, {
                            icon: 2,
                            time: 1000
                        });
                    }
                },
                "error": function () {
                    layer.msg("修改失败，请重试", {
                        icon: 2,
                        time: 1000
                    });
                }
            });
            return false;
        }
        find_currentAdmin_info();
    });
});