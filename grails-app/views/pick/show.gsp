<%@ page import="logh.Pick" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pick.label', default: 'Pick')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pick" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pick" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pick">
				<g:if test="${pickInstance?.team}">
				<li class="fieldcontain">
					<span id="team-label" class="property-label"><g:message code="pick.team.label" default="Team" /></span>
					<span class="property-value" aria-labelledby="team-label"><g:link controller="team" action="show" id="${pickInstance?.team?.id}">${pickInstance?.team?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
				<g:if test="${pickInstance?.week}">
				<li class="fieldcontain">
					<span id="week-label" class="property-label"><g:message code="pick.week.label" default="Week" /></span>
					<span class="property-value" aria-labelledby="week-label">${pickInstance?.week?.encodeAsHTML()}</span>
				</li>
				</g:if>
				<g:if test="${pickInstance?.game}">
				<li class="fieldcontain">
					<span id="game-label" class="property-label"><g:message code="pick.game.label" default="Game" /></span>
					<span class="property-value" aria-labelledby="game-label">${pickInstance?.game?.encodeAsHTML()}</span>
				</li>
				</g:if>
				<g:if test="${pickInstance?.loser}">
				<li class="fieldcontain">
					<span id="loser-label" class="property-label"><g:message code="pick.loser.label" default="Loser" /></span>
					<span class="property-value" aria-labelledby="loser-label">${pickInstance?.loser?.encodeAsHTML()}</span>
				</li>
				</g:if>
				<g:if test="${pickInstance?.correct}">
				<li class="fieldcontain">
					<span id="correct-label" class="property-label"><g:message code="pick.correct.label" default="Correct" /></span>
					<span class="property-value" aria-labelledby="correct-label"><g:formatBoolean boolean="${pickInstance?.correct}" /></span>
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pickInstance?.id}" />
					<g:link class="edit" action="edit" id="${pickInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
