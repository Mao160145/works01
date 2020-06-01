//修改简历信息
$(function() {
   /*转换时间格式*/
    function changeDateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
           /* var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();*/
            return date.getFullYear() + "-" + month + "-" + currentDate;
        }
    }

    /*根据id查询简历信息*/
    function fimd() {
        var user_id=sessionStorage.getItem("userId");
        find_currentResume_info(user_id);
    }
    function find_currentResume_info(user_id) {
        $.ajax({
            type: "get",
            url: "/information/selectInformation?user_id="+user_id,
            async: true,
            "success": function(result) {
                if(result.resultCode == 0) {
                    build_currentResume_info(result);
                }
            }
        });
    }
    //修改简历信息显示
    function build_currentResume_info(result) {
        var data = result.data;
        $("input[name='name']").val(result.data[0].name);
        var sex = ["input[title='保密']", "input[title='男']", "input[title='女']"];
        $(sex[result.data[0].sex]).prop("checked", true);
        $("input[name='education']").val(result.data[0].education);
        $("input[name='date_birth']").val(changeDateFormat(result.data[0].date_birth));
        var marital_status = ["input[title='未婚']", "input[title='已婚']", "input[title='离婚']"];
        $(marital_status[result.data[0].marital_status]).prop("checked", true);
        $("input[name='phone']").val(result.data[0].phone);
        $("input[name='emil']").val(result.data[0].emil);
        $("input[name='expected_career']").val(result.data[0].expected_career);
        $("textarea[name='brief_introduction']").val(result.data[0].brief_introduction);
    }

    $(".updateresume").click(function () {
        $("#userId").val(sessionStorage.getItem("userId"));
        // $("#version").val(sessionStorage.getItem("version"));
        var all=$(".cen").serializeArray();
        updateResume(all);
        return false;
    });
//绑定表单提交事件
    function updateResume(all) {
        $.ajax({
            type:"POST",
            url:"/information/updateInformation",
            data:all,
            success: function(result) {
                console.log(result.resultCode);
                if(result.resultCode==0){
                    alert(result.message);
                    window.location.href="resume.html";
                }else if(result.resultCode==-1){
                    alert(result.message);
                }
            }
        });
    }
    fimd();
});
