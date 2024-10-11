<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/manager/category/insert'></c:url>"
		method="post" enctype="multipart/form-data">
		<label for="categoryname">Category name:</label> <input type="text"
			id="categoryname" name="categoryname" value="${cate.categoryname }"><br>
		<label for="images">Images:</label>
		<c:if test="${cate.images.substring(0,5) == 'https' }">
			<c:url value="${cate.images}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${cate.images.substring(0,5) != 'https' }">
			<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" /> <input type="file"
			id="images" name="images"><br>
		<br> <label for="status">Status:</label><br> <label
			for="status">Đang hoạt động</label> <input type="radio" id="ston"
			name="status" value="1" checked><br>
		<br> <label for="status">Khóa</label> <input type="radio"
			id="stoff" name="status" value="0"><br>
		<br> <input type="submit" value="Insert">
	</form>

</body>
</html>