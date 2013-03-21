
<%@ page import="logh.Squad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'squad.label', default: 'Squad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-squad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-squad" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'squad.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="abbrev" title="${message(code: 'squad.abbrev.label', default: 'Abbrev')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${squadInstanceList}" status="i" var="squadInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${squadInstance.id}">${fieldValue(bean: squadInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: squadInstance, field: "abbrev")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${squadInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
