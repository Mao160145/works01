$(function() {
    layui.use(['form', 'laydate', 'layer', 'util'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var layer = layui.layer;
        var util = layui.util;
        form.render();
        //重新加载date空间
        var version = null;
        BaseUtil.ajaxSetup();
        var position_id = BaseUtil.GetQueryString("position_id");
        console.log(position_id);
        find_company(position_id);
//修改公司信息（显示）
        function find_company(position_id) {
            $.ajax({
                type: "get",
                url: "/Position/selectPosition?position_id=" + position_id,
                async: true,
                "success": function (result) {
                    if (result.resultCode == 0) {
                       $("#position_id").val(result.data[0].position_id);
                       $("#position_name").val(result.data[0].position_name);
                        $("#position_type").val(result.data[0].position_type);
                        $("#position_people").val(result.data[0].position_people);
                        $("#salary").val(result.data[0].salary);
                        $("#education").val(result.data[0].education);
                        $("#work_address").val(result.data[0].work_address);
                    }
                }
            });
        }
        $(".updatePosition").click(function () {
            //获取form表单的所有值serializeArray()
            var all = $(".cen").serializeArray();
            registered(all);
            return false;
        });

//修改公司信息
        function registered(all) {
            console.log(all);
            $.ajax({
                type: "POST",
                url: "/Position/updatePosition",
                data: all,
                success: function (result) {
                    console.log(result.resultCode);
                    if (result.resultCode == 0) {
                        alert(result.message);
                        window.location.href = "/views/client/companyInformation/companyPersonal.html";
                    } else if (result.resultCode == -1) {
                        alert(result.message);
                    } else if (result.resultCode == -2) {
                        alert(result.message);
                    }
                }
            });
        }
    });
    });