<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


</body>
<script src="../js/jquery-1.8.3.min.js"></script>
<script>

    $.ajax({
        type:"post",
        url:"http://localhost:8079/user/getSession.do",
        async:true,
        success:function (data) {
            console.log(data);
        }

    })

</script>
</html>