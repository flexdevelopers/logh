
<%@ page import="logh.Pick" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pick.label', default: 'Pick')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pick" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pick" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="pick.team.label" default="Team" /></th>
					
						<th><g:message code="pick.week.label" default="Week" /></th>
					
						<th><g:message code="pick.game.label" default="Game" /></th>
					
						<th><g:message code="pick.loser.label" default="Loser" /></th>
					
						<g:sortableColumn property="correct" title="${message(code: 'pick.correct.label', default: 'Correct')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pickInstanceList}" status="i" var="pickInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pickInstance.id}">${fieldValue(bean: pickInstance, field: "team")}</g:link></td>
					
						<td>${fieldValue(bean: pickInstance, field: "week")}</td>
					
						<td>${fieldValue(bean: pickInstance, field: "game")}</td>
					
						<td>${fieldValue(bean: pickInstance, field: "loser")}</td>
					
						<td><g:formatBoolean boolean="${pickInstance.correct}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pickInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
