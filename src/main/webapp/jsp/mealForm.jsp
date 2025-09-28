<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="d" uri="https://javaops.ru/fn/dates" %>

<%--@elvariable id="meal" type="ru.javaops.topjava.to.Meal"--%>
<%--@elvariable id="now"  type="java.time.LocalDateTime"--%>

<c:set var="editing" value="${meal != null}"/>
<c:choose>
    <c:when test="${editing}"><c:set var="dateTime" value='${d:format(meal.getDateTime(), "yyyy-MM-dd\'T\'HH:mm")}' /></c:when>
    <c:otherwise><c:set var="dateTime" value='${d:format(now, "yyyy-MM-dd\'T\'HH:mm")}'/></c:otherwise>
</c:choose>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>${editing ? 'Редактирование приёма пищи' : 'Новый приём пищи'}</title>
</head>
<body>
<h2>${editing ? 'Редактирование приёма пищи' : 'Новый приём пищи'}</h2>

<form action="${pageContext.request.contextPath}/meals" method="post">
    <input type="hidden" name="id" value="${editing ? meal.getId() : ''}"/>
    <p>
        <label for="dateTime">Дата и время:</label><br/>
        <input type="datetime-local" id="dateTime" name="dateTime" value="${dateTime}"/>
    </p>
    <p>
        <label for="description">Описание:</label><br/>
        <input type="text" id="description" name="description" value="${editing ? meal.getDescription() : ''}" required/>
    </p>
    <p>
        <label for="calories">Калории:</label><br/>
        <input type="number" id="calories" name="calories" min="1" value="${editing ? meal.getCalories() : ''}" required/>
    </p>
    <p>
        <button type="submit">Сохранить</button>&nbsp;
        <a href="${pageContext.request.contextPath}/meals">Отмена</a>
    </p>
</form>

</body>
</html>
