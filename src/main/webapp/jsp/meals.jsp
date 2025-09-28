<%--suppress HtmlUnknownTarget --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="d" uri="https://javaops.ru/fn/dates" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Meals</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Meals</h1>
<a href="meals?action=add">Add meal</a>
<br><br>
<table class="meals-table">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <jsp:useBean id="meals" scope="request" type="java.util.List<ru.javaops.topjava.to.MealTo>"/>
    <c:forEach var="meal" items="${meals}">
        <tr>
            <td class="${meal.excess() ? 'red' : 'green'}">${d:format(meal.dateTime(), "dd.MM.yyyy HH:mm")}</td>
            <td class="${meal.excess() ? 'red' : 'green'}">${meal.description()}</td>
            <td class="${meal.excess() ? 'red' : 'green'}">${meal.calories()}</td>
            <td><a href="meals?id=${meal.id()}&action=edit">Редактировать</a> </td>
            <td><a href="meals?id=${meal.id()}&action=delete">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
