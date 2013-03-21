
<%@ page import="logh.Game" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-game" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list game">
			
				<g:if test="${gameInstance?.home}">
				<li class="fieldcontain">
					<span id="home-label" class="property-label"><g:message code="game.home.label" default="Home" /></span>
					
						<span class="property-value" aria-labelledby="home-label"><g:link controller="squad" action="show" id="${gameInstance?.home?.id}">${gameInstance?.home?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.visitor}">
				<li class="fieldcontain">
					<span id="visitor-label" class="property-label"><g:message code="game.visitor.label" default="Visitor" /></span>
					
						<span class="property-value" aria-labelledby="visitor-label"><g:link controller="squad" action="show" id="${gameInstance?.visitor?.id}">${gameInstance?.visitor?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.loser}">
				<li class="fieldcontain">
					<span id="loser-label" class="property-label"><g:message code="game.loser.label" default="Loser" /></span>
					
						<span class="property-value" aria-labelledby="loser-label"><g:link controller="squad" action="show" id="${gameInstance?.loser?.id}">${gameInstance?.loser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.week}">
				<li class="fieldcontain">
					<span id="week-label" class="property-label"><g:message code="game.week.label" default="Week" /></span>
					
						<span class="property-value" aria-labelledby="week-label"><g:link controller="week" action="show" id="${gameInstance?.week?.id}">${gameInstance?.week?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="game.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${gameInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
            <g:if test="${session?.user?.admin}">
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
            </g:if>
		</div>
	</body>
</html>
