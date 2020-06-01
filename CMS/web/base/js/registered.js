
//绑定表单提交事件注册用户
function registered(all) {
    console.log(all);
    $.ajax({
        type:"POST",
        url:"/index/insertUser",
        data:all,
       success: function(result) {
           console.log(result.resultCode);
        if(result.resultCode==0){
            alert(result.message);
            window.location.href="/views/client/login.html";
        }else if(result.resultCode==-1){
               alert(result.message);
           }else if(result.resultCode==-2){
               alert(result.message);
           }

       }

    });

}

$(".registered").click(function () {
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
    var all=$("#myform").serializeArray();
    registered(all);
    return false;
});

/*$(".up").click(function () {
    // 创建表单数据对象
    var obj = new FormData();
    // 获取文件框中的数据
    var file= document.getElementById("file").files[0];
    // 将文件数据添加至表单数据对象中
    obj.append("file", file);
    console.log("请求参数---》》》" + obj);
    upload1(obj);
    return false;
});*/
function  upload1(obj) {
    $.ajax({
        type:"POST",
        url:"/index/upload",
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

