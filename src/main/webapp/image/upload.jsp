<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Upload</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>Action</th>
            <th>View</th>
        </tr>
        </thead>
        <c:forEach var="image" items="${images}">
            <tr valign="top">
                    <td><c:out value="${image}"/></td>
                        <c:choose>
                            <c:when test="${param.action == 'select'}">
                                <td><a href="<c:url value='/candidate/edit.jsp?photoId=${image}&id=${param.id}'/>">Выбрать</a></td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="<c:url value='/download?photoId=${image}'/>">Download</a></td>
                            </c:otherwise>
                        </c:choose>
                    <td>
                        <img src="<c:url value='/download?photoId=${image}'/>" width="100px" height="100px"/>
                    </td>
                    <td><a /a>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h3>Upload image</h3>
    <form action="<c:url value='/upload?file'/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="<c:out value='${param.action}'/>"/>
        <input type="hidden" name="id" value="<c:out value='${param.id}'/>"/>
        <div class="checkbox">
            <input type="file" name="file">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

</body>
</html>